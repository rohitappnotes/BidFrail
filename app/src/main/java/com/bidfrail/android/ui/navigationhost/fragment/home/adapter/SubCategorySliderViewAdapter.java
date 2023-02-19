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
import com.bidfrail.android.model.SubCategoryBanner;
import com.library.sliderview.SliderViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class SubCategorySliderViewAdapter extends SliderViewAdapter<SubCategorySliderViewAdapter.SliderViewHolder> {

    private Context context;
    private ArrayList<SubCategoryBanner> mSliderItems = new ArrayList<>();

    public SubCategorySliderViewAdapter(Context context) {
        this.context = context;
    }

    public void renewItems(ArrayList<SubCategoryBanner> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(SubCategoryBanner sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_sliderview_layout, null);
        return new SliderViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
        SubCategoryBanner subCategoryBanner = mSliderItems.get(position);
        GlideImageLoader.load(
                context,
                ApiConfiguration.IMAGE_URL + subCategoryBanner.getImage(),
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

    static class SliderViewHolder extends SubCategorySliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageView;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            this.itemView = itemView;
        }
    }
}
