use simple_journal_entry_db;

-- account_categoryだとclass nameがAccountsAccountCategoryになるので修正する
alter table accounts change account_category category enum('ASSETS', 'LIABILITIES', 'NET_ASSETS', 'REVENUE', 'COST') default 'ASSETS' not null;