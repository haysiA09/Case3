package com.example.case3;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    int requestCode = 123;
    int notificationID = 888;
    Button btnNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNotify = findViewById(R.id.buttonNoti);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManager notificationManager = (NotificationManager)
                        getSystemService(NOTIFICATION_SERVICE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel channel = new
                            NotificationChannel("default", "Default Channel",
                            NotificationManager.IMPORTANCE_DEFAULT);

                    channel.setDescription("This is for default notification");
                    notificationManager.createNotificationChannel(channel);
                }

                // Assign big picture notification
                NotificationCompat.BigPictureStyle bpStyle = new NotificationCompat.BigPictureStyle();
                bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.SentosaExpressTrainTicket)).build();
                // Set the intent to fire when the user taps on notification.
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                PendingIntent pIntent = PendingIntent.getActivity
                        (MainActivity.this, requestCode, intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);

                // Build notification
                NotificationCompat.Builder builder = new
                        NotificationCompat.Builder(MainActivity.this, "default");
                builder.setContentTitle("Welcome to Sentosa!");
                builder.setContentText("Singapore's premier island getaway");
                builder.setSmallIcon(android.R.drawable.btn_star_big_off);
                builder.setContentIntent(pIntent);
                builder.setAutoCancel(true);
                builder.setStyle(bpStyle);

                Notification n = builder.build();

                // This replaces the existing notification with the same ID
                notificationManager.notify(notificationID, n);
                finish();

            }
        });
    }
}
