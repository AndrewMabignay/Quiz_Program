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
    private EditText gpa;
    private Spinner scholarshipType;

    private RadioGroup workingStudent;
    private RadioButton selectedAnswer;

    private TextView verification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AddElement();
        Interaction();
    }

    @SuppressLint("SetTextI18n")
    private void AddElement() {
        gpa = (EditText) findViewById(R.id.GPA);

        scholarshipType = (Spinner) findViewById(R.id.Scholarship);
        String[] type = {"Select an Academic", "Academic", "Non-academic"};
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        scholarshipType.setAdapter(typeAdapter);

        workingStudent = (RadioGroup) findViewById(R.id.Verify);

        verification = (TextView) findViewById(R.id.Eligibility);
    }

    @SuppressLint("SetTextI18n")
    private void Interaction() {
        gpa.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (gpa.getText().toString().isEmpty() || scholarshipType.getSelectedItem().toString().equals("Select an Academic")) {
                    verification.setText("Eligibility : Fill up all requirements!");
                    return;
                }

                int selectIDStudent = workingStudent.getCheckedRadioButtonId();
                if (selectIDStudent == -1) {
                    verification.setText("Eligibility : Fill up all requirements!");
                    return;
                }


                // TESTING
                selectedAnswer = findViewById(selectIDStudent);
                String finalSelectedAnswer = selectedAnswer.getText().toString();

                verification.setText("Eligibility 2 : " + finalSelectedAnswer);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        scholarshipType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (gpa.getText().toString().isEmpty() || scholarshipType.getSelectedItem().toString().equals("Select an Academic")) {
                    verification.setText("Eligibility : Fill up all requirements!");
                    return;
                }

                int selectIDStudent = workingStudent.getCheckedRadioButtonId();
                if (selectIDStudent == -1) {
                    verification.setText("Eligibility : Fill up all requirements!");
                    return;
                }

                selectedAnswer = findViewById(selectIDStudent);
                String finalSelectedAnswer = selectedAnswer.getText().toString();

                verification.setText("Eligibility 3: " + finalSelectedAnswer);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        workingStudent.setOnCheckedChangeListener((group, checkedID) -> {
            if (gpa.getText().toString().isEmpty() || scholarshipType.getSelectedItem().toString().equals("Select an Academic")) {
                verification.setText("Eligibility : Fill up all requirements!");
                return;
            }

            int selectIDStudent = workingStudent.getCheckedRadioButtonId();
            if (selectIDStudent == -1) {
                verification.setText("Eligibility : Fill up all requirements!");
                return;
            }

            RadioConditionInteraction(selectIDStudent);
        });
    }

    @SuppressLint("SetTextI18n")
    private void RadioConditionInteraction(int select) {
        selectedAnswer = findViewById(select);
        String finalSelectedAnswer = selectedAnswer.getText().toString();

        double gpaValue = Double.parseDouble(gpa.getText().toString());

        if (gpaValue >= 3.8 && finalSelectedAnswer.equals("Yes")) {
            verification.setText("Eligibility : 50% Discount");
        } else if (gpaValue >= 3.5 && finalSelectedAnswer.equals("Yes")) {
            verification.setText("Eligibility : 20% Discount");
        } else {
            verification.setText("Eligibility : Not Qualified!");
        }
    }

    private void SpinnerConditionInteraction() {

    }
}

