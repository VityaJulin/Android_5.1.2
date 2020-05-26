package com.example.android_312;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {
    private Button num9;
    private Button num8;
    private Button num7;
    private Button num6;
    private Button num5;
    private Button num4;
    private Button num3;
    private Button num2;
    private Button num1;
    private Button num0;
    private Button numDot;
    private TextView calcScreen;
    public StringBuffer inputSequence;
    private Button clear;
    private Button sign;
    private Button percent;
    private Button result;
    private Button plus;
    private Button minus;
    private Button division;
    private Button multiply;
    private BigDecimal calculate;
    public ImageView imageBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        Bundle arguments = getIntent().getExtras();
        String name = arguments.get("file name").toString();
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        imageBackground.setImageBitmap(bitmap);

        inputSequence = new StringBuffer();

        num9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("9");
                calcScreen.setText(inputSequence);
            }
        });

        num8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("8");
                calcScreen.setText(inputSequence);
            }
        });

        num7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("7");
                calcScreen.setText(inputSequence);
            }
        });

        num6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("6");
                calcScreen.setText(inputSequence);
            }
        });
        num5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("5");
                calcScreen.setText(inputSequence);
            }
        });
        num4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("4");
                calcScreen.setText(inputSequence);
            }
        });
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("3");
                calcScreen.setText(inputSequence);
            }
        });
        num2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("2");
                calcScreen.setText(inputSequence);
            }
        });
        num1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("1");
                calcScreen.setText(inputSequence);
            }
        });
        num0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("0");
                calcScreen.setText(inputSequence);
            }
        });
        numDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append(".");
                calcScreen.setText(inputSequence);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.setLength(0);
                calcScreen.setText(inputSequence);
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("#");
                calcScreen.setText(inputSequence);
            }
        });
        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("%");
                calcScreen.setText(inputSequence);
            }
        });
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("/");
                calcScreen.setText(inputSequence);
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("*");
                calcScreen.setText(inputSequence);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("-");
                calcScreen.setText(inputSequence);
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputSequence.append("+");
                calcScreen.setText(inputSequence);
            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getResult();
                calcScreen.setText(String.valueOf(calculate));
                inputSequence.setLength(0);
            }
        });

    }

    private void initView() {
        num9 = findViewById(R.id.btn_9);
        num8 = findViewById(R.id.btn_8);
        num7 = findViewById(R.id.btn_7);
        num6 = findViewById(R.id.btn_6);
        num5 = findViewById(R.id.btn_5);
        num4 = findViewById(R.id.btn_4);
        num3 = findViewById(R.id.btn_3);
        num2 = findViewById(R.id.btn_2);
        num1 = findViewById(R.id.btn_1);
        num0 = findViewById(R.id.btn_0);
        numDot = findViewById(R.id.btn_dot);
        calcScreen = findViewById(R.id.calc_screen);
        clear = findViewById(R.id.btn_clear);
        sign = findViewById(R.id.btn_sign);
        percent = findViewById(R.id.btn_percent);
        result = findViewById(R.id.btn_result);
        plus = findViewById(R.id.btn_plus);
        minus = findViewById(R.id.btn_minus);
        division = findViewById(R.id.btn_division);
        multiply = findViewById(R.id.btn_multiply);
        imageBackground = findViewById(R.id.image_background);
        //imageBackgroundLand = findViewById(R.id.image_background_land);
    }

    private void getResult() {
        List<String> splitNumbers = new ArrayList<>(Arrays.asList(inputSequence.toString().split("[+*/\\-]")));
        List<String> splitSigns = new ArrayList<>(Arrays.asList(inputSequence.toString().split("[0-9]*[.,]?[0-9]")));

        Queue<String> signs = new ArrayDeque<>();
        Queue<BigDecimal> numbers = new ArrayDeque<>();

        Iterator numberIterator = splitNumbers.iterator();
        Iterator signIterator = splitSigns.iterator();

        while (numberIterator.hasNext()) {
            BigDecimal num = new BigDecimal(Double.valueOf((String.valueOf(numberIterator.next()))));
            numbers.add(num);
        }

        while (signIterator.hasNext()) {
            String sign = String.valueOf(signIterator.next());
            signs.add(sign);
        }

        calculate = numbers.poll();
        while (!signs.isEmpty()) {
            switch (signs.poll()) {
                case "/":
                    calculate = calculate.divide(numbers.poll()).setScale(8, BigDecimal.ROUND_HALF_EVEN);
                    break;
                case "*":
                    calculate = calculate.multiply(numbers.poll()).setScale(8, BigDecimal.ROUND_HALF_EVEN);
                    break;
                case "-":
                    calculate = calculate.subtract(numbers.poll()).setScale(8, BigDecimal.ROUND_HALF_EVEN);
                    break;
                case "+":
                    calculate = calculate.add(numbers.poll()).setScale(8, BigDecimal.ROUND_HALF_EVEN);
                    break;
                default:
                    break;
            }
        }

    }
}
