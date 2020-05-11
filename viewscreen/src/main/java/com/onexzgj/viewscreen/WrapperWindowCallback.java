package com.onexzgj.viewscreen;

import android.annotation.TargetApi;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;

public class WrapperWindowCallback implements Window.Callback {
    public Activity mActivity;
    public Window.Callback mCallback;

    public WrapperWindowCallback(Activity activity, Window.Callback callback) {
        this.mActivity = activity;
        this.mCallback = callback;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.mCallback.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return this.mCallback.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        TouchEventHandler.dispatchTouchEvent(mActivity, event);
        return this.mCallback.dispatchTouchEvent(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return this.mCallback.dispatchTrackballEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return this.mCallback.dispatchGenericMotionEvent(event);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return this.mCallback.dispatchPopulateAccessibilityEvent(event);
    }

    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        return this.mCallback.onCreatePanelView(featureId);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        return this.mCallback.onCreatePanelMenu(featureId, menu);
    }

    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        return this.mCallback.onPreparePanel(featureId, view, menu);
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        return this.mCallback.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return this.mCallback.onMenuItemSelected(featureId, item);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams attrs) {
        this.mCallback.onWindowAttributesChanged(attrs);
    }

    @Override
    public void onContentChanged() {
        this.mCallback.onContentChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        this.mCallback.onWindowFocusChanged(hasFocus);
    }

    @Override
    public void onAttachedToWindow() {
        this.mCallback.onAttachedToWindow();
    }

    @Override
    public void onDetachedFromWindow() {
        this.mCallback.onDetachedFromWindow();
    }

    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        this.mCallback.onPanelClosed(featureId, menu);
    }

    @Override
    public boolean onSearchRequested() {
        return this.mCallback.onSearchRequested();
    }

    @Override
    @TargetApi(23)
    public boolean onSearchRequested(SearchEvent searchEvent) {
        return this.mCallback.onSearchRequested(searchEvent);
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback mCallback) {
        return this.mCallback.onWindowStartingActionMode(mCallback);
    }

    @Nullable
    @TargetApi(23)
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback mCallback, int type) {
        return this.mCallback.onWindowStartingActionMode(mCallback, type);
    }

    @Override
    public void onActionModeStarted(ActionMode mode) {
        this.mCallback.onActionModeStarted(mode);
    }

    @Override
    public void onActionModeFinished(ActionMode mode) {
        this.mCallback.onActionModeFinished(mode);
    }
}
