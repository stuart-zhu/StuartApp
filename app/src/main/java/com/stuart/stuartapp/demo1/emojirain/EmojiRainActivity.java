package com.stuart.stuartapp.demo1.emojirain;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;

import com.stuart.stuartapp.BaseActivity;
import com.stuart.stuartapp.R;
import com.stuart.stuartapp.widget.EmojiRainLayout;

/**
 * Created by stuart on 2018/1/11.
 */

public class EmojiRainActivity extends BaseActivity{

    private EmojiRainLayout erl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emojirain);
        erl = (EmojiRainLayout) findViewById(R.id.group_emoji_container);

        erl.addEmoji(R.drawable.emoji_1_3);
        erl.addEmoji(R.drawable.emoji_2_3);
        erl.addEmoji(R.drawable.emoji_3_3);
        erl.addEmoji(R.drawable.emoji_4_3);
        erl.addEmoji(R.drawable.emoji_5_3);


        // set emojis per flow, default 6
        erl.setPer(10);

        // set total duration in milliseconds, default 8000
        erl.setDuration(7000);

        // set average drop duration in milliseconds, default 2400
        erl.setDropDuration(2400);

        // set drop frequency in milliseconds, default 500
        erl.setDropFrequency(500);
        mHandler.sendEmptyMessageDelayed(0, 10);
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            erl.startDropping();
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        erl.stopDropping();
    }


    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
    }
}
