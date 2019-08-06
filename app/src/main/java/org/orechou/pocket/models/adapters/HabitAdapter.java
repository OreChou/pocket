package org.orechou.pocket.models.adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;
import org.orechou.pocket.R;
import org.orechou.pocket.models.entity.Habit;
import org.orechou.pocket.models.entity.HabitTag;
import org.orechou.pocket.utils.DateUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HabitAdapter extends RecyclerView.Adapter<HabitAdapter.ViewHolder> {

    private Context mContext;

    private List<Habit> mHabitList;

    Map<Habit, Map<Integer, HabitTag>> mHabitTagMap;

    private int nowWeekIndex;

    public HabitAdapter(List<Habit> habitList, Map<Habit, Map<Integer, HabitTag>> habitTagMap) {
        this.mHabitList = habitList;
        this.mHabitTagMap = habitTagMap;
        this.nowWeekIndex = DateUtils.getWeekIndex(DateUtils.getNow());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_habit, parent, false);
//        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Habit habit = mHabitList.get(position);
        holder.textViewName.setText(habit.getName());

        for (int i = 1; i <= 7; i++) {
            if (mHabitTagMap.get(habit).get(i) != null) {
                switch (i) {
                    case 1:
                        holder.checkboxMonday.setChecked(true);
                        break;
                    case 2:
                        holder.checkboxTuesday.setChecked(true);
                        break;
                    case 3:
                        holder.checkboxWednesday.setChecked(true);
                        break;
                    case 4:
                        holder.checkboxThursday.setChecked(true);
                        break;
                    case 5:
                        holder.checkboxFriday.setChecked(true);
                        break;
                    case 6:
                        holder.checkboxSaturday.setChecked(true);
                        break;
                    case 7:
                        holder.checkboxSunday.setChecked(true);
                        break;
                }
            } else {
                switch (i) {
                    case 1:
                        holder.checkboxMonday.setChecked(false);
                        break;
                    case 2:
                        holder.checkboxTuesday.setChecked(false);
                        break;
                    case 3:
                        holder.checkboxWednesday.setChecked(false);
                        break;
                    case 4:
                        holder.checkboxThursday.setChecked(false);
                        break;
                    case 5:
                        holder.checkboxFriday.setChecked(false);
                        break;
                    case 6:
                        holder.checkboxSaturday.setChecked(false);
                        break;
                    case 7:
                        holder.checkboxSunday.setChecked(false);
                        break;
                }
            }
            if (mHabitTagMap.get(habit).get(this.nowWeekIndex) != null) {
                holder.checkboxConfirm.setChecked(true);
            }
        }

        View.OnClickListener listener = (view -> {

            switch (view.getId()) {
                case R.id.checkbox_confirm:
                    // 打卡
                    if (holder.checkboxConfirm.isChecked()) {
                        HabitTag tag = new HabitTag();
                        tag.setHabit(habit);
                        tag.setTagTime(DateUtils.getNow());
                        tag.save();
                        mHabitTagMap.get(habit).put(this.nowWeekIndex, tag);
                        holder.checkboxConfirm.setChecked(true);
                    }
                    else {
                        if (mHabitTagMap.get(habit).get(this.nowWeekIndex) != null) {
                            LitePal.delete(HabitTag.class, mHabitTagMap.get(habit).get(this.nowWeekIndex).getId());
                            mHabitTagMap.get(habit).put(this.nowWeekIndex, null);
                        }
                        holder.checkboxConfirm.setChecked(false);
                    }
                    this.notifyDataSetChanged();
                    break;
                default:

                    CheckBox cb = (CheckBox) view;
                    if (cb.isChecked()) {
                        cb.setChecked(false);
                    } else {
                        cb.setChecked(true);
                    }

                    break;
            }

        });

        holder.checkboxConfirm.setOnClickListener(listener);
        holder.checkboxMonday.setOnClickListener(listener);
        holder.checkboxTuesday.setOnClickListener(listener);
        holder.checkboxWednesday.setOnClickListener(listener);
        holder.checkboxThursday.setOnClickListener(listener);
        holder.checkboxFriday.setOnClickListener(listener);
        holder.checkboxSaturday.setOnClickListener(listener);
        holder.checkboxSunday.setOnClickListener(listener);

    }

    @Override
    public int getItemCount() {
        return mHabitList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.checkbox_confirm)
        CheckBox checkboxConfirm;
        @BindView(R.id.text_view_name)
        TextView textViewName;
        @BindView(R.id.checkbox_monday)
        CheckBox checkboxMonday;
        @BindView(R.id.checkbox_tuesday)
        CheckBox checkboxTuesday;
        @BindView(R.id.checkbox_wednesday)
        CheckBox checkboxWednesday;
        @BindView(R.id.checkbox_thursday)
        CheckBox checkboxThursday;
        @BindView(R.id.checkbox_friday)
        CheckBox checkboxFriday;
        @BindView(R.id.checkbox_saturday)
        CheckBox checkboxSaturday;
        @BindView(R.id.checkbox_sunday)
        CheckBox checkboxSunday;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, this.itemView);
        }
    }

}
