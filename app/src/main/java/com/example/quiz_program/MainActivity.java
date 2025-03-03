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

        /* ============ 2. RADIO GROUP ============ */
        radioGroup = (RadioGroup) findViewById(R.id.RadioGroup);

        /* ============ 3. OUTPUT ============ */
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

        radioGroup.setOnCheckedChangeListener((group, checkedID) -> {
            FunctionFieldsEmpty();
        });
    }

    @SuppressLint("SetTextI18n")
    private void FunctionFieldsEmpty() {
        if (userInput.getText().toString().isEmpty()) {
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

        output.setText("Eligibility Filled up : " + finalSelectedButton + " | " + userInput.getText().toString());
    }
}

