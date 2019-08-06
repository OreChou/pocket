package org.orechou.pocket.models.entity;

import org.litepal.LitePal;
import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * 习惯打卡
 */
public class HabitTag extends LitePalSupport {

    private Integer id;

    private Habit habit;

    private Date tagTime;

    private String record;

    public HabitTag() {}

    public HabitTag(Integer id, Habit habit, Date tagTime, String record) {
        this.id = id;
        this.habit = habit;
        this.tagTime = tagTime;
        this.record = record;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Habit getHabit() {
        return this.habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
    }

    public Date getTagTime() {
        return tagTime;
    }

    public void setTagTime(Date tagTime) {
        this.tagTime = tagTime;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }
}
