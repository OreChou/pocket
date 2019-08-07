package org.orechou.pocket.views;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.orechou.pocket.R;
import org.orechou.pocket.models.adapters.EntryAdapter;
import org.orechou.pocket.models.entity.Entry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    /**
     * Views
     */
    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    /**
     * Data
     */
    private List<Entry> mEntryList;
    private EntryAdapter mEntryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        initEntry();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        mRecyclerView.setLayoutManager(layoutManager);
        mEntryAdapter = new EntryAdapter(mEntryList);
        mRecyclerView.setAdapter(mEntryAdapter);
    }

    private void initEntry() {
        mEntryList = new ArrayList<>();

        Entry entry1 = new Entry("办事列表", R.drawable.ic_entry_todo_list);
        Entry entry2 = new Entry("每日习惯", R.drawable.ic_entry_routine);
        Entry entry3 = new Entry("倒数日", R.drawable.ic_entry_deadline);
        Entry entry4 = new Entry("剪贴板", R.drawable.ic_entry_clipboard);
        Entry entry5 = new Entry("二维码生成", R.drawable.ic_entry_code);
        Entry entry6 = new Entry("文字识别", R.drawable.ic_entry_text_recognition);
        Entry entry7 = new Entry("快捷相机", R.drawable.ic_entry_camera);

        mEntryList.add(entry1);
        mEntryList.add(entry2);
        mEntryList.add(entry3);
        mEntryList.add(entry4);
        mEntryList.add(entry5);
        mEntryList.add(entry6);
        mEntryList.add(entry7);
    }

}
