package org.orechou.pocket.models.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import org.orechou.pocket.R;
import org.orechou.pocket.models.entity.Deadline;
import org.orechou.pocket.utils.DateUtils;
import org.orechou.pocket.views.deadline.DeadlineActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeadlineAdapter extends RecyclerView.Adapter<DeadlineAdapter.ViewHolder> {

    private Context mContext;

    private List<Deadline> mDeadlineList;

    private DeadlineActivity mActivity;

    private Drawable redCircle, greenCircle, blueCircle;

    public DeadlineAdapter(List<Deadline> deadlineList, DeadlineActivity activity) {
        mDeadlineList = deadlineList;
        mActivity = activity;

        redCircle = mActivity.getResources().getDrawable(R.drawable.circle_red);
        greenCircle = mActivity.getResources().getDrawable(R.drawable.circle_green);
        blueCircle = mActivity.getResources().getDrawable(R.drawable.circle_blue);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_deadline, parent, false);
        final ViewHolder holder = new ViewHolder(view);

        holder.itemView.setOnClickListener((View v) -> mActivity.actionEditFragment(mDeadlineList.get(holder.getAdapterPosition())));

        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Deadline deadline = mDeadlineList.get(position);
        holder.textViewName.setText(deadline.getName());
        holder.textViewDate.setText(DateUtils.formatDate(deadline.getDate()));

        int days = DateUtils.getDaysDifferenceFromNow(deadline.getDate());
        if (days == 0) {
            holder.imageView.setImageDrawable(greenCircle);
            holder.textViewNumber.setText(String.valueOf(days));
            holder.textViewNumber.setTextColor(mActivity.getResources().getColor(R.color.colorGreen, null));
            holder.textViewDes.setText("就是今天");
        }
        else if (days < 0) {
            holder.imageView.setImageDrawable(redCircle);
            holder.textViewNumber.setText(String.valueOf(0 - days));
            holder.textViewNumber.setTextColor(mActivity.getResources().getColor(R.color.colorRed, null));
            holder.textViewDes.setText("已过天数");
        }
        else {
            holder.imageView.setImageDrawable(blueCircle);
            holder.textViewNumber.setText(String.valueOf(days));
            holder.textViewNumber.setTextColor(mActivity.getResources().getColor(R.color.colorBlue, null));
            holder.textViewDes.setText("剩余天数");
        }

    }

    @Override
    public int getItemCount() {
        return mDeadlineList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.text_view_name)
        TextView textViewName;
        @BindView(R.id.text_view_date)
        TextView textViewDate;
        @BindView(R.id.text_view_number)
        TextView textViewNumber;
        @BindView(R.id.text_view_des)
        TextView textViewDes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, this.itemView);
        }
    }

}
