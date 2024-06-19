package com.diekleinemietkiste.app.dao

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.diekleinemietkiste.app.model.Adresse
import com.diekleinemietkiste.app.model.Artikel
import com.diekleinemietkiste.app.model.DateConverters
import com.diekleinemietkiste.app.model.Hersteller
import com.diekleinemietkiste.app.model.Kategorie
import com.diekleinemietkiste.app.model.Kunde
import com.diekleinemietkiste.app.model.Miete
import com.diekleinemietkiste.app.model.Produkt
import com.diekleinemietkiste.app.model.Produktart
import com.diekleinemietkiste.app.model.Wartungstyp
import com.diekleinemietkiste.app.model.Wartungsvorgang
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

@Database(
    entities = [
        Wartungstyp::class,
        Kunde::class,
        Adresse::class,
        Miete::class,
        Wartungsvorgang::class,
        Artikel::class,
        Hersteller::class,
        Produkt::class,
        Produktart::class,
        Kategorie::class,
    ],
    version = 1,
)
@TypeConverters(DateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun wartungstypDao(): WartungstypDao

    abstract fun kundeDao(): KundeDao

    abstract fun adresseDao(): AdresseDao

    abstract fun mieteDao(): MieteDao

    abstract fun wartungsvorgangDao(): WartungsvorgangDao

    abstract fun artikelDao(): ArtikelDao

    abstract fun herstellerDao(): HerstellerDao

    abstract fun produktDao(): ProduktDao

    abstract fun produktartDao(): ProduktartDao

    abstract fun kategorieDao(): KategorieDao

    companion object {
        // Mock Daten fuer die App
        val kundenList =
            listOf(
                Kunde(
                    kundeId = 1,
                    vorname = "Max",
                    nachname = "Mustermann",
                    email = "max@example.com",
                    username = "maxmuster",
                ),
                Kunde(
                    kundeId = 2,
                    vorname = "Julia",
                    nachname = "Beispiel",
                    email = "julia@example.com",
                    username = "juliab",
                ),
                Kunde(
                    kundeId = 3,
                    vorname = "Erik",
                    nachname = "Testfall",
                    email = "erik@example.com",
                    username = "erikt",
                ),
                Kunde(
                    kundeId = 4,
                    vorname = "Pauline",
                    nachname = "Peters",
                    email = "pauline@example.com",
                    username = "pauline98",
                ),
            )
        val adressenList =
            listOf(
                Adresse(
                    adresseId = 1,
                    strasse = "Musterstraße",
                    hausnummer = 5,
                    plz = "12345",
                    kundeId = 1,
                ),
                Adresse(
                    adresseId = 2,
                    strasse = "Beispielweg",
                    hausnummer = 12,
                    plz = "23456",
                    kundeId = 2,
                ),
                Adresse(
                    adresseId = 3,
                    strasse = "Testallee",
                    hausnummer = 19,
                    plz = "34567",
                    kundeId = 3,
                ),
            )
        val hersteller =
            listOf(
                Hersteller(herstellerId = 1, name = "Wolkenkissen GmbH"),
                Hersteller(herstellerId = 2, name = "Traumland AG"),
                Hersteller(herstellerId = 3, name = "Schlafparadies Ltd."),
            )
        val kategorienList =
            listOf(
                Kategorie(kategorieId = 1, bezeichnung = "Kinderzimmer"),
                Kategorie(kategorieId = 2, bezeichnung = "Spiel & Spaß"),
                Kategorie(kategorieId = 3, bezeichnung = "Unterwegs"),
            )
        val produktartenList =
            listOf(
                Produktart(produktartId = 1, bezeichnung = "Kinderbetten", kategorieId = 1),
                Produktart(produktartId = 2, bezeichnung = "Wickeltische", kategorieId = 1),
                Produktart(produktartId = 3, bezeichnung = "Schränke", kategorieId = 1),
                Produktart(produktartId = 4, bezeichnung = "Kommoden", kategorieId = 1),
                Produktart(produktartId = 5, bezeichnung = "Holzspielzeuge", kategorieId = 2),
                Produktart(produktartId = 6, bezeichnung = "Baukästen", kategorieId = 2),
                Produktart(produktartId = 7, bezeichnung = "Rollenspiele", kategorieId = 2),
                Produktart(produktartId = 8, bezeichnung = "Autositze", kategorieId = 3),
                Produktart(produktartId = 9, bezeichnung = "Kinderfahrräder", kategorieId = 3),
                Produktart(produktartId = 10, bezeichnung = "Kinderwägen", kategorieId = 3),
            )
        val produkteList =
            listOf(
                Produkt(
                    produktId = 1,
                    bezeichnung = "Kinderbett Classic",
                    preis = 30,
                    beschreibung = "Einsteiger Kinderbett",
                    abmessungen = "140x70 cm",
                    herstellerId = 1,
                    produktartId = 1,
                ),
                Produkt(
                    produktId = 2,
                    bezeichnung = "Kinderbett Standard",
                    preis = 24,
                    beschreibung = "Kinderbett mit längerer Matratze",
                    abmessungen = "200x90 cm",
                    herstellerId = 2,
                    produktartId = 1,
                ),
                Produkt(
                    produktId = 3,
                    bezeichnung = "Kinderbett Deluxe",
                    preis = 45,
                    beschreibung = "Kinderbett mit extra Breite",
                    abmessungen = "200x120 cm",
                    herstellerId = 3,
                    produktartId = 1,
                ),
                Produkt(
                    produktId = 4,
                    bezeichnung = "Kinderbett Twinsies",
                    preis = 45,
                    beschreibung = "Kinderbett für zwei Kinder",
                    abmessungen = "200x140 cm",
                    herstellerId = 3,
                    produktartId = 1,
                ),
                Produkt(
                    produktId = 5,
                    bezeichnung = "Kinderbett Wipeable",
                    preis = 35,
                    beschreibung = "Kinderbett mit abwaschbarer Matratze",
                    abmessungen = "140x70 cm",
                    herstellerId = 3,
                    produktartId = 1,
                ),
                Produkt(
                    produktId = 6,
                    bezeichnung = "Kinderbett Water",
                    preis = 60,
                    beschreibung = "Wasserbett für Kinder",
                    abmessungen = "140x70 cm",
                    herstellerId = 1,
                    produktartId = 1,
                ),
            )
        val artikelList =
            listOf(
                Artikel(artikelId = 1, zustand = "Neu", produktId = 1),
                Artikel(artikelId = 2, zustand = "Gebraucht", produktId = 2),
                Artikel(artikelId = 3, zustand = "Stark gebraucht", produktId = 3),
            )
        val mietenList =
            listOf(
                Miete(
                    mieteId = 1,
                    startdatum = Date(),
                    enddatum = Date(),
                    lieferdatum = Date(),
                    aufbauservice = true,
                    abbauservice = false,
                    kundeId = 1,
                    artikelId = 1,
                ),
                Miete(
                    mieteId = 2,
                    startdatum = Date(),
                    enddatum = Date(),
                    lieferdatum = Date(),
                    aufbauservice = false,
                    abbauservice = true,
                    kundeId = 2,
                    artikelId = 2,
                ),
                Miete(
                    mieteId = 3,
                    startdatum = Date(),
                    enddatum = Date(),
                    lieferdatum = Date(),
                    aufbauservice = true,
                    abbauservice = true,
                    kundeId = 3,
                    artikelId = 3,
                ),
                Miete(
                    mieteId = 4,
                    startdatum = Date(),
                    enddatum = Date(),
                    lieferdatum = Date(),
                    aufbauservice = false,
                    abbauservice = true,
                    kundeId = 4,
                    artikelId = 4,
                ),
            )
        val wartungstypList =
            listOf(
                Wartungstyp(wartungstypId = 1, bezeichnung = "Reinigung"),
                Wartungstyp(wartungstypId = 2, bezeichnung = "Inspektion"),
                Wartungstyp(wartungstypId = 3, bezeichnung = "Reparatur"),
            )
        val wartungsvorgangList =
            listOf(
                Wartungsvorgang(
                    wartungsvorgangId = 1,
                    status = "Abgeschlossen",
                    datum = Date(),
                    beschreibung = "Regelmäßige Reinigung",
                    kosten = 50,
                    artikelId = 1,
                    wartungstypId = 1,
                ),
                Wartungsvorgang(
                    wartungsvorgangId = 2,
                    status = "In Bearbeitung",
                    datum = Date(),
                    beschreibung = "Jährliche Inspektion",
                    kosten = 100,
                    artikelId = 2,
                    wartungstypId = 2,
                ),
                Wartungsvorgang(
                    wartungsvorgangId = 3,
                    status = "Geplant",
                    datum = Date(),
                    beschreibung = "Reparatur nach Defekt",
                    kosten = 150,
                    artikelId = 3,
                    wartungstypId = 3,
                ),
            )

        private val dbCallback =
            object : Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Asynchrones Einfügen der Testdaten
                    CoroutineScope(Dispatchers.IO).launch {
                        Log.d("Testdaten", "Testdaten werden eingefügt")
                        INSTANCE?.kundeDao()?.insert(kundenList[0])
                        INSTANCE?.kundeDao()?.insert(kundenList[1])
                        INSTANCE?.kundeDao()?.insert(kundenList[2])
                        INSTANCE?.kundeDao()?.insert(kundenList[3])
                        INSTANCE?.adresseDao()?.insert(adressenList[0])
                        INSTANCE?.adresseDao()?.insert(adressenList[1])
                        INSTANCE?.adresseDao()?.insert(adressenList[2])
                        INSTANCE?.herstellerDao()?.insert(hersteller[0])
                        INSTANCE?.herstellerDao()?.insert(hersteller[1])
                        INSTANCE?.herstellerDao()?.insert(hersteller[2])
                        INSTANCE?.kategorieDao()?.insert(kategorienList[0])
                        INSTANCE?.kategorieDao()?.insert(kategorienList[1])
                        INSTANCE?.kategorieDao()?.insert(kategorienList[2])
                        INSTANCE?.produktartDao()?.insert(produktartenList[0])
                        INSTANCE?.produktartDao()?.insert(produktartenList[1])
                        INSTANCE?.produktartDao()?.insert(produktartenList[2])
                        INSTANCE?.produktartDao()?.insert(produktartenList[3])
                        INSTANCE?.produktartDao()?.insert(produktartenList[4])
                        INSTANCE?.produktartDao()?.insert(produktartenList[5])
                        INSTANCE?.produktartDao()?.insert(produktartenList[6])
                        INSTANCE?.produktartDao()?.insert(produktartenList[7])
                        INSTANCE?.produktartDao()?.insert(produktartenList[8])
                        INSTANCE?.produktartDao()?.insert(produktartenList[9])
                        INSTANCE?.produktDao()?.insert(produkteList[0])
                        INSTANCE?.produktDao()?.insert(produkteList[1])
                        INSTANCE?.produktDao()?.insert(produkteList[2])
                        INSTANCE?.produktDao()?.insert(produkteList[3])
                        INSTANCE?.produktDao()?.insert(produkteList[4])
                        INSTANCE?.produktDao()?.insert(produkteList[5])
                        INSTANCE?.artikelDao()?.insert(artikelList[0])
                        INSTANCE?.artikelDao()?.insert(artikelList[1])
                        INSTANCE?.artikelDao()?.insert(artikelList[2])
                        INSTANCE?.mieteDao()?.insert(mietenList[0])
                        INSTANCE?.mieteDao()?.insert(mietenList[1])
                        INSTANCE?.mieteDao()?.insert(mietenList[2])
                        INSTANCE?.mieteDao()?.insert(mietenList[3])
                        INSTANCE?.wartungstypDao()?.insert(wartungstypList[0])
                        INSTANCE?.wartungstypDao()?.insert(wartungstypList[1])
                        INSTANCE?.wartungstypDao()?.insert(wartungstypList[2])
                        INSTANCE?.wartungsvorgangDao()?.insert(wartungsvorgangList[0])
                        INSTANCE?.wartungsvorgangDao()?.insert(wartungsvorgangList[1])
                        INSTANCE?.wartungsvorgangDao()?.insert(wartungsvorgangList[2])
                    }
                }
            }

        // Singleton verhindert mehrfache Instanzen der Datenbank, die gleichzeitig geöffnet werden
        @Volatile
        @Suppress("ktlint:standard:property-naming")
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app_database",
                    )
                        .addCallback(dbCallback)
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
