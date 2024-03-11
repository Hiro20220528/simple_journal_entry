package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Account
import com.okeicalm.simpleJournalEntry.handler.type.JournalEntryType
import com.okeicalm.simpleJournalEntry.infra.db.enums.AccountsCategory
import com.okeicalm.simpleJournalEntry.infra.db.tables.Accounts
import org.jooq.DSLContext
import org.jooq.Record
import org.springframework.stereotype.Repository

interface AccountRepository {
    fun findAll(): List<Account>
    fun findById(id: Long): Account?
    fun create(account: Account): Account
    fun update(account: Account): Account
    fun delete(id: Long): Long

    /**
     * 新しく追加
     */
    fun findByIds(journalEntryList: List<List<JournalEntryType>>): List<Account>
}

@Repository
class AccountRepositoryImpl(private val dslContext: DSLContext) : AccountRepository {
    override fun findAll(): List<Account> {
        return dslContext.select()
            .from(Accounts.ACCOUNTS)
            .fetch()
            .into(Account::class.java)
    }

    override fun findById(id: Long): Account? {
        return dslContext
            .fetchOne(Accounts.ACCOUNTS, Accounts.ACCOUNTS.ID.eq(id))
            ?.into(Account::class.java)
    }

    override fun findByIds(journalEntryList: List<List<JournalEntryType>>): List<Account> {
        println("================ findByIds ====================")
        println(journalEntryList)

        println("=========== list?? =====================")
        val ids: List<com.expediagroup.graphql.generator.scalars.ID> = journalEntryList.map { it[0].accountId }
        println(ids)

        return dslContext.select()
            .from(Accounts.ACCOUNTS)
            .where(Accounts.ACCOUNTS.ID.`in`(ids))
            .fetch { recordToAccount(it) }
    }

    private fun recordToAccount(record: Record?): Account? {
        return if (record != null) Account(
            id = record.get(Accounts.ACCOUNTS.ID)!!,
            code = record.get(Accounts.ACCOUNTS.CODE)!!,
            category = record.get(Accounts.ACCOUNTS.CATEGORY)!!,
            name = record.get(Accounts.ACCOUNTS.NAME)!!,
        ) else null
    }

    override fun create(account: Account): Account {
        val record = dslContext
            .newRecord(Accounts.ACCOUNTS)
            .apply {
                name = account.name
                code = account.code
                category = AccountsCategory.valueOf(account.category.name)
            }
        record.store()

        return account.copy(id = record.id!!)
    }

    override fun update(account: Account): Account {
        dslContext
            .update(Accounts.ACCOUNTS)
            .set(Accounts.ACCOUNTS.CODE, account.code)
            .set(Accounts.ACCOUNTS.NAME, account.name)
            .set(Accounts.ACCOUNTS.CATEGORY, AccountsCategory.valueOf(account.category.name))
            .where(Accounts.ACCOUNTS.ID.eq(account.id))
            .execute()
        return account
    }

    override fun delete(id: Long): Long {
        dslContext
            .delete(Accounts.ACCOUNTS)
            .where(Accounts.ACCOUNTS.ID.eq(id))
            .execute()
        return id
    }
}
