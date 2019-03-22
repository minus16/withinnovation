package com.test.withinnovation.ui.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.test.withinnovation.ui.item.MovieListItem;
import com.test.withinnovation.view.base.BaseMVP.AbstractPresenter;
import com.test.withinnovation.view.base.BaseMVP.ClassContract;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class GetListPresenter extends AbstractPresenter<ClassContract.View> implements ClassContract.Presenter {

    private ClassContract.View mView;

    private static String URL = "https://www.thewrap.com/marvel-movies-ranked-worst-best-avengers-infinity-war-ant-man-venom-stan-lee-spider-man-into-the-spider-verse/";

    private Document mDoc = null;

    private ArrayList<MovieListItem> mList = new ArrayList<>();


    public GetListPresenter(Context context, @NotNull ClassContract.View view) {
        super(view);
        this.mView = view;
    }

    @Override
    public void connect() {

    }

    @Override
    public void start() {
        //view.updateUI();

        new Thread(){
            public void run(){
                //Document doc = null;
                Log.d("minus", "thread");
                try {
                    mDoc = Jsoup.connect(URL).get();
                }catch (Exception e){

                }
                //parseDataFromDoc();
                handler.sendMessage(handler.obtainMessage());
            }
        }.start();
    }

    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            parseDataFromDoc();
        }
    };


    public void parseDataFromDoc(){
        Log.d("minus", "parseDataFromDoc");
        //Elements elements = mDoc.select("a.imagelink");
        Elements elements = mDoc.select("div.item-wrap");
        //Elements elements2 = elements.get(0).select("div.caption");
        //elements2.text();


        int l = 100;
        for(int i = elements.size()-1; i >= 0; i--){
            //Log.d("minus", String.valueOf(elements.get(i).getElementById("caption")));
            Elements caption = elements.get(i).select("div.caption");
            String str_caption = caption.text();
            if(str_caption.equals("")){
                continue;
            }
            Elements src = elements.get(i).select("img");
            String str_src = src.attr("src");
            //String temp = src.attr("data-src");

            if(src.attr("data-src").equals("") == false){
                str_src = src.attr("data-src");
            }

            Log.d("minus", str_caption);
            Log.d("minus", str_src);
            mList.add(new MovieListItem(str_caption, str_src));
        }

        mView.updateUI(mList);
    }
}
