package com.library.fab.bottomnavigationview.java;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.library.fab.bottomnavigationview.R;

@SuppressLint("RestrictedApi")
public class FabBottomNavigationView extends BottomNavigationView {

    public FabBottomNavigationView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public FabBottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FabBottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private BottomAppBarTopEdgeTreatment bottomAppBarTopEdgeTreatment;
    private MaterialShapeDrawable materialShapeDrawable;

    private float myFabDiameter = 0f;
    private float fabCradleMargin = 0f;
    private float fabCradleRoundedCornerRadius = 0f;
    private float cradleVerticalOffset = 0f;

    private int animDuration = 200;
    private float cornerSize = 0f;
    //private float cornerSize = 30f;

    private ShapeAppearanceModel shapeAppearanceModel;
    private int itemId = 0;
    private Drawable itemIcon;
    private String itemTitle;

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        if (attrs != null) {
            final TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FabBottomNavigationView, 0, 0);

            myFabDiameter = typedArray.getDimension(R.styleable.FabBottomNavigationView_fab_cradle_diameter, 0F);
            fabCradleMargin = typedArray.getDimension(R.styleable.FabBottomNavigationView_fab_cradle_margin, 0F);
            fabCradleRoundedCornerRadius = typedArray.getDimension(R.styleable.FabBottomNavigationView_fab_cradle_rounded_corner_radius, 0F);
            cradleVerticalOffset = typedArray.getDimension(R.styleable.FabBottomNavigationView_fab_cradle_vertical_offset, 0F);

            bottomAppBarTopEdgeTreatment = new BottomAppBarTopEdgeTreatment(fabCradleMargin, fabCradleRoundedCornerRadius, cradleVerticalOffset);
            bottomAppBarTopEdgeTreatment.setFabDiameter(myFabDiameter);

            shapeAppearanceModel = new ShapeAppearanceModel.Builder()
                    .setTopEdge(bottomAppBarTopEdgeTreatment)
                    .setTopLeftCorner(CornerFamily.ROUNDED, cornerSize)
                    .setTopRightCorner(CornerFamily.ROUNDED, cornerSize)
                    .build();

            materialShapeDrawable = new MaterialShapeDrawable(shapeAppearanceModel);
            materialShapeDrawable.setTint(ContextCompat.getColor(context, typedArray.getResourceId(R.styleable.FabBottomNavigationView_background_color, android.R.color.white)));
            materialShapeDrawable.setPaintStyle(Paint.Style.FILL_AND_STROKE);
            setBackground(materialShapeDrawable);
        }
    }

    public boolean showFAB(@NonNull FloatingActionButton fab) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(materialShapeDrawable.getInterpolation(), 1f);
        valueAnimator.setDuration(animDuration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                materialShapeDrawable.setInterpolation((Float) animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                fab.show();
            }
        });
        valueAnimator.start();
        hideItem();
        return true;
    }

    public boolean hideFAB(@NonNull FloatingActionButton fab) {
        fab.hide(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onShown(FloatingActionButton fab) {
                super.onShown(fab);
            }

            @Override
            public void onHidden(FloatingActionButton fab) {
                super.onHidden(fab);
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(materialShapeDrawable.getInterpolation(), 0f);
                valueAnimator.setDuration(animDuration);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        materialShapeDrawable.setInterpolation((Float) animation.getAnimatedValue());
                    }
                });
                valueAnimator.start();
                showItem();
            }
        });
        return true;
    }

    public void setFabItem(int res) {
        itemId = res;
        itemIcon = getMenu().findItem(res).getIcon();
        itemTitle = getMenu().findItem(res).getTitle().toString();
    }

    public void showItem() {
        getMenu().findItem(itemId).setIcon(itemIcon);
        getMenu().findItem(itemId).setTitle(itemTitle);
    }

    public void hideItem() {
        getMenu().findItem(itemId).setIcon(getContext().getDrawable(android.R.color.transparent));
        getMenu().findItem(itemId).setTitle("");
    }
}