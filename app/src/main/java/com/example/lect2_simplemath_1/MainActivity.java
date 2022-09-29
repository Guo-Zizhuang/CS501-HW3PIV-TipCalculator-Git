package com.example.lect2_simplemath_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.SeekBar;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import java.util.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView enter_Amount;

    private TextView Total;
    private TextView edtOp2;
    private TextView tip_percent;
    private TextView Tip;
    private TextView num_show;

    private SeekBar tip_SeekBar;

    private Button Button_One,Button_Two,Button_Three,Button_Four,Button_Five,Button_Six;
    private Button Button_Seven,Button_Eight,Button_Nine,Button_Null;
    private Button Button_point,Button_Baseline,Button_Equal,Button_Clear,Button_coma,Button_minus;
    private List<Float> num = new ArrayList<>();
    private List<String> op = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enter_Amount = (TextView) findViewById(R.id.enter_Amount);

        Button_One = findViewById(R.id.Button_One);
        Button_One.setOnClickListener(this);
        Button_Two = findViewById(R.id.Button_Two);
        Button_Two.setOnClickListener(this);
        Button_Three = findViewById(R.id.Button_Three);
        Button_Three.setOnClickListener(this);
        Button_Four = findViewById(R.id.Button_Four);
        Button_Four.setOnClickListener(this);
        Button_Five = findViewById(R.id.Button_Five);
        Button_Five.setOnClickListener(this);
        Button_Six = findViewById(R.id.Button_Six);
        Button_Six.setOnClickListener(this);
        Button_Seven = findViewById(R.id.Button_Seven);
        Button_Seven.setOnClickListener(this);
        Button_Eight = findViewById(R.id.Button_Eight);
        Button_Eight.setOnClickListener(this);
        Button_Nine = findViewById(R.id.Button_Nine);
        Button_Nine.setOnClickListener(this);
        Button_Null = findViewById(R.id.Button_Null);
        Button_Null.setOnClickListener(this);
        Button_Equal = findViewById(R.id.Button_Equal);
        Button_Equal.setOnClickListener(this);
        Button_Clear = findViewById(R.id.Button_Clear);
        Button_Clear.setOnClickListener(this);

        Button_coma = findViewById(R.id.Button_coma);
        Button_coma.setOnClickListener(this);
        Button_minus = findViewById(R.id.Button_minus);
        Button_minus.setOnClickListener(this);
        Button_Baseline = findViewById(R.id.Button_Baseline);
        Button_Baseline.setOnClickListener(this);
        Button_point = findViewById(R.id.Button_point);
        Button_point.setOnClickListener(this);

        tip_SeekBar = (SeekBar) findViewById(R.id.tip_SeekBar);

        Total = (TextView) findViewById(R.id.Total);
        edtOp2 = (TextView) findViewById(R.id.edtOp2);
        Tip = (TextView) findViewById(R.id.Tip);
        num_show = (TextView) findViewById(R.id.num_show);
        tip_percent = (TextView) findViewById(R.id.tip_percent);

        tip_SeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

                tip_percent.setText(String.valueOf(progress) + "%");
                String base_Amount = enter_Amount.getText().toString();
                String progress_s = String.valueOf(progress);
                String tip_Amount = String.format("%.2f", (Double.parseDouble(progress_s)/100) * Double.parseDouble(base_Amount));
                edtOp2.setText("$" + tip_Amount);
                String total_Amount = String.format("%.2f",(Double.parseDouble(tip_Amount) + Double.parseDouble(base_Amount)));
                num_show.setText("$" + total_Amount);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });







    }

    public void onClick(View view){
        String result = "0";
        Button button = (Button) view;
        String button_txt = button.getText().toString();
        String record_txt = "";
        if (enter_Amount.getText().toString() == "Enter Amount"){
            record_txt = "";
        }else{
            record_txt = enter_Amount.getText().toString();
        }
        if (button_txt.equals("C")){
            if (record_txt.length() == 0){
                record_txt = "";
            }else{
                record_txt = record_txt.substring(0,record_txt.length()-1);
            }
            enter_Amount.setText(record_txt);

        }else{
            record_txt = record_txt + button_txt;
            enter_Amount.setText( record_txt);
        }






    }



    String calculate (String record_txt){
        try{
            Context con = Context.enter();
            con.setOptimizationLevel(-1);
            Scriptable scrip = con.initStandardObjects();
            String result = con.evaluateString(scrip,record_txt,"Javascript", 1,null).toString();
            return result;
        }catch (Exception e){
            return "Error";
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();


    }
}