package com.nk.myapplication.firestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.nk.myapplication.databinding.ActivityFirestoreBinding

class FirestoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFirestoreBinding
    val firestore = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirestoreBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.buttonAdd.setOnClickListener{
            val userInfo = hashMapOf(
                "name" to binding.etName.text.toString(),
                "email" to binding.etEmail.text.toString(),
                "updates" to binding.updateswitch.isChecked,
                "age" to binding.etAge.text.toString().toIntOrNull()
            )

            firestore.collection("subscribers").document(binding.etEmail.text.toString()).set(userInfo)
                .addOnSuccessListener {
                    Toast.makeText(this, "Subscriber added", Toast.LENGTH_LONG).show()
                }.addOnFailureListener{
                    Toast.makeText(this, "Failed !!", Toast.LENGTH_LONG).show()
                }
        }

    }
}