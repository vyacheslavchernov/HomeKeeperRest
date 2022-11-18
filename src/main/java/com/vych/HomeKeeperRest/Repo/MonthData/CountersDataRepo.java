package com.vych.HomeKeeperRest.Repo.MonthData;

import com.vych.HomeKeeperRest.Domain.MonthData.CountersData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountersDataRepo extends JpaRepository<CountersData, Long> {
}
