package com.okeicalm.simpleJournalEntry.entity

import com.okeicalm.simpleJournalEntry.infra.db.enums.AccountsCategory

// 勘定科目
data class Account(
    val id: Long = 0,
    val code: String,
    val name: String,
    val category: AccountsCategory
)
