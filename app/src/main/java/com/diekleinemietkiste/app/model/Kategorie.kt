package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Die Daten einer Kategorie, beispielsweise "Kinderzimmer"
 * Dies ist die erste Filterebene im Shop.
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @param kategorieId Die ID einer Kategorie.
 * @param bezeichnung Die Bezeichnung der Kategorie.
 */

@Entity(tableName = "kategorie")
data class Kategorie(
    @PrimaryKey
    @ColumnInfo(index = true)
    val kategorieId: Int,
    val bezeichnung: String,
)
