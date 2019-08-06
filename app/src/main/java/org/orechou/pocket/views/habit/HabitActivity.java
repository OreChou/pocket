package org.orechou.pocket.views.habit;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import org.litepal.LitePal;
import org.orechou.pocket.R;
import org.orechou.pocket.models.entity.Habit;
import org.orechou.pocket.models.entity.HabitTag;
import org.orechou.pocket.utils.DateUtils;
import org.orechou.pocket.views.BaseActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HabitActivity extends BaseActivity {

    private static final String TAG = "HabitActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private HabitIndexFragment mIndexFragment;

    private List<Habit> mHabitList;

    private Map<Habit, Map<Integer, HabitTag>> mHabitTagMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initData();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mIndexFragment = new HabitIndexFragment(mHabitList, mHabitTagMap);
        transaction.add(R.id.content, mIndexFragment);
        transaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.N)
    private void initData() {
        // 所有的 Habit
        mHabitList = LitePal.findAll(Habit.class);
        // 获取本周的所以打卡记录
        List<HabitTag> habitTagList = LitePal.where("tagtime >= ? and tagtime <= ?", DateUtils.getFirstTimeOfCurrentWeek().getTime() + "", DateUtils.getLastTimeOfCurrentWeek().getTime() + "").order("tagtime").find(HabitTag.class, true);
        Log.d(TAG, "habitTagList.size(): " + habitTagList.size());

        mHabitTagMap = new HashMap<>();
        mHabitList.forEach((habit) -> {

            Map<Integer, HabitTag> tagMap = new HashMap<>();
            mHabitTagMap.put(habit, tagMap);
            habitTagList.forEach((tag) -> {

                if (habit.getId().equals(tag.getHabit().getId())) {
                    tagMap.put(DateUtils.getWeekIndex(tag.getTagTime()), tag);
                }

            });

        });

    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, HabitActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
