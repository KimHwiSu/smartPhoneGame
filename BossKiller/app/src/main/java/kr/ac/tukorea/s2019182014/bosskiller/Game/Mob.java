package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.AnimSprite;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BoxCollidable;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.Metrics;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class Mob extends AnimSprite implements BoxCollidable {
    private static final int size = 500;
    public static final float FRAME_PER_SECOND = 2.5f;
    private RectF collisionBox = new RectF();
    private MobAttackEffect m;

    private String TAG = Mob.class.getSimpleName();
    public String state;
    public float x, y;
    private float dx;
    private boolean isChanged;
    private boolean isRight;
    private Player p;
    private MobAttackEffect attackEffect;

    public Mob(float x, float y, Player p) {
        super(x, y, size, size, R.mipmap.mob_idle, FRAME_PER_SECOND, 4);
        this.x = x;
        this.y = y;
        this.p = p;
        this.m = m;
        state = "idle";
        collisionBox.set(dstRect);
        collisionBox.inset(190f, 100f);
        collisionBox.offset(0.f, 25.f);
    }

    @Override
    public void update() {
        super.update();
        if(isRight){
            collisionBox.left = x - 40.f;
            collisionBox.right = x + 60.f;
        }
        else{
            collisionBox.left = x - 60.f;
            collisionBox.right = x + 40.f;
        }
        switch(state){
            case "idle":
                if((p.x-this.x) <= 0 ){
                    setDirection(false);
                    isRight = false;
                }
                else {
                    setDirection(true);
                    isRight = true;
                }
                float dis = (p.x-this.x)*(p.x-this.x);
                if(dis <= 10000){
                    state = "attack1_ready";
                    changeBitmap(R.mipmap.mob_attack1_ready);
                    changeFrameCount(2);
                }
                break;
            case "attack1_ready":
                if (returnIdex() == 1) {
                    state = "attack1";
                    changeBitmap(R.mipmap.mob_attack1);
                    changeFrameCount(3);
                }
                break;
            case "attack1":
                if (returnIdex() == 2) {
                    state = "idle";
                    changeBitmap(R.mipmap.mob_idle);
                    changeFrameCount(4);
                }
                break;
        }
    }

    @Override
    public RectF getBoundingRect() {
        return collisionBox;
    }

    public void hit() {
        Log.d(TAG, "Hit");
    }

    public void setDirection(boolean isRight) {
        if (isRight) {
            if (x <= 0) {
                x = 1;
            }
            dx = 300.f;
            if (isChanged) {
                changeDirect();
                isChanged = false;
            }
        } else {
            if (x >= Metrics.width) {
                x = Metrics.width - 1;
            }
            dx = -300.f;
            if (!isChanged) {
                changeDirect();
                isChanged = true;
            }
        }
    }
}