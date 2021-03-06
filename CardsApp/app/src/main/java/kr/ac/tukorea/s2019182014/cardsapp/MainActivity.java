package kr.ac.tukorea.s2019182014.cardsapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int[] BUTTON_IDS = new int[]{
            R.id.card_00, R.id.card_01, R.id.card_02, R.id.card_03,
            R.id.card_10, R.id.card_11, R.id.card_12, R.id.card_13,
            R.id.card_20, R.id.card_21, R.id.card_22, R.id.card_23,
            R.id.card_30, R.id.card_31, R.id.card_32, R.id.card_33
    };

    private int[] resIds = new int[]{
        R.mipmap.card_as, R.mipmap.card_2c, R.mipmap.card_3d, R.mipmap.card_4h,
        R.mipmap.card_5s, R.mipmap.card_jc, R.mipmap.card_qh, R.mipmap.card_kd,
        R.mipmap.card_as, R.mipmap.card_2c, R.mipmap.card_3d, R.mipmap.card_4h,
        R.mipmap.card_5s, R.mipmap.card_jc, R.mipmap.card_qh, R.mipmap.card_kd
    };

    private ImageButton previousImageButton;
    private int flips;
    private TextView scoreTextView;
    private int openCardCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTextView = findViewById(R.id.scoreTextView);
        startGame();

    }

    private void startGame() {

        Random r = new Random();
        for(int i=0; i<resIds.length; i++){
            int t = r.nextInt(resIds.length);
            int id = resIds[t];
            resIds[t] = resIds[i];
            resIds[i] = id;
        }

        openCardCount = resIds.length;
        for(int i=0; i < BUTTON_IDS.length; i++){
            ImageButton btn = findViewById(BUTTON_IDS[i]);
            int resId = resIds[i];
            btn.setImageResource(R.mipmap.card_blue_back);
            btn.setVisibility(View.VISIBLE);
            btn.setTag(resId);
            // ?????? : ???????????? ?????? ?????????, ?????? ??????
        }
        setScore(0);
        previousImageButton = null;
    }

    public void onBtnRestart(View view){
        Log.d(TAG, "Restart");
        askRetry();
    }

    private void askRetry() {
        new AlertDialog.Builder(this)
            .setTitle(R.string.restart)
            .setMessage(R.string.restart_alert_msg)
            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    startGame();
                }
        })
            .setNegativeButton(R.string.no, null)
            .create()
            .show();
    }

    public void onBtnCard(View view) {
        if (!(view instanceof ImageButton)) {
            Log.e(TAG, "Not an ImageButton" + view);
        }
        ImageButton imageButton = (ImageButton) view;
        int cardIndex = findButtonIndex(view.getId());
        Log.d(TAG, "card" + view.getId());

        if (previousImageButton == view) {
            Log.d(TAG, "Same Image button");
            Toast.makeText(this, R.string.same_card, Toast.LENGTH_LONG).show();
            return;
        }

        int resId = (Integer) imageButton.getTag();
        if (previousImageButton != null) {
            int previousResourceId = (Integer) previousImageButton.getTag();
            if (resId == previousResourceId) {
                imageButton.setVisibility(View.INVISIBLE);
                previousImageButton.setVisibility(View.INVISIBLE);
                previousImageButton = null;
                openCardCount -= 2;
                if(openCardCount == 0){
                    askRetry();
                }
            } else {
                imageButton.setImageResource(resId);
                setScore(flips + 1);
                previousImageButton.setImageResource((R.mipmap.card_blue_back));
                previousImageButton = imageButton;
            }
        } else {
            setScore(flips + 1);
            imageButton.setImageResource(resId);
            previousImageButton = imageButton;
        }
    }

    private void setScore(int score) {
        flips = score;
        Resources res = getResources();
        String format =res.getString(R.string.flip_fmt);
        String text = String.format(format, score);
        scoreTextView.setText(text);
    }

    private int findButtonIndex(int id) {
        for (int i=0; i < BUTTON_IDS.length; i++){
            if(id == BUTTON_IDS[i]){
                return i;
            }
        }
        return -1;
    }
}