package com.study.survivalcoding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class TargetActivity : AppCompatActivity(), View.OnClickListener {
    private var mValueTextView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_target)
        mValueTextView = findViewById<View>(R.id.value_text) as TextView
        findViewById<View>(R.id.result_button).setOnClickListener(this)
        if (intent != null) {
            val value = intent.getStringExtra("value")
            mValueTextView!!.text = value
        }
    }

    override fun onClick(v: View) {
        // 결과 전달
        val intent = Intent()
        intent.putExtra("result", "이것은 내가 지정한 문구다")
        intent.putExtra("int", 50)
        setResult(Activity.RESULT_OK, intent)
        //        setResult(RESULT_CANCELED);
        finish()
    }
}
