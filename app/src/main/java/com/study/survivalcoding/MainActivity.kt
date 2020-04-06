package com.study.survivalcoding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    // MainActivity 클래스 이름을 String 으로 변환해줌
    private val TAG = MainActivity::class.java.simpleName
    private val QUANTITY_MIN = 0
    private val QUANTITY_MAX = 10
    private val COFFEE_PRICE = 3000

    private var mMinusButton: Button? = null
    private var mPlusButton: Button? = null
    private var mQuantityTextView: TextView? = null

    // result 를 표기할 TextView
    private var mResultTextView: TextView? = null
    private var mOrderButton: Button? = null

    // 수량
    private var mQuantity = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 초기화
        init()

        // 레이아웃 표시
        setContentView(R.layout.activity_coffee)

        // 레이아웃에서 특정 id를 인스턴스 변수와 연결
        mMinusButton = findViewById<View>(R.id.btn_minus) as Button
        mPlusButton = findViewById<View>(R.id.btn_plus) as Button
        mQuantityTextView = findViewById<View>(R.id.txt_quantity) as TextView
        mResultTextView = findViewById<View>(R.id.txt_result) as TextView
        mOrderButton = findViewById<View>(R.id.btn_order) as Button

        // 무명클래스
        mMinusButton!!.setOnClickListener { // debug
            //                Log.d (TAG, "마이너스 버튼 클릭");
            //                Log.v (TAG, "일반로그");
            //                Log.e (TAG, "에러로그");
            //                Log.i (TAG, "정보로그");
            //                Log.w (TAG, "경고로그");

            // Toast 메시지
            //                Toast.makeText(MainActivity.this, "메세지", Toast.LENGTH_SHORT).show();
            mQuantity--
            // 0보다 작을경우 0으로 제한
            if (mQuantity < QUANTITY_MIN) {
                mQuantity = QUANTITY_MIN
            }
            displayResult()
        }
        mPlusButton!!.setOnClickListener {
            mQuantity++
            // 최대 10 개로 제한
            if (mQuantity > QUANTITY_MAX) {
                mQuantity = QUANTITY_MAX
            }
            displayResult()
        }
        mOrderButton!!.setOnClickListener {
            val message = mResultTextView!!.text.toString()
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    // 중복된 코드를 밖으로 뺌
    private fun displayResult() {
        mQuantityTextView!!.text = "" + mQuantity
        // "" + mQuantity int 가 들어오더라도 강제로 string 으로 만들어줌
        val result = """
            가격 : ${COFFEE_PRICE * mQuantity}원
            감사합니다
            """.trimIndent()
        mResultTextView!!.text = result
    }

    private fun init() {
        mQuantity = 0
    }
}
