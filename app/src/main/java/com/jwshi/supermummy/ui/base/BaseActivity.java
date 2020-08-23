package com.jwshi.supermummy.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.jwshi.supermummy.R;
import com.jwshi.supermummy.presentation.base.BasePresenter;
import com.jwshi.supermummy.presentation.base.BaseView;
import com.jwshi.supermummy.utils.ScreenAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseActivity<P extends BasePresenter<V>, V extends BaseView>
    extends DaggerActivity {

  @BindView(R.id.toolbar)
  Toolbar toolbar;

  P presenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ScreenAdapter.calcuteDensity(getOrientation(), this, getApplication());
    setContentView(getLayoutResId());
    ImmersionBar.with(this)
        .keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        .init();
    ButterKnife.bind(this);
    initToolbar();
    onInit(savedInstanceState);
  }

  @Override
  public void setContentView(int layoutResID) {
    super.setContentView(R.layout.container);
    FrameLayout content = findViewById(R.id.content);
    LayoutInflater.from(this).inflate(layoutResID, content, true);
  }

  public abstract int getLayoutResId();

  public abstract boolean showToolbar();

  public abstract void onInit(@Nullable Bundle savedInstanceState);

  public void delegate(P p, V v) {
    this.presenter = p;
    this.presenter.attach(v);
  }

  public void toast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }

  public abstract int getOrientation();

  private void initToolbar() {
    setSupportActionBar(toolbar);
    if (showToolbar()) {
      toolbar.setVisibility(View.VISIBLE);
      if (getSupportActionBar() != null) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      }

    } else {
      toolbar.setVisibility(View.GONE);
    }
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void onDestroy() {
    if (presenter != null) this.presenter.detach();
    super.onDestroy();
    ImmersionBar.with(this).destroy();
  }
}
