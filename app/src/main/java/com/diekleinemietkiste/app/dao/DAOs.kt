package com.diekleinemietkiste.app.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.diekleinemietkiste.app.model.Adresse
import com.diekleinemietkiste.app.model.Artikel
import com.diekleinemietkiste.app.model.Hersteller
import com.diekleinemietkiste.app.model.Kategorie
import com.diekleinemietkiste.app.model.Kunde
import com.diekleinemietkiste.app.model.Miete
import com.diekleinemietkiste.app.model.Produkt
import com.diekleinemietkiste.app.model.Produktart
import com.diekleinemietkiste.app.model.Wartungstyp
import com.diekleinemietkiste.app.model.Wartungsvorgang

@Dao
interface AdresseDao {
    @Insert
    suspend fun insert(adresse: Adresse)

    @Update
    suspend fun update(adresse: Adresse)

    @Delete
    suspend fun delete(adresse: Adresse)

    @Query("SELECT * FROM adresse")
    suspend fun getAll(): List<Adresse>
}

@Dao
interface ArtikelDao {
    @Insert
    suspend fun insert(artikel: Artikel)

    @Update
    suspend fun update(artikel: Artikel)

    @Delete
    suspend fun delete(artikel: Artikel)

    @Query("SELECT * FROM artikel")
    suspend fun getAll(): List<Artikel>
}

@Dao
interface HerstellerDao {
    @Insert
    suspend fun insert(hersteller: Hersteller)

    @Update
    suspend fun update(hersteller: Hersteller)

    @Delete
    suspend fun delete(hersteller: Hersteller)

    @Query("SELECT * FROM hersteller")
    suspend fun getAll(): List<Hersteller>
}

@Dao
interface KategorieDao {
    @Insert
    suspend fun insert(kategorie: Kategorie)

    @Update
    suspend fun update(kategorie: Kategorie)

    @Delete
    suspend fun delete(kategorie: Kategorie)

    @Query("SELECT * FROM kategorie")
    suspend fun getAll(): List<Kategorie>

    @Query("SELECT bezeichnung FROM kategorie WHERE kategorieId = :id")
    suspend fun getById(id: Int): String
}

@Dao
interface KundeDao {
    @Insert
    suspend fun insert(kunde: Kunde)

    @Update
    suspend fun update(kunde: Kunde)

    @Delete
    suspend fun delete(kunde: Kunde)

    @Query("SELECT * FROM kunde")
    suspend fun getAll(): List<Kunde>

    @Query("SELECT * FROM kunde WHERE kundeId = :id")
    suspend fun findById(id: Int): Kunde
}

@Dao
interface MieteDao {
    @Insert
    suspend fun insert(miete: Miete)

    @Update
    suspend fun update(miete: Miete)

    @Delete
    suspend fun delete(miete: Miete)

    @Query("SELECT * FROM miete")
    suspend fun getAll(): List<Miete>

    @Query("SELECT Count(*) FROM miete WHERE kundeId = :id")
    suspend fun getCountByKundeId(id: Int): Int
}

@Dao
interface ProduktartDao {
    @Insert
    suspend fun insert(produktart: Produktart)

    @Update
    suspend fun update(produktart: Produktart)

    @Delete
    suspend fun delete(produktart: Produktart)

    @Query("SELECT * FROM produktart")
    suspend fun getAll(): List<Produktart>

    @Query("SELECT * FROM produktart WHERE kategorieId = :kategorieId")
    suspend fun getProduktartenByKategorieId(kategorieId: Int): List<Produktart>
}

@Dao
interface ProduktDao {
    @Insert
    suspend fun insert(produkt: Produkt)

    @Update
    suspend fun update(produkt: Produkt)

    @Delete
    suspend fun delete(produkt: Produkt)

    @Query("SELECT * FROM produkt")
    suspend fun getAll(): List<Produkt>

    @Query("SELECT * FROM produkt WHERE produktartId = :produktartId")
    suspend fun getProduktListByProduktartId(produktartId: Int): List<Produkt>
}

@Dao
interface WartungstypDao {
    @Insert
    suspend fun insert(wartungstyp: Wartungstyp)

    @Update
    suspend fun update(wartungstyp: Wartungstyp)

    @Delete
    suspend fun delete(wartungstyp: Wartungstyp)

    @Query("SELECT * FROM wartungstyp")
    suspend fun getAll(): List<Wartungstyp>
}

@Dao
interface WartungsvorgangDao {
    @Insert
    suspend fun insert(wartungsvorgang: Wartungsvorgang)

    @Update
    suspend fun update(wartungsvorgang: Wartungsvorgang)

    @Delete
    suspend fun delete(wartungsvorgang: Wartungsvorgang)

    @Query("SELECT * FROM wartungsvorgang")
    suspend fun getAll(): List<Wartungsvorgang>
}
