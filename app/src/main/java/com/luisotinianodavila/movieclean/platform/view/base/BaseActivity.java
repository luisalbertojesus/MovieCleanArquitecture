package com.luisotinianodavila.movieclean.platform.view.base;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    //private Toolbar mToolbar;
    //public Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //setupToolbar();
        //bindViews();
        this.initElementos();
        this.initView();
    }

    public void initElementos() {}

    /**
    * Use this method to initialize view components. This method is called after {@link
    * BaseActivity # bindViews()}
    */
    public void initView() {}

    /**
    * Its common use a toolbar within activity, if it exists in the
    * layout this will be configured
    */
   /* public void setupToolbar() {
        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }
*/
    /*private void bindViews() {
        unbinder = ButterKnife.bind(this);
    }*/

    protected abstract int getLayoutId();
}
