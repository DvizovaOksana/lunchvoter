package ru.lunchvoter.to;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MealTo extends BaseTo {
    @NotBlank
    private String name;

    @NotNull
    @Range(min = 1, max = 999999)
    private Integer price;

    public MealTo() {}

    public MealTo(Integer id, String name, Integer price) {
        super(id);
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "DishTo{" +
                "id=" + getId() +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
