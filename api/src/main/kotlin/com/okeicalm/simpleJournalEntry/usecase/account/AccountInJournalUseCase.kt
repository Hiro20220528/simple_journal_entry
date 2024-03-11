package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.handler.type.JournalEntryType
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service

interface AccountInJournalUseCase {
    fun call(input: AccountInJournalUseCaseInput): AccountInJournalUseCaseOutput
}

data class AccountInJournalUseCaseInput(
    val ids: List<List<JournalEntryType>>
)

data class AccountInJournalUseCaseOutput(
    val accountsInJournal: List<Account>
)

@Service
class AccountInJournalUseCaseImpl(
    private val repository: AccountRepository
) : AccountInJournalUseCase {
    override fun call(
        input: AccountInJournalUseCaseInput
    ): AccountInJournalUseCaseOutput {
        return AccountInJournalUseCaseOutput(repository.findByIds(input.ids))
    }
}
