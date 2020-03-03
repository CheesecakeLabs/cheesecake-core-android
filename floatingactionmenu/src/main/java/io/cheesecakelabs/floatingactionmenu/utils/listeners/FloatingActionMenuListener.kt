package io.cheesecakelabs.floatingactionmenu.utils.listeners

interface FloatingActionMenuListener {

    fun onMenuExpanded()
    fun onMenuCollapsed()
    fun onMenuItemClicked(position: Int)
}