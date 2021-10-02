package com.nk.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myRef = Firebase.database.reference
        val userId = "userid-dummy"

        val name = findViewById<TextView>(R.id.nameEditText)
        val email = findViewById<TextView>(R.id.emailEditText)

        val addButton = findViewById<Button>(R.id.addButton)
        val updateButton = findViewById<Button>(R.id.updateButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val readButton = findViewById<Button>(R.id.buttonRead)

        addButton.setOnClickListener{
            val userData = UserData(name.text.toString(), email.text.toString())

            myRef.child("users").child(userId).setValue(userData).addOnSuccessListener {
                Toast.makeText(this, "User data saved !", Toast.LENGTH_SHORT).show();
            }.addOnFailureListener{

            }
        }

        updateButton.setOnClickListener{
            val userData = UserData(name.text.toString(), email.text.toString())

            myRef.child("users").child(userId).updateChildren(userData.getMap()).addOnSuccessListener {
                Toast.makeText(this, "User data updated !", Toast.LENGTH_SHORT).show();
            }.addOnFailureListener{

            }

        }

        deleteButton.setOnClickListener{
            myRef.child("users").child(userId).removeValue().addOnSuccessListener {
                Toast.makeText(this, "User data deleted !", Toast.LENGTH_SHORT).show();
            }.addOnFailureListener{

            }
        }

        readButton.setOnClickListener{
            myRef.child("users").child(userId).get().addOnSuccessListener {
                Toast.makeText(this, "User data ${it.value}", Toast.LENGTH_SHORT).show();
            }.addOnFailureListener{

            }
        }

    }
}