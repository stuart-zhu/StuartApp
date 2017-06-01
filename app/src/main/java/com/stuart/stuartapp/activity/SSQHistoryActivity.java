package com.stuart.stuartapp.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.dao.SsqDao;
import com.stuart.stuartapp.entity.SSQ;
import com.stuart.stuartapp.utils.LogUtil;
import com.stuart.stuartapp.utils.MyDecoration;
import com.stuart.stuartapp.utils.ViewUtil;
import com.stuart.stuartapp.widget.TwoColorBall;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by stuart on 2016/11/14.
 */
public class SSQHistoryActivity extends BaseActivity {

    private static final String TAG = "SSQHistoryActivity";

    private SSQHistoryAdapter mAdapter;

    private View mRecommend;

    private TextView tvRecommendTitle, tvRecommend;

    private RecyclerView lv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssq_hisotry);
        lv = (RecyclerView) findViewById(R.id.lv);

        List<SSQ> query = SsqDao.getInstance().query(this, null, null, null, null);

        mAdapter = new SSQHistoryAdapter(this, query);

        lv.setLayoutManager(new LinearLayoutManager(this));
        lv.setAdapter(mAdapter);
        lv.addItemDecoration(new MyDecoration(this, MyDecoration.VERTICAL_LIST));

        mRecommend = findViewById(R.id.recommend);

        tvRecommendTitle = (TextView) findViewById(R.id.tv_recommend_title);

        tvRecommend = (TextView) findViewById(R.id.tv_recommend);

        if (query != null && query.size() > 16) {
            mRecommend.setVisibility(View.VISIBLE);
            tvRecommendTitle.setText(R.string.ssq_recommend);
            tvRecommend.setText(R.string.ssq_recommending);
            getRecommendCard(query);
        } else {
            mRecommend.setVisibility(View.GONE);
        }

    }


    private synchronized void getRecommendCard(List<SSQ> l) {
        List<SSQ> list = new ArrayList<>();
        list.addAll(l);
        final Map<String, Integer> redCount = new TreeMap<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return String.valueOf(o1).compareTo(String.valueOf(o2));
            }
        });
        final Map<String, Integer> blueCount = new TreeMap<>(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return String.valueOf(o1).compareTo(String.valueOf(o2));
            }
        });
        final int size = list.size();
        SSQ[] s = new SSQ[size];
        for (int i = 0; i < s.length; i++) {
            s[i] = list.get(i);
        }
        Observable.from(s).

                subscribeOn(Schedulers.newThread()).map(new Func1<SSQ, List<String>>() {
            @Override
            public List<String> call(SSQ ssq) {
                List<String> reds = ssq.getReds();
                List<String> l = new ArrayList<>();
                l.addAll(reds);
                l.add(ssq.getBlue());
                return l;
            }
        }).subscribe(new Action1<List<String>>() {
            @Override
            public void call(List<String> strings) {
                for (int i = 0; i < strings.size(); i++) {
                    if (i == strings.size() - 1) {
                        Integer x = blueCount.get(strings.get(i));
                        if (x == null) x = 0;
                        blueCount.put(strings.get(i), x + 1);
                    } else {
                        Integer x = redCount.get(strings.get(i));
                        if (x == null) x = 0;
                        redCount.put(strings.get(i), x + 1);
                    }

                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

                LogUtil.e(TAG, "call ", throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                StringBuffer sb = new StringBuffer();
                sb.append("红球:").append("\n");
                Set<String> strings = redCount.keySet();
                int x = 0;
                for (String s : strings) {
                    x++;
                    sb.append("\t").append(s).append(":").append("\t").append(redCount.get(s)).append("次");
                    if (x == 5) {
                        sb.append("\n");
                        x = 0;
                    } else sb.append("\t");
                }

                x = 0;
                sb.append("\n").append("蓝球:").append("\n");
                Set<String> bstrings = blueCount.keySet();
                for (String s : bstrings) {
                    x++;
                    sb.append("\t").append(s).append(":").append("\t").append(blueCount.get(s)).append("次");
                    if (x == 5) {
                        sb.append("\n");
                        x = 0;
                    } else sb.append("\t");
                }

                final String s = sb.toString();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvRecommend.setText(s);
                    }
                });
            }
        });
    }

    private synchronized void getRecommendMiddleCard(List<SSQ> list) {
        final int size = list.size();
        SSQ[] s = new SSQ[size];
        final int[] max = new int[7];
        for (int i = 0; i < s.length; i++) {
            s[i] = list.get(i);
        }
        Observable.from(s).subscribeOn(Schedulers.newThread())
                .map(new Func1<SSQ, int[]>() {
                    @Override
                    public int[] call(SSQ ssq) {
                        List<String> reds = ssq.getReds();
                        int[] result = new int[reds.size() + 1];
                        for (int i = 0; i < reds.size(); i++) {
                            result[i] = Integer.parseInt(reds.get(i));
                        }
                        result[result.length - 1] = Integer.parseInt(ssq.getBlue());
                        return result;
                    }
                }).subscribe(new Action1<int[]>() {
            @Override
            public void call(int[] ints) {
                for (int i = 0; i < ints.length; i++) {
                    max[i] = max[i] + ints[i];
                }
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {

            }
        }, new Action0() {
            @Override
            public void call() {

                final StringBuffer sb = new StringBuffer();
                for (int i = 0; i < max.length; i++) {
                    max[i] = max[i] / size;
                    sb.append(max[i]).append("     ");
                    if (i == max.length - 2) {
                        sb.append(",                  ");
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ViewUtil.showTextHighlight(tvRecommend, sb.toString());
                    }
                });
            }
        });
    }

    private class SSQHistoryAdapter extends Adapter<SSQHistoryAdapter.ViewHolder> {

        private List<SSQ> mList;

        private Context mContext;

        public SSQHistoryAdapter(Context context, List<SSQ> list) {
            mList = list;
            mContext = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.ssq_hisitory_item_layout, parent, false));
        }

        @Override
        public void onBindViewHolder(ViewHolder vh, int position) {


            SSQ ssq = mList.get(position);
            vh.tvExpect.setText(getString(R.string.ssq_number, ssq.getExpect()));

            vh.tvOpenTime.setText(formatDate(ssq.getOpenTimeDate()));
            vh.blue.setText(ssq.getBlue());
            List<String> reds = ssq.getReds();
            if (reds != null && reds.size() == 6) {
                vh.red1.setText(reds.get(0).trim());
                vh.red2.setText(reds.get(1));
                vh.red3.setText(reds.get(2));
                vh.red4.setText(reds.get(3));
                vh.red5.setText(reds.get(4));
                vh.red6.setText(reds.get(5));
            }

            if (position == 0) {
                for (TwoColorBall t : vh.mBalls) {
                    t.setChecked(true);
                }
            } else {
                for (TwoColorBall t : vh.mBalls) {
                    t.setChecked(false);
                }
            }

        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tvExpect, tvOpenTime;
            TwoColorBall red1, red2, red3, red4, red5, red6, blue;

            List<TwoColorBall> mBalls;

            public ViewHolder(View v) {

                super(v);
                tvExpect = (TextView) v.findViewById(R.id.expect);
                tvOpenTime = (TextView) v.findViewById(R.id.openTime);

                mBalls = new ArrayList<>();
                red1 = (TwoColorBall) v.findViewById(R.id.red1);
                mBalls.add(red1);
                red2 = (TwoColorBall) v.findViewById(R.id.red2);
                mBalls.add(red2);
                red3 = (TwoColorBall) v.findViewById(R.id.red3);
                mBalls.add(red3);
                red4 = (TwoColorBall) v.findViewById(R.id.red4);
                mBalls.add(red4);
                red5 = (TwoColorBall) v.findViewById(R.id.red5);
                mBalls.add(red5);
                red6 = (TwoColorBall) v.findViewById(R.id.red6);
                mBalls.add(red6);
                blue = (TwoColorBall) v.findViewById(R.id.blue);
                mBalls.add(blue);
                v.setTag(this);
            }
        }
    }

    private String formatDate(long time) {
//        yyyy年MM月dd日 EEEE
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd (EEEE)");
        return fmt.format(new Date(time));
    }
}
