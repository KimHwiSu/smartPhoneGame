package kr.ac.tukorea.s2019182014.bosskiller.FrameWork;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;

import kr.ac.tukorea.s2019182014.bosskiller.R;

public class Sprite implements GameObject {
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected Matrix matrix = new Matrix();

    protected Bitmap reverseBitmap;


    protected float x, y, radius;
    public boolean isReverse = false;

    public Sprite(float x, float y, int radiusDimenResId, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = Metrics.size(radiusDimenResId);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        bitmap = BitmapPool.get(bitmapResId);

        matrix.preScale(-1.0f, 1.0f); // 좌우 반전 (1.0f, -1.0f)일경우 상하반전
        reverseBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

    }

    public Sprite(float x, float y, float w, float h, int bitmapResId) {
        this.x = x;
        this.y = y;
        this.radius = w / 2;
        dstRect.set(x - w / 2, y - h / 2, x + w / 2, y + h / 2);
        bitmap = BitmapPool.get(bitmapResId);
        matrix.preScale(-1.0f, 1.0f); // 좌우 반전 (1.0f, -1.0f)일경우 상하반전
        reverseBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

    }

    public void setDstRectWithRadius() {
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
     }

    public void setDstRect(float width, float height) {
        dstRect.set(x - width / 2, y - height / 2, x + width / 2, y + height / 2);
    }

    public void changeBitmap(int bitmapId){
        Bitmap bit = BitmapPool.get(bitmapId);
        bitmap = bit;
        reverseBitmap = reverseBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);

    }

    public void changeDirect(){
        if(isReverse){
            isReverse = false;
        }
        else{
            isReverse = true;
        }
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        if(!isReverse) {
            canvas.drawBitmap(bitmap, null, dstRect, null);
        }
        else{
            canvas.drawBitmap(reverseBitmap, null, dstRect, null);
        }
    }
}