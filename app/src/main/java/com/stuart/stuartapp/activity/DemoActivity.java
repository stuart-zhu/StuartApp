package com.stuart.stuartapp.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stuart.stuartapp.R;
import com.stuart.stuartapp.entity.ItemDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2016/12/12.
 */

public class DemoActivity extends BaseDemoFragment {

  private static final String TAG = "DemoActivity";

  private RecyclerView mListView;


  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    super.onCreateView(inflater, container, savedInstanceState);
    View view = inflater.inflate(R.layout.activiy_demo, null);
    mListView = (RecyclerView) view.findViewById(R.id.lv);

    //mAdapter = new DemoAdapter(getContext(), prepareData());
    mListView.setAdapter(new MyAdapter());
    mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mListView.addItemDecoration(new Divider(getContext()));


    return view;
  }




  protected List<ItemDemo> prepareData() {
    List<ItemDemo> list = new ArrayList<>();
    list.add(new ItemDemo(R.string.add_contact, 1, "com.stuart.addContacts"));
    list.add(new ItemDemo(R.string.demo_test_recycler, 1, "testRecycler"));
    list.add(new ItemDemo(R.string.demo_test_sample, 1, "com.stuart.sampleAct"));
    list.add(new ItemDemo(R.string.weather, 1, "com.stuart.weather"));
    list.add(new ItemDemo(R.string.only_wifi, 1, "com.stuart.only_wifi"));
    list.add(new ItemDemo(R.string.demo_test_file, 1, "com.demo.test_file"));
    list.add(new ItemDemo(R.string.demo_test_logcat, 1, "com.stuart.logcat"));
    list.add(new ItemDemo(R.string.news, 1, "com.stuart.news"));
    list.add(new ItemDemo(R.string.jia_kao_ti_ku, 1, "com.stuart.jktk"));
    list.add(new ItemDemo(R.string.test_taskAffinity, 1, "com.stuart.test_taskAffinity"));


    return list;
  }




}
