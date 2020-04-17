package com.iteo.advanced_navigation_plugin

import com.google.android.material.bottomnavigation.BottomNavigationView

data class BottomBarContainerSetting(
    val graphIds: List<Int>,
    val containerId: Int,
    val bottomBar: BottomNavigationView
)
