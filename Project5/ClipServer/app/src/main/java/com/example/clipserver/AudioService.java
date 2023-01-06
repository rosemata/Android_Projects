package com.example.clipserver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

public class AudioService extends Service {
    private  MediaPlayer mPlayer;
    private static String CHANNEL_ID = "Music player style" ;
    private static final int NOTIFICATION_ID = 1;
    Notification notification;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onCreate() {
        super.onCreate();
        this.createNotificationChannel();



        final Intent notificationIntent = new Intent(getApplicationContext(),
                AudioService.class);

        final PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, PendingIntent.FLAG_IMMUTABLE) ;

        notification =
                new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_media_play)
                        .setOngoing(true).setContentTitle("Music Playing")
                        .setContentText("Click to Access Music Player")
                        .setTicker("Music is playing!")
                        .setFullScreenIntent(pendingIntent, false)
                        .build();

        startForeground(NOTIFICATION_ID, notification);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new AudioServiceImpl();
    }
    // UB 11-12-2018:  Now Oreo wants communication channels...
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        CharSequence name = "Music player notification";
        String description = "The channel for music player notifications";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel ;
            channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

    }

    public class AudioServiceImpl extends MyAIDL.Stub{

        MediaPlayer mPlayer = null;

        @Override
        public void play(String name) throws RemoteException {
            if(mPlayer != null)
                stop();
            mPlayer = MediaPlayer.create(getApplicationContext(),getSong(name));

            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    Intent intent = new Intent("unbind");
                    sendBroadcast(intent);
                }
            });

            mPlayer.start();
        }

        @Override
        public void pause() throws RemoteException {
            mPlayer.pause();
        }

        @Override
        public void stop() throws RemoteException {
            mPlayer.stop();
        }

        @Override
        public void resume() throws RemoteException {
            mPlayer.start();
        }

        @Override
        public void stopService() throws RemoteException {
            stopSelf();
        }

        private int getSong(String req){
            switch (req){
                case "1": return R.raw.song1;
                case "2": return R.raw.song2;
                case "3": return R.raw.song3;
                case "4": return R.raw.song4;
                case "5": return R.raw.song5;
            }
            throw new UnsupportedOperationException("Illegal state");
        }
    }
}

