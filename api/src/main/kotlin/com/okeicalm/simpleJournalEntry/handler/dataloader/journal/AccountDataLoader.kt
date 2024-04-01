package com.okeicalm.simpleJournalEntry.handler.dataloader.journal

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.execution.KotlinDataLoader
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.handler.type.JournalEntryType
import com.okeicalm.simpleJournalEntry.usecase.account.FindAccountUseCase
import com.okeicalm.simpleJournalEntry.usecase.account.FindAccountUseCaseInput
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class AccountDataLoader( private val useCase: FindAccountUseCase ) : KotlinDataLoader<ID, AccountType> {
    override val dataLoaderName: String = "AccountDataLoader"

    override fun getDataLoader(): DataLoader<ID, AccountType> =
        DataLoaderFactory.newDataLoader { ids ->
            CompletableFuture.supplyAsync {
                useCase.call(
                    FindAccountUseCaseInput(ids)
                ).findAccount.map { AccountType(it) }
            }
        }
}