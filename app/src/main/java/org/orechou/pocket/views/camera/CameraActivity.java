package org.orechou.pocket.views.camera;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.orechou.pocket.R;
import org.orechou.pocket.views.BaseActivity;

import butterknife.ButterKnife;

public class CameraActivity extends BaseActivity {

    private CameraFragment mCameraFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mCameraFragment = new CameraFragment();
        transaction.add(R.id.content, mCameraFragment);
        transaction.commit();
    }


    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CameraActivity.class);
        context.startActivity(intent);
    }

}
