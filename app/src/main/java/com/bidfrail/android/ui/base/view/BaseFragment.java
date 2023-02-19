package com.bidfrail.android.ui.base.view;

import androidx.annotation.CallSuper;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;

public abstract class BaseFragment<VB extends ViewBinding, VM extends ViewModel> extends Fragment implements BaseFragmentView {

    private BaseActivity baseActivity;
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

    protected abstract String getFragmentClassName();
    protected abstract void doInOnCreate();
    @NonNull protected abstract VB getViewBinding(LayoutInflater inflater, ViewGroup container);
    @NonNull protected abstract VM getViewModel();
    protected abstract void setupToolbar();
    protected abstract void init();
    protected abstract void initView();
    protected abstract void addTextChangedListener();
    protected abstract void setOnFocusChangeListener();
    protected abstract void setupObservers();
    protected abstract void setupListeners();

    private void initTag() {
        mTag = getFragmentClassName();
    }

    private View initViewBinding(LayoutInflater inflater, ViewGroup container) {
        mViewBinding = getViewBinding(inflater, container);
        rootView = mViewBinding.getRoot();
        return rootView;
    }

    private View initViewBindingUsingReflection(LayoutInflater inflater, ViewGroup container) {
        ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
        Class cls = (Class) type.getActualTypeArguments()[0];
        try {
            Method inflate = cls.getDeclaredMethod("inflate", LayoutInflater.class, ViewGroup.class, boolean.class);
            mViewBinding = (VB) inflate.invoke(null, inflate, container, false);
            rootView = mViewBinding.getRoot();
        }  catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
            e.printStackTrace();
        }
        return rootView;
    }

    private void initViewModel() {
        mViewModel = getViewModel();
    }

    /*
     * OnAttach(Context context) is not call, If API level of the android version is lower than 23.
     * Because OnAttach(Context context) is added in API level 23.
     */
    @TargetApi(23)
    @Override
    @CallSuper
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        initTag();
        Log.i(mTag, "onAttach(@NonNull Context context)");

        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.baseActivity = activity;
        }
    }

    /*
     * OnAttach(Activity activity) is not call, If API level of the android version is greater than 22.
     * Because OnAttach(Activity activity) is deprecated in API level 23, but must remain to allow compatibility with api<23
     */
    @Override
    @CallSuper
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        initTag();
        Log.i(mTag, "onAttach(@NonNull Activity activity)");

        if (activity instanceof BaseActivity) {
            BaseActivity baseMVVMActivity = (BaseActivity) activity;
            this.baseActivity = baseMVVMActivity;
        }
    }

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(mTag, "onCreate(Bundle savedInstanceState)");
        doInOnCreate();
    }

    @Override
    @CallSuper
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(mTag, "onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)");
        return initViewBinding(inflater, container);
    }

    @Override
    @CallSuper
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(mTag, "onViewCreated(@NonNull View view, Bundle savedInstanceState)");

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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(mTag, "onActivityCreated(@Nullable Bundle savedInstanceState)");
    }

    @Override
    @CallSuper
    public void onStart() {
        super.onStart();
        Log.i(mTag, "onStart()");
    }

    @Override
    @CallSuper
    public void onResume() {
        super.onResume();
        Log.i(mTag, "onResume()");
    }

    @Override
    @CallSuper
    public void onPause() {
        super.onPause();
        Log.i(mTag, "onPause()");
    }

    @Override
    @CallSuper
    public void onStop() {
        super.onStop();
        Log.i(mTag, "onStop()");
    }

    @Override
    @CallSuper
    public void onDestroyView() {
      /*  rootView = null;
        mViewBinding = null;
        mViewModel = null;*/
        super.onDestroyView();
        Log.i(mTag, "onDestroyView()");
    }

    @Override
    @CallSuper
    public void onDestroy() {
        super.onDestroy();
        Log.i(mTag, "onDestroy()");
    }

    @Override
    @CallSuper
    public void onDetach() {
        super.onDetach();
        Log.i(mTag, "onDetach()");
        baseActivity = null;
    }

    @Override
    public void showShortToast(String message) {
        Log.i(mTag, message);
        getBaseMVVMActivity().showShortToast(message);
    }

    @Override
    public void showShortToast(int messageResId) {
        Log.i(mTag, getString(messageResId));
        getBaseMVVMActivity().showShortToast(getString(messageResId));
    }

    @Override
    public void showLongToast(String message) {
        Log.i(mTag, message);
        getBaseMVVMActivity().showShortToast(message);
    }

    @Override
    public void showLongToast(int messageResId) {
        Log.i(mTag, getString(messageResId));
        getBaseMVVMActivity().showLongToast(getString(messageResId));
    }

    @Override
    public void showIndefiniteSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        getBaseMVVMActivity().showIndefiniteSnackBar(parent, message, actionText, onClickListener);
    }

    @Override
    public void showIndefiniteSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        getBaseMVVMActivity().showIndefiniteSnackBar(parent, getString(messageResId), actionText, onClickListener);
    }

    @Override
    public void showShortSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        getBaseMVVMActivity().showShortSnackBar(parent, message, actionText, onClickListener);
    }

    @Override
    public void showShortSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        getBaseMVVMActivity().showShortSnackBar(parent, getString(messageResId), actionText, onClickListener);
    }

    @Override
    public void showLongSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        getBaseMVVMActivity().showLongSnackBar(parent, message, actionText, onClickListener);
    }

    @Override
    public void showLongSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        getBaseMVVMActivity().showLongSnackBar(parent, getString(messageResId), actionText, onClickListener);
    }

    @Override
    public void setVisible(View... views) {
        getBaseMVVMActivity().setVisible(views);
    }

    @Override
    public void setInVisible(View... views) {
        getBaseMVVMActivity().setInVisible(views);
    }

    @Override
    public void setGone(View... views) {
        getBaseMVVMActivity().setGone(views);
    }

    @Override
    public void hideKeyboard() {
        getBaseMVVMActivity().hideKeyboard();
    }
    //==========================================ViewModel=========================================//
    protected <VMC extends ViewModel> VMC viewModelProvider(@NonNull Class<VMC> viewModelClass) {
        return new ViewModelProvider(this).get(viewModelClass);
    }

    protected <VMC extends ViewModel> VMC viewModelProvider(@NonNull Class<VMC> viewModelClass, @NonNull ViewModelProvider.Factory factory){
        return new ViewModelProvider(this, factory).get(viewModelClass);
    }
    //=============================================Helper=========================================//
    public BaseActivity getBaseMVVMActivity() {
        return baseActivity;
    }
}