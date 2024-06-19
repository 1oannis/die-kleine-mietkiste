package com.diekleinemietkiste.app.adapter

import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.LinearLayout
import android.widget.TextView
import com.diekleinemietkiste.app.R
import com.diekleinemietkiste.app.dataclass.ChildItem
import com.diekleinemietkiste.app.dataclass.ParentItem

/**
 * Custom Adapter fuer ExpandableList mit Parent, Child und Sub-Child items.
 * Wird verwendet fuer FAQ.
 */
class CustomNestedExpandableListAdapter(
    private val context: Context,
    private val parentItemList: List<ParentItem>,
) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return parentItemList.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return parentItemList[groupPosition].children.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return parentItemList[groupPosition]
    }

    override fun getChild(
        groupPosition: Int,
        childPosition: Int,
    ): Any {
        return parentItemList[groupPosition].children[childPosition]
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
        return true
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val parentItem = getGroup(groupPosition) as ParentItem
        val view =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.parent_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.parent_item_title) // Updated ID
        textView.text = parentItem.title
        return view
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val childItem = getChild(groupPosition, childPosition) as ChildItem
        val view =
            convertView ?: LayoutInflater.from(context).inflate(R.layout.child_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.childItemTitle)
        textView.text = childItem.title

        // Layout for sub-child items
        val subChildLayout = view.findViewById<LinearLayout>(R.id.subChildLayout)
        subChildLayout.removeAllViews() // Clear previous views

        // Dynamically add TextViews for each SubChildItem
        for (subChild in childItem.subChildren) {
            val subChildTextView =
                TextView(context).apply {
                    text = subChild.title
                    layoutParams =
                        LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                        )
                    setPadding(40, 20, 20, 20) // Adjust padding as needed
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                    setTextColor(Color.parseColor("#FF000000"))
                }
            subChildLayout.addView(subChildTextView)
        }

        return view
    }

    override fun isChildSelectable(
        groupPosition: Int,
        childPosition: Int,
    ): Boolean {
        return true
    }
}
