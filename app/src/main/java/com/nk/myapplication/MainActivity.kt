package com.nk.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nk.myapplication.firestore.FirestoreActivity
import com.nk.myapplication.realtimedb.RealtimeDBActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    fun openRealtimeDBActivity(view: android.view.View) {
        startActivity(Intent(this, RealtimeDBActivity::class.java))
    }
    fun openFirestoreActivity(view: android.view.View) {
        startActivity(Intent(this, FirestoreActivity::class.java))
    }
}