package com.arctouch.movies.base.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.arctouch.movies.R;
import com.arctouch.movies.base.presenter.BasePresenter;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * Created by marcelo.alves on 06/09/17.
 */

public abstract class BaseActivity<Presenter extends BasePresenter> extends RxAppCompatActivity {

    protected Presenter presenter;

    protected ProgressDialog progressDialog;

    @NonNull
    protected abstract Presenter createPresenter(@NonNull final Context context);

    @Override
    public void setContentView(@LayoutRes final int layoutResID) {
        super.setContentView(layoutResID);
        setUpToolbar();
    }

    @Override
    public void setContentView(final View view) {
        super.setContentView(view);
        setUpToolbar();
    }

    @Override
    public void setContentView(final View view, final ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        setUpToolbar();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter(this);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        presenter.onCreate(savedInstanceState);
    }

    private void setUpToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            configureToolbar(actionBar);
        }
    }

    @SuppressWarnings("UnusedParameters")
    @CallSuper
    protected void configureToolbar(@NonNull final ActionBar actionBar) {
    }

    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
        hideLoading();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        hideLoading();
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        this.setIntent(intent);
    }

    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(getString(R.string.progress_loading));
            progressDialog.setCancelable(false);
            progressDialog.setIndeterminate(true);
        }

        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void hideLoading() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    protected boolean isLoadingShowing() {
        return progressDialog != null && progressDialog.isShowing();
    }

}
