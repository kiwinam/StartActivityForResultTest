package com.example.charlie.startactivityforresulttest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by charlie on 2018. 2. 8
 *
 * EditText 에 입력한 두 정수를 MainActivity 에서 선택한 더하기 혹은 빼기 연산합니다.
 * 연산이 완료되면 CalculateActivity 를 종료하고 MainActivity 로 결과 값을 되돌려 줍니다.
 */

public class CalculateActivity extends AppCompatActivity {

    private EditText num1;
    private EditText num2;

    // MainActivity 에서 선택한 연산이 더하기 연산인지 빼기 연산인지 구분하기 위한 변수
    // 더하기라면 true 를 빼기라면 false 를 전달 받음.
    private boolean isPlus;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // MainActivity 에서 선택한 연산이 어떤 것인지 확인 (더하기 , 빼기)
        isPlus = getIntent().getBooleanExtra("isPlus",false);

        // 숫자 입력하는 EditText 와 연산하는 버튼 뷰 설정
        num1 = findViewById(R.id.calNum1Et);
        num2 = findViewById(R.id.calNum2Et);

        // 두 숫연산을 실행하고 MainActivity 로 결과를 리턴함.
        // 1. 숫자 값 둘 중 하나가 없다면 토스트 메시지를 띄우고 아니라면 연산을 실행함
        // 2. 인텐트로 넘겨받은 isPlus 의 값의 따라 더하기,빼기 연산을 함.
        // 3. 연산한 결과 값을 resultIntent 에 담아서 MainActivity 로 전달하고 현재 Activity 는 종료.
        Button calBtn = findViewById(R.id.calBtn);
        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. 숫자 값 둘 중 하나가 없다면 토스트 메시지를 띄우고 아니라면 연산을 실행함
                if(num1.getText() == null || num2.getText() == null){
                    Toast.makeText(CalculateActivity.this,"연산할 값이 없습니다. 확인해주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    int n1 = Integer.parseInt(num1.getText().toString());
                    int n2 = Integer.parseInt(num2.getText().toString());
                    int result;

                    // 2. 인텐트로 넘겨받은 isPlus 의 값의 따라 더하기,빼기 연산을 함.
                    if(isPlus){
                        result = n1 + n2;
                    }else{
                        result = n1 - n2;
                    }

                    // 3. 연산한 결과 값을 resultIntent 에 담아서 MainActivity 로 전달하고 현재 Activity 는 종료.
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("result","연산 결과는 "+result+" 입니다.");
                    setResult(RESULT_OK,resultIntent);
                    finish();
                }
            }
        });
    }
}
