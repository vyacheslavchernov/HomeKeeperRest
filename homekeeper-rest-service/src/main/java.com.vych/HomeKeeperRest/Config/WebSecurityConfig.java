package HomeKeeperRest.Config;

import HomeKeeperRest.Domain.Users.UserData;
import HomeKeeperRest.Repo.Users.UserDataRepo;
import jakarta.servlet.DispatcherType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDataRepo userDataRepo;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Настройка аутентификации
     */
    @Bean
    public UserDetailsManager usersDetailsService(DataSource dataSource, BCryptPasswordEncoder bCryptPasswordEncoder) {
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        String adminName = System.getenv("adminName");

        try {
            users.loadUserByUsername(adminName);
        } catch (UsernameNotFoundException e) {
            String adminPsw = System.getenv("adminPassword");
            UserDetails admin = User.builder()
                    .username(adminName)
                    .password(bCryptPasswordEncoder.encode(adminPsw))
                    .roles("USER", "ADMIN")
                    .build();

            UserData userData = new UserData()
                    .setUsername(adminName)
                    .setFirstName(adminName)
                    .setLastName(adminName);

            userDataRepo.save(userData);
            users.createUser(admin);
        }

        return users;
    }

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(
                        (authorize) -> authorize
                                .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                                .requestMatchers("/", "/login", /*"/signup",*/ "/logout", "/error", "/perform-login?continue").permitAll()
                                //.requestMatchers(HttpMethod.POST, "/proceed-signup").permitAll()
                                .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers("api/admin", "api/admin/**", "/admin", "/admin/**").hasAuthority("ROLE_ADMIN")
                                .requestMatchers("/api", "/api/**").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                )
                .httpBasic(Customizer.withDefaults())
                .formLogin().defaultSuccessUrl("/login?success=true");
        return http.build();
    }

    /**
     * Настройка cors для корректной работы приложения
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}