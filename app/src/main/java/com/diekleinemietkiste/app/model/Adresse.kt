package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Die Adressdaten eines Kunden.
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @property adresseId ID einer Adresse.
 * @property strasse Die Straße des Adressdatensatzes.
 * @property hausnummer Die Hausnummer des Adressdatensatzes.
 * @property plz Die Postleitzahl des Adressdatensatzes.
 * @property kundeId ID des zugehörigen Adressdatensatzes.
 */

@Entity(
    tableName = "adresse",
    foreignKeys = [
        ForeignKey(
            entity = Kunde::class,
            parentColumns = arrayOf("kundeId"),
            childColumns = arrayOf("kundeId"),
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class Adresse(
    @PrimaryKey
    @ColumnInfo(index = true)
    val adresseId: Int,
    val strasse: String,
    val hausnummer: Int,
    val plz: String,
    @ColumnInfo(index = true)
    val kundeId: Int,
)
