package com.example.developmc.carddemo;

import android.content.Context;
import android.util.TypedValue;

/**配置文件
 * Created by developmc on 17/2/9.
 */

public class CardConfig {
    //同时显示的item数目
    public static int MAX_SHOW_COUNT;
    //每一级scale的变化大小
    public static float SCALE_GAP;
    //每一级translation的变化大小
    public static float TRANS_Y_GAP;
    public static void initConfig(Context context){
        MAX_SHOW_COUNT = 6;
        SCALE_GAP = 0.05f;
        //15dip
        TRANS_Y_GAP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                15,context.getResources().getDisplayMetrics());
    }
}
