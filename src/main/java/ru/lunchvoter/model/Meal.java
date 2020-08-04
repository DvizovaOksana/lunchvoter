package ru.lunchvoter.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ru.lunchvoter.model.AbstractNamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "date", "restaurant_id"}, name = "dishes_unique_rest_date_name_idx")})
public class Meal extends AbstractNamedEntity {
    @Column(name = "date", nullable = false, columnDefinition = "date default today()")
    @NotNull
    private LocalDate date = LocalDate.now();

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 0)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    public Meal(){

    }

    public Meal(Meal meal){
        this(meal.getId(), meal.getDate(), meal.getName(), meal.getPrice());
    }

    public Meal(LocalDate date, String name, int price) {
        this(null, date, name, price);
    }

    public Meal(Integer id, LocalDate date, String name, int price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
