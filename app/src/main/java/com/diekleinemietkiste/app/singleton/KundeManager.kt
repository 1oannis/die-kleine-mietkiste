package com.diekleinemietkiste.app.singleton

/**
 * Singleton, welches ein int im Bereich der vergebenen Kunden IDs aussucht. Diese
 * ID ermöglicht es einen Kunden aus der DB zu fetchen. Sinn und Zweck ist es konsistent einen
 * und den selben Kunden auszugeben bei der Nutzung der Mock-App. Das Singleton wird beim Start der
 * App erzeugt.
 */
object KundeManager {
    val kundeId: Int = (1..4).random()
}
