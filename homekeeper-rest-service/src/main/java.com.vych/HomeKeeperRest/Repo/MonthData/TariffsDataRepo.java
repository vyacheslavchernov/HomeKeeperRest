package HomeKeeperRest.Repo.MonthData;

import HomeKeeperRest.Domain.MonthData.TariffsData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffsDataRepo extends JpaRepository<TariffsData, Long> {
}
