package com.stuart.stuartapp.callback;

import com.stuart.stuartapp.entity.SSQ;

import java.util.List;

/**
 * Created by stuart on 2016/11/14.
 */
public interface GetSSQListener {

    public void onGetSuccess(List<SSQ> infos);

    public void onGetFaile(String result);
}
