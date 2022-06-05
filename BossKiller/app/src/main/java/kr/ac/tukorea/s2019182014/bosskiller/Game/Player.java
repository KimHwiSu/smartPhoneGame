package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Debug;
import android.util.Log;
import android.view.View;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.AnimSprite;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BitmapPool;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BtnView;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.GameView;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.Metrics;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class Player extends AnimSprite {

    public static final float FRAMES_PER_SECOND = 5.0f;
    public static final int size = 500;
    private static final String TAG = Player.class.getSimpleName();
    private float x, y;
    private float dx, dy;
    private boolean isChanged;
    public boolean move = false;
    public boolean behavior = false;
    public int behaviorFrame = 0;

    public Player(float x, float y) {
        super(x, y, size, size, R.mipmap.player_idle, FRAMES_PER_SECOND, 4);
        setPosition(x, y);
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        if (!behavior) {
            if (move) {
                this.changeBitmap(R.mipmap.player_move);
                changeFrameCount(3);

                if((GameView.view.moveBtn!=null)&&GameView.view.moveBtn.isPressed()) {
                    float dx = this.dx * frameTime;
                    x += dx;
                    dstRect.offset(dx, 0);
                }
            } else {
                this.changeBitmap(R.mipmap.player_idle);
                changeFrameCount(4);
            }

        }
        else{
            Log.d(TAG, String.valueOf(isReverse));
            Log.d(TAG, String.valueOf(returnIdex()));
            if(returnIdex() == (behaviorFrame-1)){
                behavior = false;
                changeBitmap(R.mipmap.player_idle);
                changeFrameCount(4);
            }
        }


    }

    public void attack(){
        changeBitmap(R.mipmap.player_attack);
        behaviorFrame = 4;
        changeFrameCount(behaviorFrame);
    }

    public void jump(){
        changeBitmap(R.mipmap.player_jump);
        behaviorFrame = 5;
        changeFrameCount(behaviorFrame);
    }
    public void roll(){
        changeBitmap(R.mipmap.player_roll);
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
            dx = 300.f;
            if(isChanged){
                changeDirect();
                isChanged = false;
            }
        }
        else{
            dx = -300.f;
            if(!isChanged) {
                changeDirect();
                isChanged = true;
            }
        }
    }

}