package kr.ac.tukorea.s2019182014.imageswitcher;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

    protected static final int[] RES_IDS = new int[] {
            R.mipmap.dear,
            R.mipmap.dog,
            R.mipmap.fox
    };
    int page;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        pref = getSharedPreferences("pref", MainActivity.MODE_PRIVATE);
        editor = pref.edit();

        setPage(pref.getInt("savedPage", 1));
    }


    public void onBtnPrev(View view){
        Log.d(TAG, "Prev Button Press");
        setPage(page-1);

        findViewById(R.id.nextButton).setEnabled(true);
    }


    public void onBtnNext(View view){
        Log.d(TAG, "Next Button Press");
        setPage(page+1);

        findViewById(R.id.prevButton).setEnabled(true);

    }

    private void setPage(int newPage) {
        if (newPage < 1 || newPage > RES_IDS.length){
            return;
        }
        page = newPage;
        String text = page + " / " + RES_IDS.length;
        TextView tv = findViewById(R.id.pageText);
        tv.setText(text);

        ImageView iv =findViewById(R.id.contentImageView);
        iv.setImageResource(RES_IDS[page-1]);

        if(page == 1){
            findViewById(R.id.prevButton).setEnabled(false);
        }
        else if(page == RES_IDS.length){
            findViewById(R.id.nextButton).setEnabled(false);
        }
        editor.putInt("savedPage", page);
        editor.apply();
    }
}