package com.example.charlie.startactivityforresulttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by charlie on 2018. 2. 8
 * startActivityForResult 를 학습하기 위한 Test Application
 *
 * MainActivity 에서 더하기 혹은 빼기를 선택하면 CalculateActivity 에서 연산할 숫자를 입력하고 결과를 리턴한다.
 * 리턴된 결과는 MainActivity 에 표시된다.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mainResultTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // find view
        ImageView mainPlusIv = findViewById(R.id.mainPlusIv);
        ImageView mainMinusIv = findViewById(R.id.mainMinusIv);
        mainResultTv = findViewById(R.id.mainResultTv);

        // 더하기, 빼기 ClickListener 설정
        // Override 된 onClick 메소드에서 클릭 처리.
        mainPlusIv.setOnClickListener(this);
        mainMinusIv.setOnClickListener(this);

    }

    // 더하기나 빼기를 할 때 CalculateActivity 에서 연산의 구분을 두기 위해 인텐트로 Flag 를 넘겨줍니다.
    @Override
    public void onClick(View v) {
        Intent calIntent = new Intent(this, CalculateActivity.class);
        switch (v.getId()){
            case R.id.mainPlusIv:
                calIntent.putExtra("isPlus",true);
                break;
            case R.id.mainMinusIv:
                calIntent.putExtra("isPlus",false);
                break;
        }
        startActivityForResult(calIntent,3000);
    }


    // CalculateActivity 에서 처리된 결과를 받는 메소드
    // 처리된 결과 코드 (resultCode) 가 RESULT_OK 이면 requestCode 를 판별해 결과 처리를 진행한다.
    // CalculateActivity 에서 처리 결과가 담겨온 데이터를 TextView 에 보여준다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                // MainActivity 에서 요청할 때 보낸 요청 코드 (3000)
                case 3000:
                    mainResultTv.setText(data.getStringExtra("result"));
                    break;
            }
        }
    }
}
