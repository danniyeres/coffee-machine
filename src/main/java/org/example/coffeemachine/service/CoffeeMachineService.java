package org.example.coffeemachine.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeemachine.model.Statistics;
import org.example.coffeemachine.model.coffee.CoffeeFactory;
import org.example.coffeemachine.repository.StatisticsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoffeeMachineService {
    private final MachineService machine;
    private final CoffeeFactory coffeeFactory;
    private final StatisticsRepository statistics;


    /**
     * @param type coffee type
     * @return coffee preparation status
     * This method prepares coffee based on the type
     * It also updates the statistics for the coffee preparation
     * If the machine is not active, it returns "Machine is not active"
     */
    public String prepareCoffee(String type) {
        if (machine.isMachineActive()) {
            var coffee = coffeeFactory.createCoffee(type);
            log.info("coffee preparation completed {}", coffee.getName());

            var coffeeStatistics = statistics.findByCoffeeName(coffee.getName());
            if (coffeeStatistics == null) {
                coffeeStatistics = new Statistics();
                coffeeStatistics.setCoffeeName(coffee.getName());
                coffeeStatistics.setPreparationCount(1);
                statistics.save(coffeeStatistics);
            } else {
                coffeeStatistics.setPreparationCount(coffeeStatistics.getPreparationCount() + 1);
                statistics.save(coffeeStatistics);
            }

            return coffee.prepare();
        } else {
            return "Machine is not active on weekends or holidays";
        }
    }

    public List<Statistics> getStatistics() {
        return statistics.findAll();
    }
}
