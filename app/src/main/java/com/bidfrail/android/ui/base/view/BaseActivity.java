package com.bidfrail.android.ui.base.view;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.bidfrail.android.R;
import com.google.android.material.snackbar.Snackbar;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity<VB extends ViewBinding, VM extends ViewModel> extends AppCompatActivity implements BaseActivityView {

    protected String mTag;

    /**
     * viewBinding
     */
    protected VB mViewBinding;

    /**
     * view
     */
    protected View rootView;

    /**
     * ViewModel
     */
    protected VM mViewModel;

    protected abstract String getActivityClassName();
    protected abstract void doBeforeSetContentView();
    protected abstract void doInOnCreate();
    @NonNull protected abstract VB getViewBinding(LayoutInflater inflater);
    @NonNull protected abstract VM getViewModel();
    protected abstract void setupToolbar();
    protected abstract void init();
    protected abstract void initView();
    protected abstract void addTextChangedListener();
    protected abstract void setOnFocusChangeListener();
    protected abstract void setupObservers();
    protected abstract void setupListeners();

    private void initTag() {
        mTag = getActivityClassName();
    }

    private View initViewBinding(LayoutInflater inflater) {
        mViewBinding = getViewBinding(inflater);
        rootView = mViewBinding.getRoot();
        return rootView;
    }

    private void initViewBindingUsingReflection(LayoutInflater inflater) {
        Type superclass = getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        Class<?> cls = (Class<?>) parameterizedType.getActualTypeArguments()[0];
        try {
            Method method = cls.getDeclaredMethod("inflate", LayoutInflater.class);
            mViewBinding = (VB) method.invoke(null, inflater);
            View view = mViewBinding.getRoot();
            setContentView(view);
        } catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private void initViewModel() {
        mViewModel = getViewModel();
    }

    @Override
    @CallSuper
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTag();
        Log.i(mTag, "onCreate(Bundle savedInstanceState)");

        doBeforeSetContentView();
        setContentView(initViewBinding(getLayoutInflater()));
        doInOnCreate();

        initViewModel();
        setupToolbar();
        init();
        initView();
        addTextChangedListener();
        setOnFocusChangeListener();
        setupObservers();
        setupListeners();
    }

    @Override
    @CallSuper
    protected void onStart() {
        super.onStart();
        Log.i(mTag, "onStart()");
    }

    @Override
    @CallSuper
    protected void onRestart() {
        super.onRestart();
        Log.i(mTag, "onRestart()");
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(mTag, "onResume()");
    }

    @Override
    @CallSuper
    protected void onPause() {
        super.onPause();
        Log.i(mTag, "onPause()");
    }

    @Override
    @CallSuper
    protected void onStop() {
        super.onStop();
        Log.i(mTag, "onStop()");
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        mViewBinding = null;
        mViewModel = null;
        super.onDestroy();
        Log.i(mTag, "onDestroy()");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(mTag, "onBackPressed()");
    }

    @Override
    public void networkAvailable() {
        Log.i(mTag, "INTERNET CONNECT");


    }

    @Override
    public void networkUnavailable() {
        Log.i(mTag, "INTERNET DISCONNECT");
    }

    @Override
    public void showShortToast(String message) {
        Log.i(mTag, message);
        toast(message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showShortToast(int messageResId) {
        Log.i(mTag, getString(messageResId));
        toast(getString(messageResId), Toast.LENGTH_SHORT);
    }

    @Override
    public void showLongToast(String message) {
        Log.i(mTag, message);
        toast(message, Toast.LENGTH_LONG);
    }

    @Override
    public void showLongToast(int messageResId) {
        Log.i(mTag, getString(messageResId));
        toast(getString(messageResId), Toast.LENGTH_LONG);
    }

    @Override
    public void showIndefiniteSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        snackBar(parent, message, Snackbar.LENGTH_INDEFINITE, actionText, onClickListener);
    }

    @Override
    public void showIndefiniteSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        snackBar(parent, getString(messageResId), Snackbar.LENGTH_INDEFINITE, actionText, onClickListener);
    }

    @Override
    public void showShortSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        snackBar(parent, message, Snackbar.LENGTH_SHORT, actionText, onClickListener);
    }

    @Override
    public void showShortSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        snackBar(parent, getString(messageResId), Snackbar.LENGTH_SHORT, actionText, onClickListener);
    }

    @Override
    public void showLongSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        snackBar(parent, message, Snackbar.LENGTH_LONG, actionText, onClickListener);
    }

    @Override
    public void showLongSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        snackBar(parent, getString(messageResId), Snackbar.LENGTH_LONG, actionText, onClickListener);
    }

    @Override
    public void setVisible(View... views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setInVisible(View... views) {
        for (View v : views) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setGone(View... views) {
        for (View v : views) {
            v.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view == null) {
            view = new View(this);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //==========================================ViewModel=========================================//
    protected <VMC extends ViewModel> VMC viewModelProvider(@NonNull Class<VMC> viewModelClass) {
        return new ViewModelProvider(this).get(viewModelClass);
    }

    protected <VMC extends ViewModel> VMC viewModelProvider(@NonNull Class<VMC> viewModelClass, @NonNull ViewModelProvider.Factory factory){
        return new ViewModelProvider(this, factory).get(viewModelClass);
    }
    //==========================================Toolbar===========================================//
    protected void setupToolBar(Toolbar toolbar, int background, int navigationIcon, int title) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            toolbar.setBackgroundColor(getResources().getColor(background));

            toolbar.setNavigationIcon(AppCompatResources.getDrawable(getApplicationContext(), navigationIcon));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            toolbar.setTitle(getResources().getString(title));
        }
    }
    //=============================================Helper=========================================//
    private void toast(String message, int length) {
        Toast toast= Toast.makeText(getApplicationContext(), message, length);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void snackBar(View parent, String snackBarText, int length, String actionText, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(parent, snackBarText, length);
        snackbar.setBackgroundTint(Color.parseColor("#FF0000")); // background red
        snackbar.setTextColor(Color.parseColor("#FFFFFF")); // snackBarText white
        if (actionText != null && onClickListener!=null) {
            snackbar.setActionTextColor(Color.parseColor("#008505")); // actionText green
            snackbar.setAction(actionText, onClickListener);
        }
        snackbar.show();
    }
}
