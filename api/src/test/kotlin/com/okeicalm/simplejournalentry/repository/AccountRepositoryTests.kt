package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.infra.db.enums.AccountsCategory
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
// import org.flywaydb.core.internal.jdbc.JdbcTemplate
import org.springframework.boot.test.context.SpringBootTest
@SpringBootTest
class AccountRepositoryTests(
    private val jdbcTemplate: org.springframework.jdbc.core.JdbcTemplate,
    private val accountRepository: AccountRepository
) : DescribeSpec({
    describe("test") {
        it("test should be true") {
            val test = 5
            test shouldBe 5
        }
    }

    describe("create") {
        it("should create a new account") {
//            val result = Account(
//                elementType = 2,
//                code = "newCode",
//                name = "for test",
//                id = 14
//            )
            val result = accountRepository.create(
                Account(
                    category = AccountsCategory.ASSETS,
                    code = "not duplicate with ${Math.random()}",
                    name = "unique code with ${Math.random()}"
                )
            )

            val actual = jdbcTemplate.queryForMap("SELECT * FROM accounts WHERE id = ${result.id}")

//            actual["id"].shouldBe(result.id)
//            actual["elementType"].shouldBe(result.elementType)
//            actual["code"].shouldBe(result.code)
//            actual["name"].shouldBe(result.name)
            actual["id"].shouldBe(result.id)
            actual["category"].shouldBe(result.category.name)
            actual["code"].shouldBe(result.code)
            actual["name"].shouldBe(result.name)
        }
    }
})
