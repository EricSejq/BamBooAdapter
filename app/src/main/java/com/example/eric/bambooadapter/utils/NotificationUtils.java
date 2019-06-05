package com.example.eric.bambooadapter.utils;

import android.app.NotificationManager;
import android.content.Context;

import androidx.core.app.NotificationCompat;


/**
 * Description: notification 工具类
 * Data：2019/5/11-14:20
 *
 * @author: eric
 */
public class NotificationUtils {
    private static NotificationManager mNotificationManager;

    /**
     * channel id
     * 渠道ID声明
     * 笔记
     * 备忘
     * 提醒
     */
    public static final String CHANNEL_NOTE = "channelNote";
    public static final String CHANNEL_MEMO = "channelMemo";
    public static final String CHANNEL_REMIND = "channelRemind";


    /**
     * 需要对android 8.0 版本进行适配
     */
    public static void showNitofication(Context context, String channel) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channel);
    }

    private static NotificationManager getNotificationManager(Context context) {
        if (mNotificationManager == null) {
            mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }
}
