package com.iteo.android_navigation

import android.graphics.Color
import com.iteo.android_navigation.list.Item

object Data {
    val items = listOf(
        Item(1, "Test1", Color.RED),
        Item(2, "Test2", Color.GREEN),
        Item(3, "Item", Color.BLUE),
        Item(4, "Item2", Color.RED),
        Item(5, "Nowy", Color.GREEN),
        Item(6, "Nowy2", Color.RED),
        Item(7, "Nowy3", Color.BLUE),
        Item(8, "Red", Color.RED),
        Item(9, "Blue", Color.BLUE),
        Item(10, "Green", Color.GREEN),
        Item(11, "Blue2", Color.BLUE),
        Item(12, "Green2", Color.GREEN),
        Item(13, "Red2", Color.RED)
    )
    val itemsMap:Map<Int, Item> = items.map { it.id to it }.toMap()
}
