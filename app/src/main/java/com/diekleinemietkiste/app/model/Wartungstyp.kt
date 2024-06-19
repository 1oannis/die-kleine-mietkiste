package com.diekleinemietkiste.app.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Die Daten eines Wartungstyps, beispielsweise "Reinigung".
 *
 * https://kotlinlang.org/docs/data-classes.html
 *
 * @param wartungstypId Die ID des Wartungstyps.
 * @param bezeichnung Die Bezeichnung des Wartungsyps.
 */
@Entity(tableName = "wartungstyp")
data class Wartungstyp(
    @PrimaryKey
    @ColumnInfo(index = true)
    val wartungstypId: Int,
    val bezeichnung: String,
)
