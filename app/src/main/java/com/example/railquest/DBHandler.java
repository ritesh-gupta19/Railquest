package com.example.railquest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DBHandler {
    private static String getDataFrom(Context context, int id) {
        EditText editText = ((Activity)context).findViewById(id);
        return editText.getText().toString();
    }
    public static UserInfo makeUserInfoObject(Context context) {
        UserInfo userInfo = new UserInfo();

        userInfo.setMobileNumber(getDataFrom(context, R.id.editTextPhone));
        userInfo.setEmail(getDataFrom(context, R.id.editTextTextEmailAddress));
        userInfo.setName(getDataFrom(context, R.id.editTextText2));
        userInfo.setPassword(getDataFrom(context, R.id.editTextTextPassword2));
        userInfo.setFirstName(getDataFrom(context, R.id.editTextText3));
        userInfo.setMiddleName(getDataFrom(context, R.id.editTextText4));
        userInfo.setLastName(getDataFrom(context, R.id.editTextText5));
        userInfo.setDob(getDataFrom(context, R.id.editTextDate2));
        userInfo.setNationality(getDataFrom(context, R.id.editTextText6));
        userInfo.setAddress(getDataFrom(context, R.id.editTextText7));
        userInfo.setCountry(getDataFrom(context, R.id.editTextText8));
        userInfo.setPinCode(getDataFrom(context, R.id.editTextNumber));
        userInfo.setCity(getDataFrom(context, R.id.editTextText9));

//        Log.d("DBHandler_data", getDataFrom(context, R.id.editTextPhone) + " " +
//                getDataFrom(context, R.id.editTextTextEmailAddress) + " " +
//                getDataFrom(context, R.id.editTextText2) + " " +
//                getDataFrom(context, R.id.editTextTextPassword2) + " " +
//                getDataFrom(context, R.id.editTextText3) + " " +
//                getDataFrom(context, R.id.editTextText4) + " " +
//                getDataFrom(context, R.id.editTextText5) + " " +
//                getDataFrom(context, R.id.editTextDate2) + " " +
//                getDataFrom(context, R.id.editTextText6) + " " +
//                getDataFrom(context, R.id.editTextText7) + " " +
//                getDataFrom(context, R.id.editTextText8) + " " +
//                getDataFrom(context, R.id.editTextNumber) + " " +
//                getDataFrom(context, R.id.editTextText9) + " end.");
        return userInfo;

    }

    public static void addUser(Context context) {
        String databaseURL = "https://railquest-c25ea-default-rtdb.asia-southeast1.firebasedatabase.app/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(databaseURL);
        DatabaseReference databaseReference = database.getReference("Users");

        String userId = databaseReference.push().getKey();

        if (userId != null) {
            databaseReference.child(userId).setValue(makeUserInfoObject(context))
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(context, "User Added!", Toast.LENGTH_SHORT).show();
                        if (context instanceof Activity) {
                            ((Activity) context).finish();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(context, "Error Adding User!", Toast.LENGTH_SHORT).show();
                    });
        }
 
    }

    public static int verifyUserCredentials(String username, String password, final Context context) {
        String databaseURL = "https://railquest-c25ea-default-rtdb.asia-southeast1.firebasedatabase.app/";
        FirebaseDatabase database = FirebaseDatabase.getInstance(databaseURL);
        DatabaseReference usersRef = database.getReference("Users");

        final int[] userValidityFlag = {0};

        // Query the database to find the user with the provided username
        usersRef.orderByChild("name").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Username exists, check if password matches
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        UserInfo userInfo = userSnapshot.getValue(UserInfo.class);
                        if (userInfo != null && userInfo.getPassword().equals(password)) {
                            // Username and password pair is correct
                            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show();
                            userValidityFlag[0] = 1;
                            return;
                        }
                    }
                    // Password does not match
                    Toast.makeText(context, "Incorrect password", Toast.LENGTH_SHORT).show();
                } else {
                    // Username does not exist
                    Toast.makeText(context, "User not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Error verifying credentials", Toast.LENGTH_SHORT).show();
            }
        });

        return userValidityFlag[0];
    }
}
