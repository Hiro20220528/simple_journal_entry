package com.okeicalm.simpleJournalEntry.usecase.account

import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service

interface FindAccountsUseCase {
    fun call(input: FindAccountsUseCaseInput): FindAccountsUseCaseOutput
}

data class FindAccountsUseCaseInput(
    val ids: List<ID>
)

data class FindAccountsUseCaseOutput(
    val account: List<Account>
)

@Service
class FindAccountsUseCaseImpl(
    private val repository: AccountRepository
) : FindAccountsUseCase {
    override fun call(
        input: FindAccountsUseCaseInput
    ): FindAccountsUseCaseOutput {
        return FindAccountsUseCaseOutput(repository.findByIds(input.ids))
    }
}
