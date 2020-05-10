package com.onexzgj.viewscreen;

import android.util.Log;
import android.view.View;

public class WrapperOnClickListener implements View.OnClickListener {

    private View.OnClickListener mSource;

    public WrapperOnClickListener(View.OnClickListener source) {
        this.mSource = source;
    }

    @Override
    public void onClick(View view) {
        if (mSource !=null){
            mSource.onClick(view);
        }

        Log.d("TAG", "onClick: 插入了Click的埋点代码 ");
        //插入埋点代码
        SensorsDataPrivate.trackViewOnClick(view);
    }
}
