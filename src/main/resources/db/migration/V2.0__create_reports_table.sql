create table analytics.company_reports
(
    id           uuid primary key,
    company_name varchar not null,
    report       bytea   not null
)
