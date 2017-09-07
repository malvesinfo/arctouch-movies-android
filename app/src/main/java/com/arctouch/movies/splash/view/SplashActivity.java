package com.arctouch.movies.splash.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.arctouch.movies.R;
import com.arctouch.movies.base.view.BaseActivity;
import com.arctouch.movies.home.view.HomeActivity;
import com.arctouch.movies.splash.presenter.SplashActivityPresenter;
import com.arctouch.movies.splash.presenter.SplashPresenter;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashView {

    @NonNull
    @Override
    protected SplashPresenter createPresenter(@NonNull Context context) {
        return SplashActivityPresenter.newInstance(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);
    }

    @Override
    public void startHomeView() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }
}
