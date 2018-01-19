package com.stuart.stuartapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.stuart.stuartapp.R;
import com.stuart.stuartapp.demo.activity.AddContacts;
import com.stuart.stuartapp.demo.activity.SampleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2016/12/12.
 */

public class DemoActivity extends Fragment {


    private ListView mListView;

    private DemoAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activiy_demo, null);
        mListView = (ListView) view.findViewById(R.id.lv);
        prepareData();

        mAdapter = new DemoAdapter(getContext(), prepareData());
        mListView.setAdapter(mAdapter);

        return view;
    }


    private List<Integer> prepareData() {
        List<Integer> list = new ArrayList<>();
        list.add(R.string.add_contact);
        list.add(R.string.demo_test_recycler);
        list.add(R.string.demo_test_sample);
        list.add(R.string.weather);
        list.add(R.string.only_wifi);
        list.add(R.string.demo_test_file);
        list.add(R.string.demo_test_logcat);
        list.add(R.string.news);
        list.add(R.string.jia_kao_ti_ku);

        return list;
    }


    private class DemoAdapter extends BaseAdapter {

        private List<Integer> mResIds;

        private LayoutInflater mInflator;

        public DemoAdapter(Context context, List<Integer> list) {
            mInflator = LayoutInflater.from(context);
            mResIds = list;
        }

        @Override
        public int getCount() {
            return mResIds == null ? 0 : mResIds.size();
        }

        @Override
        public Integer getItem(int position) {
            return mResIds.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = mInflator.inflate(R.layout.item_button_layout, null);
            }
            Button bt = (Button) convertView;

            bt.setText(getItem(position));

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = null;
                    switch (getItem(position)) {
                        case R.string.add_contact:
                            intent = new Intent(getContext(), AddContacts.class);
                            break;
                        case R.string.demo_test_recycler:
                            intent = new Intent("testRecycler");
                            break;
                        case R.string.demo_test_sample:
                            intent = new Intent(getContext(), SampleActivity.class);
                            break;
                        case R.string.weather:
                            intent = new Intent("com.stuart.weather");
                            break;
                        case R.string.only_wifi:
                            intent = new Intent("com.stuart.only_wifi");
                            break;
                        case R.string.demo_test_file:
                            intent = new Intent("com.demo.test_file");
                            break;
                        case R.string.demo_test_logcat:
                            intent = new Intent("com.stuart.logcat");
                            break;
                        case R.string.news:
                            intent = new Intent("com.stuart.news");
                            break;
                        case R.string.san_guo_q:
                            intent = new Intent("com.stuart.sanguoq");
                            break;
                        case R.string.jia_kao_ti_ku:
                            intent = new Intent("com.stuart.jktk");
                            break;
                        default:
                            break;

                    }
                    if (intent != null) {
                        startActivity(intent);
                    }
                }
            });
            return convertView;
        }
    }
}
