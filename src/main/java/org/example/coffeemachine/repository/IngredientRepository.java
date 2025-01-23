package org.example.coffeemachine.repository;

import org.example.coffeemachine.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    Ingredient findByName(String name);
    boolean existsByName(String name);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient i SET i.quantity = i.quantity - :quantity WHERE i.name = :name")
    void deductIngredientQuantity(String name, int quantity);

    @Modifying
    @Transactional
    @Query("UPDATE Ingredient i SET i.quantity = i.quantity + :quantity WHERE i.name = :name")
    void addIngredientQuantity(String name, int quantity);
}
