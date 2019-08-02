package org.orechou.pocket.views.clipboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.litepal.LitePal;
import org.orechou.pocket.R;
import org.orechou.pocket.models.entity.ClipItem;
import org.orechou.pocket.utils.StringUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClipboardActivity extends AppCompatActivity {

    private static final String TAG = "ClipboardActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tabs)
    TabLayout mTabs;

    @BindView(R.id.btn_save)
    Button mBtnSave;

    List<ClipItem> mClipTextList, mClipLinkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboard);
        ButterKnife.bind(this);

        initData();

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        mViewPager.setAdapter(pagerAdapter);
        mTabs.setupWithViewPager(mViewPager);

    }

    private void initData() {
        mClipTextList = LitePal.where("type = ?", "0").find(ClipItem.class);
        mClipLinkList = LitePal.where("type = ?", "1").find(ClipItem.class);
    }

    public List<ClipItem> getClipTextList() {
        return mClipTextList;
    }

    public List<ClipItem> getClipLinkList() {
        return mClipLinkList;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, ClipboardActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.btn_save)
    public void saveClipboardContent(View view) {
        String content;

        if ((content = getClipboardContent()) == null) {
            Snackbar.make(view.getRootView(), "未复制内容", Snackbar.LENGTH_SHORT).show();
            return;
        }

        ClipItem clipItem;
        if (!StringUtils.isUrlLink(content)) {
            mTabs.getTabAt(0).select();
            clipItem = ClipItem.newTextType(content);
            clipItem.save();
            mClipTextList.add(clipItem);
        } else {
            mTabs.getTabAt(1).select();
            clipItem = ClipItem.newLinkType(content);
            clipItem.save();
            mClipLinkList.add(clipItem);
        }

    }

    private String getClipboardContent() {
        ClipboardManager manager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

        if (manager != null) {
            if (manager.hasPrimaryClip() && manager.getPrimaryClip().getItemCount() > 0) {
                CharSequence text = manager.getPrimaryClip().getItemAt(0).getText();
                if (text != null) {
                    String content = String.valueOf(text);
                    return content;
                }
            }
        }

        return null;
    }

}
