package com.nk.myapplication.firestore.models

import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import java.util.*

data class User(
    val name: String? = null, val email: String? = null,
    @field:JvmField
    val isUpdate: Boolean? = false, val age: Int? = -1,
    val createdAt: Timestamp = Timestamp(Date()),
    val updatedAt: FieldValue = FieldValue.serverTimestamp()
)
