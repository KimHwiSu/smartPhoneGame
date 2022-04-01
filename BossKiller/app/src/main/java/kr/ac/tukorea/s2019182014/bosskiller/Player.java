package kr.ac.tukorea.s2019182014.bosskiller;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Player {
    private Rect dstRect = new Rect();

    private static Bitmap bitmap;
    private static Rect srcRect = new Rect();

    public Player(){
        dstRect.set(0, 0, 200, 200);

        if(bitmap == null){
            Resources res = GameView.view.getResources();
            bitmap = BitmapFactory.decodeResource(res, R.mipmap.plane_240);
            srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        }
    }
}
