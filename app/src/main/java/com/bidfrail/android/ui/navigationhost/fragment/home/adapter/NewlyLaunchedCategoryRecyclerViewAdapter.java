package com.bidfrail.android.ui.navigationhost.fragment.home.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bidfrail.android.R;
import com.bidfrail.android.model.Category;
import com.library.adapter.recyclerview.adapter.BaseSingleItemAdapter;
import com.library.adapter.recyclerview.adapter.BaseViewHolder;

public class NewlyLaunchedCategoryRecyclerViewAdapter extends BaseSingleItemAdapter<Category, BaseViewHolder> {

    private Context context;

    public NewlyLaunchedCategoryRecyclerViewAdapter(Context context) {
        addChildClickViewIds(R.id.newlyLaunchedCategoryRowItem);
        this.context = context;
    }

    @Override
    protected int getViewHolderLayoutResId() {
        return R.layout.newly_launched_category_row_item;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder viewHolder, Category category, int position) {
        TextView categoryNameTextView = viewHolder.findView(R.id.categoryNameTextView);
        ImageView categoryImageView = viewHolder.findView(R.id.categoryImageView);

       /* GlideImageLoader.load(
                context,
                ApiConfiguration.IMAGE_URL + subCategory.getImage(),
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                categoryImageView,
                new GlideImageLoadingListener() {
                    @Override
                    public void imageLoadSuccess() {
                    }

                    @Override
                    public void imageLoadError() {
                    }
                });*/
        categoryNameTextView.setText(category.getName());
    }
}
