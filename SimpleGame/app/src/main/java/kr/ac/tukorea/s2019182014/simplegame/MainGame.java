package kr.ac.tukorea.s2019182014.simplegame;

import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Random;

public class MainGame {
    private static MainGame singleton;
    public static MainGame getInstance(){
        if(singleton == null){
            singleton = new MainGame();
        }
        return singleton;
    }
    private static final int BALL_COUNT = 10;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Fighter fighter;
    public float frameTime;

    public void init() {
        Random random = new Random();
        for(int i = 0; i < BALL_COUNT; i++){
            int dx = random.nextInt(10) + 5;
            int dy = random.nextInt(10) + 5;
            Ball ball = new Ball(dx, dy);
            gameObjects.add(ball);
        }

        float fx = Matrics.width / 2;
        float fy = Matrics.height / 2;
        fighter = new Fighter(fx, fy);
        gameObjects.add(fighter);
    }

    public boolean getTouchEvent(MotionEvent event) {
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                fighter.setTargetPosition(x, y);
                return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        for(GameObject gobj : gameObjects){
            gobj.draw(canvas);
        }
        fighter.draw(canvas);
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        for(GameObject gobj : gameObjects){
            gobj.update();
        }
        fighter.update();
    }
}
