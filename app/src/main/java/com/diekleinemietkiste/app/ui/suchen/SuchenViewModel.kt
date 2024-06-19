package com.diekleinemietkiste.app.ui.suchen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.diekleinemietkiste.app.dao.AppDatabase
import com.diekleinemietkiste.app.dao.KategorieDao
import com.diekleinemietkiste.app.dao.ProduktartDao
import com.diekleinemietkiste.app.model.Kategorie
import com.diekleinemietkiste.app.model.Produktart

class SuchenViewModel(application: Application) : AndroidViewModel(application) {
    private val kategorieDao: KategorieDao = AppDatabase.getDatabase(application).kategorieDao()
    private val produktartDao: ProduktartDao = AppDatabase.getDatabase(application).produktartDao()

    val kategorien: LiveData<List<Kategorie>> =
        liveData {
            emit(kategorieDao.getAll())
        }

    suspend fun getProduktartenZuKategorie(kategorieId: Int): List<Produktart> {
        return produktartDao.getProduktartenByKategorieId(kategorieId)
    }

    suspend fun getKategorieNameById(kategorieId: Int): String {
        return kategorieDao.getById(kategorieId)
    }

    suspend fun getProduktartenZuKategorieList(kategorieList: List<Kategorie>): HashMap<Kategorie, List<Produktart>> {
        val map = HashMap<Kategorie, List<Produktart>>()
        for (kategorie in kategorieList) {
            val produktarten = getProduktartenZuKategorie(kategorie.kategorieId)
            map[kategorie] = produktarten
        }
        return map
    }
}
