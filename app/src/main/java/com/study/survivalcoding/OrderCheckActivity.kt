package com.study.survivalcoding

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView

class OrderCheckActivity : AppCompatActivity(), View.OnClickListener {

    private var mResultTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_check)

        mResultTextView = findViewById<View>(R.id.result_text) as TextView

        findViewById<View>(R.id.cancel_button).setOnClickListener(this)
        findViewById<View>(R.id.ok_button).setOnClickListener(this)

        if (intent != null) {
            val result = intent.getStringExtra("result")
            val comment = intent.getStringExtra("comment")

            Log.d(
                TAG,
                "onCreate: $result, $comment"
            )
            mResultTextView!!.text = "$result\n\n 코멘트 : $comment"
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cancel_button ->                 // 현재 액티비티 종료
                finish()
            R.id.ok_button -> {
                // 이메일로 쏘기
                val addresses =
                    arrayOf("wahwah00@gmail.com")
                composeEmail(
                    addresses,
                    "주문 요청",
                    mResultTextView!!.text.toString()
                )
            }
        }
    }

    private fun composeEmail(
        addresses: Array<String>,
        subject: String,
        text: String
    ) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_TEXT, text)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    companion object {
        private val TAG = OrderCheckActivity::class.java.simpleName
    }
}
