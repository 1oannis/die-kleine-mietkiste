package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Die Daten eines Herstellers, beispielsweise "Wolkenkissen GmbH".
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @param herstellerId Die ID des Herstellers.
 * @param name Der Name des Herstellers.
 */

@Entity(tableName = "hersteller")
data class Hersteller(
    @PrimaryKey
    @ColumnInfo(index = true)
    val herstellerId: Int,
    val name: String,
)
