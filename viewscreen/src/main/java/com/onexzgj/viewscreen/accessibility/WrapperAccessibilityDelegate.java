package com.onexzgj.viewscreen.accessibility;

import android.view.View;
import android.view.accessibility.AccessibilityEvent;

public class WrapperAccessibilityDelegate extends View.AccessibilityDelegate {

    private View.AccessibilityDelegate mDelegate;
    public WrapperAccessibilityDelegate(View.AccessibilityDelegate delegate) {
        this.mDelegate = delegate;
    }

    @Override
    public void sendAccessibilityEvent(View host, int eventType) {
        if (mDelegate!=null){
            mDelegate.sendAccessibilityEvent(host, eventType);
        }
        if (eventType== AccessibilityEvent.TYPE_VIEW_CLICKED){
            AccessibilityDataPrivate.trackViewOnClick(host);
        }


    }
}
