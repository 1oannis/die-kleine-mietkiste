package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Die Daten eines Produktes, beispielsweise "Wolkenkissen GmbH Kinderbett Typ 1".
 * Diese finden sich unter anderem auf der Produktseite wieder.
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @param produktId Die ID des Produktes.
 * @param bezeichnung Der Name des Produktes.
 * @param preis Der Preis des Produktes.
 * @param beschreibung Die Beschreibung des Produktes.
 * @param abmessungen Die Maße des Produktes
 * @param herstellerId Die ID des Herstellers.
 * @param produktartId Die ID der zugehörigen Produktart.
 */

@Entity(
    tableName = "produkt",
    foreignKeys = [
        ForeignKey(
            entity = Hersteller::class,
            parentColumns = ["herstellerId"],
            childColumns = ["herstellerId"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Produktart::class,
            parentColumns = ["produktartId"],
            childColumns = ["produktartId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class Produkt(
    @PrimaryKey
    @ColumnInfo(index = true)
    val produktId: Int,
    val bezeichnung: String,
    val preis: Int,
    val beschreibung: String,
    val abmessungen: String,
    @ColumnInfo(index = true)
    val herstellerId: Int,
    @ColumnInfo(index = true)
    val produktartId: Int,
)
