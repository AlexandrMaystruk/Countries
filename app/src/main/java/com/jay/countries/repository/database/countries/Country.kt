package com.jay.countries.repository.database.countries

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Country {

    @PrimaryKey(autoGenerate = true)
    var id = 0L

    var name: String? = null
    var native: String? = null
    var phone: String? = null
    var currency: String? = null
    var isLiked = false
}