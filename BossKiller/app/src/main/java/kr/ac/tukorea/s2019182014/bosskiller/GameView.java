package kr.ac.tukorea.s2019182014.bosskiller;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.View;

import androidx.annotation.Nullable;

public class GameView extends View implements Choreographer.FrameCallback {
    private static GameView view;

    private Player player;

    private long lastTimeNanos;
    private int framePerSecond;

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        view = this;

        initView();
        Choreographer.getInstance().postFrameCallback(this);
    }

    private void initView() {
        player = new Player();
    }


    @Override
    public void doFrame(long currentTimeNanos) {
        long now = currentTimeNanos;
        int elapsed = (int) (now - lastTimeNanos);
        if(elapsed == 0){
            Choreographer.getInstance().postFrameCallback(this);
            return;
        }
        update();
        invalidate();
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {

    }

    private void update(){

    }
}
