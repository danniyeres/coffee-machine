package org.example.coffeemachine.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffeemachine.model.Statistics;
import org.example.coffeemachine.service.CoffeeMachineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coffee")
public class CoffeeMachineController {
    private final CoffeeMachineService coffeeMachineService;

    @GetMapping
    public String hello() {
        return """
        Welcome to coffee machine!
        Choose your coffee and enjoy!
        
        Available coffee types:
        - espresso
        - americano
        - cappuccino
        """;
    }

    @PostMapping("/prepare/{coffeeType}")
    public String prepareCoffee(@PathVariable String coffeeType) {
        return coffeeMachineService.prepareCoffee(coffeeType);
    }

    @GetMapping("/statistics")
    public List<Statistics> getStatistics() {
        return coffeeMachineService.getStatistics();
    }
}
