package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TweetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet)

        val tweetForm = findViewById<EditText>(R.id.tweetForm)

        val button = findViewById<FloatingActionButton>(R.id.tweetButton).setOnClickListener {
            val intent = Intent()
            intent.putExtra("tweet", tweetForm.text.toString())
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}