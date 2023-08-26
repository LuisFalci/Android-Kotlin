package com.example.a16_room.ui.listeners

interface OnSubjectListener {
    fun OnClick(id: Int, source: ClickSourceSubject)
}

enum class ClickSourceSubject {
    TEXT,
    BUTTON_REMOVE
}