package org.orechou.pocket.models.entity;

import org.litepal.crud.LitePalSupport;

/**
 * 功能卡片
 */
public class Entry extends LitePalSupport {

    private Integer id;

    private String name;

    private String icon;

    private Integer drawable;

    private String description;

    public Entry() {}

    public Entry(String name, Integer drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    public Entry(String name, String icon) {
        this.name = name;
        this.icon = icon;
    }

    public Entry(String name, String icon, String description) {
        this.name = name;
        this.icon = icon;
        this.description = description;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDrawable() {
        return drawable;
    }

    public void setDrawable(Integer drawable) {
        this.drawable = drawable;
    }
}
