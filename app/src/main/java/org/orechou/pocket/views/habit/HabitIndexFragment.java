package org.orechou.pocket.views.habit;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.litepal.LitePal;
import org.orechou.pocket.R;
import org.orechou.pocket.models.adapters.HabitAdapter;
import org.orechou.pocket.models.entity.Habit;
import org.orechou.pocket.models.entity.HabitTag;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HabitIndexFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.btn_new)
    Button mBtnNew;

    private HabitActivity mActivity;

    private List<Habit> mHabitList;

    private Map<Habit, Map<Integer, HabitTag>> mHabitTagMap;

    private HabitAdapter mAdapter;

    public HabitIndexFragment(List<Habit> habitList, Map<Habit, Map<Integer, HabitTag>> habitTagMap) {
        mHabitList = habitList;
        mHabitTagMap = habitTagMap;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_habit_index, container, false);
        ButterKnife.bind(this, view);
        mActivity = (HabitActivity) getActivity();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new HabitAdapter(mHabitList, mHabitTagMap);
        mRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private Habit createNewHabit(String name) {
        Habit habit = Habit.newInstance(name);
        habit.save();
        return habit;
    }

    @OnClick(R.id.btn_new)
    public void onViewClicked() {
        final EditText et = new EditText(getContext());

        new AlertDialog.Builder(getContext()).setTitle("请输入习惯名称")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setView(et)
                .setPositiveButton("确定", (dialog, which) -> {
                    String input = et.getText().toString();
                    if (!input.equals("")) {
                        Habit newHabit = createNewHabit(input);
                        newHabit.save();
                        mHabitList.add(newHabit);
                        mAdapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("取消", null)
                .show();
    }

}
