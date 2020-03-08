package com.dicoding.roomwordssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.dicoding.roomwordssample.MainActivity.EXTRA_DATA_ID;
import static com.dicoding.roomwordssample.MainActivity.EXTRA_DATA_UPDATE_WORD;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "extra_reply_data";
    public static final String EXTRA_REPLY_ID = "extra_reply_id";
    private EditText mEditWordView;
    private final String TAG = "Trace "+NewWordActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        int id = -1;

        final Bundle bundle = getIntent().getExtras();

        // If we are passed content, fill it in for the user to edit.
        if (bundle != null){
            String word = bundle.getString(EXTRA_DATA_UPDATE_WORD, "");
            if (!word.isEmpty()){
                mEditWordView.setText(word);
                mEditWordView.setSelection(word.length());
                mEditWordView.requestFocus();
            }
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditWordView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String word = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    if (bundle != null && bundle.containsKey(EXTRA_DATA_ID)){
                        int id = bundle.getInt(EXTRA_DATA_ID, -1);
                        if (id != -1){
                            replyIntent.putExtra(EXTRA_REPLY_ID, id);
                        }
                    }
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
