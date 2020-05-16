# OnexzgjMD
android埋点方式总结


window.callback
        -- WindowCallback
        -- WrapperWindowCallback
            --TouchEventHandler
                核心遍历所有的可以是否点击的View,然后再Event.ACTION_UP中集中处理

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

View.AccessibilityDelegate  通过View中的preformClick --然后代理AccessibilityDelegate的sendEvent的事件，及所有的点击事件
都会最后调用sendEvent的事件，然后通过代理sendEvent的方法，达到埋点效果


添加透明层，当触摸事件的时候，检测子View中点击事件的消费，通过找到谁消费了这个事件，间接进行埋点操作