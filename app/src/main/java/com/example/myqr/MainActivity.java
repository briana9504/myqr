package com.example.myqr;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    //Activity는 화면에 표시되는 UI 구성을 위해 가장 기본이 되는 요소,  앱 실행 시 지정된 Activity를 실행하여 사용자에게 UI를 표시하게 됩니다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {//java의 main과 비슷해 보이나 클래스당 필요에 따라 전부 존재할 수 있다.
        super.onCreate(savedInstanceState);//부모 클래스에 있는 onCreate()함수를 호출
        //setContentView(R.layout.activity_main);
        //  -> 화면에 무엇을 보여줄지에 대한 설정 그러나 여기서는 카메라를 사용해 qr코드 스캔할 것이므로 필요x

        new IntentIntegrator(this).initiateScan();//this 옆의 activity? -> intelliJ의 타입을 보여주는 기능
        //화면 스캔
    }

    //QRcode가 스캔되면 QR리더기는 종료되고 원래의 activity로 돌아와 onActivityResult를 호출
    //onActivityResult : main액티비티에서 sub액티비티를 호출하여 넘어갔다가, 다시 main 액티비티로 돌아올때 사용되는 기본 메소드 이다.
    //Intent : 액티비티끼리 서로 호출하기 위해서 필요한 통신 장치가 인텐트(Intent)이다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {//내용물 null : 취소 , android는 text타입을 사용(String x)
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                // toast -> alert(경고창) 창
            } else {//null이 아니면, getContnets 가져와서 출력을 해라
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                // todo
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}