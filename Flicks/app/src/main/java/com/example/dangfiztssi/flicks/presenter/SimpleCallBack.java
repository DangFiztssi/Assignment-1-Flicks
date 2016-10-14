package com.example.dangfiztssi.flicks.presenter;

/**
 * Created by DangF on 10/14/16.
 */

public interface SimpleCallBack {
    void onSuccess();
    void onFailure(Exception e);
    void onFailure();
}
