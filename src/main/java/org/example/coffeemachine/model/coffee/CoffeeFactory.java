package org.example.coffeemachine.model.coffee;

import lombok.RequiredArgsConstructor;
import org.example.coffeemachine.service.InventoryService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CoffeeFactory {
    private final InventoryService inventoryService;

    public Coffee createCoffee(String type) {
        return switch (type.toLowerCase()) {
            case "espresso" -> new Espresso(inventoryService);
            case "americano" -> new Americano(this,inventoryService);
            case "cappuccino" -> new Cappuccino(this,inventoryService);
            default -> throw new IllegalArgumentException("Unknown coffee type: " + type);
        };
    }
}