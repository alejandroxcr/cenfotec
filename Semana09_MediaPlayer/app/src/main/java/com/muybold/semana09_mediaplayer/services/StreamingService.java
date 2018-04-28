package com.muybold.semana09_mediaplayer.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;

/**
 * Created by Estudiantes on 28/04/2018.
 */

public class StreamingService extends Service implements MediaPlayer.OnPreparedListener {

    private static final String ACTION_PLAY = "com.muybold.action.PLAY";
    private MediaPlayer _mMediaPlayer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Inicializa el streaming de audio
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
            try {

                String url = "http://streamer.teknoweb.co/s967167041/listen";
                _mMediaPlayer = new MediaPlayer();
                _mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                _mMediaPlayer.setDataSource(url);
                _mMediaPlayer.setOnPreparedListener(this);
                _mMediaPlayer.prepareAsync(); // might take long! (for buffering, etc)
                //_mMediaPlayer.start();

            } catch (Exception ex){
                ex.printStackTrace();
            }

        return START_STICKY;
    }


    /**
     * Se llama cuando esta listo
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }
} // StreamingService
