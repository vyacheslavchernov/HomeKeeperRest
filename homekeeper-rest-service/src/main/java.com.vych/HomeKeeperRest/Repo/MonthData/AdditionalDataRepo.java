package HomeKeeperRest.Repo.MonthData;

import HomeKeeperRest.Domain.MonthData.AdditionalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalDataRepo extends JpaRepository<AdditionalData, Long> {
}
