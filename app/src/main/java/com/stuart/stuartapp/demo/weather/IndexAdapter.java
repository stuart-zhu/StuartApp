package com.stuart.stuartapp.demo.weather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.stuart.stuartapp.R;
import com.stuart.stuartapp.demo.weather.entity.IndexInfo;

import java.util.List;

/**
 * Created by stuart on 2017/5/10.
 */

public class IndexAdapter extends BaseAdapter{


    private LayoutInflater mInflater;
    private List<IndexInfo> list;

    public IndexAdapter(Context context, List<IndexInfo> list) {
        mInflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public IndexInfo getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_index_weather, null);
            vh = new ViewHolder(convertView);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        IndexInfo item = getItem(position);
        vh.tvName.setText(item.getiName());
        vh.tvValue.setText(item.getIvalue());
        vh.tvDetail.setText(item.getDetail());
        return convertView;
    }
    private class ViewHolder {
        private TextView tvName;
        private TextView tvValue;
        private TextView tvDetail;

        public ViewHolder(View v) {
            tvName = (TextView) v.findViewById(R.id.index_name);
            tvValue = (TextView) v.findViewById(R.id.index_value);
            tvDetail = (TextView) v.findViewById(R.id.detail);
            v.setTag(this);
        }
    }
}
