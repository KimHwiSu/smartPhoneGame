package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.AnimSprite;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BoxCollidable;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.Metrics;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class Mob extends AnimSprite implements BoxCollidable {
    private static final int size = 500;
    public static final float FRAME_PER_SECOND = 2.5f;
    private RectF collisionBox = new RectF();
    private String TAG = Mob.class.getSimpleName();
    public String state;
    public float x, y;
    private float dx;
    private boolean isChanged;
    private boolean isRight;
    private Player p;

    public Mob(float x, float y, Player p) {
        super(x, y, size, size, R.mipmap.mob_idle, FRAME_PER_SECOND, 4);
        this.x = x;
        this.y = y;
        this.p = p;
        collisionBox.set(dstRect);
        collisionBox.inset(190f, 100f);
        collisionBox.offset(0.f, 25.f);
    }

    @Override
    public void update() {
        super.update();
        switch(state){
            case "idle":
                Log.d(TAG, String.valueOf(p.x - this.x));
                break;
            case "attack1_ready":
                state = "attack1";
                break;
            case "attack1":
                break;
        }
    }

    @Override
    public RectF getBoundingRect() {
        return collisionBox;
    }

    public void hit() {
        Log.d(TAG, "M Hit");
    }

    public void setDirection(boolean isRight) {
        if (isRight) {
            if (x <= 0) {
                x = 1;
            }
            dx = 300.f;
            if (isChanged) {
                changeDirect();
                changeCollision(isChanged);
                isChanged = false;
            }
        } else {
            if (x >= Metrics.width) {
                x = Metrics.width - 1;
            }
            dx = -300.f;
            if (!isChanged) {
                changeDirect();
                changeCollision(isChanged);
                isChanged = true;
            }
        }

    }

    private void changeCollision(boolean isChanged) {
        if (isRight) {
            collisionBox.offset(-40.f, 0.f);
        } else {
            collisionBox.offset(40.f, 0.f);
        }
    }
}