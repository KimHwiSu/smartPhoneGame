package kr.ac.tukorea.s2019182014.bosskiller.Game;

import android.graphics.RectF;

import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.AnimSprite;
import kr.ac.tukorea.s2019182014.bosskiller.FrameWork.BoxCollidable;
import kr.ac.tukorea.s2019182014.bosskiller.R;

public class MobAttackEffect extends AnimSprite implements BoxCollidable {
    private static final int size = 500;
    private RectF collisionBox = new RectF();


    public MobAttackEffect(Mob m) {
        super(m.x, m.y, size, size, R.mipmap.attack_effect, m.FRAME_PER_SECOND, 3);
    }

    @Override
    public RectF getBoundingRect() {
        return collisionBox;
    }
}
