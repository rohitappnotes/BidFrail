package com.bidfrail.android.ui.navigationhost.fragment.home.adapter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import com.bidfrail.android.R;
import com.bidfrail.android.data.remote.ApiConfiguration;
import com.bidfrail.android.data.remote.glide.GlideImageLoader;
import com.bidfrail.android.data.remote.glide.GlideImageLoadingListener;
import com.bidfrail.android.model.SubCategory;
import com.google.android.material.card.MaterialCardView;
import com.library.adapter.recyclerview.adapter.BaseSingleItemAdapter;
import com.library.adapter.recyclerview.adapter.BaseViewHolder;

public class TrendingSubCategoryRecyclerViewAdapter extends BaseSingleItemAdapter<SubCategory, BaseViewHolder> {

    private Context context;

    public TrendingSubCategoryRecyclerViewAdapter(Context context) {
        this.context = context;
        addChildClickViewIds(R.id.trendingSubCategoryRowItem);
    }

    @Override
    protected int getViewHolderLayoutResId() {
        return R.layout.trending_sub_category_row_item;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder viewHolder, SubCategory subCategory, int position) {
        TextView subCategoryNameTextView = viewHolder.findView(R.id.subCategoryNameTextView);
       /* ImageView subCategoryImageView = viewHolder.findView(R.id.subCategoryImageView);
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
                });*/
        subCategoryNameTextView.setText(subCategory.getName());

        TextView blinkTextView = viewHolder.findView(R.id.tagTextView);
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(context,
                R.animator.blink_animation);
        //アニメーション対称のオブジェクトを設定
        set.setTarget(blinkTextView);
        set.start();
    }
}
