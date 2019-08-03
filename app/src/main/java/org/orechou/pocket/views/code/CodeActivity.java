package org.orechou.pocket.views.code;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;

import org.orechou.pocket.R;
import org.orechou.pocket.utils.QRCodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 二维码生成
 */
public class CodeActivity extends AppCompatActivity {

    private final static String TAG = "CodeActivity";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.edit_text_code)
    EditText mEditTextCode;

    @BindView(R.id.image_view_code)
    ImageView mImageViewCode;

    private Bitmap mQRCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditTextCode.setOnEditorActionListener((view, action, event) -> {
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                String content = view.getText().toString();
                Log.i(TAG, view.getText().toString());
                mQRCode = QRCodeUtils.createQRCodeBitmap(content, 1080, 1080);
                mImageViewCode.setImageBitmap(mQRCode);
            }
            return false;
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        mQRCode = null;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, CodeActivity.class);
        context.startActivity(intent);
    }
}
