package com.test.withinnovation.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.withinnovation.R;
import com.test.withinnovation.databinding.InflateMainlistBinding;
import com.test.withinnovation.ui.activity.ImageDetailActivity;
import com.test.withinnovation.ui.adapter.MovieListAdapter;
import com.test.withinnovation.ui.item.MovieListItem;
import com.test.withinnovation.ui.presenter.GetListPresenter;
import com.test.withinnovation.view.base.BaseMVP.ClassContract;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MainFragment  extends Fragment implements ClassContract.View {

    private InflateMainlistBinding mBinding;
    private GetListPresenter mPresenter;
    private ArrayList<MovieListItem> mList  = new ArrayList();
    private MovieListAdapter mAdapter;
    private Context mContext;


    //Dynamically load
    private int previousTotal = 0;
    private int lastVisibleItem = 0;
    private int totalItemCount = 0;
    private int visibleThreshold = 5;
    private boolean isLoading = false;
    //Feed dynamical loading
    public final int LOADING_LIMIT = 10;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.inflate_mainlist, container, false, null);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity();

        //init List
        intiList();

        //presenter
        mPresenter = new GetListPresenter(getActivity(), this);
        mPresenter.start();
    }

    @Override
    public void updateUI(Object obj) {
        Log.d("minus", "here");
        //mList = (ArrayList<MovieListItem>)obj;
        mBinding.loadingBar.setVisibility(View.GONE);
        mBinding.movieList.setVisibility(View.VISIBLE);


        mList.addAll((ArrayList<MovieListItem>)obj);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(@NotNull ClassContract.Presenter presenter) {

    }

    @Override
    public void showProgress(boolean b) {

    }

    @Override
    public void showError(int a, @NotNull String str) {

    }

    private void intiList(){
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.movieList.setLayoutManager(layoutManager);
        mBinding.movieList.setItemAnimator(null);

        mBinding.movieList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                    isLoading = true;
                    if(totalItemCount > previousTotal){
                        //load more
                        previousTotal = totalItemCount;
                        //getFeedList(mList.get(mList.size()-1).getPageId());

                    }
                    else{
                        //do not load
                        isLoading = false;
                    }

                }
//                if(isLoading==true && totalItemCount-1 == lastVisibleItem){
//                    showBottomLoading(true);
//                }
            }
        });

        mAdapter = new MovieListAdapter(mList, ClickListener);
        mBinding.movieList.setAdapter(mAdapter);


    }

    MovieListAdapter.ListClickListener ClickListener = new MovieListAdapter.ListClickListener() {
        @Override
        public void gotoLargePage(int id, String url) {
            Intent intent = new Intent(mContext, ImageDetailActivity.class);
            intent.putExtra("data", url);
            mContext.startActivity(intent);
        }
    };
}
