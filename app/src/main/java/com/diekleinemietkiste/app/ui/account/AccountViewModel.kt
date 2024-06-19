package com.diekleinemietkiste.app.ui.account

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.diekleinemietkiste.app.dao.AppDatabase
import com.diekleinemietkiste.app.dao.KundeDao
import com.diekleinemietkiste.app.dao.MieteDao
import com.diekleinemietkiste.app.model.Kunde
import com.diekleinemietkiste.app.singleton.KundeManager
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    private val logName = "AccountViewModel"
    private val kundeDao: KundeDao = AppDatabase.getDatabase(application).kundeDao()
    private val mieteDao: MieteDao = AppDatabase.getDatabase(application).mieteDao()
    private val kundeDataMutable = MutableLiveData<Kunde>()
    private val kundeData: LiveData<Kunde> = kundeDataMutable

    private val mieteCountMutable = MutableLiveData<Int>()
    val mieteCount: LiveData<Int> = mieteCountMutable

    init {
        val kundeId = KundeManager.kundeId
        // Kundennamen und Anzahl der Mieten aus der Datenbank fetchen
        viewModelScope.launch {
            Log.d(logName, "KundeId: $kundeId")
            kundeDataMutable.postValue(kundeDao.findById(kundeId))
            val count = mieteDao.getCountByKundeId(kundeId)
            mieteCountMutable.postValue(count)
        }
        // Nur fuer logging notwendig
        kundeDataMutable.observeForever {
            Log.d(logName, it.toString())
        }
        mieteCountMutable.observeForever {
            Log.d(logName, "Miete Count($it)")
        }
    }

    private val _accountName: LiveData<String> =
        kundeData.map { kunde ->
            "Hallo ${kunde.vorname}"
        }
    val accountName: LiveData<String> = _accountName
}
