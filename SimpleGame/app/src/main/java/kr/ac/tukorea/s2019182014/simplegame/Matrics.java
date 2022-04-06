package kr.ac.tukorea.s2019182014.simplegame;

import android.content.res.Resources;

public class Matrics {
    public static int width;
    public static int height;

    public static float size(int dimenResId){
        Resources res = GameView.view.getResources();
        float size = res.getDimension(dimenResId);
        return size;
    }
}
