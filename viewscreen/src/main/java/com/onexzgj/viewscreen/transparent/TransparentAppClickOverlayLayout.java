package com.onexzgj.viewscreen.transparent;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.onexzgj.viewscreen.SensorsDataPrivate;

import java.lang.reflect.Field;

public class TransparentAppClickOverlayLayout extends FrameLayout {
    public TransparentAppClickOverlayLayout(@NonNull Context context) {
        this(context, null);
    }

    public TransparentAppClickOverlayLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TransparentAppClickOverlayLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, 0);
    }

    @TargetApi(15)
    private boolean hasOnClickListeners(View view) {
        return view.hasOnClickListeners();
    }

    private boolean isContainView(View view, MotionEvent event) {
        double x = event.getRawX();
        double y = event.getRawY();
        Rect outRect = new Rect();
        view.getGlobalVisibleRect(outRect);
        return outRect.contains((int) x, (int) y);
    }

    @SuppressWarnings("all")
    private View getTargetView(ViewGroup viewGroup, MotionEvent event) {
        if (viewGroup == null) {
            return null;
        }
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = viewGroup.getChildAt(i);
            if (!view.isShown()) {
                continue;
            }
            if (isContainView(view, event)) {
                if (hasOnClickListeners(view) || view instanceof Button) {
                    return view;
                }
            }

            if (view instanceof ViewGroup) {
                View targetView = getTargetView((ViewGroup) view, event);
                if (null != targetView) {
                    return targetView;
                }
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("all")
    public boolean onTouchEvent(MotionEvent event) {
        try {
            if (event != null) {
                int ac = event.getAction() & MotionEvent.ACTION_MASK;
                if (ac == MotionEvent.ACTION_DOWN) {
                    View view = getTargetView((ViewGroup) getRootView(), event);
                    if (view != null) {
                        if (view instanceof AdapterView) {
                            SensorsDataPrivate.trackViewOnClick(view);
                        } else {
                            SensorsDataPrivate.trackViewOnClick(view);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onTouchEvent(event);
    }
}
