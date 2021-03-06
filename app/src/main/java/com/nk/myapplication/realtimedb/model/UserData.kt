package com.nk.myapplication.realtimedb.model

import com.google.firebase.database.Exclude

data class UserData(val name: String? = "", val email: String? = ""){

    @Exclude
    fun getMap(): Map<String, Any?>{
        return mapOf(
            "name" to name
        )
    }
}
