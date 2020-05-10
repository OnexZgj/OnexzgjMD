package com.onexzgj.onexzgjmd;

import android.view.View;

public class TextClass extends View.AccessibilityDelegate {
    @Override
    public void sendAccessibilityEvent(View host, int eventType) {
        super.sendAccessibilityEvent(host, eventType);
    }
}
