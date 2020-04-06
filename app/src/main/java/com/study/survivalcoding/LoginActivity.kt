package com.study.survivalcoding

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private var editTextId: EditText? = null
    private var editTextPass: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextId = findViewById<EditText>(R.id.edt_id)
        editTextPass = findViewById<EditText>(R.id.edt_password)

        findViewById<View>(R.id.btn_login).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(this, ManageMainActivity::class.java)
        intent.putExtra("id", editTextId!!.text.toString())
        intent.putExtra("pass", editTextPass!!.text.toString())
        startActivityForResult(intent,REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            val text = data.getStringExtra("result")
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    companion object {
        const val REQUEST_CODE = 1000
    }
}
