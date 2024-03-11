package com.okeicalm.simpleJournalEntry.handler.type

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.infra.db.enums.AccountsCategory

const val accountTypeGraphQLName = "Account"

@GraphQLName(accountTypeGraphQLName)
data class AccountType(
    val id: ID,
    val code: String,
    val name: String,
    val accountsCategory: AccountsCategory
) {
    constructor(account: Account) : this(
        ID(account.id.toString()),
        account.code,
        account.name,
        account.category,
    )
    companion object {
        fun from(entity: Account) = AccountType(
            id = ID(entity.id.toString()),
            code = entity.code,
            name = entity.name,
            accountsCategory = entity.category
        )
    }
}
