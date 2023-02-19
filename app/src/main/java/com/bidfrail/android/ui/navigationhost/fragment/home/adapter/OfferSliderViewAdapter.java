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
import com.library.sliderview.SliderViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class OfferSliderViewAdapter extends SliderViewAdapter<OfferSliderViewAdapter.SliderViewHolder> {

    private Context context;
    private List<OfferBanner> mSliderItems = new ArrayList<>();

    public OfferSliderViewAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(List<OfferBanner> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(OfferBanner sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.offer_banner_sliderview_layout, null);
        return new SliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        OfferBanner offerBanner = mSliderItems.get(position);

        GlideImageLoader.load(
                context,
                ApiConfiguration.IMAGE_URL + offerBanner.getImage(),
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
