package com.example.railquest;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class book_train extends AppCompatActivity {

    private EditText editTextDeparture;
    private EditText editTextArrival;
    private EditText editTextDate;
    private Button buttonBook;

    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_train);

        editTextDeparture = findViewById(R.id.editTextDeparture);
        editTextArrival = findViewById(R.id.editTextArrival);
        editTextDate = findViewById(R.id.editTextDate);
        buttonBook = findViewById(R.id.buttonBook);

        // Date picker dialog
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(book_train.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                editTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        // Book Now button click listener
        buttonBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookTrain();
            }
        });
    }

    private void bookTrain() {
        String departure = editTextDeparture.getText().toString();
        String arrival = editTextArrival.getText().toString();
        String date = editTextDate.getText().toString();

        // Perform validation
        if (departure.isEmpty() || arrival.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show();
            return;
        }

        // Perform booking process here
        // You can add your logic to book the train based on the entered details

        // For demonstration, show a toast message indicating successful booking
        //Toast.makeText(this, "Train booked successfully!", Toast.LENGTH_SHORT).show();
    }
}
