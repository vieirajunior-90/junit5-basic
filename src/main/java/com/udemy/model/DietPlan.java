package com.udemy.model;

import lombok.Data;

@Data
public class DietPlan {

    private int calories;
    private int protein;
    private int fat;
    private int carbohydrate;

    public DietPlan(int calories, int protein, int fat, int carbohydrate) {
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbohydrate = carbohydrate;
    }
}
