package org.example.coffeemachine.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffeemachine.model.Ingredient;
import org.example.coffeemachine.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ingredient")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/add/{ingredient}/{quantity}")
    public String addIngredientQuantity(@PathVariable String ingredient,@PathVariable int quantity) {
        inventoryService.addIngredientQuantity(ingredient, quantity);
        return switch (ingredient) {
            case "water", "milk" -> quantity + " ml " + ingredient + " added";
            case "coffee beans" -> quantity + " g " + ingredient + " added";
            default -> quantity + " " + ingredient + " added";
        };
    }

    @GetMapping
    public List<Ingredient> getIngredients() {
        return inventoryService.getIngredients();
    }
}
