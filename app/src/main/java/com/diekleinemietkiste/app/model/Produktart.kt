package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Die Daten einer Produktart, beispielsweise "Kinderbett".
 * Dies ist die zweite Filterebene im Shop.
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @param produktartId Die ID der Produktart.
 * @param bezeichnung Die Bezeichnung der Produktart.
 * @param kategorieId Die ID der zugehörigen Kategorie.
 */

@Entity(
    tableName = "produktart",
    foreignKeys = [
        ForeignKey(
            entity = Kategorie::class,
            parentColumns = ["kategorieId"],
            childColumns = ["kategorieId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class Produktart(
    @PrimaryKey
    @ColumnInfo(index = true)
    val produktartId: Int,
    val bezeichnung: String,
    @ColumnInfo(index = true)
    val kategorieId: Int,
)
