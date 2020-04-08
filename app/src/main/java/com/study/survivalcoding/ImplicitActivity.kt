package com.study.survivalcoding

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_implicit.*

class ImplicitActivity : AppCompatActivity(), View.OnClickListener {

    private var editTextPhone : EditText? = null
    private var editTextUrl : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        editTextPhone = findViewById<View>(R.id.edittext_phone) as EditText
        editTextUrl = findViewById<View>(R.id.edittext_url) as EditText

        findViewById<View>(R.id.edittext_phone).setOnClickListener(this)
        findViewById<View>(R.id.edittext_url).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_phone -> {
                val phoneNumber: String = editTextPhone!!.text.toString()
                dialTel(phoneNumber)
            }
            R.id.btn_url -> {
                val url: String = editTextUrl!!.text.toString()
                showUrl(url)
            }
        }
    }

    private fun showUrl(url: String) {
        var url = url
        if (!url.startsWith("http://")) {
            url = "http://$url"
        }
        val webpage = Uri.parse("http://$url")
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        intent.data = Uri.parse(url)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "수행할 앱이 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun dialTel(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "수행할 앱이 없습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}
