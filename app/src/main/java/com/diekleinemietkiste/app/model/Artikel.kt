package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Die Daten eines Artikels, der vermietet werden kann.
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @param artikelId Die ID des Artikels.
 * @param zustand Beschreibung des aktuellen Zustandes des Artikels.
 * @param produktId Die ID des Produktes des Artikels
 */

@Entity(
    tableName = "artikel",
    foreignKeys = [
        ForeignKey(
            entity = Produkt::class,
            parentColumns = ["produktId"],
            childColumns = ["produktId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class Artikel(
    @PrimaryKey
    @ColumnInfo(index = true)
    val artikelId: Int,
    val zustand: String,
    @ColumnInfo(index = true)
    val produktId: Int,
)
