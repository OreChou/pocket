package org.orechou.pocket.views;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import org.orechou.pocket.R;
import org.orechou.pocket.models.entity.Deadline;
import org.orechou.pocket.utils.DateUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeadlineEditFragment extends Fragment {

    public static final Integer EDIT_TYPE_NEW = 0;

    public static final Integer EDIT_TYPE_UPDATE = 1;

    @BindView(R.id.btn_save)
    Button mBtnSave;

    @BindView(R.id.btn_delete)
    Button mBtnDelete;

    @BindView(R.id.edit_text_name)
    EditText mEditTextName;

    @BindView(R.id.text_view_date)
    TextView mTextViewDate;

    private View mContentView;

    private Unbinder mUnBinder;

    private Integer mEditFragmentType;

    private Date mChooseDate;

    private DeadlineActivity mActivity;

    private Deadline mUpdateDeadline;

    public DeadlineEditFragment() {
        mEditFragmentType = DeadlineEditFragment.EDIT_TYPE_NEW;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContentView = inflater.inflate(R.layout.fragment_deadline_edit, container, false);
        mUnBinder = ButterKnife.bind(this, mContentView);
        this.mActivity = (DeadlineActivity) getActivity();
        return mContentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mEditFragmentType == DeadlineEditFragment.EDIT_TYPE_NEW) {
            showNewTypeUI();
        } else if (mEditFragmentType == DeadlineEditFragment.EDIT_TYPE_UPDATE) {
            showUpdateTypeUI();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    public void setNewType() {
        this.mEditFragmentType = DeadlineEditFragment.EDIT_TYPE_NEW;
    }

    public void setUpdateType(Deadline deadline) {
        this.mUpdateDeadline = deadline;
        this.mEditFragmentType = DeadlineEditFragment.EDIT_TYPE_UPDATE;
    }


    private void showNewTypeUI() {
        mBtnDelete.setVisibility(View.GONE);
        mEditTextName.setText(null);
        mTextViewDate.setText(null);
    }

    private void showUpdateTypeUI() {
        mBtnDelete.setVisibility(View.VISIBLE);
        mEditTextName.setText(mUpdateDeadline.getName());
        mTextViewDate.setText(DateUtils.formatDate(mUpdateDeadline.getDate()));
        mChooseDate = mUpdateDeadline.getDate();
    }

    @OnClick({R.id.btn_save, R.id.btn_delete, R.id.text_view_date})
    public void uiElementClick(View view) {

        switch (view.getId()) {
            case R.id.text_view_date:
                chooseDate();
                break;
            case R.id.btn_save:
                // New
                if (mEditFragmentType == DeadlineEditFragment.EDIT_TYPE_NEW) {
                    if (mChooseDate == null) {
                        Snackbar.make(mContentView, "未选择日期", Snackbar.LENGTH_SHORT).show();
                        break;
                    }
                    if (mEditTextName.getText().length() == 0) {
                        Snackbar.make(mContentView, "未输入项目名称", Snackbar.LENGTH_SHORT).show();
                        break;
                    }
                    Deadline deadline = new Deadline(mEditTextName.getText().toString(), mChooseDate);

                    mActivity.addDeadline(deadline);
                    mActivity.onBackPressed();

                }
                // update
                else if (mEditFragmentType == DeadlineEditFragment.EDIT_TYPE_UPDATE) {
                    if (mChooseDate == null) {
                        Snackbar.make(mContentView, "未选择日期", Snackbar.LENGTH_SHORT).show();
                        break;
                    }
                    if (mEditTextName.getText().length() == 0) {
                        Snackbar.make(mContentView, "未输入项目名称", Snackbar.LENGTH_SHORT).show();
                        break;
                    }
                    mUpdateDeadline.setName(mEditTextName.getText().toString());
                    mUpdateDeadline.setDate(mChooseDate);
                    mActivity.updateDeadline(mUpdateDeadline);
                    mActivity.onBackPressed();
                }
                break;
            case R.id.btn_delete:
                mActivity.deleteDeadline(mUpdateDeadline);
                mActivity.onBackPressed();
                break;
        }

    }

    @TargetApi(Build.VERSION_CODES.N)
    private void chooseDate() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog dialog = new DatePickerDialog(
                this.getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    mTextViewDate.setText(DateUtils.formatDate(calendar.getTime()));
                    mChooseDate = calendar.getTime();
                    },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }

}
