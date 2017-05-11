package com.stuart.stuartapp.activity;

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
import com.stuart.stuartapp.demo.activity.AddContacts;
import com.stuart.stuartapp.demo.activity.SampleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by stuart on 2016/12/12.
 */
@ContentView(R.layout.activiy_demo)
public class DemoActivity extends BaseActivity {

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
        list.add(R.string.add_contact);
        list.add(R.string.demo_test_recycler);
        list.add(R.string.demo_test_sample);
        list.add(R.string.weather);
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
                            intent = new Intent(DemoActivity.this, AddContacts.class);
                            break;
                        case R.string.demo_test_recycler:
                            intent = new Intent("testRecycler");
                            break;
                        case R.string.demo_test_sample:
                            intent = new Intent(DemoActivity.this, SampleActivity.class);
                        case R.string.weather:
                            intent = new Intent("com.stuart.weather");
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
