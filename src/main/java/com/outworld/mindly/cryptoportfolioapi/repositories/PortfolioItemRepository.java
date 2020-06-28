package com.outworld.mindly.cryptoportfolioapi.repositories;

import com.outworld.mindly.cryptoportfolioapi.models.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
}
