package com.diekleinemietkiste.app.dataclass

/**
 * Datenklassen fuer eine custom Implementierung einer NestedExpandableList
 */
data class ParentItem(val title: String, val children: List<ChildItem>)

data class ChildItem(val title: String, val subChildren: List<SubChildItem>)

data class SubChildItem(val title: String)
