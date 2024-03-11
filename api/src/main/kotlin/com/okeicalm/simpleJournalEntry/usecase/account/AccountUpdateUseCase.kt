package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.infra.db.enums.AccountsCategory
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class AccountUpdateUseCaseInput(val id: Long, val code: String, val name: String, val category: AccountsCategory)
data class AccountUpdateUseCaseOutput(val account: Account)

interface AccountUpdateUseCase {
    fun call(input: AccountUpdateUseCaseInput): AccountUpdateUseCaseOutput
}

@Service
class AccountUpdateUseCaseImpl(private val accountRepository: AccountRepository) : AccountUpdateUseCase {
    @Transactional
    override fun call(input: AccountUpdateUseCaseInput): AccountUpdateUseCaseOutput {
        val account = Account(
            id = input.id,
            code = input.code,
            name = input.name,
            category = input.category
        )

        return AccountUpdateUseCaseOutput(accountRepository.update(account))
    }
}
