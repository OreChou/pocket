package org.orechou.pocket.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.orechou.pocket.R;
import org.orechou.pocket.models.entity.ClipItem;
import org.orechou.pocket.utils.DateUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ClipItemAdapter extends RecyclerView.Adapter<ClipItemAdapter.ViewHolder> {

    private List<ClipItem> mClipItemList;

    private Context mContext;

    public ClipItemAdapter(Context context, List<ClipItem> clipItems) {
        mContext = context;
        mClipItemList = clipItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_clip_text, parent, false);
        ButterKnife.bind(this, view);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClipItem clipItem = mClipItemList.get(position);
        holder.textViewContent.setText(clipItem.getContent());
        holder.textViewDate.setText(DateUtils.formatDate(clipItem.getDate()));
    }

    @Override
    public int getItemCount() {
        return mClipItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_content)
        TextView textViewContent;
        @BindView(R.id.text_view_date)
        TextView textViewDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, this.itemView);
        }
    }
}
