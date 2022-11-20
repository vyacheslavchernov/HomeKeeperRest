package com.vych.HomeKeeperRest.Repo.Aspects;

import com.vych.HomeKeeperRest.Domain.Aspects.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogsRepo extends JpaRepository<LogEntity, Long> {
}
