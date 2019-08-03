package org.orechou.pocket.views.deadline;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.orechou.pocket.R;
import org.orechou.pocket.models.adapters.DeadlineAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeadlineIndexFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.btn_add)
    Button mBtnAdd;

    private Unbinder mUnBinder;

    private DeadlineAdapter mAdapter;

    private DeadlineActivity mActivity;

    public DeadlineIndexFragment() {
        // ? 这里 getActivity 返回为空
        // mActivity = (DeadlineActivity) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deadline_index, container, false);
        mUnBinder = ButterKnife.bind(this, view);
        mActivity = (DeadlineActivity) getActivity();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new DeadlineAdapter(mActivity.getDeadlineList(), mActivity);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnBinder.unbind();
    }

    @OnClick(R.id.btn_add)
    public void btnAddClick() {
        mActivity.actionEditFragment();
    }

}
