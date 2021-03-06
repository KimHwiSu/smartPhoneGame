package kr.ac.tukorea.s2019182014.cookierun.game;

import java.util.Random;

import kr.ac.tukorea.s2019182014.cookierun.R;
import kr.ac.tukorea.s2019182014.cookierun.framework.game.interfaces.RecycleBin;
import kr.ac.tukorea.s2019182014.cookierun.framework.res.BitmapPool;

public class Platform extends MapSprite {
    private Type type;

    public boolean canPass() {
        return type != Type.T_10x2;
    }

    public enum Type {
        T_10x2, T_2x2, T_3x1, COUNT;
        float width() {
            int w = 1;
            switch (this) {
                case T_10x2: w = 10; break;
                case T_2x2: w = 2; break;
                case T_3x1: w = 3; break;
            }
            return MainGame.get().size(w);
        }
        float height() {
            int h = 1;
            switch (this) {
                case T_10x2: case T_2x2: h = 2; break;
                case T_3x1: h = 1; break;
            }
            return MainGame.get().size(h);
        }
        int bitmapId() {
            return BITMAP_IDS[this.ordinal()];
        }
        public static Type random(Random random) {
            int index = random.nextInt(COUNT.ordinal());
            return values()[index];
        }
    }
    protected static int[] BITMAP_IDS = {
            R.mipmap.cookierun_platform_480x48,
            R.mipmap.cookierun_platform_124x120,
            R.mipmap.cookierun_platform_120x40,
    };
    public static Platform get(Type type, float unitLeft, float unitTop) {
        Platform platform = (Platform) RecycleBin.get(Platform.class);
        if (platform == null) {
            platform = new Platform();
        }
        platform.init(type, unitLeft, unitTop);
        return platform;
    }

    private Platform() {
    }

    private void init(Type type, float unitLeft, float unitTop) {
        this.type = type;
        bitmap = BitmapPool.get(type.bitmapId());
        MainGame game = MainGame.get();
        float left = game.size(unitLeft);
        float top = game.size(unitTop);
        dstRect.set(left, top, left + type.width(), top + type.height());
    }
}