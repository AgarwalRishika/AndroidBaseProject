package com.example.baseproject.activity;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.baseproject.R;
import com.example.baseproject.adapter.AddTaskAdapter;
import com.example.baseproject.common.AppConstant;
import com.example.baseproject.common.AppUtils;
import com.example.baseproject.common.UIUtils;
import com.example.baseproject.databinding.ActivityCreateTaskBinding;
import com.example.baseproject.interfaces.AfterDbOperationListener;
import com.example.baseproject.models.TaskModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.wdullaer.materialdatetimepicker.Utils;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateTaskActivity extends BaseActivity implements AfterDbOperationListener, TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {
    ActivityCreateTaskBinding activityBinding;
    BottomSheetBehavior bottomSheetBehavior;
    AddTaskAdapter taskAdapter;
    ArrayList<String> tempList = new ArrayList<>();
    ArrayList<String> permList = new ArrayList<>();
    TaskModel taskModel = new TaskModel();
    //private AlertDialog  dialog;
    private EditText editText;
    Calendar timeCalendar = Calendar.getInstance();
    Calendar dateCalendar = Calendar.getInstance();
    Calendar timeDatecalendar = Calendar.getInstance();
    private String TAG = getClass().getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        initListener();
    }

    @Override
    int getLayout() {
        return R.layout.activity_create_task;
    }

    @Override
    void init() {
        super.init();
        taskRespository.setListener(this);
        activityBinding = (ActivityCreateTaskBinding) binding;
        taskAdapter = new AddTaskAdapter(permList);
        activityBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityBinding.recyclerView.setAdapter(taskAdapter);
        activityBinding.tvDate.setText(AppUtils.parseDateFormat(timeDatecalendar.getTime(),AppConstant.DATE_FORMAT));
        activityBinding.tvTime.setText(AppUtils.parseDateFormat(timeDatecalendar.getTime(),AppConstant.TIME_FORMAT));


        activityBinding.subtaskLayout.setVisibility(View.GONE);
        activityBinding.camera.setOnClickListener(this::click);
        activityBinding.video.setOnClickListener(this::click);
        activityBinding.music.setOnClickListener(this::click);
        activityBinding.folder.setOnClickListener(this::click);
        activityBinding.location.setOnClickListener(this::click);
        activityBinding.subTaskCardview.setOnClickListener(this::click);
        activityBinding.add.setOnClickListener(this::click);
        activityBinding.cancel.setOnClickListener(this::click);
        activityBinding.fab.setOnClickListener(this::click);
        activityBinding.btnDone.setOnClickListener(this::click);
        activityBinding.dateLayout.setOnClickListener(this::showDatePicker);
        activityBinding.timeLayout.setOnClickListener(this::showTimePicker);
        initToolbar();

        bottomSheetBehavior = BottomSheetBehavior.from(activityBinding.bottomSheet);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
//        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

    }

    private void showDatePicker(View view) {
        DatePickerDialog dpd = DatePickerDialog.newInstance(
                this,
                timeDatecalendar.get(Calendar.YEAR), // Initial year selection
                timeDatecalendar.get(Calendar.MONTH), // Initial month selection
                timeDatecalendar.get(Calendar.DAY_OF_MONTH) // Inital day selection

        );
        dpd.setMinDate(Calendar.getInstance());
        dpd.show(getSupportFragmentManager(), "Datepickerdialog");
    }

    private void showTimePicker(View v) {
        TimePickerDialog tpd = new TimePickerDialog(this, this, timeDatecalendar.get(Calendar.HOUR_OF_DAY), timeDatecalendar.get(Calendar.MINUTE), true);
        tpd.show();

    }


    private void initToolbar() {
        setSupportActionBar(activityBinding.toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        activityBinding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle(TAG);
    }


    @Override
    void initListener() {
        super.initListener();
//        activityBinding.addItem.setOnClickListener(this::openDialog);
//        activityBinding.fab.setOnClickListener(this::openDialog);
//        dialog = UIUtils.createDialog(this , "" , "ADD TASK" , "ADD" ,this::addItem );
//        editText = new EditText(this);
//        dialog.setView(editText);


    }

    void addItem(DialogInterface dialog, int which) {
//    String task =  editText.getText().toString();
//    tempList.add(task);
//    taskAdapter.notifyDataSetChanged( );

    }

    void openDialog(View v) {
//       dialog.show();
    }


    void click(View v) {

        switch (v.getId()) {
            case R.id.camera:
            case R.id.video:
            case R.id.music:
            case R.id.folder:
            case R.id.location:
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                break;

            case R.id.subTask_cardview:
                subTaskLayoutVisibility();
                break;

            case R.id.add:
                if (!AppUtils.isStringEmpty(activityBinding.tvTask.getText().toString())) {
                    permList.add(activityBinding.tvTask.getText().toString());
                    activityBinding.tvTask.setText("");
                    taskAdapter.notifyDataSetChanged();
                } else {
                    UIUtils.showToast(this, "Enter Text");
                }
                break;


            case R.id.btn_done:
                tempList = permList;
                subTaskLayoutVisibility();

                break;


            case R.id.cancel:
                permList = tempList;
                subTaskLayoutVisibility();
                break;

            case R.id.fab:
                if (AppUtils.isStringEmpty(activityBinding.heading.getText().toString())) {
                    UIUtils.showToast(this, "Enter Title");
                    return;
                }
                if (AppUtils.isListEmpty(permList)) {
                    UIUtils.showToast(this, "Add Subtask");
                    return;
                }
                if (timeDatecalendar.getTimeInMillis() < Calendar.getInstance().getTimeInMillis()) {
                    AppUtils.animVibrate(activityBinding.dateTimeCardView);
                    UIUtils.showToast(this,"Add Correct time");
                    return;
                }

                taskModel.setContent(AppUtils.convertListToString(permList));
                taskModel.setDate(timeDatecalendar.getTimeInMillis());
                taskModel.setTitle(activityBinding.heading.getText().toString());
                taskRespository.insertTask(taskModel);
                break;


        }

    }

    private void subTaskLayoutVisibility() {
        if (activityBinding.subtaskLayout.getVisibility() == View.VISIBLE) {
            activityBinding.subtaskLayout.setVisibility(View.GONE);
            activityBinding.subtaskLayout.setClickable(true);

        } else {
            activityBinding.subtaskLayout.setVisibility(View.VISIBLE);
            activityBinding.tvTask.setText("");
            taskAdapter.setList(permList);
            taskAdapter.notifyDataSetChanged();
        }

    }

    void photo() {


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == AppConstant.PERMISSION_GALLERY_IMAGE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                AppUtils.openImageGallery(CreateTaskActivity.this, AppConstant.RESULT_GALLERY_IMAGE);

            }


        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK) {
                if (requestCode == AppConstant.RESULT_GALLERY_IMAGE) {
                    File file = AppUtils.getOutputFileFromGallery(CreateTaskActivity.this, data);
                }
            }
        } catch (Exception e) {
            Log.e("FileSelectorActivity", "File select error", e);
        }
    }


    @Override
    public void dbOperationCompleted(Object o) {
        long id = (Long) o;
//        if (result == 1) {
        UIUtils.showToast(this, getResources().getString(R.string.dbInsertOperation));
        Intent intent=    AppUtils.createAlarmIntenet(this , id);
        AppUtils.createAlarm(this , intent,id,timeCalendar.getTimeInMillis());
           finish();
//        } else {
//            UIUtils.showToast(this, getResources().getString(R.string.dbOperationFail));
//        }

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        timeCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        timeCalendar.set(Calendar.MINUTE, minute);

        timeDatecalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        timeDatecalendar.set(Calendar.MINUTE, minute);

        activityBinding.tvTime.setText(AppUtils.parseDateFormat(timeDatecalendar.getTime(), AppConstant.TIME_FORMAT));



    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
       dateCalendar.set(Calendar.YEAR,year);
       dateCalendar.set(Calendar.MONTH,monthOfYear);
       dateCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);

        timeDatecalendar.set(Calendar.YEAR,year);
        timeDatecalendar.set(Calendar.MONTH,monthOfYear);
        timeDatecalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        activityBinding.tvDate.setText(AppUtils.parseDateFormat(timeDatecalendar.getTime(),AppConstant.DATE_FORMAT));
    }
}
