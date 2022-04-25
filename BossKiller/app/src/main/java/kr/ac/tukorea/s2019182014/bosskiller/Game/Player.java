package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BitmapPool;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.Metrics;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.Sprite;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class Player extends Sprite {
    private static final String TAG = Player.class.getSimpleName();
    private RectF targetRect = new RectF();

    //    private float angle;
    private float dx, dy;
    private float tx, ty;
    private float elapsedTimeForFire;

    private static Bitmap targetBitmap;
//    private static Rect srcRect;

    public Player(float x, float y) {
        super(x, y, R.dimen.player_radius, R.mipmap.Player);
        setTargetPosition(x, y);
        //angle = -(float) (Math.PI / 2);
    }

    public void draw(Canvas canvas) {
//        canvas.save();
//        canvas.rotate((float) (angle * 180 / Math.PI) + 90, x, y);
        canvas.drawBitmap(bitmap, null, dstRect, null);
//        canvas.restore();
        if (dx != 0 || dy != 0) {
            canvas.drawBitmap(targetBitmap, null, targetRect, null);
        }
    }

    public void update() {
        float frameTime = MainGame.getInstance().frameTime;

        // 일정 시간 후 반복 하는 행동
/*        elapsedTimeForFire += frameTime;
        if (elapsedTimeForFire >= fireInterval) {
            elapsedTimeForFire -= fireInterval;
        }
*/
        if (dx == 0)
            return;

        float dx = this.dx * frameTime;
        boolean arrived = false;
        if ((dx > 0 && x + dx > tx) || (dx < 0 && x + dx < tx)) {
            dx = tx - x;
            x = tx;
            arrived = true;
        } else {
            x += dx;
        }
        dstRect.offset(dx, 0);
        if (arrived) {
            this.dx = 0;
        }
    }

    public void setTargetPosition(float tx, float ty) {
        this.tx = tx;
        this.ty = y;
        targetRect.set(tx - radius/2, y - radius/2,
                tx + radius/2, y + radius/2);
//        angle = (float) Math.atan2(ty - y, tx - x);
        dx = Metrics.size(R.dimen.player_speed);
        if (tx < x) {
            dx = -dx;
        }
//        dy = (float) (dist * Math.sin(angle));
    }
}