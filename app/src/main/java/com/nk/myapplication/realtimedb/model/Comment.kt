package com.nk.myapplication.realtimedb.model

import com.google.firebase.database.Exclude

data class Comment(val userId: String? = "", val comment: String? = ""){

    @Exclude
    fun getMap(): Map<String, Any?>{
        return mapOf(
            "userId" to userId,
            "comment" to comment
        )
    }
}
