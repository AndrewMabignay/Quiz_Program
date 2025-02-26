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

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText userInput;
    private Spinner spinner;

    private RadioGroup radioGroup;
    private RadioButton selectedRadioButton;
    private TextView output;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ELEMENT INSTANTIATION
        AddElement();

        // FUNCTIONS
        Interaction();
    }

    @SuppressLint("SetTextI18n")
    private void AddElement() {
        /* ============ 1. USER INPUT ============ */
        userInput = (EditText) findViewById(R.id.UserInput);

        /* ============ 2. SPINNER WITH ADAPTER ============ */
        spinner = (Spinner) findViewById(R.id.SpinnerChoose);
        String[] spinnerType = {"Select a Spinner", "Spinner 1", "Spinner 2", "Spinner 3"};
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerType);
        spinner.setAdapter(itemAdapter);

        /* ============ 3. RADIO GROUP ============ */
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);

        /* ============ 4. OUTPUT ============ */
        output = (TextView) findViewById(R.id.Output);
    }

    @SuppressLint("SetTextI18n")
    private void Interaction() {
        userInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                FunctionFieldsEmpty();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FunctionFieldsEmpty();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioGroup.setOnCheckedChangeListener((group, checkedID) -> {
            FunctionFieldsEmpty();
        });
    }

    @SuppressLint("SetTextI18n")
    private void FunctionFieldsEmpty() {
        if (userInput.getText().toString().isEmpty() || spinner.getSelectedItem().toString().equals("Select a Spinner")) {
            output.setText("Eligibility User Input and Spinner : Fill up all requirements!");
            return;
        }

        int selectedButtonID = radioGroup.getCheckedRadioButtonId();
        if (selectedButtonID == -1) {
            output.setText("Eligibility Radio Group and Button : Fill up all requirements!");
            return;
        }

        // TESTING
        selectedRadioButton = findViewById(selectedButtonID);
        String finalSelectedButton = selectedRadioButton.getText().toString();

        output.setText("Eligibility Filled up : " + finalSelectedButton + " | " + userInput.getText().toString() + " | " + spinner.getSelectedItem());
    }
}

