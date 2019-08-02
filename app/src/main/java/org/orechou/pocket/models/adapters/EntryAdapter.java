package org.orechou.pocket.models.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.orechou.pocket.R;
import org.orechou.pocket.models.entity.Entry;
import org.orechou.pocket.views.DeadlineActivity;
import org.orechou.pocket.views.clipboard.ClipboardActivity;
import org.orechou.pocket.views.code.CodeActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {

    private Context mContext;

    private List<Entry> mEntryList;

    public EntryAdapter(List<Entry> entryList) {
        this.mEntryList = entryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.entry_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener((View v) -> {
            Entry entry = mEntryList.get(holder.getAdapterPosition());
            switch (entry.getName()) {
                case "倒数日":
                    DeadlineActivity.startActivity(mContext);
                    break;
                case "剪贴板":
                    ClipboardActivity.startActivity(mContext);
                    break;
                case "二维码生成":
                    CodeActivity.startActivity(mContext);
                    break;
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Entry entry = mEntryList.get(position);
        holder.textView.setText(entry.getName());
        holder.imageView.setImageDrawable(mContext.getResources().getDrawable(entry.getDrawable()));
    }

    @Override
    public int getItemCount() {
        return mEntryList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.entry_image) ImageView imageView;
        @BindView(R.id.entry_name) TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
