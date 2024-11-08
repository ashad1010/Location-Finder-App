package com.example.locationfinder;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LocationDatabaseHelper dbHelper;
    private EditText editTextId, editTextAddress, editTextLatitude, editTextLongitude;
    private Button buttonAdd, buttonSearch, buttonDelete, buttonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize database helper
        dbHelper = new LocationDatabaseHelper(this);

        // Initialize UI elements
        editTextId = findViewById(R.id.editTextId);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextLatitude = findViewById(R.id.editTextLatitude);
        editTextLongitude = findViewById(R.id.editTextLongitude);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSearch = findViewById(R.id.buttonSearch);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonUpdate = findViewById(R.id.buttonUpdate);

        // Add location button
        buttonAdd.setOnClickListener(v -> {
            String address = editTextAddress.getText().toString();
            String latitudeStr = editTextLatitude.getText().toString();
            String longitudeStr = editTextLongitude.getText().toString();

            if (address.isEmpty() || latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);

            long result = dbHelper.insertLocation(address, latitude, longitude);
            if (result != -1) {
                Toast.makeText(this, "Location Added", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Error adding location", Toast.LENGTH_SHORT).show();
            }
        });

        // Search location button
        buttonSearch.setOnClickListener(v -> {
            String input = editTextAddress.getText().toString();
            Cursor cursor = dbHelper.getLocationByIdOrAddress(input);

            if (cursor.moveToFirst()) {
                // Retrieve and display the location data
                int id = cursor.getInt(cursor.getColumnIndex(LocationDatabaseHelper.COLUMN_ID));
                String address = cursor.getString(cursor.getColumnIndex(LocationDatabaseHelper.COLUMN_ADDRESS));
                double latitude = cursor.getDouble(cursor.getColumnIndex(LocationDatabaseHelper.COLUMN_LATITUDE));
                double longitude = cursor.getDouble(cursor.getColumnIndex(LocationDatabaseHelper.COLUMN_LONGITUDE));

                // Display data in EditText fields
                editTextId.setText(String.valueOf(id));
                editTextAddress.setText(address);
                editTextLatitude.setText(String.valueOf(latitude));
                editTextLongitude.setText(String.valueOf(longitude));
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });

        // Delete location button
        buttonDelete.setOnClickListener(v -> {
            String input = editTextAddress.getText().toString();
            int deletedRows = dbHelper.deleteLocationByAddress(input);

            if (deletedRows > 0) {
                Toast.makeText(this, "Location Deleted", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
            }
        });

        // Update location button
        buttonUpdate.setOnClickListener(v -> {
            String address = editTextAddress.getText().toString();
            String latitudeStr = editTextLatitude.getText().toString();
            String longitudeStr = editTextLongitude.getText().toString();

            if (address.isEmpty() || latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            double latitude = Double.parseDouble(latitudeStr);
            double longitude = Double.parseDouble(longitudeStr);

            int updatedRows = dbHelper.updateLocation(address, latitude, longitude);
            if (updatedRows > 0) {
                Toast.makeText(this, "Location Updated", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                Toast.makeText(this, "Location not found", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Clear the input fields after an action
    private void clearFields() {
        editTextId.setText("");
        editTextAddress.setText("");
        editTextLatitude.setText("");
        editTextLongitude.setText("");
    }
}
