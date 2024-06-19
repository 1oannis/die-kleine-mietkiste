package com.diekleinemietkiste.app.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.diekleinemietkiste.app.model.Produkt

/**
 * Callback Funktionen fuer items eines RecyclerViews. Sorgt fuer das schnellere updaten einer
 * RecyclerView, indem es die LiveData Listen vergleicht, Unterschiede erkennt und eine neue Liste
 * mit updates ausgiebt.
 */
class ProduktDiffCallback : DiffUtil.ItemCallback<Produkt>() {
    override fun areItemsTheSame(
        oldItem: Produkt,
        newItem: Produkt,
    ): Boolean {
        return oldItem.produktId == newItem.produktId
    }

    override fun areContentsTheSame(
        oldItem: Produkt,
        newItem: Produkt,
    ): Boolean {
        return oldItem == newItem
    }
}
