package com.okeicalm.simpleJournalEntry.repository

import com.okeicalm.simpleJournalEntry.entity.Journal
import com.okeicalm.simpleJournalEntry.entity.JournalEntry
import com.okeicalm.simpleJournalEntry.infra.db.tables.references.JOURNALS
import com.okeicalm.simpleJournalEntry.infra.db.tables.references.JOURNAL_ENTRIES
import org.jooq.DSLContext
import org.springframework.stereotype.Repository

interface JournalRepository {
    fun findAll(): List<Journal>
    fun findById(id: Long): Journal?
    fun create(journal: Journal): Journal
}

@Repository
class JournalRepositoryImpl(private val dslContext: DSLContext) : JournalRepository {
    override fun findAll(): List<Journal> {
        return dslContext
            .select(
                JOURNALS.ID,
                JOURNALS.INCURRED_ON,
                JOURNAL_ENTRIES.ID,
                JOURNAL_ENTRIES.JOURNAL_ID,
                JOURNAL_ENTRIES.SIDE,
                JOURNAL_ENTRIES.VALUE,
                JOURNAL_ENTRIES.ACCOUNT_ID,
            )
            .from(JOURNALS)
            .join(JOURNAL_ENTRIES)
            .on(JOURNALS.ID.eq(JOURNAL_ENTRIES.JOURNAL_ID))
            .fetch {
                Journal(
                    id = it.getValue(JOURNALS.ID)!!,
                    incurredOn = it.getValue(JOURNALS.INCURRED_ON)!!,
//                    journalEntries = null,
                    journalEntries = listOf(JournalEntry(
                        id = it.getValue( JOURNAL_ENTRIES.ID)!!,
                        journalId = it.getValue( JOURNAL_ENTRIES.JOURNAL_ID)!!,
                        side = it.getValue( JOURNAL_ENTRIES.SIDE)!!,
                        value = it.getValue(JOURNAL_ENTRIES.VALUE)!!,
                        accountId = it.getValue(JOURNAL_ENTRIES.ACCOUNT_ID)!!,
                    ))
                )
            }
    }

    override fun findById(id: Long): Journal? {
        return dslContext
            .fetchOne(JOURNALS, JOURNALS.ID.eq(id))
            ?.into(Journal::class.java)
    }

    override fun create(journal: Journal): Journal {
        val record = dslContext
            .newRecord(JOURNALS)
            .apply {
                incurredOn = journal.incurredOn
            }
        record.store()
        return journal.copy(id = record.id!!)
    }
}
