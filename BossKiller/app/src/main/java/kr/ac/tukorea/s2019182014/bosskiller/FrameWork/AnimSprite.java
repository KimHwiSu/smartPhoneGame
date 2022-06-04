package kr.ac.tukorea.s2019182014.bosskiller.FrameWork;

import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimSprite extends Sprite {
    private final float framesPerSecond;
    private int frameCount;
    private final int imageWidth;
    private final int imageHeight;

    private Rect srcRect = new Rect();
    private long createdOn;

    private int index = 0;
    public AnimSprite(float x, float y, float w, float h, int bitmapResId, float framesPerSecond, int frameCount) {
        super(x, y, w, h, bitmapResId);
        int imageWidth = bitmap.getWidth();
        imageHeight = bitmap.getHeight();
        this.framesPerSecond = framesPerSecond;
        if (frameCount == 0) {
            frameCount = imageWidth / imageHeight;
            imageWidth = imageHeight;
        } else {
            imageWidth = imageWidth / frameCount;
        }
        this.imageWidth = imageWidth;
        this.frameCount = frameCount;

        createdOn = System.currentTimeMillis();
    }

    public void changeFrameCount(int frameCount){
        this.frameCount = frameCount;
    }

    public int returnIdex(){
        return index;
    }
    public void setIndex(){
        createdOn = System.currentTimeMillis();
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        index = Math.round(time * framesPerSecond) % frameCount;
        if(!isReverse) {
            srcRect.set(index * imageWidth, 0, (index + 1) * imageWidth, imageHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
        }
        else{
            index = (frameCount-1) - index;
            srcRect.set(index * imageWidth, 0, (index + 1) * imageWidth, imageHeight);
            canvas.drawBitmap(reverseBitmap, srcRect, dstRect, null);
        }
    }
}