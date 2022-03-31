package kr.ac.tukorea.s2019182014.simplegame;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Choreographer;
import android.view.View;

/*
커스텀 뷰는 4가지
그냥xml에서 만들기만 할거다 -> 첫번째꺼
...
 */

public class GameView extends View implements Choreographer.FrameCallback{
    private static final String TAG = GameView.class.getSimpleName();
    //private final Handler handler;
    private int ballDx_1, ballDy_1;
    private int ballDx_2, ballDy_2;
    private Bitmap soccoerBitmap;
    private Rect srcRect = new Rect();
    private Rect dstRect_1 = new Rect();
    private Rect dstRect_2 = new Rect();
    private long lastTimeNanos;
    private int framePerSecond;
    private Paint fpsPaint = new Paint();

    public GameView(Context context, AttributeSet as) {
        super(context, as);
        initView();

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
        update();
        invalidate();
//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                updateGame();
//            }
//        }, 30);
        Choreographer.getInstance().postFrameCallback(this);
    }


    private void initView() {
        Resources res = getResources();
        soccoerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        srcRect.set(0, 0, soccoerBitmap.getWidth(), soccoerBitmap.getHeight());
        dstRect_1.set(0, 0, 100, 100);
        dstRect_2.set(0,500, 100, 600);
        ballDx_1 = 10; ballDy_1 = 10;
        ballDx_2 = 5; ballDy_2 = 5;
        
        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    //    super.onDraw(canvas);
        canvas.drawBitmap(soccoerBitmap, srcRect, dstRect_1, null);
        canvas.drawBitmap(soccoerBitmap, srcRect, dstRect_2, null);
        canvas.drawText("" + framePerSecond, 0, 100, fpsPaint);
        //        canvas.drawText(String.valueOf(framePerSecond), 0, 0, fpsPaint);
        Log.d(TAG, "onDraw()");
    }

    private void update() {
        dstRect_1.offset(ballDx_1, ballDy_1);
        if(dstRect_1.left < 0){
            ballDx_1 = -ballDx_1;
        }
        else if(dstRect_1.right > getWidth()){
            ballDx_1 = -ballDx_1;
        }

        if(dstRect_1.top < 0){
            ballDy_1 = -ballDy_1;
        }
        else if(dstRect_1.bottom > getHeight()){
            ballDy_1 = -ballDy_1;
        }

        dstRect_2.offset(ballDx_2, ballDy_2);
        if(dstRect_2.left < 0){
            ballDx_2 = -ballDx_2;
        }
        else if(dstRect_2.right > getWidth()){
            ballDx_2 = -ballDx_2;
        }

        if(dstRect_2.top < 0){
            ballDy_2 = -ballDy_2;
        }
        else if(dstRect_2.bottom > getHeight()){
            ballDy_2 = -ballDy_2;
        }

    }


}
