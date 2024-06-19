package com.diekleinemietkiste.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.diekleinemietkiste.app.R
import com.diekleinemietkiste.app.model.Kategorie
import com.diekleinemietkiste.app.model.Produktart

/**
 * Custom Adapter fuer eine ExpandableList mit Parent und Child items.
 * Wird verwendet fuer die Suche mit Kategorien.
 */
class CustomExpandableListAdapter(
    private val context: Context,
    private val kategorien: List<Kategorie>,
    private val produktarten: HashMap<Kategorie, List<Produktart>>,
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return kategorien.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return produktarten[kategorien[groupPosition]]?.size ?: 0
    }

    override fun getGroup(groupPosition: Int): Any {
        return kategorien[groupPosition]
    }

    override fun getChild(
        groupPosition: Int,
        childPosition: Int,
    ): Any {
        return produktarten[kategorien[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(
        groupPosition: Int,
        childPosition: Int,
    ): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val kategorie = getGroup(groupPosition) as Kategorie
        val view =
            convertView ?: LayoutInflater.from(context)
                .inflate(android.R.layout.simple_expandable_list_item_1, parent, false)
        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = kategorie.bezeichnung
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val produktart = getChild(groupPosition, childPosition) as Produktart
        val view =
            convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.child_item_layout, parent, false)
        val textView = view.findViewById<TextView>(R.id.childItemText)
        textView.text = produktart.bezeichnung
        return view
    }

    override fun isChildSelectable(
        groupPosition: Int,
        childPosition: Int,
    ): Boolean {
        return true
    }
}
