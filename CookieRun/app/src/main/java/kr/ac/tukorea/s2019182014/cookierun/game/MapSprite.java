package kr.ac.tukorea.s2019182014.cookierun.game;

import android.graphics.RectF;

import kr.ac.tukorea.s2019182014.cookierun.framework.game.interfaces.BaseGame;
import kr.ac.tukorea.s2019182014.cookierun.framework.interfaces.BoxCollidable;
import kr.ac.tukorea.s2019182014.cookierun.framework.interfaces.Recycleable;
import kr.ac.tukorea.s2019182014.cookierun.framework.objects.Sprite;

public class MapSprite extends Sprite implements Recycleable, BoxCollidable {
    private static final String TAG = MapSprite.class.getSimpleName();

    protected MapSprite() {
//        Log.d(TAG, "New:" + this);
    }

    @Override
    public void update(float frameTime) {
        float speed = MapLoader.get().speed;
        float dx = speed * frameTime;
        dstRect.offset(dx, 0);
        if (dstRect.right < 0) {
//            Log.d(TAG, "Removing:" + this);
            BaseGame.getInstance().remove(this);
        }
    }

    @Override
    public void finish() {
    }

    @Override
    public RectF getBoundingRect() {
        return dstRect;
    }
}