package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.security.Key;
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
    private MobAttackEffect mobAE;

    public static MainGame getInstance() {
        if (singleton == null) {
            singleton = new MainGame();
        }
        return singleton;
    }
    private static final int BALL_COUNT = 10;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Player player;
    private Mob mob;
    private SwordEnergy attackEffect;
    public float frameTime;
    protected boolean showsBoxCollidables;

    public static void clear() {
        singleton = null;
    }

    public void init() {
        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setColor(Color.RED);

        gameObjects.clear();

        //gameObjects.add(new EnemyGenerator());

        float fx = Metrics.width / 2;
        float fy = Metrics.height - Metrics.size(R.dimen.player_y_offset);
        player = new Player(fx, fy);
        mob = new Mob(Metrics.width - 800, fy, player);
        mobAE = new MobAttackEffect(fx, fy, mob);
        attackEffect = new SwordEnergy(player);

        gameObjects.add(mobAE);
        gameObjects.add(player);
        gameObjects.add(mob);
        gameObjects.add(attackEffect);

        collisionPaint = new Paint();
        collisionPaint.setStyle(Paint.Style.STROKE);
        collisionPaint.setColor(Color.RED);
    }

    public boolean onTouchEvent(MotionEvent event, View moveButton, View behaviorBtn) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
            {
                if((moveButton!=null)&&moveButton.isPressed()) {
                    player.move = true;
                    switch (moveButton.getId()) {
                        case R.id.leftBtn:
                            attackEffect.setDirection(false);
                            player.setDirection(false);
                            player.setPosition(player.getX(), player.getY());
                            break;
                        case R.id.rightBtn:
                            attackEffect.setDirection(true);
                            player.setDirection(true);
                            player.setPosition(player.getX(), player.getY());
                            break;
                    }

                }
                if((behaviorBtn!=null)&&behaviorBtn.isPressed()){
                    if(player.behavior){
                        break;
                    }
                    player.setIndex();
                    if(player.move){
                        if((player.state == "roll")||(player.state == "attack")) {
                            player.move = false;
                        }
                    }
                    player.behavior = true;
                    switch (behaviorBtn.getId()){
                        case R.id.attackBtn:
                            attackEffect.setIndex();
                            player.attack();
                            break;
                        case R.id.jumpBtn:
                            player.jump();
                            break;
                        case R.id.rollBtn:
                            player.roll();
                            break;
                    }
                }
                return true;
            }
            case MotionEvent.ACTION_UP: {
                if((moveButton == null) ||(!moveButton.isPressed())) {
                    if (player.move) {
                        player.move = false;
                    }
                }
            }
            return true;
        }


        return false;
    }


    public void draw(Canvas canvas) {
        for (GameObject gobj : gameObjects) {
            if (gobj instanceof BoxCollidable) {
                if((!(gobj instanceof SwordEnergy))&&(!(gobj instanceof MobAttackEffect))) {
                    gobj.draw(canvas);
                }
                else if((gobj instanceof MobAttackEffect)){
                    if(mob.state == "attack1"){
                        gobj.draw(canvas);
                    }
                }
                else{
                    if(player.state == "attack"){
                        gobj.draw(canvas);
                    }
                }
            }
        }
        if (showsBoxCollidables) {
            drawBoxCollidables(canvas);
        }
    }

    public void drawBoxCollidables(Canvas canvas) {
        for (GameObject gobj : gameObjects) {
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
            //boolean removed = false;
            for (GameObject o2 : gameObjects) {
                if (!(o2 instanceof MobAttackEffect)) {
                    continue;
                }
                MobAttackEffect mobAE = (MobAttackEffect) o2;
                if (CollisionHelper.collides(player, mobAE)) {
                    if((player.state != "roll")&&(player.state != "hit")&&(mob.state=="attack1")) {
                        player.setIndex();
                        player.hit();
//                    remove(bullet);
//                    remove(player);
//                    removed = true;
                        break;
                    }
                }

            }
            for(GameObject o3 : gameObjects){
                if (!(o3 instanceof SwordEnergy)){
                    continue;
                }
                SwordEnergy ae = (SwordEnergy) o3;

                for(GameObject o4 : gameObjects){
                    if(!(o4 instanceof Mob)){
                        continue;
                    }
                    Mob mob = (Mob) o4;
                    if (CollisionHelper.collides(ae, mob)) {
                        if(player.state == "attack") {
                            mob.hit();
                        }
//                    remove(bullet);
//                    remove(player);
//                    removed = true;
                            break;
                    }
                }
            }
        }
//            if (removed) {
//                continue;
//            }
            // check enemy vs fighter
    }

//
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
//
//    public int objectCount() {
//        return gameObjects.size();
//    }
}