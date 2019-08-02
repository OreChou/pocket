package org.orechou.pocket.views.clipboard;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.orechou.pocket.R;
import org.orechou.pocket.models.adapters.ClipItemAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClipTextFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private ClipItemAdapter mAdapter;

    private int index;

    private ClipboardActivity mActivity;

    public ClipTextFragment() {
        // Required empty public constructor
    }

    public static ClipTextFragment newInstance(int index) {
        ClipTextFragment fragment = new ClipTextFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        fragment.index = index;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clip_text, container, false);
        ButterKnife.bind(this, view);
        mActivity = (ClipboardActivity) getActivity();
        switch (index) {
            case 0:
                mAdapter = new ClipItemAdapter(getContext(), mActivity.getClipTextList());
                break;
            case 1:
                mAdapter = new ClipItemAdapter(getContext(), mActivity.getClipLinkList());
                break;
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

}
