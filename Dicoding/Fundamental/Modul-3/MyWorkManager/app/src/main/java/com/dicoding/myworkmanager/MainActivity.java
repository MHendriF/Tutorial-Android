package com.dicoding.myworkmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOneTimeTask, btnPeriodicTask, btnCancelTask ;
    private EditText editCity;
    private TextView textStatus;
    private PeriodicWorkRequest periodicWorkRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOneTimeTask = findViewById(R.id.btnOneTimeTask);
        editCity = findViewById(R.id.editCity);
        textStatus = findViewById(R.id.textStatus);
        btnOneTimeTask.setOnClickListener(this);

        btnPeriodicTask = findViewById(R.id.btnPeriodicTask);
        btnCancelTask = findViewById(R.id.btnCancelTask);
        btnPeriodicTask.setOnClickListener(this);
        btnCancelTask.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOneTimeTask:
                startOneTimeTask();
                break;
            case R.id.btnPeriodicTask:
                startPeriodicTask();
                break;
            case R.id.btnCancelTask:
                cancelPeriodicTask();
                break;
        }
    }

    private void startPeriodicTask() {
        textStatus.setText(getString(R.string.status));
        Data data = new Data.Builder()
                .putString(MyWorker.EXTRA_CITY, editCity.getText().toString())
                .build();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        periodicWorkRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 15, TimeUnit.MINUTES)
                .setInputData(data)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance().enqueue(periodicWorkRequest);
        WorkManager.getInstance().getWorkInfoByIdLiveData(periodicWorkRequest.getId()).observe(MainActivity.this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                String status = workInfo.getState().name();
                textStatus.append("\n"+status);
                btnCancelTask.setEnabled(false);
                if (workInfo.getState() == WorkInfo.State.ENQUEUED){
                    btnCancelTask.setEnabled(true);
                }
            }
        });
    }

    private void startOneTimeTask() {
        textStatus.setText(getString(R.string.status));
        Data data = new Data.Builder()
                .putString(MyWorker.EXTRA_CITY, editCity.getText().toString())
                .build();
        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
                .setInputData(data)
                .build();
        WorkManager.getInstance().enqueue(oneTimeWorkRequest);
        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observe(MainActivity.this, new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                String status = workInfo.getState().name();
                textStatus.append("\n"+status);
            }
        });
    }

    private void cancelPeriodicTask() {
        WorkManager.getInstance().cancelWorkById(periodicWorkRequest.getId());
    }
}
