package kr.ac.tukorea.s2019182014.bosskiller.Game;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.AnimSprite;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BoxCollidable;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class SwordEnergy extends AnimSprite implements BoxCollidable {
    private static final int size = 500;
    private static final float FRAME_PER_SECOND = 5.f;
    private RectF collisionBox = new RectF();
    private Player p;
    private boolean isChanged;

    public SwordEnergy(Player p) {
        super(p.x, p.y, size, size, R.mipmap.attack_effect, FRAME_PER_SECOND, 5);
        this.p = p;
        collisionBox.set(dstRect);
    }
    @Override
    public void update() {
        super.update();
        if(!isReverse) {
            this.x = p.x + 75.f;
            dstRect.left = this.x - this.radius;
            dstRect.right = this.x + this.radius;
            collisionBox.set(dstRect);
            collisionBox.inset(200, 131);
            collisionBox.offset(-30, 32);
        }
        else {
            this.x = p.x - 75.f;
            dstRect.left = this.x - this.radius;
            dstRect.right = this.x + this.radius;
            collisionBox.set(dstRect);
            collisionBox.inset(200, 131);
            collisionBox.offset(30, 32);
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
