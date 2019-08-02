package org.orechou.pocket.models.entity;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class ClipItem extends LitePalSupport {

    private Integer id;

    private String content;

    private Date date;

    private Integer Type;

    public ClipItem() {}

    public ClipItem(String content) {
        this.content = content;
    }

    public ClipItem(Integer id, String content, Date date, Integer type) {
        this.id = id;
        this.content = content;
        this.date = date;
        Type = type;
    }

    static public ClipItem newTextType(String content) {
        ClipItem clipItem = new ClipItem(content);
        clipItem.date = new Date();
        clipItem.Type = 0;
        return clipItem;
    }

    static public ClipItem newLinkType(String content) {
        ClipItem clipItem = new ClipItem(content);
        clipItem.date = new Date();
        clipItem.Type = 1;
        return clipItem;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

}
