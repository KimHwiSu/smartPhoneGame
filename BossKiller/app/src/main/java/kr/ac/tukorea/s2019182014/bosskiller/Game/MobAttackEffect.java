package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.RectF;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.AnimSprite;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BoxCollidable;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class MobAttackEffect extends AnimSprite implements BoxCollidable {
    private static final int size = 500;
    private static final float FRAME_PER_SECOND = 2.5f;
    public String state;
    private float x, y;
    private RectF collisionBox = new RectF();
    private Mob m;
    private boolean isChanged;


    public MobAttackEffect(float x, float y, Mob m) {
        super(x, y, size, size, R.mipmap.mob_attack1_effect, FRAME_PER_SECOND, 3);
        this.x = x;
        this.y = y;
        this.m = m;
        state = "attack1";
        collisionBox.set(dstRect);
        collisionBox.inset(264, 264);
        collisionBox.offset(85, 22);
    }

    @Override
    public void update() {
        super.update();
        if(m.isReverse){
            setDirection(false);
        }
        else{
            setDirection(true);
        }
        switch (state){
            case "attack1":{
                changeBitmap(R.mipmap.mob_attack1_effect);
                changeFrameCount(3);
                if(!isReverse){
                   this.x = m.x+10.f;
                   dstRect.left = this.x - this.radius;
                   dstRect.right = this.x + this.radius;
                   collisionBox.set(dstRect);
                    collisionBox.inset(200, 234);
                    collisionBox.offset(50, 22);
                }
                else{
                    this.x = m.x-10.f;
                    dstRect.left = this.x - this.radius;
                    dstRect.right = this.x + this.radius;
                    collisionBox.set(dstRect);
                    collisionBox.inset(200, 234);
                    collisionBox.offset(-50, 22);
                }
                break;
            }
        }
    }

    public void setDirection(boolean isRight){
        if(isRight){
            if(isChanged){
                changeDirect();
                isChanged = false;
            }
        }
        else{
            if(!isChanged) {
                changeDirect();
                isChanged = true;
            }
        }
    }

    @Override
    public RectF getBoundingRect() {
        return collisionBox;
    }
}
