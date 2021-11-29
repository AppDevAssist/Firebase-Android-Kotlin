package com.nk.myapplication.firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nk.myapplication.databinding.ActivityFirestoreBinding

class FirestoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirestoreBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirestoreBinding.inflate(layoutInflater)

        setContentView(binding.root)





    }
}