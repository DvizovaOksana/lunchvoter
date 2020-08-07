package ru.lunchvoter.to;

import org.springframework.data.domain.Persistable;

public abstract class BaseTo implements Persistable<Integer> {
    private Integer id;

    public BaseTo() {
    }

    public BaseTo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }
}
