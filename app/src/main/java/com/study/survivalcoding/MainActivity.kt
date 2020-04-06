package com.study.survivalcoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var mQuantityTextView: TextView? = null
    private var mResultTextView: TextView? = null
    private var mCreamCheckBox: CheckBox? = null
    private var mCommentEditText: EditText? = null

    // 수량
    private var mQuantity = 0

    // 휘핑크림
    private var mIsCream = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 초기화
        init()

        // 레이아웃 표시
        setContentView(R.layout.activity_coffee)

        // 레이아웃에서 특정 id를 인스턴스 변수와 연결
        mQuantityTextView = findViewById<View>(R.id.quantity_text) as TextView
        mResultTextView = findViewById<View>(R.id.result_text) as TextView
        mCreamCheckBox = findViewById<View>(R.id.cream_check) as CheckBox
        mCommentEditText = findViewById<View>(R.id.comment_edit) as EditText

        // 무명클래스
        findViewById<View>(R.id.minus_button).setOnClickListener(this)
        findViewById<View>(R.id.plus_button).setOnClickListener(this)
        findViewById<View>(R.id.order_button).setOnClickListener(this)

        mCreamCheckBox!!.setOnCheckedChangeListener { buttonView, isChecked ->
            mIsCream = isChecked
            displayResult()
        }
    }

    // 중복된 코드를 밖으로 뺌
    private fun displayResult() {
        mQuantityTextView!!.text = "" + mQuantity
        // "" + mQuantity int 가 들어오더라도 강제로 string 으로 만들어줌
        var total = COFFEE_PRICE * mQuantity
        if (mIsCream) {
            total += CREAM_PRICE
        } else {
            total -= CREAM_PRICE
        }
        val result = String.format("가격 : %d원\n수량 : %d개\n휘핑크림 : %s\n감사합니다",
            total,
            mQuantity,
            mIsCream)
        mResultTextView!!.text = result
    }

    private fun init() {
        mQuantity = 0
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.minus_button -> {
                mQuantity--
                if (mQuantity < QUANTITY_MIN) {
                    mQuantity = QUANTITY_MIN
                }
                displayResult()
            }
            R.id.plus_button -> {
                mQuantity++
                if (mQuantity > QUANTITY_MAX) {
                    mQuantity = QUANTITY_MAX
                }
                displayResult()
            }
            R.id.order_button -> {
                val message = mResultTextView!!.text.toString()

                // OrderCheckActivity 화면을 시작
                val intent = Intent(this, OrderCheckActivity::class.java)
                // 데이터 담기
                intent.putExtra("result", message)
                intent.putExtra("comment", mCommentEditText!!.text.toString())
                startActivity(intent)
            }
        }
    }

    companion object {
        // MainActivity 클래스 이름을 String 으로 변환해줌
        private val TAG = MainActivity::class.java.simpleName
        const val QUANTITY_MIN = 0
        const val QUANTITY_MAX = 10
        const val COFFEE_PRICE = 3000
        const val CREAM_PRICE = 500
    }
}
