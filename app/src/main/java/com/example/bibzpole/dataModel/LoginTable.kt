package com.example.bibzpole.dataModel

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class LoginTable(
    val name :String? = null,
    val user :String? = null,
    val password :String? = null,
    val loginStatus :Int? = null
){
    @PrimaryKey(autoGenerate = true)
    var Id :Int = 0
}