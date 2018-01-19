package com.stuart.stuartapp.activity;

import android.app.Activity;
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
public class Demo1Activity extends Fragment {

    private ListView mListView;

    private DemoAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.activiy_demo, null);
        mListView = (ListView) inflate.findViewById(R.id.lv);
        prepareData();

        mAdapter = new DemoAdapter(getContext(), prepareData());
        mListView.setAdapter(mAdapter);
        return inflate;
    }



    private List<Integer> prepareData() {
        List<Integer> list = new ArrayList<>();

        list.add(R.string.hao_mao);
        list.add(R.string.baidu_map);
        list.add(R.string.emojirain);

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
                        case R.string.baidu_map:
                            intent = new Intent("com.stuart.baidumap.main");
                            break;
                        case R.string.emojirain:
                            intent = new Intent("com.stuart.emojirain");
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
