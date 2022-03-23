package kr.ac.tukorea.s2019182014.morecontrolos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private EditText edit;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edit = findViewById(R.id.nameEdit);
        output = findViewById(R.id.outputTextView);
    }

    public void onCheckBox(View view) {
        CheckBox cb = (CheckBox) view;
        Log.d(TAG, "Checked" + cb.isChecked());
    }

    public void onBtnDoIt(View view) {
        CheckBox cb = findViewById(R.id.checkBox);
        Log.d(TAG, "onBtnDoIt(), Checked" + cb.isChecked());


        String text = edit.getText().toString();
        output.setText(text);
    }
}