package com.diekleinemietkiste.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.diekleinemietkiste.app.dao.AppDatabase.Companion.getDatabase
import com.diekleinemietkiste.app.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Initialisieren und Abrufen der Room-Datenbankinstanz als Coroutine, um memory leks zu vermeiden
         *
         * https://stackoverflow.com/questions/48280941/room-database-force-oncreate-callback
         * Dummy-Abfrage an die Datenbank, um die Ausführung des onCreate-Callbacks der
         * AppDatabase Klasse auszulösen. Nicht die Ausführung der Methode .build() des databaseBuilder löst
         * das schreiben der DB auf das Filesystem aus, sondern ein Lese-/Schreibvorgang.
         */
        lifecycleScope.launch {
            val db = getDatabase(applicationContext)
            db.kundeDao().getAll()
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialisierung der Bottom NavBar
        val navView: BottomNavigationView = binding.navView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration =
            AppBarConfiguration(
                setOf(
                    R.id.navigation_startseite,
                    R.id.navigation_suchen,
                    R.id.navigation_warenkorb,
                    R.id.navigation_favoriten,
                    R.id.navigation_account,
                ),
            )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setOnItemSelectedListener { item ->
            // Check ob das ausgewaehlte item nicht die aktuelle position ist
            if (navController.currentDestination?.id != item.itemId) {
                when (item.itemId) {
                    R.id.navigation_startseite -> {
                        navController.navigate(R.id.navigation_startseite)
                        true
                    }

                    R.id.navigation_suchen -> {
                        navController.navigate(R.id.navigation_suchen)
                        true
                    }

                    R.id.navigation_warenkorb -> {
                        navController.navigate(R.id.navigation_warenkorb)
                        true
                    }

                    R.id.navigation_favoriten -> {
                        navController.navigate(R.id.navigation_favoriten)
                        true
                    }

                    R.id.navigation_account -> {
                        navController.navigate(R.id.navigation_account)
                        true
                    }

                    else -> false
                }
            } else {
                true // Ausgewaehltes Item entspricht aktueller Position
            }
        }
    }

    /**
     * Ermöglicht die Back Arrow Funktionalität
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
