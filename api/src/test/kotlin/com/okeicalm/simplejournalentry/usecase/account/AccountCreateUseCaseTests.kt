package com.okeicalm.simpleJournalEntry.usecase.account

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.infra.db.enums.AccountsCategory
import com.okeicalm.simpleJournalEntry.repository.AccountRepository
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class AccountCreateUseCaseTests : DescribeSpec({
    lateinit var usecase: AccountCreateUseCase
    val repository = mockk<AccountRepository>()

    beforeEach {
        usecase = AccountCreateUseCaseImpl(repository)
    }

    describe("call") {
        val input = AccountCreateUseCaseInput("code", "name", AccountsCategory.NET_ASSETS)
        val account = Account(code = "code", name = "name", category = AccountsCategory.NET_ASSETS)

        every { repository.create(account) } returns account.copy(id = 1)
        every { repository.findById(1) } returns account.copy(id = 1)

        println("テスト")

        it("returns new account") {
            val output = usecase.call(input)
            output.account.id.shouldBe(1)
            output.account.code.shouldBe("code")
            output.account.name.shouldBe("name")
            output.account.category.shouldBe(AccountsCategory.NET_ASSETS)
        }
    }
})
