package com.trunghoang.calculatorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView operator;
    private String pendingOperator = "=";
    private Double operand1 = null;

    private final String STATE_OPERATOR = "operator";
    private final String STATE_PENDING_OPERATOR = "pendingOperator";
    private final String STATE_OPERAND1 = "operand1";

    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonDot;

    private Button buttonPlus;
    private Button buttonMinus;
    private Button buttonMulti;
    private Button buttonDivide;
    private Button buttonEquals;
    private Button buttonNeg;

    private View.OnClickListener numberClickedListener;
    private View.OnClickListener opClickedListener;
    private View.OnClickListener negClickedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initApp();
        setListener();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(STATE_OPERATOR, operator.getText().toString());
        outState.putString(STATE_PENDING_OPERATOR, pendingOperator);
        if (operand1 != null) outState.putDouble(STATE_OPERAND1, operand1);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        operator.setText(savedInstanceState.getString(STATE_OPERATOR));
        pendingOperator = savedInstanceState.getString(STATE_PENDING_OPERATOR);
        operand1 = savedInstanceState.getDouble(STATE_OPERAND1);
    }

    private void initApp() {
        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        operator = findViewById(R.id.operator);
        result.setText("");
        newNumber.setText("");
        operator.setText("");

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonDot = findViewById(R.id.buttonDot);

        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMinus = findViewById(R.id.buttonMinus);
        buttonMulti = findViewById(R.id.buttonMulti);
        buttonDivide = findViewById(R.id.buttonDivide);
        buttonEquals = findViewById(R.id.buttonEquals);
        buttonNeg = findViewById(R.id.buttonNeg);

        numberClickedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                newNumber.append(b.getText().toString());
            }
        };

        opClickedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                String clickedOp = b.getText().toString();
                try {
                    Double newNumberValue = Double.valueOf(newNumber.getText().toString());
                    performOp(newNumberValue);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                pendingOperator = clickedOp;
                operator.setText(clickedOp);
            }
        };

        negClickedListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (newNumber.getText().length() == 0) {
                        newNumber.setText("-");
                    } else {
                        Double newNumberValue = Double.valueOf(newNumber.getText().toString()) * -1;
                        newNumber.setText(newNumberValue.toString());
                    }
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
            }
        };
    }

    private void setListener() {
        button0.setOnClickListener(numberClickedListener);
        button1.setOnClickListener(numberClickedListener);
        button2.setOnClickListener(numberClickedListener);
        button3.setOnClickListener(numberClickedListener);
        button4.setOnClickListener(numberClickedListener);
        button5.setOnClickListener(numberClickedListener);
        button6.setOnClickListener(numberClickedListener);
        button7.setOnClickListener(numberClickedListener);
        button8.setOnClickListener(numberClickedListener);
        button9.setOnClickListener(numberClickedListener);
        buttonDot.setOnClickListener(numberClickedListener);

        buttonPlus.setOnClickListener(opClickedListener);
        buttonMinus.setOnClickListener(opClickedListener);
        buttonMulti.setOnClickListener(opClickedListener);
        buttonDivide.setOnClickListener(opClickedListener);
        buttonEquals.setOnClickListener(opClickedListener);

        buttonNeg.setOnClickListener(negClickedListener);
    }

    private void performOp(Double newNumberValue) {
        if (null == operand1) {
            operand1 = newNumberValue;
        } else {

            switch (pendingOperator) {
                case "=":
                    operand1 = newNumberValue;
                    break;
                case "+":
                    operand1 = operand1 + newNumberValue;
                    break;
                case "-":
                    operand1 = operand1 - newNumberValue;
                    break;
                case "*":
                    operand1 = operand1 * newNumberValue;
                    break;
                case "/":
                    if (newNumberValue == 0) {
                        operand1 = 0.0;
                    } else {
                        operand1 = operand1 / newNumberValue;
                    }
                    break;
            }
        }
        result.setText(operand1.toString());
        newNumber.setText("");
    }
}
