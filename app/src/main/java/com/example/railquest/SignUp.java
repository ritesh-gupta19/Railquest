package com.example.railquest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private EditText mobileNumber;
    private EditText emailId;
    private EditText name;
    private EditText password;
    private EditText confirmPassword;
    private EditText firstName;
    private EditText dateOfBirth;
    private EditText residentialAddress;
    private EditText country;
    private EditText pinCode;
    private EditText city;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mobileNumber = findViewById(R.id.editTextPhone);
        emailId = findViewById(R.id.editTextTextEmailAddress);
        name = findViewById(R.id.editTextText2);
        password = findViewById(R.id.editTextTextPassword2);
        confirmPassword = findViewById(R.id.editTextTextPassword4);
        firstName = findViewById(R.id.editTextText3);
        dateOfBirth = findViewById(R.id.editTextDate2);
        residentialAddress = findViewById(R.id.editTextText7);
        country = findViewById(R.id.editTextText8);
        pinCode = findViewById(R.id.editTextNumber);
        city = findViewById(R.id.editTextText9);
        button = findViewById(R.id.button3);

        View imageViewCalendar = findViewById(R.id.imageViewCalendar2);

        imageViewCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        setFieldListeners();

        button.setOnClickListener(view -> DBHandler.addUser(SignUp.this));
    }

    public void showDatePickerDialog() {
        EditText editTextDate = findViewById(R.id.editTextDate2);

        // Get current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new DatePickerDialog instance
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        // Set the selected date to the EditText
                        String formattedDate = String.format(java.util.Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
                        editTextDate.setText(formattedDate);
                    }
                }, year, month, day);

        // Show the DatePickerDialog
        datePickerDialog.show();
    }

    private void setFieldListeners() {
        mobileNumber.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateMobileNumber();
                }
            }
        });

        emailId.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateEmail();
                }
            }
        });

        name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validateName();
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validatePassword();
                }
            }
        });

        confirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateConfirmPassword();
                }
            }
        });

        firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validatefirstName();
                }
            }
        });

        dateOfBirth.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validateDOB();
                }
            }
        });

        residentialAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validateresidentialAddress();
                }
            }
        });

        country.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validateCountry();
                }
            }
        });

        pinCode.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    validatePinCode();
                }
            }
        });

        city.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validateCity();
                }
            }
        });
    }

    private boolean validateMobileNumber() {
        String mobile_number = mobileNumber.getText().toString();
        Pattern pattern_mobile = Pattern.compile("^(\\+?91|0)?[6789]\\d{9}$");
        Matcher matcher_mobile = pattern_mobile.matcher(mobile_number);
        if (!matcher_mobile.matches()) {
            mobileNumber.setError("Invalid mobile number");
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String email_id = emailId.getText().toString();
        Pattern pattern_email = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        Matcher matcher_email = pattern_email.matcher(email_id);
        if (!matcher_email.matches()) {
            emailId.setError("Invalid email address");
            return false;
        }
        return true;
    }

    private boolean validateName() {
        String user_name = name.getText().toString();
        if(user_name.length() == 0) {
            name.setError("Invalid User name");
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        String pass_word = password.getText().toString();
        Pattern pattern_password = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher matcher_password = pattern_password.matcher(pass_word);
        if (!matcher_password.matches()) {
            password.setError("The password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, one digit, and one special character");
            return false;
        }
        return true;
    }

    private boolean validateConfirmPassword() {
        String confirm_password = confirmPassword.getText().toString();
        String pass_word = password.getText().toString();
        if (!confirm_password.equals(pass_word)) {
            confirmPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    private boolean validatefirstName() {
        String first_name = firstName.getText().toString();
        if(first_name.length() == 0) {
            firstName.setError("Please enter a valid name");
            return false;
        }
        return true;
    }

    private boolean validateDOB() {
        String dobString = dateOfBirth.getText().toString();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // This will not allow 31st of February, for instance

        try {
            Date dob = sdf.parse(dobString);
            assert dob != null;
            if (dob.after(new Date())) {
                dateOfBirth.setError("Date of birth cannot be in the future");
                return false;
            }
        } catch (ParseException e) {
            dateOfBirth.setError("Invalid date format. Please enter date in dd/MM/yyyy format");
            return false;
        }
        return true;
    }

    private boolean validateresidentialAddress() {
        String residential_address = residentialAddress.getText().toString();
        if(residential_address.length() == 0) {
            firstName.setError("Please enter a valid address");
            return false;
        }
        return true;
    }

    private boolean validateCountry() {
        String country_name = country.getText().toString();
        if(country_name.length() == 0) {
            firstName.setError("Please enter a valid country name");
            return false;
        }
        return true;
    }

    private boolean validatePinCode() {
        String pinCodeString = pinCode.getText().toString();
        Pattern pattern_pinCode = Pattern.compile("^\\d{6}$");
        Matcher matcher_pinCode = pattern_pinCode.matcher(pinCodeString);
        if (!matcher_pinCode.matches()) {
            pinCode.setError("Invalid PIN code. PIN code must be 6 digits.");
            return false;
        }
        return true;
    }

    private boolean validateCity() {
        String city_name = city.getText().toString();
        if(city_name.length() == 0) {
            firstName.setError("Please enter a valid city name");
            return false;
        }
        return true;
    }

    public void onClickSubmit(View v) {
        // Validate all fields
        boolean isValid = true;

        if (!validateMobileNumber()) {
            isValid = false;
        }

        if (!validateEmail()) {
            isValid = false;
        }

        if(!validateName()) {
            isValid = false;
        }

        if (!validatePassword()) {
            isValid = false;
        }

        if (!validateConfirmPassword()) {
            isValid = false;
        }

        if(!validatefirstName()) {
            isValid = false;
        }

        if (!validateDOB()) {
            isValid = false;
        }

        if(!validateresidentialAddress()) {
            isValid = false;
        }

        if(!validateCountry()) {
            isValid = false;
        }

        if (!validatePinCode()) {
            isValid = false;
        }

        if(!validateCity()) {
            isValid = false;
        }

        if (isValid) {
            // Switch to next page
            Intent intent = new Intent(this, SignIn.class); // Replace NextActivity with the name of your next activity
            startActivity(intent);
        } else {
            // Highlight incorrect fields
            mobileNumber.requestFocus(); // Move focus to the first incorrect field
            emailId.requestFocus();
            name.requestFocus();
            password.requestFocus();
            confirmPassword.requestFocus();
            dateOfBirth.requestFocus();
            pinCode.requestFocus();
            // Show error message or Toast indicating validation errors
            Toast.makeText(this, "Please fill all compulsory fields correctly", Toast.LENGTH_SHORT).show();
        }
    }

    public void closeActivity() {
        finish();
    }
}
