package com.bidfrail.android.ui.navigationhost.fragment.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bidfrail.android.R;
import com.bidfrail.android.data.remote.ApiConfiguration;
import com.bidfrail.android.data.remote.glide.GlideImageLoader;
import com.bidfrail.android.data.remote.glide.GlideImageLoadingListener;
import com.bidfrail.android.model.OfferBanner;
import com.bidfrail.android.model.ReferEarnBanner;
import com.library.sliderview.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReferAndEarnSliderViewAdapter extends SliderViewAdapter<ReferAndEarnSliderViewAdapter.SliderViewHolder> {

    private Context context;
    private List<ReferEarnBanner> mSliderItems = new ArrayList<>();

    public ReferAndEarnSliderViewAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<ReferEarnBanner> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(ReferEarnBanner sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.refer_and_earn_sliderview_layout, null);
        return new SliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        ReferEarnBanner referEarnBanner = mSliderItems.get(position);
        System.out.println("====Banner===="+referEarnBanner.getImage());
        GlideImageLoader.load(
                context,
                ApiConfiguration.IMAGE_URL + referEarnBanner.getImage(),
                R.drawable.ic_logo,
                R.drawable.ic_logo,
                viewHolder.imageView,
                new GlideImageLoadingListener() {
                    @Override
                    public void imageLoadSuccess() {
                    }

                    @Override
                    public void imageLoadError() {
                    }
                });
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    static class SliderViewHolder extends ViewHolder {
        View itemView;
        ImageView imageView;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            this.itemView = itemView;
        }
    }
}
