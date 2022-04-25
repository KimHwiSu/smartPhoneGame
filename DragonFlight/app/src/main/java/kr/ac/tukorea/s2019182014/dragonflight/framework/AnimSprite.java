package kr.ac.tukorea.s2019182014.dragonflight.framework;

import android.graphics.Canvas;
import android.graphics.Rect;

import kr.ac.tukorea.s2019182014.dragonflight.game.MainGame;

public class AnimSprite extends Sprite{
    private final float framePerSecond;
    private final int frameCount;

    private final int imageHeight;
    private final int imageWidth;

    private Rect srcRect = new Rect();
//    private float time;
    private long createOn;

    public AnimSprite(float x, float y, float w, float h, int bitmapResId, float framePerSecond, int frameCount){
        super(x, y, w, h, bitmapResId);
        int imageWidth;
        imageWidth = bitmap.getWidth();

        imageHeight = bitmap.getHeight();
        this.framePerSecond = framePerSecond;
        if(frameCount == 0){
            frameCount = imageWidth / imageHeight;
            imageWidth = imageHeight;
        }else{
            imageWidth = imageWidth / frameCount;
        }
        this.imageWidth = imageWidth;
        this.frameCount = frameCount;

        createOn = System.currentTimeMillis();
    }

    @Override
    public void update() {}

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = now - createOn / 1000.0f;
        int index = Math.round(time * framePerSecond) % frameCount;
        srcRect.set(index * imageWidth, 0, (index+1) * imageWidth, imageHeight);

        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }
}
