package com.nk.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //region initialize
        setContentView(R.layout.activity_main)

        val myRef = Firebase.database.reference
        val userId = "userid-dummy"

        val name = findViewById<TextView>(R.id.nameEditText)
        val email = findViewById<TextView>(R.id.emailEditText)
        val comment = findViewById<TextView>(R.id.commentEditText)

        val addButton = findViewById<Button>(R.id.addButton)
        val updateButton = findViewById<Button>(R.id.updateButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)
        val readButton = findViewById<Button>(R.id.buttonRead)
        val commentButton = findViewById<Button>(R.id.addCommentButton)
        //endregion

        //region read-write-update-delete
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
        //endregion

        //region list-data-listener
        val TAG = "FirebaseRealtimeDBLog"

        val commentListner = object: ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val key = snapshot.key
                val data = snapshot.getValue<Comment>()

                Log.d(TAG, "onChildAdded key $key, comment: $data")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                val key = snapshot.key
                val data = snapshot.getValue<Comment>()

                Log.d(TAG, "onChildChanged key $key, comment: $data")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val key = snapshot.key
                val data = snapshot.getValue<Comment>()

                Log.d(TAG, "onChildRemoved key $key, comment: $data")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                val key = snapshot.key
                val data = snapshot.getValue<Comment>()

                Log.d(TAG, "onChildMoved key $key, comment: $data")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "onCancelled")
            }
        }
        myRef.child("comments").addChildEventListener(commentListner)
        //endregion

        //region list-data
        commentButton.setOnClickListener{
            val key = myRef.child("comments").push().key

            if(key != null){
                val commentObj = Comment(userId, comment.text.toString())
                val commentMap = commentObj.getMap()

                val commentToAdd = hashMapOf<String, Any>(
                    "/comments/$key" to commentMap
                )

                myRef.updateChildren(commentToAdd)
            }
        }
        //endregion
    }
}