package com.study.survivalcoding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class ManageMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_main)

        if (intent != null) {
            val id = intent.getStringExtra("id")
            val password = intent.getStringExtra("pass")

            Toast.makeText(this, "id: $id,pw: $password", Toast.LENGTH_SHORT).show()
        }

//        findViewById<View>(R.id.btn_manage_customer).setOnClickListener(this)
//        findViewById<View>(R.id.btn_manage_sales).setOnClickListener(this)
//        findViewById<View>(R.id.btn_manage_product).setOnClickListener(this)
        //귀찮아...  여러개일경우 귀찮으니까 xml 에 onClick 속성입력하자.
    }

    override fun onClick(view: View) {
        val text = (view as Button).text.toString()

        val intent = Intent()
        intent.putExtra("result", text)

        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}

