package com.bidfrail.android.ui.navigationhost.fragment.search.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.bidfrail.android.R;
import com.bidfrail.android.model.SubCategory;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.SearchSubCategoryRecyclerViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.adapter.TrendingSubCategoryRecyclerViewAdapter;
import com.bidfrail.android.ui.navigationhost.fragment.home.viewmodel.HomeViewModel;
import com.bidfrail.android.ui.navigationhost.fragment.search.viewmodel.SearchViewModel;
import com.library.adapter.recyclerview.LayoutManagerUtils;
import com.library.utilities.string.StringUtils;

import java.util.ArrayList;
import javax.inject.Inject;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends FullScreenBottomSheetDialogFragment {

    private static final String ARG_TITLE = "title";

    private String title;

    public SearchFragment() {
        // Required empty public constructor
    }

    private String query;
    private EditText searchEditText;
    private RecyclerView searchRecyclerView, trendingRecyclerView;
    private ProgressBar progressBar;
    private ImageView errorImageView;
    private TextView errorTextView;

    private SearchSubCategoryRecyclerViewAdapter searchSubCategoryRecyclerViewAdapter;
    private ArrayList<SubCategory> subCategorySearchArrayList = new ArrayList<SubCategory>();

    SearchViewModel mViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container);
    }

    @Override
    protected int getHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel = viewModelProvider(SearchViewModel.class);

        searchEditText = view.findViewById(R.id.searchEditText);
        searchRecyclerView = view.findViewById(R.id.searchRecyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        errorImageView = view.findViewById(R.id.errorImageView);
        errorTextView = view.findViewById(R.id.errorTextView);
        trendingRecyclerView = view.findViewById(R.id.trendingRecyclerView);

        searchEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showProgressBar();
                query = s.toString().trim();
                mViewModel.subCategorySearch(query);
            }
        });

        searchRecyclerView.setLayoutManager(LayoutManagerUtils.getLinearLayoutManagerVertical(getContext()));
        searchSubCategoryRecyclerViewAdapter = new SearchSubCategoryRecyclerViewAdapter(getContext());
        searchSubCategoryRecyclerViewAdapter.addArrayList(subCategorySearchArrayList);
        searchRecyclerView.setAdapter(searchSubCategoryRecyclerViewAdapter);

        Observer<String> subCategorySearchErrorObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable String error) {
                hideProgressBar();
                if (StringUtils.isEmpty(query))
                {
                    subCategorySearchArrayList.clear();
                    searchSubCategoryRecyclerViewAdapter.clearAllItem();
                    searchSubCategoryRecyclerViewAdapter.addArrayList(subCategorySearchArrayList);
                    searchRecyclerView.setVisibility(View.VISIBLE);
                }
                else
                {
                    if (error == null) {
                        errorTextView.setText(getString(R.string.something_went_wrong_please_try_again));
                    } else {
                        errorTextView.setText(error);
                    }

                    errorImageView.setVisibility(View.VISIBLE);
                    errorTextView.setVisibility(View.VISIBLE);
                    searchRecyclerView.setVisibility(View.GONE);
                }
            }
        };
        mViewModel.subCategorySearchError().observe(this, subCategorySearchErrorObserver);

        final Observer<ArrayList<SubCategory>> subCategorySearchSuccessObserver = new Observer<ArrayList<SubCategory>>() {
            @Override
            public void onChanged(ArrayList<SubCategory> subCategories) {
                hideProgressBar();

                subCategorySearchArrayList.clear();
                subCategorySearchArrayList = subCategories;

                searchSubCategoryRecyclerViewAdapter.clearAllItem();
                searchSubCategoryRecyclerViewAdapter.addArrayList(subCategorySearchArrayList);

                errorImageView.setVisibility(View.GONE);
                errorTextView.setVisibility(View.GONE);
                searchRecyclerView.setVisibility(View.VISIBLE);
            }
        };
        mViewModel.subCategorySearchSuccess().observe(this, subCategorySearchSuccessObserver);
    }

    public void showProgressBar() {
        if (progressBar.getVisibility() == View.GONE) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar() {
        if (progressBar.getVisibility() == View.VISIBLE) {
            progressBar.setVisibility(View.GONE);
        }
    }

    protected <VMC extends ViewModel> VMC viewModelProvider(@NonNull Class<VMC> viewModelClass) {
        return new ViewModelProvider(this).get(viewModelClass);
    }
}