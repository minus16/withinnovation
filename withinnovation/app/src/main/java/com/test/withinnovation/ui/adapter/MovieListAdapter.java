package com.test.withinnovation.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.withinnovation.R;
import com.test.withinnovation.ui.item.MovieListItem;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private ArrayList<MovieListItem> mList;
    private Context mContext;

    private ListClickListener mClickListener;

    public MovieListAdapter(ArrayList<MovieListItem> list, ListClickListener clickListener) {
        this.mList = list;
        this.mClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_feed, viewGroup, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int pos) {
        holder.mTxt.setText(mList.get(pos).getTitle());
        Glide.with(mContext).load(mList.get(pos).getURL()).into(holder.mImg);
        //GlideApp.with(mContext).load(mList.get(position).getProfile().getImagePath()).placeholder(ContextCompat.getDrawable(mContext, R.drawable.icon_user_03)).apply(new RequestOptions().centerCrop().circleCrop()).into(feedholder.officalImg);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.gotoLargePage(pos, mList.get(pos).getURL());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImg;
        public TextView mTxt;
        public LinearLayout linearLayout;


        public ViewHolder(View view) {
            super(view);
            mImg = (ImageView) view.findViewById(R.id.content_img);
            mTxt = (TextView) view.findViewById(R.id.content_txt);
            linearLayout = (LinearLayout) view.findViewById(R.id.container_layout);
        }
    }

    public interface ListClickListener {
        void gotoLargePage(int id, String url);
    }
}
