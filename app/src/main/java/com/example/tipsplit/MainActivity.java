package com.example.tipsplit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final DecimalFormat df = new DecimalFormat("0.00");
    float tBill,tipAmt,percent,actualTamount,over;
    int person;
    String dis ,tipAmtS,tBillS;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button btn_clear ;
    Button go;
    EditText NumberOfPeople ;
    EditText TotalBill;
    TextView TipAmount ;
    TextView TotalWithTip ;
    TextView TotalPerPerson ;
    TextView Overage ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
           Toast.makeText(this, "savedInstanceState is NULL", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "savedInstanceState is NOT NULL", Toast.LENGTH_LONG).show();


        btn_clear=findViewById(R.id.button2);
        NumberOfPeople=findViewById(R.id.editTextNumber2);
        TotalPerPerson = findViewById(R.id.textView12);
        Overage = findViewById(R.id.textView13);
        radioGroup = findViewById(R.id.radioGroup);
        go = findViewById(R.id.button);
        TipAmount=findViewById(R.id.textView4);
        TotalBill=findViewById(R.id.editTextNumber);
        TotalWithTip = findViewById(R.id.textView6);
        Overage = findViewById(R.id.textView13);




        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Toast.makeText(MainActivity.this,"Cleared",Toast.LENGTH_SHORT).show();
                NumberOfPeople.getText().clear();
                TotalBill.getText().clear();
                TipAmount.setText(null);
                TotalWithTip.setText(null);
                Overage.setText(null);
                TotalPerPerson.setText(null);
                radioGroup.clearCheck();
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View view) {
                if((TotalBill.getText().toString()).isEmpty() || (NumberOfPeople.getText().toString()).isEmpty())
                {
                    return ;
                }

                df.setRoundingMode(RoundingMode.UP);
                person = Integer.parseInt(NumberOfPeople.getText().toString());
                TotalPerPerson.setText(df.format(tBill/person));
                actualTamount = Float.parseFloat(TotalBill.getText().toString());
                dis = df.format(tBill/person);
                over = person * Float.parseFloat(dis);
                over = over - tBill;
                Overage.setText(String.format("%.2f",(over)));

            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("TipAmount", TipAmount.getText().toString());
        outState.putString("TotalPerPerson", TotalPerPerson.getText().toString());
        outState.putString("Overage", Overage.getText().toString());
        outState.putString("TotalWithTip",TotalWithTip.getText().toString());
        super.onSaveInstanceState(outState);

        Toast.makeText(MainActivity.this , "Data Saved" , Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        // Call super first
        super.onRestoreInstanceState(savedInstanceState);
        TipAmount.setText(savedInstanceState.getString("TipAmount"));
        TotalPerPerson.setText(savedInstanceState.getString("TotalPerPerson"));
        Overage.setText(savedInstanceState.getString("Overage"));
        TotalWithTip.setText(savedInstanceState.getString("TotalWithTip"));
        Toast.makeText(MainActivity.this , "Data Restored" , Toast.LENGTH_SHORT).show();
    }


    @SuppressLint({"DefaultLocale", "SetTextI18n", "NonConstantResourceId"})
    public void CalculatePercent(View v)
    {
        int rID = radioGroup.getCheckedRadioButtonId();

        radioButton = findViewById(rID);
        switch(radioButton.getId())
        {
            case R.id.radioButton1:  percent = 12;
            break ;
            case R.id.radioButton2: percent = 15;
            break ;
            case R.id.radioButton3: percent = 18 ;
            break ;
            case R.id.radioButton4: percent = 20;
            break;
            default: percent = 1;
        }
        if((TotalBill.getText().toString()).isEmpty())
        {
            radioGroup.clearCheck();
            return;
        }

        tBill = Float.parseFloat(TotalBill.getText().toString());
        tipAmtS = String.format("%.2f",(tBill * percent)/100);
        tipAmt = Float.parseFloat(tipAmtS);
        TipAmount.setText("$"+tipAmt);
        tBillS = String.format("%.2f",(tBill+tipAmt));
        tBill =  Float.parseFloat(tBillS);
        TotalWithTip.setText("$"+tBill);

    }
}