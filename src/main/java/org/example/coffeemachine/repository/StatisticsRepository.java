package org.example.coffeemachine.repository;

import org.example.coffeemachine.model.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Statistics findByCoffeeName(String coffeeName);
}
