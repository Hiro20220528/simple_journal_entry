package com.okeicalm.simpleJournalEntry.handler.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.okeicalm.simpleJournalEntry.handler.type.AccountType
import com.okeicalm.simpleJournalEntry.infra.db.enums.AccountsCategory
import com.okeicalm.simpleJournalEntry.usecase.account.AccountCreateUseCase
import com.okeicalm.simpleJournalEntry.usecase.account.AccountCreateUseCaseInput
import org.springframework.stereotype.Component

data class CreateAccountInput(val code: String, val name: String, val category: AccountsCategory)

@Component
class CreateAccountMutation(private val accountCreateUseCase: AccountCreateUseCase) : Mutation {
    fun createAccount(input: CreateAccountInput): AccountType {
        println("============ createAccount function ============")
        println(input)
        val output = accountCreateUseCase.call(
            AccountCreateUseCaseInput(
                code = input.code,
                name = input.name,
                category = input.category
            )
        )
        return AccountType(output.account)
    }
}
