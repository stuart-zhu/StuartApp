package com.stuart.stuartapp.weather;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.weather.db.WeatherDbUtil;
import com.stuart.stuartapp.weather.entity.CityInfo;
import com.stuart.stuartapp.widget.ClearEditText;

import java.util.List;


/**
 * Created by stuart on 2017/5/10.
 */

public class SelectCity extends BaseActivity{

    private ClearEditText mSearch;

    private ListView lv;

    private TextView tvEmpty;

    private String searchText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        setTitle(R.string.choose_city);
        mSearch = (ClearEditText) findViewById(R.id.filter_edit);
        lv = (ListView) findViewById(R.id.lv);
        tvEmpty = (TextView) findViewById(R.id.empty);
        lv.setEmptyView(tvEmpty);
        lv.setOnItemClickListener(onItemClickListener);

        loadSearch();
        mSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchText = s.toString();
                loadSearch();
            }
        });
    }

    private void loadSearch() {

        new AsyncTask<Void, Void, List<CityInfo>>() {
            @Override
            protected List<CityInfo> doInBackground(Void... params) {
                return  WeatherDbUtil.getCityInfo(getApplicationContext(), searchText);
            }

            @Override
            protected void onPostExecute(List<CityInfo> cityInfos) {
                CityAdapter adapter = new CityAdapter(SelectCity.this, cityInfos);
                lv.setAdapter(adapter);

            }
        }.execute();
    }

    private class CityAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        private List<CityInfo> infos;
        public CityAdapter(Context context, List<CityInfo> infos) {

            mInflater = LayoutInflater.from(context);
            this.infos = infos;
        }

        @Override
        public int getCount() {
            return infos == null ? 0 : infos.size();
        }

        @Override
        public CityInfo getItem(int position) {
            return infos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return infos.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHodler vh;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.item_city_info, null);
                vh = new ViewHodler(convertView);

            } else {
                vh = (ViewHodler) convertView.getTag();
            }
            CityInfo city = getItem(position);
            vh.tvCity.setText(city.getCity());
           // if (city.getCityInfoP() != null)
            vh.tvCityP.setText(city.getCityP());

                vh.tvCityPp.setText(city.getCityPP());
            return convertView;
        }

        private class ViewHodler{
            private TextView tvCity;
            private TextView tvCityP;
            private TextView tvCityPp;

            ViewHodler(View v) {
                tvCity = (TextView) v.findViewById(R.id.city);
                tvCityP = (TextView) v.findViewById(R.id.cityp);
                tvCityPp = (TextView) v.findViewById(R.id.citypp);
                v.setTag(this);
            }
        }
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Object item = parent.getAdapter().getItem(position);
            if (item instanceof  CityInfo) {
                Intent intent= new Intent();
                intent.putExtra("city", ((CityInfo) item).getCity());
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    };
}
