package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        val message = intent.getStringExtra("message")
        Log.i("SubActivity", "recieve = ${message}")

        val subMessageInput = findViewById<EditText>(R.id.subMessageInput)

        message?.let {
            subMessageInput.setText(it)
        }

        findViewById<Button>(R.id.subSubmitButton).setOnClickListener {
            val intent = Intent()
            intent.putExtra("message", subMessageInput.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}