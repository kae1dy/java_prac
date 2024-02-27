DROP SCHEMA IF EXISTS billing CASCADE;
CREATE SCHEMA billing;

-- Счета клиентов
-- Баланс
-- Поступления на счет
-- Списания за оказание услуг связи
-- Ограничения: размер максимального кредита и сроки его погашения

DROP TYPE IF EXISTS client_type;
CREATE TYPE client_type AS ENUM (
                        'Физ. лицо', 
                        'Юр. лицо'
);

CREATE TABLE billing.accounts
(
    acc_id bigserial PRIMARY KEY,
	acc_balance decimal DEFAULT 0,
	acc_history jsonb,
	acc_credit decimal DEFAULT 0 CHECK(acc_credit >= 0),
	acc_terms date DEFAULT NULL
);

-- Клиенты – физические лица и организации
-- Наименование или ФИО
-- Контакты: контактные лица, адрес(а), телефон(ы), e-mail(ы)
-- История услуг: услуги, в какое время оказывались

CREATE TABLE billing.clients
(
    client_id bigserial PRIMARY KEY,
	client_type client_type,
    client_name text NOT NULL,
	client_info jsonb,
	client_services jsonb,
	-- 1-to-1
	acc_id bigint DEFAULT NULL UNIQUE REFERENCES billing.accounts ON DELETE SET NULL
);

-- Услуги
-- Наименование
-- Характеристики: номер, группа номеров, Интернет, SMS, спец. предложения
-- Тарифный план (какая часть услуги в какое время сколько будет стоить)

CREATE TABLE billing.services 
(   
    service_code char(8) UNIQUE NOT NULL,
    service_name text NOT NULL,
	service_package jsonb,
	service_tariff jsonb
);

