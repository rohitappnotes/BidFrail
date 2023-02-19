package com.bidfrail.android.ui.navigationhost.fragment.search.adapter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.bidfrail.android.R;
import com.bidfrail.android.model.SubCategory;
import com.library.adapter.recyclerview.adapter.BaseSingleItemAdapter;
import com.library.adapter.recyclerview.adapter.BaseViewHolder;

public class SearchTrendingSubCategoryRecyclerViewAdapter extends BaseSingleItemAdapter<SubCategory, BaseViewHolder> {

    private Context context;

    public SearchTrendingSubCategoryRecyclerViewAdapter(Context context) {
        this.context = context;
        addChildClickViewIds(R.id.searchTrendingSubCategoryRowItem);
    }

    @Override
    protected int getViewHolderLayoutResId() {
        return R.layout.search_trending_sub_category_row_item;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder viewHolder, SubCategory subCategory, int position) {
    }
}
