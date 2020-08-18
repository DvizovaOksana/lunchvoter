package ru.lunchvoter.to;

import org.springframework.data.domain.Persistable;
import ru.lunchvoter.HasId;

public abstract class BaseTo implements HasId {
    private Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
