package org.orechou.pocket.views.deadline;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.litepal.LitePal;
import org.orechou.pocket.R;
import org.orechou.pocket.models.entity.Deadline;
import org.orechou.pocket.views.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeadlineActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    DeadlineIndexFragment mIndexFragment;

    DeadlineEditFragment mEditFragment;

    private List<Deadline> mDeadlineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deadline);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 初始化数据
        initData();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        mIndexFragment = new DeadlineIndexFragment();
        transaction.add(R.id.content, mIndexFragment);
        transaction.commit();
    }

    /**
     * Data Operation Begins
     */
    private void initData() {
        mDeadlineList = LitePal.findAll(Deadline.class);
    }

    public void addDeadline(Deadline deadline) {
        mDeadlineList.add(deadline);
        // 存储数据库
        deadline.save();
    }

    public void deleteDeadline(Deadline deadline) {
        mDeadlineList.remove(deadline);
        LitePal.delete(Deadline.class, deadline.getId());
    }

    public void updateDeadline(Deadline deadline) {

        for (Deadline item : mDeadlineList) {
            if (item.getId() == deadline.getId()) {
                item.setName(deadline.getName());
                item.setDate(deadline.getDate());
            }
        }

        deadline.update(deadline.getId());
    }

    public List<Deadline> getDeadlineList() {
        return mDeadlineList;
    }

    /**
     * Data Operation ends
     */

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, DeadlineActivity.class);
        context.startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * 新增记录的方式打开
     */
    public void actionEditFragment() {
        if (mEditFragment == null) {
            mEditFragment = new DeadlineEditFragment();
        }
        mEditFragment.setNewType();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, mEditFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * 修改记录的方式打开
     * @param deadline
     */
    public void actionEditFragment(Deadline deadline) {
        //
        if (mEditFragment == null) {
            mEditFragment = new DeadlineEditFragment();
        }
        mEditFragment.setUpdateType(deadline);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, mEditFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
