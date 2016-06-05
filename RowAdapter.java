package com.tcs.problem_first.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcs.problem_first.MyCallBackInterface;
import com.tcs.problem_first.R;
import com.tcs.problem_first.Utils.utils;


/**
 * Created by 983798 on 5/30/2016.
 */
public class RowAdapter extends RecyclerView.Adapter<RowAdapter.ViewHolder> {

    public static final int ADD_NEWS=0;
    public static final int VIEW_NEWS=1;
    public static final int LOG_OUT=2;
    private static MyCallBackInterface mContext;

    public RowAdapter(MyCallBackInterface context) {
        this.mContext=context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mImage;
        private TextView  mText;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mImage = (ImageView) itemView.findViewById(R.id.iv_image);
            mText = (TextView) itemView.findViewById(R.id.tv_text);
        }

        @Override
        public void onClick(View v) {

            switch (getLayoutPosition()){
                case 0:
                    mContext.onCommunicate(0);
                    break;
                case 1:
                    mContext.onCommunicate(1);
                    break;
                case 2:
                    mContext.onCommunicate(2);
                    break;
            }

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_layout, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.mText.setText(utils.rowList.get(i).getRowText());
        viewHolder.mImage.setImageResource(utils.rowList.get(i).getRowImage());
    }

    @Override
    public int getItemCount() {
        return utils.rowList.size();
    }


}
