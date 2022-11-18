package com.vych.HomeKeeperRest.Repo.MonthData;

import com.vych.HomeKeeperRest.Domain.MonthData.MonthData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MonthDataRepo extends JpaRepository<MonthData, Long> {
    Optional<MonthData> findByMonthAndYear(int month, int year);

    @Query(
            value = "select * from monthdata where year = (select year from monthdata order by year desc limit 1) order by month desc limit 1",
            nativeQuery = true
    )
    Optional<MonthData> findLastMonth();
}
