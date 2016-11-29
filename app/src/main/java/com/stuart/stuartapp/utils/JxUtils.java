package com.stuart.stuartapp.utils;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by stuart on 2016/11/8.
 */
public class JxUtils {

    /**
     * 在0 到 max 中间 选择 length 个 不同数字列表，以String传出
     * @param max 范围
     * @param length 长度
     * @return
     */
    public static List<String> getList(final int max, final int length, final int samCount) {
        if (max <=0) {
            throw new IllegalStateException("max must more than 0");
        }
        if (length <= 0) {
            throw new IllegalStateException("length must more than 0");
        }
        Integer [] list = new Integer[max];
        for (int i = 0 ; i < max ;i ++) {
            list[i] = i+1;
        }

        Map<Integer, Integer> count = new HashMap<>();
        List<String> result = new ArrayList<>();
        int successCount = 0;


            while (successCount <length) {
                Log.i("stuart", "successCount " + successCount);
               int x =  getRandomInteger(max);
                Integer integer = count.get(x);
                int i = integer == null ? 0 : integer;
                if (!result.contains(x+"")) {
                    if (i + 1 == samCount) {
                        successCount++;
                        result.add(x+"");
                    }
                }
                count.put(x, i + 1);


            }
            Log.i("stuart", "break");



        return result;
    }


    private static Integer getRandomInteger(int x) {
       return (int)(Math.random() * x) + 1;
    }
}
