package com.bidfrail.android.ui.navigationhost.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bidfrail.android.AppConstants;
import com.bidfrail.android.R;
import com.bidfrail.android.data.local.sharedpreferences.SharedPreferencesHelper;
import com.bidfrail.android.data.remote.ApiConfiguration;
import com.bidfrail.android.data.remote.glide.GlideImageLoader;
import com.bidfrail.android.data.remote.glide.GlideImageLoadingListener;
import com.bidfrail.android.databinding.ActivityNavigationHostBinding;
import com.bidfrail.android.ui.base.view.BaseActivity;
import com.bidfrail.android.ui.loginregister.view.LoginRegisterActivity;
import com.bidfrail.android.ui.navigationhost.viewmodel.NavigationHostViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.library.utilities.activity.ActivityUtils;
import com.library.utilities.custom.CircleImageView;
import com.library.utilities.custom.MyBadgeDrawable;
import com.library.utilities.string.StringUtils;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NavigationHostActivity extends BaseActivity<ActivityNavigationHostBinding, NavigationHostViewModel> {

    private AppBarConfiguration appBarConfiguration;
    private NavHostFragment navHostFragment;
    private NavController navController;


    private Menu menu;
    private int badgeCount = 0;

    @Inject
    SharedPreferencesHelper sharedPreferencesHelper;

    @Override
    protected String getActivityClassName() {
        return NavigationHostActivity.class.getSimpleName();
    }

    @Override
    protected void doBeforeSetContentView() {
    }

    @Override
    protected void doInOnCreate() {
        handleIntent(getIntent());
    }

    @NonNull
    @Override
    protected ActivityNavigationHostBinding getViewBinding(LayoutInflater inflater) {
        return ActivityNavigationHostBinding.inflate(inflater);
    }

    @NonNull
    @Override
    protected NavigationHostViewModel getViewModel() {
        return viewModelProvider(NavigationHostViewModel.class);
    }

    @Override
    protected void setupToolbar() {
        setSupportActionBar(mViewBinding.includeIdAppBar.toolbar);
    }

    @Override
    protected void init() {
        mViewBinding.navigationView.setItemIconTintList(null);
        setupDrawerHeader(mViewBinding.navigationView);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        if (navHostFragment != null) {
            navController = navHostFragment.getNavController();
            setupActionBarWithNavController(this, navController);
            setupLeftNavigationDrawer(mViewBinding.navigationView, navController);
            setupBottomNavigationView(mViewBinding.includeIdAppBar.includeIdContent.fabBottomNavigationView, navController);

        /*  navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
                @Override
                public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                    if (destination.getId() == R.id.homeFragment) {
                        Log.e(mTag, "Profile Fragment");
                    }
                }
            });
*/




           /* mViewBinding.includeIdAppBar.includeIdContent.fabBottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.profileFragment:
                            Log.e(mTag, "Profile Fragment");
                            break;
                        default:
                            Log.e(mTag, "Other Fragment");
                    }
                    return true;
                }
            });*/
        }
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void addTextChangedListener() {
    }

    @Override
    protected void setOnFocusChangeListener() {
    }

    @Override
    protected void setupObservers() {
        final Observer<Integer> integerObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                if (integer != null) {
                    badgeCount = integer;
                    updateProductBadge(badgeCount);
                }
            }
        };
        mViewModel.getCartCount().observe(this/*Activity or Fragment*/, integerObserver);
    }

    @Override
    protected void setupListeners() {
    }

    @Override
    public void showProgressBar() {
    }

    @Override
    public void hideProgressBar() {
    }

    @Override
    public void showProgressDialog() {
    }

    @Override
    public void hideProgressDialog() {
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        if (navController.getGraph().getStartDestination() == navController.getCurrentDestination().getId()) {
            alertDialogExit(NavigationHostActivity.this);
        } else {
            super.onBackPressed();
        }
    }
    /***********************************************************************************************
     *********************************************Helper********************************************
     **********************************************************************************************/
    private void setupActionBarWithNavController(AppCompatActivity activity, NavController navController) {
        DrawerLayout drawerLayout = mViewBinding.drawerLayout;
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment,
                R.id.bookingsFragment,
                R.id.walletFragment,
                R.id.profileFragment
        ).setOpenableLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(activity, navController, appBarConfiguration);
    }

    public void setupDrawerHeader(NavigationView navigationView) {
        View navigationDrawerHeaderView = navigationView.getHeaderView(0);
        CircleImageView circleImageView = navigationDrawerHeaderView.findViewById(R.id.profilePictureCircleImageView);
        TextView name = navigationDrawerHeaderView.findViewById(R.id.nameTextView);

        if (!StringUtils.isEmpty(sharedPreferencesHelper.getPicture())) {
            GlideImageLoader.load(
                    getApplicationContext(),
                    ApiConfiguration.BASE_URL + sharedPreferencesHelper.getPicture(),
                    R.drawable.user_placeholder,
                    R.drawable.user_placeholder,
                    circleImageView,
                    new GlideImageLoadingListener() {
                        @Override
                        public void imageLoadSuccess() {
                        }

                        @Override
                        public void imageLoadError() {
                        }
                    });
        }

        if (!StringUtils.isEmpty(sharedPreferencesHelper.getName())) {
            name.setText(sharedPreferencesHelper.getName());
        } else {
            name.setText(getResources().getString(R.string.navigation_host_update_profile));
        }

        MaterialButton logoutMaterialButton = mViewBinding.navigationView.findViewById(R.id.logoutMaterialButton);
        logoutMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesHelper.setRemember(false);
                Intent intent = ActivityUtils.launchActivityWithClearBackStack(NavigationHostActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupLeftNavigationDrawer(NavigationView navigationView, NavController navController) {
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void setupBottomNavigationView(BottomNavigationView bottomNavigationView, NavController navController) {
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
    }

    public void hideAppBar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
    }

    public void showAppBar() {
        if (getSupportActionBar() != null)
            getSupportActionBar().show();
    }

    public void showBottomNavigation() {
        mViewBinding.includeIdAppBar.includeIdContent.fabBottomNavigationView.setVisibility(View.VISIBLE);
        mViewBinding.includeIdAppBar.includeIdContent.floatingActionButton.setVisibility(View.VISIBLE);
    }

    public void hideBottomNavigation() {
        mViewBinding.includeIdAppBar.includeIdContent.fabBottomNavigationView.setVisibility(View.GONE);
        mViewBinding.includeIdAppBar.includeIdContent.floatingActionButton.setVisibility(View.GONE);
    }

    private void alertDialogExit(Activity activity) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);

        alertDialogBuilder.setIcon(R.drawable.ic_question_mark);
        alertDialogBuilder.setTitle("Confirm Exit");
        alertDialogBuilder.setMessage("Are you sure you want to Exit?");

        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                finishAffinity();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();

        Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        positiveButton.setTextColor(Color.parseColor("#C20114"));
        negativeButton.setTextColor(Color.parseColor("#C20114"));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        params.setMargins(20, 0, 0, 0);
        positiveButton.setLayoutParams(params);
    }

    private void setBadgeCount(Context context, LayerDrawable icon, int count) {
        MyBadgeDrawable badge;

        Drawable reuse = icon.findDrawableByLayerId(R.id.ic_badge);

        if (reuse instanceof MyBadgeDrawable) {
            badge = (MyBadgeDrawable) reuse;
        } else {
            badge = new MyBadgeDrawable(context);
        }

        badge.setCount(String.valueOf(count));
        icon.mutate();
        icon.setDrawableByLayerId(R.id.ic_badge, badge);
    }

    private void updateProductBadge(int count) {
        badgeCount = count;
        invalidateOptionsMenu();
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /* Inflate the menu. it adds items to the action bar if it's present. */
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        /* Add to Cart Count */
        MenuItem menuItem = menu.findItem(R.id.action_view_cart);
        LayerDrawable icon = (LayerDrawable) menuItem.getIcon();
        setBadgeCount(this, icon, badgeCount);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_view_cart:
                updateProductBadge(5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
    }
}