package org.orechou.pocket.views.habit;

import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.orechou.pocket.R;
import org.orechou.pocket.views.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HabitActivity extends BaseActivity {

    private static final String TAG = "HabitActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
