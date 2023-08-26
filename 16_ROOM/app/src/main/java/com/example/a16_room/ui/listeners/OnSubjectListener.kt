package com.example.a16_room.ui.listeners

interface OnSubjectListener {
    fun OnClick(id: Int, source: ClickSourceSubject)
}

enum class ClickSourceSubject {
    OPTION_EDIT,
    OPTION_REMOVE
}