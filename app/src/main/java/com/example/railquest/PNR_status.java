package com.example.railquest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PNR_status extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr_status);

        // Initialize UI components
        EditText editTextPNR = findViewById(R.id.editTextPNR);
        Button buttonSearch = findViewById(R.id.buttonSearch);

        // Set onClickListener for the Search button
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered PNR number
                String pnr = editTextPNR.getText().toString();

                // Start a new activity to display the PNR status
                Intent intent = new Intent(PNR_status.this, PNR_status_display.class);
                intent.putExtra("PNR", pnr);
                startActivity(intent);
            }
        });
    }
}
