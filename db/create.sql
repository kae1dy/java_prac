-- DROP SCHEMA IF EXISTS public CASCADE;
-- UPDATE pg_attribute SET atttypmod = 16192;

CREATE SCHEMA public;


-- Счета клиентов:
-- Баланс
-- История поступлений/списаний
-- Размер максимального кредита
-- Сроки его погашения

CREATE TABLE users
(
    user_id bigserial PRIMARY KEY,
    user_login text NOT NULL UNIQUE,
    user_password text NOT NULL
)

CREATE TABLE accounts
(
    acc_id bigserial PRIMARY KEY,
	acc_balance decimal DEFAULT 0,
	acc_history jsonb,
	acc_credit decimal DEFAULT 0 CHECK(acc_credit >= 0),
	acc_terms date DEFAULT NULL
);

-- Организации:
-- Название
-- 1-ая запись - "Физ. лицо"

CREATE TABLE organizations
(
	org_id bigserial PRIMARY KEY,
	org_name text NOT NULL,
	org_inn char(10)
);

-- Клиенты:
-- Относится ли клиент к организации (если нет, то внешний ключ ведёт на 1-ую запись в таблице "organization", где стоит "Физ. лицо")
-- ФИО
-- Информация о клиенте: телефон, email, адрес
-- История услуг: услуги, в какое время оказывались
-- Внешний ключ на счёт клиента

CREATE TABLE clients
(
    client_id bigserial PRIMARY KEY,
	client_org bigint DEFAULT 1 REFERENCES organizations ON DELETE CASCADE,
    client_name text NOT NULL,
	client_info jsonb,
	-- 1-to-1
	acc_id bigint DEFAULT NULL UNIQUE REFERENCES accounts ON DELETE SET NULL
);

-- Услуги:
-- Наименование
-- Характеристики пакета
-- Тарифный план

CREATE TABLE services
(
	service_id bigserial PRIMARY KEY,
    service_name text NOT NULL,
	service_package jsonb,
	service_tariff jsonb
);

-- Many-to-Many
CREATE TABLE client_service
(
    client_id bigint REFERENCES clients ON DELETE CASCADE,
    service_id bigint REFERENCES services,
	contract_num text NOT NULL,
	contract_begin date NOT NULL,
	contract_end date DEFAULT NULL CHECK(contract_begin <= contract_end),
    CONSTRAINT client_service_pkey PRIMARY KEY (client_id, service_id)
);