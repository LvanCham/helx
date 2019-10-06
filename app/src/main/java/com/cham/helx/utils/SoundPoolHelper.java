package com.cham.helx.utils;

import android.app.Activity;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;


import com.cham.helx.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello World
 * Date: 2019/8/13
 * Author: Cham
 */
public class SoundPoolHelper {
    private static  String TAG ="SoundPoolHelper";
    private Context mContext;
    private SoundPoolHelper(Context context){
        mContext =context;
        init();
    }
   /**
    * 单例模式
    **/
   private static SoundPoolHelper instance = null;
   /**
    * 公有的静态函数，对外暴露获取单例对象的接口
    **/
   public static SoundPoolHelper getInstance(Context context) {
       if (instance == null) {
           synchronized (SoundPoolHelper.class) {
               if (instance == null) {
                   instance = new SoundPoolHelper(context);

               }
           }
       }
       return instance;
   }
    private SoundPool mainSoundPool;
    private AudioManager mainAudioManager;
    private float volume;

    private static final int MAX_STREAMS = 10;
    // Stream type.
    private static final int streamType = AudioManager.STREAM_MUSIC;
    private  int streamId ;


    private List<Integer> streamIdS ;


    //init settings
    private void init(){
        streamIdS = new ArrayList<>();
        // AudioManager audio settings for adjusting the volume
        mainAudioManager = (AudioManager)this.mContext. getSystemService(Context.AUDIO_SERVICE);
        // Current volumn Index of particular stream type.
        assert mainAudioManager != null;
        float currentVolumeIndex = (float) mainAudioManager.getStreamVolume(streamType);
        // Get the maximum volume index for a particular stream type.
        float maxVolumeIndex  = (float) mainAudioManager.getStreamMaxVolume(streamType);
        // Volumn (0 --> 1)
        this.volume = currentVolumeIndex / maxVolumeIndex;
        // Suggests an audio stream whose volume should be changed by
        // the hardware volume controls.
        ((Activity)this.mContext).setVolumeControlStream(streamType);
        // For Android SDK >= 21
        if (Build.VERSION.SDK_INT >= 21 ) {
            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);
            this.mainSoundPool = builder.build();

        }

        streamIdS.add( this. mainSoundPool.load(mContext, R.raw.piano_do, 1)) ;
        streamIdS.add(  this. mainSoundPool.load(mContext, R.raw.piano_re, 1)) ;
        streamIdS.add(  this. mainSoundPool.load(mContext, R.raw.piano_mi, 1)) ;
        streamIdS.add(  this. mainSoundPool.load(mContext, R.raw.piano_fa, 1)) ;
        streamIdS.add(  this. mainSoundPool.load(mContext, R.raw.piano_so, 1)) ;
        streamIdS.add(  this. mainSoundPool.load(mContext, R.raw.piano_la, 1)) ;
        streamIdS.add(  this. mainSoundPool.load(mContext, R.raw.piano_si, 1)) ;


    }

    public  void stoplop(){
        mainSoundPool.autoPause();
    }

    //play the sound res
    public void playSound( int soundId){
        float leftVolumn = volume;
        float rightVolumn = volume;
       streamId = this.mainSoundPool.play(streamIdS.get(soundId),leftVolumn, rightVolumn, 1, 0, 1f);

    }



    public void release() {
        if (mainSoundPool!=null)
            mainSoundPool.release();
    }


}
