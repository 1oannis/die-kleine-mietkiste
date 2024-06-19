package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Date

/**
 * Die Daten eines konkreten Mietverhältnisses über ein Mietobjekt.
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @param mieteId ID einer Miete.
 * @param startdatum Das Startdatum einer Miete.
 * @param enddatum Das Enddatum der Miete.
 * @param lieferdatum Das Lieferdatum des Artikels der Miete.
 * @param aufbauservice Gibt an, ob der Aufbauservice gebucht wurde.
 * @param abbauservice Gibt an, ob der Abbauservice gebucht wurde.
 * @param kundeId Die ID des Mieters.
 * @param artikelId Die ID des vermietetem Artikels.
 */

@Entity(
    tableName = "miete",
    foreignKeys = [
        ForeignKey(
            entity = Kunde::class,
            parentColumns = ["kundeId"],
            childColumns = ["kundeId"],
            onDelete = ForeignKey.CASCADE,
        ),
        ForeignKey(
            entity = Artikel::class,
            parentColumns = ["artikelId"],
            childColumns = ["artikelId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class Miete(
    @PrimaryKey
    @ColumnInfo(index = true)
    val mieteId: Int,
    val startdatum: Date,
    val enddatum: Date,
    val lieferdatum: Date,
    val aufbauservice: Boolean,
    val abbauservice: Boolean,
    @ColumnInfo(index = true)
    val kundeId: Int,
    @ColumnInfo(index = true)
    val artikelId: Int,
)

/**
 * Converter Classes since SQLite does not support date attributes
 * https://developer.android.com/training/data-storage/room/referencing-data#type-converters
 */

class DateConverters {
    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }
}
