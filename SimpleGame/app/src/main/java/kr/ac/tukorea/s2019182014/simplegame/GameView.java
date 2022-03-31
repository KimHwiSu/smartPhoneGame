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
import android.view.View;

/*
커스텀 뷰는 4가지
그냥xml에서 만들기만 할거다 -> 첫번째꺼
...
 */

public class GameView extends View {
    private static final String TAG = GameView.class.getSimpleName();
    private final Handler handler;
    private int ballDx, ballDy;
    private Bitmap soccoerBitmap;
    private Rect srcRect = new Rect();
    private Rect dstRect = new Rect();
    private long lastTimeMillis;
    private int framePerSecond;
    private Paint fpsPaint = new Paint();

    public GameView(Context context, AttributeSet as) {
        super(context, as);
        initView();
        
        handler = new Handler();
        updateGame();
    }

    private void updateGame() {
        long now = System.currentTimeMillis();
        int elapsed = (int) (now - lastTimeMillis);
        framePerSecond = 1000 / elapsed;
        lastTimeMillis = now;
        update();
        invalidate();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateGame();
            }
        }, 30);
    }


    private void initView() {
        Resources res = getResources();
        soccoerBitmap = BitmapFactory.decodeResource(res, R.mipmap.soccer_ball_240);
        srcRect.set(0, 0, soccoerBitmap.getWidth(), soccoerBitmap.getHeight());
        dstRect.set(0, 0, 100, 100);
        
        ballDx = 10; ballDy = 10;
        
        fpsPaint.setColor(Color.BLUE);
        fpsPaint.setTextSize(50);
    }

    @Override
    protected void onDraw(Canvas canvas) {
    //    super.onDraw(canvas);
        canvas.drawBitmap(soccoerBitmap, srcRect, dstRect, null);
        canvas.drawText("" + framePerSecond, 0, 100, fpsPaint);
        //        canvas.drawText(String.valueOf(framePerSecond), 0, 0, fpsPaint);
        Log.d(TAG, "onDraw()");
    }

    private void update() {
        dstRect.offset(ballDx, ballDy);
        if(dstRect.left < 0){
            ballDx = 10;
        }
        else if(dstRect.right > getWidth()){
            ballDx *= -1;
        }

        if(dstRect.top < 0){
            ballDy = 10;
        }
        else if(dstRect.bottom > getHeight()){
            ballDy *= -1;
        }
    }


}
