package com.okeicalm.simpleJournalEntry.entity

// 仕分け
data class JournalEntry(
    val id: Long = 0,
    val journalId: Long,
    val side: Byte,
    val accountId: Long,
    val value: Int,
)
