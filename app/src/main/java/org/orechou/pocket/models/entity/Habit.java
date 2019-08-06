package org.orechou.pocket.models.entity;

import org.litepal.crud.LitePalSupport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Habit extends LitePalSupport {

    private Integer id;

    private String name;

    private Date notifyTime;

    /**
     * 用于计算连续天数的第一天
     */
    private Date firstContinuousDate;

    private Integer totalDays;

    private List<HabitTag> habitTags = new ArrayList<>();

    public Habit() {}

    public Habit(Integer id, String name, Date notifyTime, Date firstContinuousDate, Integer totalDays) {
        this.id = id;
        this.name = name;
        this.notifyTime = notifyTime;
        this.firstContinuousDate = firstContinuousDate;
        this.totalDays = totalDays;
    }

    public static Habit newInstance(String name) {
        Habit habit = new Habit();
        habit.setName(name);
        return habit;
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

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Date getFirstContinuousDate() {
        return firstContinuousDate;
    }

    public void setFirstContinuousDate(Date firstContinuousDate) {
        this.firstContinuousDate = firstContinuousDate;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public List<HabitTag> getHabitTags() {
        return habitTags;
    }

    public void setHabitTags(List<HabitTag> habitTags) {
        this.habitTags = habitTags;
    }
}
