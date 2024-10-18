package com.example.toodoolistapp;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Task extends AppCompatActivity {

    private EditText taskInput;
    private Button datePickerBtn;
    private CheckBox notificationCheck;
    private Spinner listSpinner;

    private Calendar dueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item); // Assuming this layout file is activity_new_task.xml

        // Initialize UI components
        taskInput = findViewById(R.id.taskInput);
        datePickerBtn = findViewById(R.id.datePickerBtn);
        notificationCheck = findViewById(R.id.notificationCheck);
        listSpinner = findViewById(R.id.listSpinner);
        Button fabbtn = findViewById(R.id.save);
        TextView dueDateLabel = findViewById(R.id.dueDateLabel);

        // Initialize Spinner with a list of options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.list_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listSpinner.setAdapter(adapter);

        // Initialize calendar for due date
        dueDate = Calendar.getInstance();

        // Set listener for date picker
        datePickerBtn.setOnClickListener(v -> showDatePicker());

        // Set listener for save button
        fabbtn.setOnClickListener(v -> saveTask());
    }

    private void showDatePicker() {
        int year = dueDate.get(Calendar.YEAR);
        int month = dueDate.get(Calendar.MONTH);
        int day = dueDate.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    dueDate.set(selectedYear, selectedMonth, selectedDay);
                    updateDateButtonText();
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void updateDateButtonText() {
        String dateText = dueDate.get(Calendar.DAY_OF_MONTH) + "/" +
                (dueDate.get(Calendar.MONTH) + 1) + "/" +
                dueDate.get(Calendar.YEAR);
        datePickerBtn.setText(dateText);
    }

    private void saveTask() {
        String taskDescription = taskInput.getText().toString();
        String dueDateText = datePickerBtn.getText().toString();
        boolean notificationsEnabled = notificationCheck.isChecked();
        String selectedList = listSpinner.getSelectedItem().toString();


        if (taskDescription.isEmpty()) {
            showToast("Please enter a task.");
            return;
        }

        String taskDetails = "Task: " + taskDescription +
                "\nDue: " + dueDateText +
                "\nNotifications: " + (notificationsEnabled ? "Enabled" : "Disabled") +
                "\nList: " + selectedList;

        showToast("Task Saved:\n" + taskDetails);

        // Here you could save the task to a database, SharedPreferences, or pass it back to the main activity.
    }

    // Utility method to show Toast messages
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
