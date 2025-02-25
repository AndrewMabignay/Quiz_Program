package com.example.quiz_program;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Spinner foodItem;
    private String[] items;
    private EditText pricePerItem, quantity;
    private RadioGroup serviceCharge;
    private RadioButton selectedServiceCharge;
    private TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddElement();
        Interaction();
    }

    @SuppressLint("SetTextI18n")
    private void AddElement() {
        foodItem = (Spinner) findViewById(R.id.FoodItem);
        items = new String[]{"Select an Item", "Burger", "Siomai", "Hotdog"};
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        foodItem.setAdapter(dataAdapter);

        pricePerItem = (EditText) findViewById(R.id.PricePerItem);

        quantity = (EditText) findViewById(R.id.Quantity);

        serviceCharge = (RadioGroup) findViewById(R.id.ServiceCharge);

        output = (TextView) findViewById(R.id.Output);
    }

    private void ConditionElement() {

    }

    @SuppressLint("SetTextI18n")
    private void Interaction() {
        foodItem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (foodItem.getSelectedItem().toString().equals("Select an Item") || pricePerItem.getText().toString().isEmpty() || quantity.getText().toString().isEmpty()) {
                    output.setText("Fill up all fields");
                    return;
                }

                int selectedId = serviceCharge.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    output.setText("Fill up all fields!");
                    return;
                }

                selectedServiceCharge = findViewById(selectedId);
                String selectedActivity = selectedServiceCharge.getText().toString();
                double value = Double.parseDouble(quantity.getText().toString()) * Double.parseDouble(pricePerItem.getText().toString());

                switch (selectedActivity) {
                    case "Dine-in":
                        output.setText(String.valueOf(value));
                        break;
                    case "Take Out":
                        output.setText(String.valueOf(value + 30.50));
                        break;
                    default:
                        output.setText("Fill up all fields!");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        pricePerItem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (foodItem.getSelectedItem().toString().equals("Select an Item") || quantity.getText().toString().isEmpty() || pricePerItem.getText().toString().isEmpty()) {
                    output.setText("Fill up all fields!");
                    return;
                }

                int selectedId = serviceCharge.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    output.setText("Fill up all fields!");
                    return;
                }

                selectedServiceCharge = findViewById(selectedId);
                String selectedActivity = selectedServiceCharge.getText().toString();
                double value = Double.parseDouble(quantity.getText().toString()) * Double.parseDouble(pricePerItem.getText().toString());

                switch (selectedActivity) {
                    case "Dine-in":
                        output.setText(String.valueOf(value));
                        break;
                    case "Take Out":
                        output.setText(String.valueOf(value + 30.50));
                        break;
                    default:
                        output.setText("Fill up all fields!");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (foodItem.getSelectedItem().toString().equals("Select an Item") || pricePerItem.getText().toString().isEmpty() || quantity.getText().toString().isEmpty()) {
                    output.setText("Fill up all fields");
                    return;
                }

                int selectedId = serviceCharge.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    output.setText("Fill up all fields!");
                    return;
                }

                selectedServiceCharge = findViewById(selectedId);
                String selectedActivity = selectedServiceCharge.getText().toString();
                double value = Double.parseDouble(quantity.getText().toString()) * Double.parseDouble(pricePerItem.getText().toString());

                switch (selectedActivity) {
                    case "Dine-in":
                        output.setText(String.valueOf(value));
                        break;
                    case "Take Out":
                        output.setText(String.valueOf(value + 30.50));
                        break;
                    default:
                        output.setText("Fill up all fields!");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        serviceCharge.setOnCheckedChangeListener((group, checkedID) -> {
            if (foodItem.getSelectedItem().toString().equals("Select an Item") || pricePerItem.getText().toString().isEmpty() || quantity.getText().toString().isEmpty()) {
                output.setText("Fill up all fields!");
                return;
            }

            selectedServiceCharge = findViewById(checkedID);

            if (selectedServiceCharge == null) {
                output.setText("Fill up all fields!");
                return;
            }

            String selectedActivity = selectedServiceCharge.getText().toString();
            double value = Double.parseDouble(quantity.getText().toString()) * Double.parseDouble(pricePerItem.getText().toString());

            switch (selectedActivity) {
                case "Dine-in":
                    output.setText(String.valueOf(value));
                    break;
                case "Take Out":
                    output.setText(String.valueOf(value + 30.50));
                    break;
                default:
                    output.setText("Fill up all fields!");
            }
        });
    }
}