package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result: ActivityResult -> if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val message = intent?.getStringExtra("message")
            Log.i("MainActivity", "from sub: ${message}")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input = findViewById<EditText>(R.id.messageaInput)
        val textView = findViewById<TextView>(R.id.textView)

        val button = findViewById<Button>(R.id.submitButton)
        button.setOnClickListener {
            Log.i("MainActivity", "button clicked")
            textView.text = input.text

            // SubActivityを開く intent
            val intent = Intent(applicationContext, SubActivity::class.java)
            intent.putExtra("message", input.text.toString())
            startForResult.launch(intent)
        }

        findViewById<Button>(R.id.openListButton).setOnClickListener {
            val intent = Intent(applicationContext, ListActivity::class.java)
            startActivity(intent)
        }
    }
}