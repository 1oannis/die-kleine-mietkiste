package com.diekleinemietkiste.app.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.diekleinemietkiste.app.R
import com.diekleinemietkiste.app.diffUtil.ProduktDiffCallback
import com.diekleinemietkiste.app.model.Produkt
import java.util.Locale

/**
 * Custom Adapter fuer eine RecyclerView, hier als Kachel-Ansicht.
 * Wird fuer den Katalog mit Produkten verwendet.
 */
class CatalogAdapter(private val onClick: (Produkt) -> Unit) :
    ListAdapter<Produkt, CatalogAdapter.ProductViewHolder>(
        ProduktDiffCallback(),
    ) {
    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val productName: TextView = view.findViewById(R.id.productName)
        private val productPrice: TextView = view.findViewById(R.id.productPrice)
        private val productImage: ImageView = view.findViewById(R.id.productImage)

        @SuppressLint("DiscouragedApi")
        fun bind(
            product: Produkt,
            onClick: (Produkt) -> Unit,
        ) {
            productName.text = product.bezeichnung
            productPrice.text = formatPrice(product.preis)
            val imageName = "bett" + product.produktId
            // Der Einfachheit geschuldet wird das Produktbild hier durch Reflection geladen
            val imageResId =
                itemView.context.resources.getIdentifier(
                    imageName,
                    "drawable",
                    itemView.context.packageName,
                )

            if (imageResId != 0) { // Resource found
                productImage.setImageResource(imageResId)
            } else {
                Log.d("CatalogAdapter", "Could not find an image for product ${product.produktId}")
            }

            itemView.setOnClickListener { onClick(product) }
        }

        private fun formatPrice(price: Int): String {
            return String.format(Locale.GERMANY, "$price,00 €", price)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int,
    ) {
        val product = getItem(position)
        holder.bind(product, onClick)
    }
}
