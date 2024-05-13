create sequence public.shortener_id_seq;
create table public.shortener (
    id bigint primary key not null default nextval('shortener_id_seq'),
    url varchar(1024) not null,
    shortened_code varchar(5) not null,
    uses integer not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp not null
);