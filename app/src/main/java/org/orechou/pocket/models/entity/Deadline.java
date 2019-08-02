package org.orechou.pocket.models.entity;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Deadline extends LitePalSupport {

    private Integer id;

    private String name;

    private Date date;

    public Deadline() {}

    public Deadline(Integer id, String name, Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Deadline(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
