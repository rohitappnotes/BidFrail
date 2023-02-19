package com.bidfrail.android.ui.navigationhost.fragment.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.bidfrail.android.R;
import com.bidfrail.android.model.CategoryWithSubCategory;
import com.google.android.material.card.MaterialCardView;
import com.library.adapter.recyclerview.adapter.BaseSingleItemAdapter;
import com.library.adapter.recyclerview.adapter.BaseViewHolder;

public class CategoryRecyclerViewAdapter extends BaseSingleItemAdapter<CategoryWithSubCategory, BaseViewHolder> {

    private Context context;

    public CategoryRecyclerViewAdapter(Context context) {
        addChildClickViewIds(R.id.categoryRowItem);
        this.context = context;
    }

    @Override
    protected int getViewHolderLayoutResId() {
        return R.layout.category_row_item;
    }

    @Override
    protected void convert(@NonNull BaseViewHolder viewHolder, CategoryWithSubCategory categoryWithSubCategory, int position) {
        MaterialCardView  categoryRowItem = viewHolder.findView(R.id.categoryRowItem);
        TextView categoryNameTextView = viewHolder.findView(R.id.categoryNameTextView);
        categoryNameTextView.setText(categoryWithSubCategory.getName());

        System.out.println("==========="+categoryWithSubCategory.isClick());

        if (categoryWithSubCategory.isClick())
        {
            categoryNameTextView.setTextColor(context.getResources().getColor(R.color.white));
            categoryRowItem.setCardBackgroundColor(context.getResources().getColor(R.color.black));
        }
        else
        {
            categoryNameTextView.setTextColor(context.getResources().getColor(R.color.black));
            categoryRowItem.setCardBackgroundColor(Color.parseColor("#E9E9E9"));
        }
    }
}
