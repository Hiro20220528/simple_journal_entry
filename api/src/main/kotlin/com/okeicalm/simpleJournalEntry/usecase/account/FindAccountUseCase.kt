package com.okeicalm.simpleJournalEntry.usecase.account

import com.expediagroup.graphql.generator.scalars.ID
import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service

interface FindAccountUseCase {
    fun call(input: FindAccountUseCaseInput): FindAccountUseCaseOutput
}

data class FindAccountUseCaseInput(
    val ids: List<ID>
)

data class FindAccountUseCaseOutput(
    val findAccount: List<Account>
)

@Service
class FindAccountUseCaseImpl(
    private val repository: AccountRepository
) : FindAccountUseCase {
    override fun call(
        input: FindAccountUseCaseInput
    ): FindAccountUseCaseOutput {
        return FindAccountUseCaseOutput(repository.findByIds(input.ids))
    }
}
