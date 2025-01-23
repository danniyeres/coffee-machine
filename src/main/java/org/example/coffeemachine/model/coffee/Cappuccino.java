package org.example.coffeemachine.model.coffee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeemachine.service.InventoryService;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
public class Cappuccino implements Coffee {
    private final CoffeeFactory coffeeFactory;
    private final InventoryService inventoryService;

    @Override
    public String getName() {
        return "Cappuccino";
    }

    @Override
    public String prepare() {
        var espresso = coffeeFactory.createCoffee("espresso");
        espresso.prepare();

        var recipe = Map.of("milk", 120, "cups", 1);
        for (String ingredient : recipe.keySet()) {
            int amount = recipe.get(ingredient);
            if (inventoryService.isNotEnoughIngredients(ingredient, amount))
                return "Not enough " + ingredient;
        }

        inventoryService.deductIngredientQuantity("milk", 120);
        inventoryService.deductIngredientQuantity("cups", 1);

        return "Cappuccino preparation completed";
    }
}