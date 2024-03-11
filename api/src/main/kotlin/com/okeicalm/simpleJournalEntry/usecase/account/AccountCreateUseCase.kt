package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.infra.db.enums.AccountsCategory
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

data class AccountCreateUseCaseInput(val code: String, val name: String, val category: AccountsCategory)
data class AccountCreateUseCaseOutput(val account: Account)

interface AccountCreateUseCase {
    fun call(input: AccountCreateUseCaseInput): AccountCreateUseCaseOutput
}

@Service
class AccountCreateUseCaseImpl(private val accountRepository: AccountRepository) : AccountCreateUseCase {
    @Transactional
    override fun call(input: AccountCreateUseCaseInput): AccountCreateUseCaseOutput {
        println("============ call function =============")
        val account = Account(
            code = input.code,
            name = input.name,
            category = input.category
        )
        return AccountCreateUseCaseOutput(accountRepository.create(account))
    }
}
