# Initialization

## Database

### PostgreSQL

```postgresql
create table member.samples
(
    id          uuid                     default gen_random_uuid() not null
        primary key,
    name        varchar(255)                                       not null,
    description varchar(255),
    created_at  timestamp with time zone default now()             not null,
    updated_at  timestamp with time zone default now()             not null,
    deleted_at  timestamp with time zone
);

alter table member.samples
    owner to kimwonung;
```
