/*
 * This file is generated by jOOQ.
 */
package com.okeicalm.simpleJournalEntry.infra.db.keys

import com.okeicalm.simpleJournalEntry.infra.db.tables.Accounts
import com.okeicalm.simpleJournalEntry.infra.db.tables.JournalEntries
import com.okeicalm.simpleJournalEntry.infra.db.tables.Journals
import com.okeicalm.simpleJournalEntry.infra.db.tables.records.AccountsRecord
import com.okeicalm.simpleJournalEntry.infra.db.tables.records.JournalEntriesRecord
import com.okeicalm.simpleJournalEntry.infra.db.tables.records.JournalsRecord
import org.jooq.ForeignKey
import org.jooq.UniqueKey
import org.jooq.impl.DSL
import org.jooq.impl.Internal

// -------------------------------------------------------------------------
// UNIQUE and PRIMARY KEY definitions
// -------------------------------------------------------------------------

val KEY_ACCOUNTS_CODE: UniqueKey<AccountsRecord> = Internal.createUniqueKey(Accounts.ACCOUNTS, DSL.name("KEY_accounts_code"), arrayOf(Accounts.ACCOUNTS.CODE), true)
val KEY_ACCOUNTS_PRIMARY: UniqueKey<AccountsRecord> = Internal.createUniqueKey(Accounts.ACCOUNTS, DSL.name("KEY_accounts_PRIMARY"), arrayOf(Accounts.ACCOUNTS.ID), true)
val KEY_JOURNAL_ENTRIES_PRIMARY: UniqueKey<JournalEntriesRecord> = Internal.createUniqueKey(JournalEntries.JOURNAL_ENTRIES, DSL.name("KEY_journal_entries_PRIMARY"), arrayOf(JournalEntries.JOURNAL_ENTRIES.ID), true)
val KEY_JOURNALS_PRIMARY: UniqueKey<JournalsRecord> = Internal.createUniqueKey(Journals.JOURNALS, DSL.name("KEY_journals_PRIMARY"), arrayOf(Journals.JOURNALS.ID), true)

// -------------------------------------------------------------------------
// FOREIGN KEY definitions
// -------------------------------------------------------------------------

val JOURNAL_ENTRIES_IBFK_1: ForeignKey<JournalEntriesRecord, JournalsRecord> = Internal.createForeignKey(JournalEntries.JOURNAL_ENTRIES, DSL.name("journal_entries_ibfk_1"), arrayOf(JournalEntries.JOURNAL_ENTRIES.JOURNAL_ID), com.okeicalm.simpleJournalEntry.infra.db.keys.KEY_JOURNALS_PRIMARY, arrayOf(Journals.JOURNALS.ID), true)
val JOURNAL_ENTRIES_IBFK_2: ForeignKey<JournalEntriesRecord, AccountsRecord> = Internal.createForeignKey(JournalEntries.JOURNAL_ENTRIES, DSL.name("journal_entries_ibfk_2"), arrayOf(JournalEntries.JOURNAL_ENTRIES.ACCOUNT_ID), com.okeicalm.simpleJournalEntry.infra.db.keys.KEY_ACCOUNTS_PRIMARY, arrayOf(Accounts.ACCOUNTS.ID), true)
