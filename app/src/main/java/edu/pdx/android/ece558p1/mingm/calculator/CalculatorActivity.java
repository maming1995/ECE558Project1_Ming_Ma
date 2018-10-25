//Ming Ma
//October 15, 2018. Reference: Professor Roy's ohm's law calculator.
//Do the basic logic operations(+, -, *, /, %, square_root) between two operands.
package edu.pdx.android.ece558p1.mingm.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import static java.lang.Math.sqrt;

public class CalculatorActivity extends AppCompatActivity {
    private static final String TAG = "CalculatorActivity";

    private EditText minput1;
    private EditText minput2;
    private TextView moutput1;

    private Button mCalcAddit;
    private Button mCalcSubst;
    private Button mCalcMultp;
    private Button mCalcDivis;
    private Button mCalcPerct;
    private Button mCalcSquar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        // return the EditText objects for each of the text boxes. minput1 is the operand 1 EditText
        // object, minput2 is the operand 2 EditText object, moutput1 is the result TextView object.

        minput1 = (EditText) findViewById(R.id.editText1);
        minput2 = (EditText) findViewById(R.id.editText2);
        moutput1 = (TextView) findViewById(R.id.textView1);

        // Handle the Calculate Addition Button by getting the Calculate Addition Button object
        // The onClick() method listens for a "Button clicked" event for the "+" button. When the
        // event occurs we check that operand1 and operand 2 are valid and calculate Result =
        // operand1 + operand2
        mCalcAddit = (Button) findViewById(R.id.buttonForAddition);
        mCalcAddit.setOnClickListener(new View.OnClickListener() {
            ///anonymous inner class method
            @Override
            public void onClick(View v) {
                //Check that the operand 1 and operand 2 are valid. If not, pop up a warning to the
                //user and clear all of the inputs.
                if (!isValidInput(true, true)) {
                    Toast.makeText(CalculatorActivity.this, "Input Error: Operand 1 or Operand 2 is Incorrect!",
                            Toast.LENGTH_SHORT).show();
                    clear_input1();
                    clear_input2();
                } else {
                    double result = Double.valueOf(minput1.getText().toString()) + Double.valueOf(minput2.getText().toString());
                    moutput1.setText(String.format("%.03f", result));
                }
            }
        });

        //Add onClick() method for subtraction "-" button.
        mCalcSubst = (Button) findViewById(R.id.buttonForSubtraction);
        mCalcSubst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidInput(true, true)) {
                    Toast.makeText(CalculatorActivity.this, "Input Error: Operand 1 or Operand 2 is Incorrect!",
                            Toast.LENGTH_SHORT).show();
                    clear_input1();
                    clear_input2();
                } else {
                    double result = Double.valueOf(minput1.getText().toString()) - Double.valueOf(minput2.getText().toString());
                    moutput1.setText(String.format("%.03f", result));

                }
            }
        });

        //Add onClick() method for multiplication "X" button.
        mCalcMultp = (Button) findViewById(R.id.buttonForMultiplication);
        mCalcMultp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidInput(true, true)) {
                    Toast.makeText(CalculatorActivity.this, "Input Error: Operand 1 or Operand 2 is Incorrect!",
                            Toast.LENGTH_SHORT).show();
                    clear_input1();
                    clear_input2();
                } else {
                    double result = Double.valueOf(minput1.getText().toString()) * Double.valueOf(minput2.getText().toString());
                    moutput1.setText(String.format("%.03f", result));
                }
            }
        });

        //Add onClick() method for Division "/" button.
        mCalcDivis = (Button) findViewById(R.id.buttonForDivision);
        mCalcDivis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidInput(true, true)) {
                    Toast.makeText(CalculatorActivity.this, "Input Error: Operand 1 and Operand2 is Incorrect!",
                            Toast.LENGTH_SHORT).show();
                    clear_input1();
                    clear_input2();
                } else {
                    if (Double.parseDouble(minput2.getText().toString()) == 0.00) {
                        Toast.makeText(CalculatorActivity.this, "Input Error: Operand 2 is Incorrect!",
                                Toast.LENGTH_SHORT).show();
                        clear_input2();
                    } else {
                        double result = Double.valueOf(minput1.getText().toString()) / Double.valueOf(minput2.getText().toString());
                        moutput1.setText(String.format("%.03f", result));
                    }
                }
            }
        });

        //Add onClick() method for percentage "%" button.
        mCalcPerct = (Button) findViewById(R.id.buttonForPercentage);
        mCalcPerct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(isValidInput(true, false) || isValidInput(true, true))) {
                    Toast.makeText(CalculatorActivity.this, "Input Error: Operand 1 is used to do Percentage!",
                            Toast.LENGTH_SHORT).show();
                    clear_input2();
                } else {
                    double result = Double.valueOf(minput1.getText().toString()) / 100.000;
                    moutput1.setText(String.format("%.03f", result));
                }
            }
        });

        //Add onClick() method for square_root "√￣" button.
        mCalcSquar = (Button) findViewById(R.id.buttonForSquareRoot);
        mCalcSquar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(isValidInput(true, false) || isValidInput(true, true))) {
                    Toast.makeText(CalculatorActivity.this, "Input Error: Operand 1 is used to do Square Root",
                            Toast.LENGTH_SHORT).show();
                    clear_input2();
                } else {
                    double result = Math.sqrt(Double.valueOf(minput1.getText().toString()));
                    moutput1.setText(String.format("%.03f", result));
                }
            }
        });
    }



    private boolean isValidInput(boolean NeedPositOp1, boolean NeedPositOp2){
            String operand1String = minput1.getText().toString();
            String operand2String = minput2.getText().toString();

            //Check if operand 1 < 0 or if the string is empty
            //We use a float because the user can enter values that aren't integers.

            if (NeedPositOp1) {
                if (operand1String.length() == 0) {
                    return false;
                } else if (Double.parseDouble(operand1String) < 0.00) {
                    return false;
                }
            }

            //Do the same check for operand 2.
            if (NeedPositOp2) {
                if (operand2String.length() == 0) {
                    return false;
                } else if (Double.parseDouble(operand2String) < 0.00) {
                    return false;
                }
            }
            return true;
        }

    //Clear input text edit boxes for operand 1.
    private void clear_input1(){
        minput1.setText("");
    }
    //Clear input text edit boxes for operand 2
    private void clear_input2(){
        minput2.setText("");
    }

}
