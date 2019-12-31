package com.cham.helx.mvp.ui;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.cham.helx.R;
import com.cham.helx.base.BaseActivity;
import com.elvishew.xlog.XLog;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Hello World
 * Date: 2019/10/7
 * Author: Cham
 */
public class VideoPlayActivity extends BaseActivity {
    @BindView(R.id.detail_player)
    StandardGSYVideoPlayer videoPlayer;
    private OrientationUtils orientationUtils;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_video;
    }

    @Override
    public void initData() {

        XLog.e(AdaptScreenUtils.pt2Px(1080));

        String source1 = "http://baobab.kaiyanapp.com/api/v1/playUrl?vid=161800&resourceType=video&editionType=default&source=aliyun&playUrlType=url_oss";
        videoPlayer.setUp(source1, true, "");
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.splashnice);
        videoPlayer.setThumbImageView(imageView);
        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, videoPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);

        videoPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
    }







    @Override
    public void onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

}
