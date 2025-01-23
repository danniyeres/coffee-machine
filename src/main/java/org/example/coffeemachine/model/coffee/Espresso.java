package org.example.coffeemachine.model.coffee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeemachine.service.InventoryService;

import java.util.Map;

@RequiredArgsConstructor
@Slf4j
public class Espresso implements Coffee{
    private final InventoryService inventoryService;

    @Override
    public String getName() {
        return "Espresso";
    }

    @Override
    public String prepare() {
        var recipe = Map.of("water", 30, "coffee beans", 8, "cups", 1);
        for (String ingredient : recipe.keySet()) {
            int amount = recipe.get(ingredient);
            if (inventoryService.isNotEnoughIngredients(ingredient, amount)) {
                log.error("Not enough {}", ingredient);
                return "Not enough " + ingredient;
            }
        }

        inventoryService.deductIngredientQuantity("water", 30);
        inventoryService.deductIngredientQuantity("coffee beans", 8);
        inventoryService.deductIngredientQuantity("cups", 1);

        return "Espresso preparation completed";

    }
}
