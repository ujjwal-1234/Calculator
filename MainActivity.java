package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView result,solution;
    MaterialButton buttonC,buttonBracketOpen,buttonBracketClose;
    MaterialButton buttonDivide,buttonPlus,buttonMinus,buttonMultiply,buttonEqual;
    MaterialButton buttonAC,buttonDot;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        solution=findViewById(R.id.solution);
        assignId(buttonC,R.id.Button_C);
        assignId(buttonAC,R.id.Button_AC);
        assignId(buttonDot,R.id.Button_dot);
        assignId(buttonBracketClose,R.id.Button_bracketclose);
        assignId(buttonBracketOpen,R.id.Button_bracketopen);
        assignId(buttonPlus,R.id.Button_plus);
        assignId(buttonMinus,R.id.Button_minus);
        assignId(buttonMultiply,R.id.Button_multiply);
        assignId(buttonDivide,R.id.Button_divide);
        assignId(buttonEqual,R.id.Button_equal);
        assignId(button0,R.id.Button_0);
        assignId(button1,R.id.Button_1);
        assignId(button2,R.id.Button_2);
        assignId(button3,R.id.Button_3);
        assignId(button4,R.id.Button_4);
        assignId(button5,R.id.Button_5);
        assignId(button6,R.id.Button_6);
        assignId(button7,R.id.Button_7);
        assignId(button8,R.id.Button_8);
        assignId(button9,R.id.Button_9);



    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton btn=(MaterialButton) v;
        String buttonText=btn.getText().toString();
        String dataToCalculate=solution.getText().toString();

        if(buttonText.equals("AC")){
            solution.setText(" ");
            result.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solution.setText((result.getText()));
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate=dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate=dataToCalculate+buttonText;
        }
        solution.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);
        if(!finalResult.equals("ERROR")){
            result.setText(finalResult);
        }
    }
    String getResult(String data){
        try{
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith((".0"))){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "ERROR";
        }
    }
}
