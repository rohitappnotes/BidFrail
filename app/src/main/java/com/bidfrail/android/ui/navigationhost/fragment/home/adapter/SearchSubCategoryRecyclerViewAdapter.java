package com.bidfrail.android.ui.navigationhost.fragment.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bidfrail.android.R;
import com.bidfrail.android.data.remote.ApiConfiguration;
import com.bidfrail.android.data.remote.glide.GlideImageLoader;
import com.bidfrail.android.data.remote.glide.GlideImageLoadingListener;
import com.bidfrail.android.model.SubCategory;
import com.library.adapter.recyclerview.adapter.BaseSingleItemAdapter;
import com.library.adapter.recyclerview.adapter.BaseViewHolder;

public class SearchSubCategoryRecyclerViewAdapter extends BaseSingleItemAdapter<SubCategory, BaseViewHolder> {

    private Context context;

    public SearchSubCategoryRecyclerViewAdapter(Context context) {
        this.context = context;
        addChildClickViewIds(R.id.searchSubCategoryRowItem);
    }

    @Override
    protected int getViewHolderLayoutResId() {
        return R.layout.search_sub_category_row_item;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder viewHolder, SubCategory subCategory, int position) {
        ImageView subCategoryImageView = viewHolder.findView(R.id.subCategoryImageView);
        TextView subCategoryNameTextView = viewHolder.findView(R.id.subCategoryNameTextView);
        GlideImageLoader.load(
                context,
                ApiConfiguration.IMAGE_URL + subCategory.getImage(),
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                subCategoryImageView,
                new GlideImageLoadingListener() {
                    @Override
                    public void imageLoadSuccess() {
                    }

                    @Override
                    public void imageLoadError() {
                    }
                });
        subCategoryNameTextView.setText(subCategory.getName());
    }
}
