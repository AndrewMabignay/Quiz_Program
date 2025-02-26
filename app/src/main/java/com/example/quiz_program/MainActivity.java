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
    private EditText loanAmount;
    private Spinner loanTerm;

    private RadioGroup interestType;
    private RadioButton selectedInterestType;
    private TextView estimatedMonthlyPayment;


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
        /* ============ 1. LOAN AMOUNT ============ */
        loanAmount = (EditText) findViewById(R.id.LoanAmount);

        /* ============ 2. LOAN TERM ============ */
        loanTerm = (Spinner) findViewById(R.id.LoanTerm);
        String[] loanTermType = {"Select a Loan Term", "1 year", "2 years", "3 years"};
        ArrayAdapter<String> loanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, loanTermType);
        loanTerm.setAdapter(loanAdapter);

        /* ============ 3. INTEREST TYPE ============ */
        interestType = (RadioGroup) findViewById(R.id.InterestType);

        /* ============ 4. ESTIMATED MONTHLY PAYMENT ============ */
        estimatedMonthlyPayment = (TextView) findViewById(R.id.EstimatedMonthlyPayment);
    }

    @SuppressLint("SetTextI18n")
    private void Interaction() {
        loanAmount.addTextChangedListener(new TextWatcher() {
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

        loanTerm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                FunctionFieldsEmpty();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        interestType.setOnCheckedChangeListener((group, checkedID) -> {
            FunctionFieldsEmpty();
        });
    }

    @SuppressLint("SetTextI18n")
    private void FunctionFieldsEmpty() {
        if (loanAmount.getText().toString().isEmpty() || loanTerm.getSelectedItem().toString().equals("Select a Loan Term")) {
            estimatedMonthlyPayment.setText("Fill up all fields!");
            return;
        }

        int selectedInterestTypeID = interestType.getCheckedRadioButtonId();
        if (selectedInterestTypeID == -1) {
            estimatedMonthlyPayment.setText("Fill up all fields!");
            return;
        }

        int year = 0;

        switch (loanTerm.getSelectedItem().toString()) {
            case "1 year":
                year = 1;
                break;
            case "2 years":
                year = 2;
                break;
            case "3 years":
                year = 3;
                break;
        }

        selectedInterestType = findViewById(selectedInterestTypeID);
        String finalSelectedInterestType = selectedInterestType.getText().toString();

        switch (finalSelectedInterestType) {
            case "Fixed":
                estimatedMonthlyPayment.setText("Estimated Monthly Payment : " + (Double.parseDouble(loanAmount.getText().toString()) * year));
                break;
            case "Variable":
                estimatedMonthlyPayment.setText("Estimated Monthly Payment : " + (Double.parseDouble(loanAmount.getText().toString()) * year) + " (May vary over time)");
                break;
        }
    }
}

