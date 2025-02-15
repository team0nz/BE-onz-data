package com.onz.bars.bar.repository;

import com.onz.bars.bar.domain.Bar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepository extends JpaRepository<Bar, Long> {
}
