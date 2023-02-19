package com.bidfrail.android.ui.navigationhost.fragment.search.view;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class FullScreenBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private FrameLayout frameLayout;

    private BottomSheetBehavior<FrameLayout> bottomSheetBehavior;
    private BottomSheetBehavior.BottomSheetCallback bottomSheetCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {
                case BottomSheetBehavior.STATE_DRAGGING:
                    Log.i("BottomSheet", "STATE_DRAGGING");
                    break;
                case BottomSheetBehavior.STATE_SETTLING:
                    Log.i("BottomSheet", "STATE_SETTLING");
                    break;
                case BottomSheetBehavior.STATE_EXPANDED:
                    Log.i("BottomSheet", "STATE_EXPANDED");
                    break;
                case BottomSheetBehavior.STATE_COLLAPSED:
                    Log.i("BottomSheet", "STATE_COLLAPSED");
                    break;
                case BottomSheetBehavior.STATE_HIDDEN:
                    Log.i("BottomSheet", "STATE_HIDDEN");
                    dismiss();
                    break;
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            Log.i("BottomSheet", "slideOffset: " + slideOffset);
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        BottomSheetDialog dialog = (BottomSheetDialog) getDialog();
        assert dialog != null;
    /*    dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    return true;    //Prevent app to dismiss on back pressed
                }
                return false;
            }
        });*/

        frameLayout = dialog.getDelegate().findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (frameLayout != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams.height = getHeight();
            frameLayout.setLayoutParams(layoutParams);
            bottomSheetBehavior = BottomSheetBehavior.from(frameLayout);
            bottomSheetBehavior.setPeekHeight(getHeight());
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            bottomSheetBehavior.addBottomSheetCallback(bottomSheetCallback);
        }
    }

    protected int getHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }
}
