package com.udemy.model;

import lombok.Data;

@Data
public class Coder {

    private double height;
    private double weight;
    private int age;
    private Gender gender;

    public Coder(double height, double weight) {
        super();
        this.height = height;
        this.weight = weight;
    }

    public Coder(double height, double weight, int age, Gender gender) {
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.gender = gender;
    }
}
