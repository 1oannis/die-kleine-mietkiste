package com.diekleinemietkiste.app.ui.katalog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.diekleinemietkiste.app.dao.AppDatabase
import com.diekleinemietkiste.app.dao.ProduktDao
import com.diekleinemietkiste.app.model.Produkt

class KatalogViewModel(application: Application) : AndroidViewModel(application) {
    private val produktDao: ProduktDao = AppDatabase.getDatabase(application).produktDao()

    fun getProduktListByProduktId(produktId: Int): LiveData<List<Produkt>> =
        liveData {
            val produktList = produktDao.getProduktListByProduktartId(produktId)
            emit(produktList)
        }
}
