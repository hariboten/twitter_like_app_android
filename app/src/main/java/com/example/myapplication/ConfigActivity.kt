package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ConfigActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        findViewById<Button>(R.id.doneButton).setOnClickListener {
            val intent = Intent()
            intent.putExtra("name", findViewById<EditText>(R.id.nameInput).text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}