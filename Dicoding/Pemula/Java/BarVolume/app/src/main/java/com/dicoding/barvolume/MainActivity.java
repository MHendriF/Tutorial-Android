package com.dicoding.barvolume;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtWidth, edtHeight, edtLength;
    private TextView tvResult;
    private Button btnCalculate;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtLength = findViewById(R.id.edt_panjang);
        edtWidth = findViewById(R.id.edt_lebar);
        edtHeight = findViewById(R.id.edt_tinggi);
        btnCalculate = findViewById(R.id.btn_hitung);
        tvResult = findViewById(R.id.tv_hasil);

        btnCalculate.setOnClickListener(this);

        if (savedInstanceState != null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tvResult.getText().toString());
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_hitung){
            String inputLength = edtLength.getText().toString().trim();
            String inputWidth = edtWidth.getText().toString().trim();
            String inputHeight = edtHeight.getText().toString().trim();

            boolean isEmptyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputLength)){
                isEmptyFields = true;
                edtLength.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputLength)){
                isEmptyFields = true;
                edtWidth.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputLength)){
                isEmptyFields = true;
                edtHeight.setError("Field ini tidak boleh kosong");
            }

            Double length = toDouble(inputLength);
            Double width = toDouble(inputWidth);
            Double height = toDouble(inputHeight);

            if (length == null){
                isInvalidDouble = true;
                edtLength.setError("Field ini harus berupa nomer valid");
            }

            if (width == null){
                isInvalidDouble = true;
                edtWidth.setError("Field ini harus berupa nomer valid");
            }

            if (height == null){
                isInvalidDouble = true;
                edtHeight.setError("Field ini harus berupa nomer valid");
            }

            if (!isEmptyFields && !isInvalidDouble){
                double volume = length * width * height;
                tvResult.setText(String.valueOf(volume));
            }
        }
    }

    private Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
