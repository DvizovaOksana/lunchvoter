package ru.lunchvoter.model;

import org.hibernate.annotations.BatchSize;
import ru.lunchvoter.model.AbstractNamedEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"}, name = "restaurant_idx")})
public class Restaurant extends AbstractNamedEntity {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")//, cascade = CascadeType.REMOVE, orphanRemoval = true)
    @OrderBy("name ASC")
    @BatchSize(size = 300)
    private List<Meal> meals;

    public Restaurant() {
    }

    public Restaurant(Restaurant r) {
        super(r.getId(), r.getName());
    }

    public Restaurant(Integer id, String name) {
        super(id, name);
    }

    public List<Meal> getMeals() {
        return meals;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
