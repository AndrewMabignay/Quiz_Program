package com.example.quiz_program;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText bill, people;
    private Spinner discount;
    private RadioGroup addingUp;
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
        bill = (EditText) findViewById(R.id.Bill);
        people = (EditText) findViewById(R.id.People);
        output = (TextView) findViewById(R.id.Output);
//        originalPrice = (EditText) findViewById(R.id.Amount);
//
//        discount = (Spinner) findViewById(R.id.Discount);
//        String[] discounts = new String[]{"Select a Discount", "10%", "20%", "30%"};
//        ArrayAdapter<String> dataDiscounts = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, discounts);
//        dataDiscounts.setDropDownViewResource(android.R.layout.simple_list_item_checked);
//        discount.setAdapter(dataDiscounts);
//
//        output = (TextView) findViewById(R.id.Output);
    }

    @SuppressLint("SetTextI18n")
    private void Interaction() {
        bill.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (bill.getText().toString().isEmpty()) {
                    output.setText(String.valueOf(0));
                    return;
                }

                if (people.getText().toString().isEmpty()) {
                    output.setText(String.valueOf(charSequence));
                    return;
                }

                double value = Double.parseDouble(bill.getText().toString()) / Integer.parseInt(people.getText().toString());
                output.setText(String.valueOf(value));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        people.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (bill.getText().toString().isEmpty() || people.getText().toString().isEmpty()) {
                    output.setText(String.valueOf(bill.getText().toString()));
                    return;
                }

                double value = Double.parseDouble(bill.getText().toString()) / Integer.parseInt(people.getText().toString());
                output.setText(String.valueOf(value));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
//        output.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (originalPrice.getText().toString().isEmpty()) {
//                    output.setText("Amount : 0 PHP");
//                    return;
//                }
//
//                output.setText("Amount : " + originalPrice.getText().toString() + " PHP");
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//
//        discount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
//                String selectedDiscount = parent.getItemAtPosition(i).toString();
//
//                switch (selectedDiscount) {
//                    case "10%":
//                        Calculation(10);
//                        break;
//                    case "20%":
//                        Calculation(20);
//                        break;
//                    case "30%":
//                        Calculation(30);
//                        break;
//                    default:
//                        output.setText("Amount : " + (originalPrice.getText().toString().isEmpty() ? "0" : originalPrice.getText().toString()));
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//    }
//
//    private void Calculation(double percent) {
//        double amountPrice = Double.parseDouble(originalPrice.getText().toString());
//        double computation = amountPrice - (amountPrice * (percent / 100));
//
//        output.setText("Amount : " + String.valueOf(computation));
//    }
}