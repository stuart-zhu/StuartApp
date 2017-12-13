package com.stuart.stuartapp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2017/12/12.
 */
@ContentView(R.layout.activiy_demo)
public class Demo1Activity extends BaseActivity {


    @ViewInject(R.id.lv)
    private ListView mListView;

    private DemoAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);

        prepareData();

        mAdapter = new DemoAdapter(this, prepareData());
        mListView.setAdapter(mAdapter);


    }

    private List<Integer> prepareData() {
        List<Integer> list = new ArrayList<>();

        list.add(R.string.hao_mao);

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
                        case R.string.hao_mao:
                            intent = new Intent("com.stuart.hao_ma");

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