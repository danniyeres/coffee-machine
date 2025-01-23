package org.example.coffeemachine.model.coffee;

import lombok.RequiredArgsConstructor;
import org.example.coffeemachine.service.InventoryService;

import java.util.Map;

@RequiredArgsConstructor
public class Americano implements Coffee{
    private final CoffeeFactory coffeeFactory;
    private final InventoryService inventoryService;

    @Override
    public String getName() {
        return "Americano";
    }

    @Override
    public String prepare() {
        var expresso = coffeeFactory.createCoffee("espresso");
        expresso.prepare();

        var recipe = Map.of("water",90,"cups",1);
        for (String ingredient : recipe.keySet()) {
            int amount = recipe.get(ingredient);
            if (inventoryService.isNotEnoughIngredients(ingredient, amount))
                return "Not enough " + ingredient;
        }

        inventoryService.deductIngredientQuantity("water", 90);
        inventoryService.deductIngredientQuantity("cups", 1);

        return "Americano preparation completed";
    }
}