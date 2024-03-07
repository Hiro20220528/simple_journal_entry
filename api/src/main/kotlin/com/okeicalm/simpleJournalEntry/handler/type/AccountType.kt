package com.okeicalm.simpleJournalEntry.handler.type

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.extensions.getValueFromDataLoader
import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.handler.dataloader.journal.AccountDataLoader
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

const val accountTypeGraphQLName = "Account"

@GraphQLName(accountTypeGraphQLName)
data class AccountType(
    val id: ID,
    val code: String,
    val name: String,
    val elementType: Int,
) {
    constructor(account: Account) : this(
        ID(account.id.toString()),
        account.code,
        account.name,
        account.elementType,
    )
    companion object {
        fun from(entity: Account) = AccountType(
            id = ID(entity.id.toString()),
            code = entity.code,
            name = entity.name,
            elementType = entity.elementType
        )
    }
}
