package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Calculator myCurrentCalculator = new Calculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void onButtonClick(View ButtonID) {
        String myButtonValue = String.valueOf(ButtonID.getId()).substring(7, 10);

        try {
            buttonValueDeterminer(myButtonValue);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }


    public void buttonValueDeterminer(String myButtonValue) {
        TextView returnValue = findViewById(R.id.returnedOutput);
        EditText equationInputView = findViewById(R.id.input);
        String returnedStatement;

        switch (myButtonValue) {
            case "350":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("0");
                equationInputView.setText(returnedStatement);
                break;
            case "280":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("1");
                equationInputView.setText(returnedStatement);
                break;
            case "343":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("2");
                equationInputView.setText(returnedStatement);
                break;
            case "336":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("3");
                equationInputView.setText(returnedStatement);
                break;
            case "250":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("4");
                equationInputView.setText(returnedStatement);
                break;
            case "248":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("5");
                equationInputView.setText(returnedStatement);
                break;
            case "315":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("6");
                equationInputView.setText(returnedStatement);
                break;
            case "310":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("7");
                equationInputView.setText(returnedStatement);
                break;
            case "240":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("8");
                equationInputView.setText(returnedStatement);
                break;
            case "274":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddNum("9");
                equationInputView.setText(returnedStatement);
                break;

            case "209":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddSymbol("+");
                equationInputView.setText(returnedStatement);
                break;
            case "327":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddSymbol("-");
                equationInputView.setText(returnedStatement);
                break;
            case "272":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddSymbol("*");
                equationInputView.setText(returnedStatement);
                break;
            case "281":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddSymbol("(");
                equationInputView.setText(returnedStatement);
                break;
            case "282":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddSymbol(")");
                equationInputView.setText(returnedStatement);
                break;
            case "271":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddSymbol("%");
                equationInputView.setText(returnedStatement);
                break;
            case "238":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddSymbol("/");
                equationInputView.setText(returnedStatement);
                break;
            case "287":
                returnedStatement = myCurrentCalculator.checkArrBeforeAddSymbol("^");
                equationInputView.setText(returnedStatement);
                break;

            case "225":
                myCurrentCalculator.equationArr.removeAll(myCurrentCalculator.equationArr);
                equationInputView.setText("");
                returnValue.setText("");
                break;
            case "242":
                returnValue.setText(myCurrentCalculator.solveEquation());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + myButtonValue);
        }
    }
}