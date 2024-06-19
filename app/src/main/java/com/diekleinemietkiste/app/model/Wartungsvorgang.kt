package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.Date

/**
 * Die Daten eines Wartungsvorganges an einem Artikel.
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @param wartungsvorgangId Die ID des Wartungsvorganges.
 * @param status Der Status des Wartunugsvorganges.
 * @param datum Das Datum, an dem der Wartungsvorgang durchgeführt wurde.
 * @param beschreibung Die Beschreibung des Wartungsvorganges.
 * @param kosten Die Kosten des Wartungsvorganges.
 * @param artikelId Die ID des gewarteten Artikels.
 * @param wartungstypId Die ID des zugehörigen Wartungstyps.
 */

@Entity(
    tableName = "wartungsvorgang",
    foreignKeys = [
        ForeignKey(
            entity = Artikel::class,
            parentColumns = ["artikelId"],
            childColumns = ["artikelId"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Wartungstyp::class,
            parentColumns = ["wartungstypId"],
            childColumns = ["wartungstypId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class Wartungsvorgang(
    @PrimaryKey
    @ColumnInfo(index = true)
    val wartungsvorgangId: Int,
    val status: String,
    val datum: Date,
    val beschreibung: String,
    val kosten: Int,
    @ColumnInfo(index = true)
    val artikelId: Int,
    @ColumnInfo(index = true)
    val wartungstypId: Int,
)
