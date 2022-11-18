package com.vych.HomeKeeperRest;

import com.vych.HomeKeeperRest.Domain.MonthData.AdditionalData;
import com.vych.HomeKeeperRest.Domain.MonthData.CountersData;
import com.vych.HomeKeeperRest.Domain.MonthData.MonthData;
import com.vych.HomeKeeperRest.Domain.MonthData.TariffsData;
import com.vych.HomeKeeperRest.Domain.Users.Role;
import com.vych.HomeKeeperRest.Domain.Users.User;
import com.vych.HomeKeeperRest.Repo.MonthData.MonthDataRepo;
import com.vych.HomeKeeperRest.Repo.Users.RoleRepo;
import com.vych.HomeKeeperRest.Repo.Users.UserRepo;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@Disabled
@SpringBootTest
class HomeKeeperRestApplicationTests {

	private final MonthDataRepo MONTH_DATA_REPO;
	private final UserRepo USER_REPO;
	private final RoleRepo ROLE_REPO;

	@Autowired
	public HomeKeeperRestApplicationTests(MonthDataRepo monthDataRepo, UserRepo userRepo, RoleRepo roleRepo) {
		this.MONTH_DATA_REPO = monthDataRepo;
		this.USER_REPO = userRepo;
		this.ROLE_REPO = roleRepo;
	}

	@Test
	void createNewUser() {
		USER_REPO.save(
				new User().setEmail("mail")
						.setEnabled(true)
						.setFirstName("user")
						.setLastName("user")
						.setPassword("0000")
						.setUsername("vy")
						.setRole(new Role().setAuthority("ROLE_ADMIN").setName("vy"))
		);
	}

	@Test
	void addMonthData() {
		ArrayList<AdditionalData> additionalData = new ArrayList<>();
		additionalData.add(new AdditionalData().setAmount(100).setDescription("Ethernet"));
		additionalData.add(new AdditionalData().setAmount(-1000).setDescription("Some stuff"));

		MONTH_DATA_REPO.save(
				new MonthData()
						.setMonth(10)
						.setHousemates(1)
						.setRent(19000)
						.setYear(2022)
						.setCountersData(new CountersData().setColdwater(10).setHotwater(10).setElectricity(10))
						.setTariffsData(new TariffsData().setColdwater(1).setHotwater(1).setDrainage(1).setElectricity(1))
						.setAdditionalData(additionalData)
		);
	}

}
