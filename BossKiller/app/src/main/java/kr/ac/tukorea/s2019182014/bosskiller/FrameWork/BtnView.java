package kr.ac.tukorea.s2019182014.bosskiller.FrameWork;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import kr.ac.tukorea.s2019182014.bosskiller.Game.MainGame;

public class BtnView extends androidx.appcompat.widget.AppCompatImageButton {
//    public BtnView(Context context) {
//        super(context);
//    }

    public BtnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        switch(action){
            case MotionEvent.ACTION_DOWN:
                this.setPressed(true);
                this.callOnClick();
                break;
            case MotionEvent.ACTION_UP:
                this.setPressed(false);
                break;
        }
        return MainGame.getInstance().onTouchEvent(event, GameView.view.moveBtn, GameView.view.behaviorBtn);
    }
}
