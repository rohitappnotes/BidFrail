package com.bidfrail.android.ui.navigationhost.fragment.home.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import com.bidfrail.android.R;
import com.bidfrail.android.databinding.FragmentHomeBinding;
import com.bidfrail.android.model.Category;
import com.bidfrail.android.model.CategoryWithSubCategory;
import com.bidfrail.android.model.OfferBanner;
import com.bidfrail.android.model.ReferEarnBanner;
import com.bidfrail.android.model.SubCategory;
import com.bidfrail.android.model.SubCategoryBanner;
import com.bidfrail.android.ui.base.view.BaseFragment;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.CategoryRecyclerViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.NewlyLaunchedCategoryRecyclerViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.OfferSliderViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.ReferAndEarnSliderViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.SearchSubCategoryRecyclerViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.SubCategoryRecyclerViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.SubCategorySliderViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.TopBiddingCategoryRecyclerViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.TrendingSubCategoryRecyclerViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.viewmodel.HomeViewModel;
import com.bidfrail.android.ui.navigationhost.fragment.search.view.SearchFragment;
import com.bidfrail.android.ui.navigationhost.view.NavigationHostActivity;
import com.library.adapter.recyclerview.LayoutManagerUtils;
import com.library.adapter.recyclerview.listener.OnRecyclerViewItemChildClick;
import com.library.sliderview.IndicatorView.animation.type.IndicatorAnimationType;
import com.library.sliderview.SliderAnimations;
import java.util.ArrayList;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private NavController navController;

    private SubCategorySliderViewAdapter subCategorySliderViewAdapter;
    private ArrayList<SubCategoryBanner> subCategoryBannerArrayList = new ArrayList<SubCategoryBanner>();

    private CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;
    private SubCategoryRecyclerViewAdapter subCategoryRecyclerViewAdapter;
    private ArrayList<CategoryWithSubCategory> categoryWithSubCategoryArrayList = new ArrayList<CategoryWithSubCategory>();

    private OfferSliderViewAdapter offerSliderViewAdapter;
    private ArrayList<OfferBanner> offerBannerArrayList = new ArrayList<OfferBanner>();

    private TopBiddingCategoryRecyclerViewAdapter topBiddingCategoryRecyclerViewAdapter;
    private ArrayList<Category> topBiddingCategoryArrayList = new ArrayList<Category>();

    private NewlyLaunchedCategoryRecyclerViewAdapter newlyLaunchedCategoryRecyclerViewAdapter;
    private ArrayList<Category> newlyLaunchedCategoryArrayList = new ArrayList<Category>();

    private ReferAndEarnSliderViewAdapter referAndEarnSliderViewAdapter;
    private ArrayList<ReferEarnBanner> referEarnBannerArrayList = new ArrayList<ReferEarnBanner>();

    private TrendingSubCategoryRecyclerViewAdapter trendingSubCategoryRecyclerViewAdapter;
    private ArrayList<SubCategory> trendingSubCategoryArrayList = new ArrayList<SubCategory>();

    @Override
    protected String getFragmentClassName() {
        return HomeFragment.class.getSimpleName();
    }

    @Override
    protected void doInOnCreate() {
    }

    @NonNull
    @Override
    protected FragmentHomeBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
    }

    @NonNull
    @Override
    protected HomeViewModel getViewModel() {
        return viewModelProvider(HomeViewModel.class);
    }

    @Override
    protected void setupToolbar() {
    }

    @Override
    protected void init() {
        navController = Navigation.findNavController(rootView);
    }

    @Override
    protected void initView() {
        mViewBinding.categoryRecyclerView.setLayoutManager(LayoutManagerUtils.getLinearLayoutManagerHorizontal(getContext()));
        categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(getContext());
        categoryRecyclerViewAdapter.addArrayList(categoryWithSubCategoryArrayList);
        mViewBinding.categoryRecyclerView.setAdapter(categoryRecyclerViewAdapter);
        mViewBinding.categoryRecyclerView.setNestedScrollingEnabled(false);
        mViewBinding.categoryRecyclerView.setHasFixedSize(false);

        subCategoryRecyclerViewAdapter = new SubCategoryRecyclerViewAdapter(getContext());

        mViewBinding.topBiddingCategoryRecyclerView.setLayoutManager(LayoutManagerUtils.getLinearLayoutManagerHorizontal(getContext()));
        topBiddingCategoryRecyclerViewAdapter = new TopBiddingCategoryRecyclerViewAdapter(getContext());
        topBiddingCategoryRecyclerViewAdapter.addArrayList(topBiddingCategoryArrayList);
        mViewBinding.topBiddingCategoryRecyclerView.setAdapter(topBiddingCategoryRecyclerViewAdapter);
        mViewBinding.topBiddingCategoryRecyclerView.setNestedScrollingEnabled(false);
        mViewBinding.topBiddingCategoryRecyclerView.setHasFixedSize(false);

        mViewBinding.newlyLaunchedCategoryRecyclerView.setLayoutManager(LayoutManagerUtils.getLinearLayoutManagerHorizontal(getContext()));
        newlyLaunchedCategoryRecyclerViewAdapter = new NewlyLaunchedCategoryRecyclerViewAdapter(getContext());
        newlyLaunchedCategoryRecyclerViewAdapter.addArrayList(newlyLaunchedCategoryArrayList);
        mViewBinding.newlyLaunchedCategoryRecyclerView.setAdapter(newlyLaunchedCategoryRecyclerViewAdapter);
        mViewBinding.newlyLaunchedCategoryRecyclerView.setNestedScrollingEnabled(false);
        mViewBinding.newlyLaunchedCategoryRecyclerView.setHasFixedSize(false);

        referAndEarnSliderViewAdapter = new ReferAndEarnSliderViewAdapter(getContext());
        mViewBinding.referEarnSliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
        mViewBinding.referEarnSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mViewBinding.referEarnSliderView.startAutoCycle();

        mViewBinding.trendingSubCategoryRecyclerView.setLayoutManager(LayoutManagerUtils.getGridLayoutManagerVertical(getContext(), 2));
        trendingSubCategoryRecyclerViewAdapter = new TrendingSubCategoryRecyclerViewAdapter(getContext());
        trendingSubCategoryRecyclerViewAdapter.addArrayList(trendingSubCategoryArrayList);
        mViewBinding.trendingSubCategoryRecyclerView.setAdapter(trendingSubCategoryRecyclerViewAdapter);
        mViewBinding.trendingSubCategoryRecyclerView.setNestedScrollingEnabled(false);
        mViewBinding.trendingSubCategoryRecyclerView.setHasFixedSize(false);
    }

    @Override
    protected void addTextChangedListener() {
    }

    @Override
    protected void setOnFocusChangeListener() {
    }

    @Override
    protected void setupObservers() {
        Observer<String> subCategoryBannerErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideProgressDialog();
                if (error == null) {
                    showShortToast(getString(R.string.something_went_wrong_please_try_again));
                } else {
                    showShortToast(error);
                }
            }
        };
        mViewModel.subCategoryBannerError().observe(this, subCategoryBannerErrorObserver);

        final Observer<ArrayList<SubCategoryBanner>> subCategoryBannerSuccessObserver = new Observer<ArrayList<SubCategoryBanner>>() {
            @Override
            public void onChanged(ArrayList<SubCategoryBanner> subCategoryBanners) {
                hideProgressDialog();

                if (subCategoryBanners.size() != 0)
                {
                    System.out.println("=======================================");
                    subCategoryBannerArrayList.clear();
                    subCategoryBannerArrayList = subCategoryBanners;
                    System.out.println("========================f==============="+subCategoryBanners.size());
                    subCategorySliderViewAdapter = new SubCategorySliderViewAdapter(getContext());
                    subCategorySliderViewAdapter.renewItems(subCategoryBanners);
                    mViewBinding.subCategorySliderView.setSliderAdapter(subCategorySliderViewAdapter);
                    mViewBinding.subCategorySliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
                    mViewBinding.subCategorySliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    mViewBinding.subCategorySliderView.startAutoCycle();
                }
            }
        };
        mViewModel.subCategoryBannerSuccess().observe(this, subCategoryBannerSuccessObserver);

        Observer<String> categoryWithSubCategoryErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideProgressDialog();
                if (error == null) {
                    showShortToast(getString(R.string.something_went_wrong_please_try_again));
                } else {
                    showShortToast(error);
                }
            }
        };
        mViewModel.categoryWithSubCategoryError().observe(this, categoryWithSubCategoryErrorObserver);

        final Observer<ArrayList<CategoryWithSubCategory>> categoryWithSubCategorySuccessObserver = new Observer<ArrayList<CategoryWithSubCategory>>() {
            @Override
            public void onChanged(ArrayList<CategoryWithSubCategory> categoryWithSubCategories) {
                hideProgressDialog();

                categoryWithSubCategoryArrayList.clear();
                categoryWithSubCategoryArrayList.addAll(categoryWithSubCategories);

                for (int i = 0; i < categoryWithSubCategoryArrayList.size(); i++) {
                    if (0 == i)
                    {
                        categoryWithSubCategoryArrayList.set(i, new CategoryWithSubCategory(
                                true,
                                categoryWithSubCategoryArrayList.get(i).getId(),
                                categoryWithSubCategoryArrayList.get(i).getName(),
                                categoryWithSubCategoryArrayList.get(i).getImage(),
                                categoryWithSubCategoryArrayList.get(i).getCategoryType(),
                                categoryWithSubCategoryArrayList.get(i).getIsActive(),
                                categoryWithSubCategoryArrayList.get(i).getIsTrending(),
                                categoryWithSubCategoryArrayList.get(i).getIsNew(),
                                categoryWithSubCategoryArrayList.get(i).getCreatedAt(),
                                categoryWithSubCategoryArrayList.get(i).getUpdatedAt(),
                                categoryWithSubCategoryArrayList.get(i).getSubCategoryArrayList()
                        ));
                    }
                    else
                    {
                        categoryWithSubCategoryArrayList.set(i, new CategoryWithSubCategory(
                                false,
                                categoryWithSubCategoryArrayList.get(i).getId(),
                                categoryWithSubCategoryArrayList.get(i).getName(),
                                categoryWithSubCategoryArrayList.get(i).getImage(),
                                categoryWithSubCategoryArrayList.get(i).getCategoryType(),
                                categoryWithSubCategoryArrayList.get(i).getIsActive(),
                                categoryWithSubCategoryArrayList.get(i).getIsTrending(),
                                categoryWithSubCategoryArrayList.get(i).getIsNew(),
                                categoryWithSubCategoryArrayList.get(i).getCreatedAt(),
                                categoryWithSubCategoryArrayList.get(i).getUpdatedAt(),
                                categoryWithSubCategoryArrayList.get(i).getSubCategoryArrayList()
                        ));
                    }
                }

                categoryRecyclerViewAdapter.clearAllItem();
                categoryRecyclerViewAdapter.replaceArrayList(categoryWithSubCategoryArrayList);

                mViewBinding.subCategoryAccordingToCategoryRecyclerView.setHasFixedSize(true);
                mViewBinding.subCategoryAccordingToCategoryRecyclerView.setLayoutManager(LayoutManagerUtils.getLinearLayoutManagerHorizontal(getContext()));
                mViewBinding.subCategoryAccordingToCategoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
                subCategoryRecyclerViewAdapter.clearAllItem();
                subCategoryRecyclerViewAdapter.addArrayList(categoryWithSubCategoryArrayList.get(0).getSubCategoryArrayList());
                mViewBinding.subCategoryAccordingToCategoryRecyclerView.setAdapter(subCategoryRecyclerViewAdapter);
            }
        };
        mViewModel.categoryWithSubCategorySuccess().observe(this, categoryWithSubCategorySuccessObserver);

        Observer<String> offerBannerErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideProgressDialog();
                if (error == null) {
                    showShortToast(getString(R.string.something_went_wrong_please_try_again));
                } else {
                    showShortToast(error);
                }
            }
        };
        mViewModel.offerBannerError().observe(this, offerBannerErrorObserver);

        final Observer<ArrayList<OfferBanner>> offerBannerSuccessObserver = new Observer<ArrayList<OfferBanner>>() {

            @Override
            public void onChanged(ArrayList<OfferBanner> offerBanners) {
                hideProgressDialog();
                offerBannerArrayList.clear();
                offerBannerArrayList = offerBanners;

                offerSliderViewAdapter = new OfferSliderViewAdapter(getContext());
                mViewBinding.offerSliderView.setIndicatorAnimation(IndicatorAnimationType.THIN_WORM);
                mViewBinding.offerSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                mViewBinding.offerSliderView.startAutoCycle();
                offerSliderViewAdapter.renewItems(offerBannerArrayList);
                mViewBinding.offerSliderView.setSliderAdapter(offerSliderViewAdapter);
            }
        };
        mViewModel.offerBannerSuccess().observe(this, offerBannerSuccessObserver);

        Observer<String> topBiddingCategoryErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideProgressDialog();
                if (error == null) {
                    showShortToast(getString(R.string.something_went_wrong_please_try_again));
                } else {
                    showShortToast(error);
                }
            }
        };
        mViewModel.topBiddingCategoryError().observe(this, topBiddingCategoryErrorObserver);

        final Observer<ArrayList<Category>> topBiddingCategorySuccessObserver = new Observer<ArrayList<Category>>() {

            @Override
            public void onChanged(ArrayList<Category> categories) {
                hideProgressDialog();
                topBiddingCategoryArrayList.clear();
                topBiddingCategoryArrayList.addAll(categories);

                topBiddingCategoryRecyclerViewAdapter.clearAllItem();
                topBiddingCategoryRecyclerViewAdapter.replaceArrayList(topBiddingCategoryArrayList);
            }
        };
        mViewModel.topBiddingCategorySuccess().observe(this, topBiddingCategorySuccessObserver);

        Observer<String> newlyLaunchedCategoryErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideProgressDialog();
                if (error == null) {
                    showShortToast(getString(R.string.something_went_wrong_please_try_again));
                } else {
                    showShortToast(error);
                }
            }
        };
        mViewModel.newlyLaunchedCategoryError().observe(this, newlyLaunchedCategoryErrorObserver);

        final Observer<ArrayList<Category>> newlyLaunchedCategorySuccessObserver = new Observer<ArrayList<Category>>() {

            @Override
            public void onChanged(ArrayList<Category> categories) {
                hideProgressDialog();
                newlyLaunchedCategoryArrayList.clear();
                newlyLaunchedCategoryArrayList.addAll(categories);

                newlyLaunchedCategoryRecyclerViewAdapter.clearAllItem();
                newlyLaunchedCategoryRecyclerViewAdapter.replaceArrayList(newlyLaunchedCategoryArrayList);
            }
        };
        mViewModel.newlyLaunchedCategorySuccess().observe(this, newlyLaunchedCategorySuccessObserver);

        Observer<String> referEarnBannerErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideProgressDialog();
                if (error == null) {
                    showShortToast(getString(R.string.something_went_wrong_please_try_again));
                } else {
                    showShortToast(error);
                }
            }
        };
        mViewModel.referEarnBannerError().observe(this, referEarnBannerErrorObserver);

        final Observer<ArrayList<ReferEarnBanner>> referEarnBannerSuccessObserver = new Observer<ArrayList<ReferEarnBanner>>() {

            @Override
            public void onChanged(ArrayList<ReferEarnBanner> referEarnBanners) {
                hideProgressDialog();
                referEarnBannerArrayList.clear();
                referEarnBannerArrayList = referEarnBanners;
                referAndEarnSliderViewAdapter.renewItems(referEarnBannerArrayList);
                mViewBinding.referEarnSliderView.setSliderAdapter(referAndEarnSliderViewAdapter);
            }
        };
        mViewModel.referEarnBannerSuccess().observe(this, referEarnBannerSuccessObserver);

        Observer<String> trendingSubCategoryErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideProgressDialog();
                if (error == null) {
                    showShortToast(getString(R.string.something_went_wrong_please_try_again));
                } else {
                    showShortToast(error);
                }
            }
        };
        mViewModel.trendingSubCategoryError().observe(this, trendingSubCategoryErrorObserver);

        final Observer<ArrayList<SubCategory>> trendingSubCategorySuccessObserver = new Observer<ArrayList<SubCategory>>() {

            @Override
            public void onChanged(ArrayList<SubCategory> subCategories) {
                hideProgressDialog();
                trendingSubCategoryArrayList.clear();
                trendingSubCategoryArrayList.addAll(subCategories);

                trendingSubCategoryRecyclerViewAdapter.clearAllItem();
                trendingSubCategoryRecyclerViewAdapter.replaceArrayList(trendingSubCategoryArrayList);
            }
        };
        mViewModel.trendingSubCategorySuccess().observe(this, trendingSubCategorySuccessObserver);
    }

    @Override
    protected void setupListeners() {
       /* mViewBinding.searchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchFragment modalBottomSheet = new SearchFragment();
                FragmentManager fragmentManager = getChildFragmentManager();
                modalBottomSheet.show(fragmentManager, modalBottomSheet.getTag());
            }
        });*/

        categoryRecyclerViewAdapter.setOnRecyclerViewItemChildClick(new OnRecyclerViewItemChildClick<CategoryWithSubCategory>() {
            @Override
            public void OnItemChildClick(View viewChild, CategoryWithSubCategory categoryWithSubCategory, int position) {
                switch (viewChild.getId()) {
                    case R.id.categoryRowItem:
                        for (int i = 0; i < categoryWithSubCategoryArrayList.size(); i++) {
                            if (position == i)
                            {
                                categoryWithSubCategoryArrayList.set(i, new CategoryWithSubCategory(
                                        true,
                                        categoryWithSubCategoryArrayList.get(i).getId(),
                                        categoryWithSubCategoryArrayList.get(i).getName(),
                                        categoryWithSubCategoryArrayList.get(i).getImage(),
                                        categoryWithSubCategoryArrayList.get(i).getCategoryType(),
                                        categoryWithSubCategoryArrayList.get(i).getIsActive(),
                                        categoryWithSubCategoryArrayList.get(i).getIsTrending(),
                                        categoryWithSubCategoryArrayList.get(i).getIsNew(),
                                        categoryWithSubCategoryArrayList.get(i).getCreatedAt(),
                                        categoryWithSubCategoryArrayList.get(i).getUpdatedAt(),
                                        categoryWithSubCategoryArrayList.get(i).getSubCategoryArrayList()
                                ));
                            }
                            else
                            {
                                categoryWithSubCategoryArrayList.set(i, new CategoryWithSubCategory(
                                        false,
                                        categoryWithSubCategoryArrayList.get(i).getId(),
                                        categoryWithSubCategoryArrayList.get(i).getName(),
                                        categoryWithSubCategoryArrayList.get(i).getImage(),
                                        categoryWithSubCategoryArrayList.get(i).getCategoryType(),
                                        categoryWithSubCategoryArrayList.get(i).getIsActive(),
                                        categoryWithSubCategoryArrayList.get(i).getIsTrending(),
                                        categoryWithSubCategoryArrayList.get(i).getIsNew(),
                                        categoryWithSubCategoryArrayList.get(i).getCreatedAt(),
                                        categoryWithSubCategoryArrayList.get(i).getUpdatedAt(),
                                        categoryWithSubCategoryArrayList.get(i).getSubCategoryArrayList()
                                ));
                            }
                        }
                        categoryRecyclerViewAdapter.replaceArrayList(categoryWithSubCategoryArrayList);
                        subCategoryRecyclerViewAdapter.replaceArrayList(categoryWithSubCategory.getSubCategoryArrayList());
                        break;
                    default:
                        break;
                }
            }
        });

        subCategoryRecyclerViewAdapter.setOnRecyclerViewItemChildClick(new OnRecyclerViewItemChildClick<SubCategory>() {
            @Override
            public void OnItemChildClick(View viewChild, SubCategory subCategory, int position) {
                switch (viewChild.getId()) {
                    case R.id.subCategoryRowItem:
                        HomeFragmentDirections.ActionHomeFragmentToServiceFragment action =  HomeFragmentDirections.actionHomeFragmentToServiceFragment(subCategory.getName(), subCategory);
                        navController.navigate((NavDirections) action);
                        break;
                    default:
                        break;
                }
            }
        });
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.subCategoryBanner();
        mViewModel.categoryWithSubCategory();
        mViewModel.offerBannerError();
        mViewModel.topBiddingCategory();
        mViewModel.newlyLaunchedCategory();
        mViewModel.referEarnBanner();
        mViewModel.trendingSubCategory();

        if (getActivity() instanceof NavigationHostActivity) {
            NavigationHostActivity navigationHostActivity = (NavigationHostActivity) getActivity();
            navigationHostActivity.showBottomNavigation();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getActivity() instanceof NavigationHostActivity) {
            NavigationHostActivity navigationHostActivity = (NavigationHostActivity) getActivity();
            navigationHostActivity.hideBottomNavigation();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}