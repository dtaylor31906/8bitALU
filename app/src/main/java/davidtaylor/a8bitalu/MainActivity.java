package davidtaylor.a8bitalu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.TextView;

import davidtaylor.a8bitalu.EightBit.EightBitALU;
import davidtaylor.a8bitalu.EightBit.EightBitWord;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    CheckedTextView a0,b0;
    CheckedTextView a1,b1;
    CheckedTextView a2,b2;
    CheckedTextView a3,b3;
    CheckedTextView a4,b4;
    CheckedTextView a5,b5;
    CheckedTextView a6,b6;
    CheckedTextView a7,b7,op;
    Button goButton;
    EightBitALU alu = new EightBitALU();
    TextView tvSolution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init() {
        a0 = (CheckedTextView) findViewById(R.id.ctvA0);
        a0.setOnClickListener((this));
        a1 = (CheckedTextView) findViewById(R.id.ctvA1);
        a1.setOnClickListener((this));
        a2 = (CheckedTextView) findViewById(R.id.ctvA2);
        a2.setOnClickListener((this));
        a3 = (CheckedTextView) findViewById(R.id.ctvA3);
        a3.setOnClickListener((this));
        a4 = (CheckedTextView) findViewById(R.id.ctvA4);
        a4.setOnClickListener((this));
        a5 = (CheckedTextView) findViewById(R.id.ctvA5);
        a5.setOnClickListener((this));
        a6 = (CheckedTextView) findViewById(R.id.ctvA6);
        a6.setOnClickListener((this));
        a7 = (CheckedTextView) findViewById(R.id.ctvA7);
        a7.setOnClickListener((this));
        b0 = (CheckedTextView) findViewById(R.id.ctvB0);
        b0.setOnClickListener((this));
        b1 = (CheckedTextView) findViewById(R.id.ctvB1);
        b1.setOnClickListener((this));
        b2 = (CheckedTextView) findViewById(R.id.ctvB2);
        b2.setOnClickListener((this));
        b3 = (CheckedTextView) findViewById(R.id.ctvB3);
        b3.setOnClickListener((this));
        b4 = (CheckedTextView) findViewById(R.id.ctvB4);
        b4.setOnClickListener((this));
        b5 = (CheckedTextView) findViewById(R.id.ctvB5);
        b5.setOnClickListener((this));
        b6 = (CheckedTextView) findViewById(R.id.ctvB6);
        b6.setOnClickListener((this));
        b7 = (CheckedTextView) findViewById(R.id.ctvB7);
        b7.setOnClickListener((this));

        op = (CheckedTextView) findViewById(R.id.ctvOperation);
        op.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                CheckedTextView ctv = (CheckedTextView) v;
                if (ctv.isChecked()) {
                    ctv.toggle();
                    ctv.setText("Add");
                } else {
                    ctv.toggle();
                    ctv.setText("Subtract");
                }
            }
        });
        tvSolution = (TextView) findViewById(R.id.tvSolution);
        goButton = (Button) findViewById(R.id.button);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) 
            {
                String resultStr;
                boolean[] temp = pullWordA();
                alu.setWordA(new EightBitWord(temp));
                temp = pullWordB();
                alu.setWordB(new EightBitWord(temp));
                alu.execute(op.isChecked());
                EightBitWord result = alu.getWordOutput();

                if(!op.isChecked()) // if adding
                {
                    if(alu.getAlu2().getC3().getValue())//if carryout is 1 display it
                    {
                        resultStr = "1"+result.getBinString();
                        tvSolution.setText(resultStr);
                    }
                    else{
                        resultStr = result.getBinString();
                        tvSolution.setText(resultStr);
                    }
                }
                else
                {
                    resultStr = result.getBinString();
                    tvSolution.setText(resultStr);
                }


            }
        });

    }

    private boolean[] pullWordB() 
    {
        boolean[] result = new boolean[8];
        result[0] = b0.isChecked();
        result[1] = b1.isChecked();
        result[2] = b2.isChecked();
        result[3] = b3.isChecked();
        result[4] = b4.isChecked();
        result[5] = b5.isChecked();
        result[6] = b6.isChecked();
        result[7] = b7.isChecked();
        return result;
    }


    private boolean[] pullWordA() 
    {
        boolean[] result = new boolean[8];
        result[0] = a0.isChecked();
        result[1] = a1.isChecked();
        result[2] = a2.isChecked();
        result[3] = a3.isChecked();
        result[4] = a4.isChecked();
        result[5] = a5.isChecked();
        result[6] = a6.isChecked();
        result[7] = a7.isChecked();
        return result;
    }

    @Override
    public void onClick(View v) {
        {
            CheckedTextView ctv = (CheckedTextView) v;
            if (ctv.isChecked()) {
                ctv.toggle();
                ctv.setText("0");
            } else {
                ctv.toggle();
                ctv.setText("1");
            }
        }
    }
}
