package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

import java.util.ArrayList;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BoxCollidable;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.CollisionHelper;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.GameObject;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.GameView;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.Metrics;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class MainGame {
    private static final String TAG = MainGame.class.getSimpleName();
    private static MainGame singleton;
    private Paint collisionPaint;

    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }
    private static final int BALL_COUNT = 10;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Player player;
    public float frameTime;

    public static void clear() {
        singleton = null;
    }

    public void init() {

        gameObjects.clear();

        gameObjects.add(new EnemyGenerator());

        float fx = Metrics.width / 2;
        float fy = Metrics.height - Metrics.size(R.dimen.fighter_y_offset);
        player = new Player(fx, fy);
        gameObjects.add(player);

        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setColor(Color.RED);
    }

    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                player.setTargetPosition(x, y);
//                if (action == MotionEvent.ACTION_DOWN) {
//                    fighter.fire();
//                }
                return true;
        }
        return false;
    }

    public void draw(Canvas canvas) {
        for (GameObject gobj : gameObjects) {
            gobj.draw(canvas);
            if (gobj instanceof BoxCollidable) {
                RectF box = ((BoxCollidable) gobj).getBoundingRect();
                canvas.drawRect(box, collisionPaint);
            }
        }
    }

    public void update(int elapsedNanos) {
        frameTime = (float) (elapsedNanos / 1_000_000_000f);
        for (GameObject gobj : gameObjects) {
            gobj.update();
        }

        checkCollision();
    }

    private void checkCollision() {
        for (GameObject o1 : gameObjects) {
            if (!(o1 instanceof Player)) {
                continue;
            }
            Player player = (Player) o1;
            boolean removed = false;
            for (GameObject o2 : gameObjects) {
                if (!(o2 instanceof Bullet)) {
                    continue;
                }
                Bullet bullet = (Bullet) o2;
                if (CollisionHelper.collides(player, bullet)) {
                    Log.d(TAG, "Collision !!");
                    remove(bullet);
                    remove(player);
                    removed = true;
                    break;
                }
            }
            if (removed) {
                continue;
            }
            // check enemy vs fighter
        }
    }

    public void add(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                gameObjects.add(gameObject);
            }
        });
    }

    public void remove(GameObject gameObject) {
        GameView.view.post(new Runnable() {
            @Override
            public void run() {
                gameObjects.remove(gameObject);
            }
        });
    }

    public int objectCount() {
        return gameObjects.size();
    }
}