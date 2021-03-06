package co.ghola.pushyme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

public class PushReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String notificationTitle = "MQTT Push Notification";
        String notificationDesc = "It works!";

        if ( intent.getStringExtra("message") != null )
        {
            notificationDesc = intent.getStringExtra("message");
        }

        int notificationID = 100;
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(
                context);
        nBuilder.setContentTitle(notificationTitle);
        nBuilder.setContentText(notificationDesc);
        nBuilder.setSmallIcon(R.drawable.icon_alpha);
        nBuilder.setLargeIcon(largeIcon); //setLargeIcon(R.drawable.ic_launcher);
        intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1234, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        nBuilder.setContentIntent(pendingIntent);
        nBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notificationID, nBuilder.build());

    }
}