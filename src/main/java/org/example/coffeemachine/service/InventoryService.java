package org.example.coffeemachine.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.coffeemachine.model.Ingredient;
import org.example.coffeemachine.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final IngredientRepository repository;


    /**
     * @param name is the name of the ingredient
     * @param quantity to refill
     */
    public void refillIngredient(String name, int quantity) {
        var ingredient = repository.findByName(name);
        ingredient.setQuantity(ingredient.getQuantity() + quantity);
        repository.save(ingredient);
        log.info("Ingredient {} refilled by {}", name, quantity);
    }

    /**
    * Deducts the quantity of the ingredient by the specified amount.
    * @param name the name of the ingredient
    * @param amount the amount to deduct
     */
    public void deductIngredientQuantity(String name, int amount) {

        if (repository.existsByName(name)) {
            var ingredient = repository.findByName(name);

            if (ingredient.getQuantity() < amount) {
                log.error("Not enough");
                throw new IllegalArgumentException("Not enough " + name + "!");
            }

            ingredient.setQuantity(ingredient.getQuantity() - amount);
            repository.save(ingredient);

            switch (name) {
                case "milk":
                    log.info("deducted {} ml milk, remaining {} ml milk", amount, ingredient.getQuantity());
                    break;
                case "cups":
                    log.info("deducted {} cup, remaining {} cups", amount, ingredient.getQuantity());
                    break;
                case "coffee beans":
                    log.info("deducted {} g coffee beans, remaining {} g coffee beans", amount, ingredient.getQuantity());
                    break;
                case "water":
                    log.info("deducted {} ml water, remaining {} ml water", amount, ingredient.getQuantity());
                    break;
                default:
                    log.info("deducted {} {}, remaining {} {}", amount, name, ingredient.getQuantity(), name);
                    break;
            }
        } else {
            throw new IllegalArgumentException("Ingredient not found!");
        }
    }


    /**
     * Checks if there is enough quantity of the ingredient.
     * @param name the name of the ingredient
     * @param amount the required amount
     * @return true if there is enough quantity, false otherwise
     */
    public boolean isNotEnoughIngredients(String name, int amount) {
        var ingredient = repository.findByName(name);
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient not found: " + name);
        }
        return ingredient.getQuantity() < amount;
    }


    /**
     * Adds a new ingredient to the inventory.
     * @param name the name of the ingredient
     * @param quantity the quantity of the ingredient
     */
    public void addIngredient(String name, int quantity) {
        if (repository.findByName(name) == null) {
            var ingredient = new Ingredient();
            ingredient.setName(name);
            ingredient.setQuantity(quantity);
            repository.save(ingredient);
        } else {
            throw new IllegalArgumentException("Ingredient already exists!");
        }
    }


    /**
     * Adds the specified amount to the ingredient quantity.
     * @param name the name of the ingredient
     * @param amount the amount to add
     */
    public void addIngredientQuantity(String name, int amount) {
        var ingredient = repository.findByName(name);
        if (ingredient == null) {
            throw new IllegalArgumentException("Ingredient not found!");
        } else {
            ingredient.setQuantity(ingredient.getQuantity() + amount);
            repository.save(ingredient);
            log.info("added {} {}", amount, name);
        }
    }

    public List<Ingredient> getIngredients() {
        return repository.findAll();
    }
}
