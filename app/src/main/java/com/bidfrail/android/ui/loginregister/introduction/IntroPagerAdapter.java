package com.bidfrail.android.ui.loginregister.introduction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.bidfrail.android.R;
import java.util.ArrayList;

public class IntroPagerAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Intro> introArrayList;

    public IntroPagerAdapter(Context context, ArrayList<Intro> introArrayList) {
        this.context = context;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.introArrayList = introArrayList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = layoutInflater.inflate(R.layout.intro_row_item, null);

        LinearLayout linearLayout = view.findViewById(R.id.introRowItem);
        ImageView image = view.findViewById(R.id.introImageView);
        TextView title = view.findViewById(R.id.introTitleTextView);
        TextView description = view.findViewById(R.id.introDescTextView);

        linearLayout.setBackgroundResource(introArrayList.get(position).getBackgroundColor());
        image.setImageResource(introArrayList.get(position).getImage());
        title.setText(introArrayList.get(position).getTitle());
        description.setText(introArrayList.get(position).getDescription());

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return introArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}