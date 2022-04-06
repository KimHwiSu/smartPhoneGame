package kr.ac.tukorea.s2019182014.simplegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Choreographer;
import android.view.MotionEvent;
import android.view.View;

/*
커스텀 뷰는 4가지
그냥xml에서 만들기만 할거다 -> 첫번째꺼
...
 */

public class GameView extends View implements Choreographer.FrameCallback{
    public static GameView view;
    private static final String TAG = GameView.class.getSimpleName();
    private long lastTimeNanos;
    private int framePerSecond;
    private Paint fpsPaint = new Paint();
    private boolean initialized;


    public GameView(Context context, AttributeSet as) {
        super(context, as);
        view = this;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Matrics.width = w;
        Matrics.height = h;
        if(!initialized)
        initView();
        initialized = true;
        Choreographer.getInstance().postFrameCallback(this);
    }

    @Override
    public void doFrame(long currentTimeNanos) {

        long now = currentTimeNanos;
        int elapsed = (int) (now - lastTimeNanos);
        if(elapsed == 0){
            Choreographer.getInstance().postFrameCallback(this);
            return;
        }
        framePerSecond = 1_000_000_000 / elapsed;
        lastTimeNanos = now;
        MainGame game = MainGame.getInstance();
        game.update(elapsed);
        invalidate();
        Choreographer.getInstance().postFrameCallback(this);
    }


    private void initView() {
        MainGame.getInstance().init();
        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(50);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return MainGame.getInstance().getTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        MainGame.getInstance().draw(canvas);

        canvas.drawText("FS:"+String.valueOf(framePerSecond), framePerSecond*10, 100, fpsPaint);
    }
}
