package com.okeicalm.simpleJournalEntry.handler.dataloader.journal

import com.expediagroup.graphql.server.execution.KotlinDataLoader
import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.entity.JournalEntry
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.handler.type.JournalEntryType
import com.okeicalm.simpleJournalEntry.usecase.account.AccountInJournalUseCase
import com.okeicalm.simpleJournalEntry.usecase.account.AccountInJournalUseCaseInput
import org.dataloader.DataLoader
import org.dataloader.DataLoaderFactory
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class AccountDataLoader(
    private val useCase: AccountInJournalUseCase
) : KotlinDataLoader<List<JournalEntryType>, AccountType> {
    override val dataLoaderName: String = "AccountDataLoader"

    override fun getDataLoader(): DataLoader<List<JournalEntryType>, AccountType> =
        DataLoaderFactory.newDataLoader { ids: List<List<JournalEntryType>>? ->
            CompletableFuture.supplyAsync {
                ids?.let {
                    useCase.call(
                        AccountInJournalUseCaseInput(
                            ids = ids
                        )
                    ).accountsInJournal.map {
                        AccountType.from(it)
                    }
                }
            }
        }

//    override fun getDataLoader(): DataLoader<List<JournalEntryType>, AccountType> =
//        DataLoaderFactory.newDataLoader { ids: List<List<JournalEntryType>>? ->
//            CompletableFuture.supplyAsync( {
//                ids?.let {
//                    useCase.call(
//                        AccountInJournalUseCaseInput(
//                            ids = ids
//                        )
//                    ).accountsInJournal.map {
//                        AccountType.from(it)
//                    }
//                }
//            })
//        }

}

