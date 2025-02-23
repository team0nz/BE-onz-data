package com.onz.bars.bar.repository;

import com.onz.bars.bar.domain.region.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, Long> {

    Region findFirstBySubdistrictName(String subdistrictName);
}
