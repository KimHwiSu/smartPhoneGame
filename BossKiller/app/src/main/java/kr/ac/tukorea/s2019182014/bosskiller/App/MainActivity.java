package kr.ac.tukorea.s2019182014.bosskiller.App;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.GameView;
import kr.ac.tukorea.s2019182014.bosskiller.Game.MainGame;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onPause() {
        GameView.view.pauseGame();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GameView.view.resumeGame();
    }

    @Override
    protected void onDestroy() {
        GameView.view = null;
        MainGame.clear();
        super.onDestroy();
    }

    public void leftBtn(View view){
        GameView.view.moveBtn = findViewById(R.id.leftBtn);
    }
    public void rightBtn(View view){
        GameView.view.moveBtn = findViewById(R.id.rightBtn);
    }
    public void attackBtn(View view){
        GameView.view.behaviorBtn = findViewById(R.id.attackBtn);
    }
    public void rollBtn(View view){
        GameView.view.behaviorBtn = findViewById(R.id.rollBtn);
    }
    public void jumpBtn(View view){
        GameView.view.behaviorBtn = findViewById(R.id.jumpBtn);
    }
}