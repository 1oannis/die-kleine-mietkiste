package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Die Daten eines Kunden.
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @param kundeId Die ID des Kunden.
 * @param vorname Der Vorname des Kunden.
 * @param nachname Der Nachname des Kunden.
 * @param email Die E-Mail-Adresse des Kunden.
 * @param username Der eindeutige Benutzername des Kunden.
 */

@Entity(tableName = "kunde")
data class Kunde(
    @PrimaryKey
    @ColumnInfo(index = true)
    val kundeId: Int,
    val vorname: String,
    val nachname: String,
    val email: String,
    val username: String,
)
