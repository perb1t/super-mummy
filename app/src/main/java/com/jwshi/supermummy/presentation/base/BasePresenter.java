package com.jwshi.supermummy.presentation.base;

public interface BasePresenter <V extends BaseView> {

    void attach(V baseView);

    void detach();

    V getBaseView();
}
