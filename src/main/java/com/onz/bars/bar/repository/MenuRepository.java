package com.onz.bars.bar.repository;

import com.onz.bars.bar.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
