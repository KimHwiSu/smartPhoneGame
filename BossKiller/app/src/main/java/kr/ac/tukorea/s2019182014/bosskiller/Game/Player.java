package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.os.Debug;
import android.util.Log;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.AnimSprite;
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

    public Player(float x, float y) {
        super(x, y, size, size, R.mipmap.player_idle, FRAMES_PER_SECOND, 4);
        setPosition(x, y);
    }

    @Override
    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        if((GameView.view.moveBtn!=null)&&GameView.view.moveBtn.isPressed()) {
            float dx = this.dx * frameTime;
            x += dx;
            dstRect.offset(dx, 0);
        }
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
        }
        else{
            dx = -300.f;
        }
    }

}