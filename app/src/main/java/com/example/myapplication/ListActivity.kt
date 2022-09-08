package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListActivity : AppCompatActivity() {
    private val dataSet = mutableListOf<Tweet>()
    private val adopter = CustomAdopter(dataSet)
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult -> if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            val message = intent?.getStringExtra("tweet")
            dataSet.add(Tweet(name, message!!))
            adopter.notifyDataSetChanged()
        }
    }
    private val startConfig = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result: ActivityResult -> if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            intent?.getStringExtra("name")?.let {
                name = it
            }
        }
    }
    private var name = "Ninja"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(findViewById(R.id.my_toolbar))

        val rvList = findViewById<RecyclerView>(R.id.rvList)
        rvList.adapter = adopter
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager(this).getOrientation())
        rvList.addItemDecoration(dividerItemDecoration)
        rvList.layoutManager = LinearLayoutManager(applicationContext)

        findViewById<FloatingActionButton>(R.id.openFormButton).setOnClickListener {
            val intent = Intent(applicationContext, TweetActivity::class.java)
            startForResult.launch(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(applicationContext, ConfigActivity::class.java)
        startConfig.launch(intent)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    class CustomAdopter(private val dataSet: List<Tweet>) : RecyclerView.Adapter<CustomAdopter.ViewHolder>() {
        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            val content: TextView
            val name: TextView

            init {
                content = view.findViewById(R.id.tweetView)
                name = view.findViewById(R.id.nameView)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_cell, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.content.text = dataSet[position].content
            holder.name.text = dataSet[position].user
        }

        override fun getItemCount(): Int {
            return dataSet.size
        }
    }
}