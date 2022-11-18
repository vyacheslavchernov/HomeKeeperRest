package com.vych.HomeKeeperRest.Repo.MonthData;

import com.vych.HomeKeeperRest.Domain.MonthData.TariffsData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffsDataRepo extends JpaRepository<TariffsData, Long> {
}
