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