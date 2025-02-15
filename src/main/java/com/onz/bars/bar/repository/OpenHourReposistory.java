package com.onz.bars.bar.repository;

import com.onz.bars.bar.domain.openHour.OpenHour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenHourReposistory extends JpaRepository<OpenHour, Long> {
}
