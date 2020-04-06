package com.study.survivalcoding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

class StartActivityForResultActivity : AppCompatActivity(),
    View.OnClickListener {
    private var mValueEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_for_result)

        mValueEditText = findViewById<View>(R.id.value_edt) as EditText

        findViewById<View>(R.id.submit_button).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        val intent = Intent(this, TargetActivity::class.java)
        intent.putExtra("value", mValueEditText!!.text.toString())
        // 일방통행
//        startActivity(intent);
        // 주거니 받거니
        startActivityForResult(
            intent,
            REQUEST_CODE_EXAMPLE
        )
        // requestCode 에 1000 을 넣는 이유
        // 하나의 인텐트가 아닌 여러 개의 인텐트에서 또는 버튼에서 데이터값이 넘어 올 경우
        // 구분이 되지 않기 때문에 코드의 번호를 지정한다ㅣ.
        // 너무 많아져 관리하기 어려워지면 1000 도 상수를 사용
    }

    // 받을 때 호출되는 콜백 메서드
    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_EXAMPLE && resultCode == Activity.RESULT_OK && data != null) {
            Log.d(
                TAG,
                "onActivityResult: $requestCode"
            )
            Log.d(
                TAG,
                "onActivityResult: $resultCode"
            )
            Log.d(
                TAG,
                "onActivityResult: $data"
            )
            val result = data.getStringExtra("result")
            val value = data.getIntExtra("int", -1)
            Toast.makeText(this, "$result, int: $value", Toast.LENGTH_SHORT).show()
        } else if (requestCode == REQUEST_CODE_MEMO) {
        } else if (requestCode == REQUEST_CODE_UPDATE_MEMO) {
        }
    }

    companion object {
        const val REQUEST_CODE_EXAMPLE = 1000
        const val REQUEST_CODE_MEMO = 2000
        const val REQUEST_CODE_UPDATE_MEMO = 3000
        private val TAG = StartActivityForResultActivity::class.java.simpleName
    }
}
