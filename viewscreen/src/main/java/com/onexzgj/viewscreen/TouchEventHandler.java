package com.onexzgj.viewscreen;

import android.app.Activity;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 触摸事件传递
 */
public class TouchEventHandler {

    public static void dispatchTouchEvent(Activity activity, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();

            ArrayList<View> targetVies = getTargetViews(rootView, event);

            if (targetVies == null) {
                return;
            }
            for (View view : targetVies) {
                if (view == null) {
                    continue;
                }
                SensorsDataPrivate.trackViewOnClick(view);
            }
        }
    }

    private static ArrayList<View> getTargetViews(View parent, MotionEvent event) {
        ArrayList<View> targetViews = new ArrayList<>();
        if (isVisable(parent) && isContainView(parent, event)) {

            if (parent.isClickable()) {
                targetViews.add(parent);
            } else if (parent instanceof ViewGroup) {
                getTargetViewsInGroup((ViewGroup) parent, event, targetViews);
            }

        }
        return targetViews;
    }

    /**
     * 获取需要得到的View
     *
     * @param parent
     * @param event
     * @param hitViews
     */
    private static void getTargetViewsInGroup(ViewGroup parent, MotionEvent event, ArrayList<View> hitViews) {
        try {
            int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                ArrayList<View> hitChildren = getTargetViews(child, event);
                if (!hitChildren.isEmpty()) {
                    hitViews.addAll(hitChildren);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isVisable(View parent) {
        return parent.getVisibility() == View.VISIBLE;
    }

    public static boolean isContainView(View view, MotionEvent event) {
        double x = event.getRawX();
        double y = event.getRawY();
        Rect outRect = new Rect();
        view.getGlobalVisibleRect(outRect);
        return outRect.contains((int) x, (int) y);
    }

}
