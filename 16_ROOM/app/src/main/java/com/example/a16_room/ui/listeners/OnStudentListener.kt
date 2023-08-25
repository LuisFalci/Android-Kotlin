package com.example.a16_room.ui.listeners

interface OnStudentListener {
    fun OnClick(id: Int, source: ClickSource)
}

enum class ClickSource {
    TEXT,
    BUTTON_REMOVE
}