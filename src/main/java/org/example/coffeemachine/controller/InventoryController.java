package org.example.coffeemachine.controller;

import lombok.RequiredArgsConstructor;
import org.example.coffeemachine.service.InventoryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
