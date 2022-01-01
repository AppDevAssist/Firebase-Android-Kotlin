package com.nk.myapplication.firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nk.myapplication.databinding.ActivityFirestoreBinding
import com.nk.myapplication.firestore.models.User

class FirestoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirestoreBinding
    val firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirestoreBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener {
            val userInfo = User(
                binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.updateswitch.isChecked,
                binding.etAge.text.toString().toIntOrNull()
            )

            val dbRef = firestore.collection("subscribers").document("test-path")


            dbRef.set(userInfo)
                .addOnSuccessListener {
                    Toast.makeText(this, "Subscriber added", Toast.LENGTH_LONG).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed !!", Toast.LENGTH_LONG).show()
                }
        }

    }
}