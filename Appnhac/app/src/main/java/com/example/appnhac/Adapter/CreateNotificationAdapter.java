package com.example.appnhac.Adapter;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.appnhac.Model.Baihat;
import com.example.appnhac.Model.Tracks;
import com.example.appnhac.R;
import com.example.appnhac.Service.NotificationActionService;

import java.util.ArrayList;
import java.util.List;

public class CreateNotificationAdapter {
    public static final String CHANNEL_ID = "channel1";

    public static final String ACTIONPREVIOUS = "actionprevious";
    public static final String ACTIONPLAY = "actionplay";
    public static final String ACTIONNEXT = "actionnext";

    public static Notification notification;

    public static void createNotification(Context context, Tracks track, int playbutton, int pos, int size){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context,"tag");

            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),track.getImage());

            PendingIntent pendingIntentPrevios;
            int drw_previous;
            if(pos == 0){
                pendingIntentPrevios = null;
                drw_previous = 0;
            }
            else {
                Intent intentPrevious = new Intent(context,NotificationActionService.class).setAction(ACTIONPREVIOUS);
                pendingIntentPrevios = PendingIntent.getBroadcast(context,0,intentPrevious,PendingIntent.FLAG_UPDATE_CURRENT);
                drw_previous = R.drawable.iconpreview;
            }

            Intent intentPlay = new Intent(context, NotificationActionService.class).setAction(ACTIONPLAY);
            PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context,0,intentPlay,PendingIntent.FLAG_UPDATE_CURRENT);

            PendingIntent pendingIntentNext;
            int drw_next;
            if(pos == size){
                pendingIntentNext = null;
                drw_next = 0;
            }
            else {
                Intent intentNext = new Intent(context,NotificationActionService.class).setAction(ACTIONNEXT);
                pendingIntentNext = PendingIntent.getBroadcast(context,0,intentNext,PendingIntent.FLAG_UPDATE_CURRENT);
                drw_next = R.drawable.iconnext;
            }

            notification = new NotificationCompat.Builder(context,CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_baseline_music_note_24)
                    .setContentTitle(track.getTitle())
                    .setContentText(track.getArtist())
                    .setLargeIcon(icon)
                    .setOnlyAlertOnce(true)
                    .setShowWhen(false)
                    .addAction(drw_previous, "Previos",pendingIntentPrevios)
                    .addAction(playbutton,"Play",pendingIntentPlay)
                    .addAction(drw_next,"Next",pendingIntentNext)
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0,1,2)
                        .setMediaSession(mediaSessionCompat.getSessionToken()))
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .build();

            notificationManagerCompat.notify(1,notification);
        }
    }
}
