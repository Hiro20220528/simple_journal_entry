package com.okeicalm.simpleJournalEntry.handler.type

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.extensions.getValueFromDataLoader
import com.okeicalm.simpleJournalEntry.entity.Journal
import graphql.schema.DataFetchingEnvironment
import java.util.concurrent.CompletableFuture

const val journalTypeGraphQLName = "Journal"

@GraphQLName(journalTypeGraphQLName)
data class JournalType(
    val id: ID,
//    val incurredOn: LocalDate,
    val journalEntries: List<JournalEntryType>?,
) {
    /**
     * このコンストラクターは引数によってクラスの生成方法を変更できる
     */
    constructor(journal: Journal) : this(
        ID(journal.id.toString()),
        journal.journalEntries?.map { JournalEntryType(it) }
    )

    /**
     * これはschemaと名前が一緒になる
     */
    fun findAccount(
        environment: DataFetchingEnvironment
    ): CompletableFuture<AccountType> {
        val ids = this.journalEntries?.get(0)?.accountId
        return  environment.getValueFromDataLoader(
            "AccountDataLoader",
            ids
        )
    }
}
