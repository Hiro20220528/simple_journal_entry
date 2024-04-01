use simple_journal_entry_db;

-- 今回のデータはは特に意味がないものだったので既存のものはdefaultでassetsとしておく, 特に意味はない
alter table accounts add column account_category enum('ASSETS', 'LIABILITIES', 'NET_ASSETS', 'REVENUE', 'COST') default 'ASSETS' not null;
alter table accounts drop column element_type;