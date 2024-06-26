package com.okeicalm.simpleJournalEntry.entity

// 勘定科目
data class Account(
    val id: Long = 0,
    val code: String,
    val name: String,
    val category: AccountCategory
)

enum class AccountCategory {
    ASSETS,
    LIABILITIES,
    NET_ASSETS,
    REVENUE,
    COST
}
