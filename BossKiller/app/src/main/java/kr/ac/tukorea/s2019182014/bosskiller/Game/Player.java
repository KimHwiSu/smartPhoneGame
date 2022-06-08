package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.AnimSprite;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BitmapPool;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BoxCollidable;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BtnView;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.GameView;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.Metrics;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class Player extends AnimSprite implements BoxCollidable {

    public static final float FRAMES_PER_SECOND = 5.0f;
    public static final int size = 500;
    private static final String TAG = Player.class.getSimpleName();
    public float x, y;
    public float dx, dy;
    private boolean isChanged;
    public boolean move = false;
    public boolean behavior = false;
    public int behaviorFrame = 0;
    private final float jumpPower;
    private final float gravity;
    private float jumpSpeed;
    public String state;
    private RectF collisionBox = new RectF();

    public Player(float x, float y) {
        super(x, y, size, size, R.mipmap.player_idle, FRAMES_PER_SECOND, 4);
        setPosition(x, y);
        dx = 300.f;
        state = "idle";
        collisionBox.set(dstRect);
        collisionBox.inset(220f, 130f);
        collisionBox.offset(-20.f, 55.f);
        jumpPower = Metrics.size(R.dimen.player_jump_power);
        gravity = Metrics.size(R.dimen.player_gravity);
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;


        if(state == "hit"){
            behavior = false;
            move = false;
            end_beh();
        }
        else {
            if (!behavior) {
                if (move) {
                    this.changeBitmap(R.mipmap.player_move);
                    changeFrameCount(3);

                    if ((GameView.view.moveBtn != null) && GameView.view.moveBtn.isPressed()) {
                        if((x <= Metrics.width)&&(x >=0 )) {
                            float dx = this.dx * frameTime;
                            x += dx;
                            collisionBox.offset(dx, 0);
                            dstRect.offset(dx, 0);
                        }
                    }
                } else {
                    this.changeBitmap(R.mipmap.player_idle);
                    changeFrameCount(4);
                }

            } else {
                float dx;
                float dy;
                switch (state) {
                    case "attack":
                        end_beh();
                        break;
                    case "roll":
                        if((x >=0)&&(x<=Metrics.width)) {
                            dx = this.dx * frameTime;
                            x += dx;
                            collisionBox.offset(dx, 0);
                            dstRect.offset(dx, 0);
                        }
                        end_beh();
                        break;
                    case "jump_ready":
                        if (returnIdex() == 2) {
                            state = "jump";
                            changeBitmap(R.mipmap.player_fall);
                            changeFrameCount(1);
                        }
                        break;
                    case "jump":
                        if (move) {
                            dx = this.dx * frameTime;
                            x += dx;
                            collisionBox.offset(dx, 0);
                            dstRect.offset(dx, 0);
                        }
                        dy = jumpSpeed * frameTime;
                        jumpSpeed += gravity * frameTime;
                        if (jumpSpeed >= 0) {
                            if (y >= (Metrics.height - Metrics.size(R.dimen.player_y_offset))) {
                                dy = (Metrics.height - Metrics.size(R.dimen.player_y_offset)) - y;
                                changeBitmap(R.mipmap.player_land);
                                changeFrameCount(1);
                                state = "land";
                            }
                        }
                        y += dy;
                        collisionBox.offset(0, dy);
                        dstRect.offset(0, dy);
                        break;
                    case "land":
                        state = "idle";
                        behavior = false;
                }
            }
        }
    }

    public void end_beh(){
        if(!isReverse) {
            if (returnIdex() == (behaviorFrame - 1)) {
                behavior = false;
                changeBitmap(R.mipmap.player_idle);
                changeFrameCount(4);
                state = "idle";
            }
        }
        else{
            if (returnIdex() == 0) {
                behavior = false;
                changeBitmap(R.mipmap.player_idle);
                changeFrameCount(4);
                state = "idle";
            }
        }

    }

    public void attack(){
        state = "attack";
        changeBitmap(R.mipmap.player_attack);
        behaviorFrame = 4;
        changeFrameCount(behaviorFrame);
    }

    public void jump(){
        state = "jump_ready";
        jumpSpeed = -jumpPower;
        changeBitmap(R.mipmap.player_jump_ready);
        behaviorFrame = 3;
        changeFrameCount(behaviorFrame);
    }
    public void roll(){
        state = "roll";
        changeBitmap(R.mipmap.player_roll);
        behaviorFrame = 5;
        changeFrameCount(behaviorFrame);
    }


    public void hit(){
        state = "hit";
        changeBitmap(R.mipmap.player_hit);
        behaviorFrame = 5;
        changeFrameCount(behaviorFrame);
    }



    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }


    public void setPosition(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setDirection(boolean isRight){
        if(isRight){
            if(x <= 0){
                x = 1;
            }
            dx = 300.f;
            if(isChanged){
                changeDirect();
                changeCollision(isChanged);
                isChanged = false;
            }
        }
        else{
            if(x >= Metrics.width){
                x = Metrics.width - 1;
            }
            dx = -300.f;
            if(!isChanged) {
                changeDirect();
                changeCollision(isChanged);
                isChanged = true;
            }
        }
    }

    private void changeCollision(boolean isRight) {
        if(isRight){
            collisionBox.offset(-40.f, 0.f);
        }
            else{
            collisionBox.offset(40.f, 0.f);
        }
    }

    @Override
    public RectF getBoundingRect() {
        return collisionBox;
    }

}