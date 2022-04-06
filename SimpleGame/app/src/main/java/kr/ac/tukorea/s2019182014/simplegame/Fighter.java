package kr.ac.tukorea.s2019182014.simplegame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.TypedValue;

public class Fighter implements GameObject {
    private RectF dstRect = new RectF();

    private float x, y; //현재 중심
    private float dx, dy; // 이번에 얼마나 움직이나
    private float tx, ty; // 어디로 움직이나
    private static Bitmap bitmap;
    private static Rect srcRect = new Rect();

    public Fighter(float x, float y) {
        this.x = x;
        this.y = y;
        this.tx = x;
        this.ty = y;
        float radius = Matrics.size(R.dimen.fighter_radius);
        dstRect.set(x - radius, y - radius, x + radius, y + radius);
        if(bitmap == null){
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    public static void setBitmap(Bitmap bitmap) {
        Fighter.bitmap = bitmap;
        srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    public void update() {

        float angle = (float) Math.atan2(ty - y, tx - x);
        float speed = Matrics.size(R.dimen.fighter_speed);
        float dist = speed * MainGame.getInstance().frameTime;
        dx = (float) (dist * Math.cos(angle));
        dy = (float) (dist * Math.sin(angle));
        if (dx > 0) {
            if (x + dx > tx) {
                x = tx;
                dx = tx - x;
            } else {
                x += dx;
            }
        } else {
            if (x + dx < tx) {
                x = tx;
                dx = tx - x;
            } else {
                x += dx;
            }
        }
        if (dy > 0) {
            if (y + dy > ty) {
                y = ty;
                dy = ty - y;
            } else {
                y += dy;
            }
        } else {
            if (y + dy < ty) {
                y = ty;
                dy = ty - y;
            } else {
                y += dy;
            }
        }
        dstRect.offset(dx, dy);
    }

    public void setTargetPosition(float x, float y) {
        tx = x;
        ty = y;
    }
}
