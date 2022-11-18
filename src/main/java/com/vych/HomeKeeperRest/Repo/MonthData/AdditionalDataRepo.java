package com.vych.HomeKeeperRest.Repo.MonthData;

import com.vych.HomeKeeperRest.Domain.MonthData.AdditionalData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdditionalDataRepo extends JpaRepository<AdditionalData, Long> {
}
