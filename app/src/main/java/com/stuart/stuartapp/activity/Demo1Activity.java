package com.stuart.stuartapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stuart.stuartapp.R;
import com.stuart.stuartapp.entity.ItemDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2017/12/12.
 */
public class Demo1Activity extends BaseDemoFragment {

    private RecyclerView mListView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View inflate = inflater.inflate(R.layout.activiy_demo, null);
        mListView = (RecyclerView) inflate.findViewById(R.id.lv);


        mListView.setAdapter(new MyAdapter());
        mListView.addItemDecoration(new Divider(getContext()));
        mListView.setLayoutManager(new GridLayoutManager(getContext(), 2));
//        mListView.setAdapter(mAdapter);
        return inflate;
    }


    @Override
    protected List<ItemDemo> prepareData() {
        List<ItemDemo> list = new ArrayList<>();

        list.add(new ItemDemo(R.string.hao_mao, 1, "com.stuart.hao_ma"));
        list.add(new ItemDemo(R.string.baidu_map, 1, "com.stuart.baidumap.main"));
        list.add(new ItemDemo(R.string.emojirain, 1, "com.stuart.emojirain"));
        list.add(new ItemDemo(R.string.yue_che, 1, "com.stuart.yueche"));
        list.add(new ItemDemo(R.string.ss_erwm, 1, "com.stuart.ss_erwmz"));
        return list;
    }
}
