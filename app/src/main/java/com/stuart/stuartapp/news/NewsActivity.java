package com.stuart.stuartapp.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.utils.LogUtil;

import java.util.List;
import java.util.concurrent.RunnableFuture;

/**
 * Created by stuart on 2017/8/22.
 */

public class NewsActivity extends BaseActivity{

    private static final String TAG = "NewsActivity";
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private ListView mlv;
    private NewAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        setTitle(R.string.news);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl);
        mlv = (ListView) findViewById(R.id.lv);
        mAdapter = new NewAdapter(this);
        mlv.setAdapter(mAdapter);

        mlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               News news = (News) parent.getAdapter().getItem(position);
                Intent intent = new Intent("com.stuart.news.info");
                intent.putExtra("url", news.getUrl());
                startActivity(intent);
            }
        });
        initSwipe();

        loadNews();
    }

    private void initSwipe() {
        // 不能在onCreate中设置，这个表示当前是刷新状态，如果一进来就是刷新状态，SwipeRefreshLayout会屏蔽掉下拉事件
        //swipeRefreshLayout.setRefreshing(true);

        // 设置颜色属性的时候一定要注意是引用了资源文件还是直接设置16进制的颜色，因为都是int值容易搞混
        // 设置下拉进度的背景颜色，默认就是白色的
        mSwipeRefreshLayout.setProgressBackgroundColorSchemeResource(android.R.color.white);
        // 设置下拉进度的主题颜色
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);

        // 下拉时触发SwipeRefreshLayout的下拉动画，动画完毕之后就会回调这个方法
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadNews();

            }
        });


    }

    private void loadNews() {
        new Thread(){
            @Override
            public void run() {
                NewsUtils.loadNews(new NewsUtils.onLoadData() {
                    @Override
                    public void onLoadSuccess(final List<News> news) {
                        runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {
                                              for (int i = news.size() -1; i>= 0; i --) {
                                                  mAdapter.addNew(news.get(i));
                                              }
                                              mSwipeRefreshLayout.setRefreshing(false);
                                          }
                                      }
                        );
                    }

                    @Override
                    public void onLoadFaile(String msg) {

                    }
                });
            }
        }.start();

    }
}
