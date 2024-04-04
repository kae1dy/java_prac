INSERT INTO organizations(org_name, org_inn) VALUES
	('Физ. лицо', NULL),
	('ИП Ромашка', '3620961634'),
	('ОАО РЖД', '9777241723'),
	('ПАО Газпром', '5474983662'),
	('ООО Советник', '5287317415');
	

INSERT INTO services(service_name, service_package, service_tariff) VALUES
	('Всё за 500', '{"min": 300, "sms": 500, "internet": 800, "max_members": 1}', '{"tariff": 500.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('Всё за 1000', '{"min": 500, "sms": 1000, "internet": 1500, "max_members": 1}', '{"tariff": 1000.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('Семья', '{"min": 1500, "sms": 2000, "internet": 2000, "max_members": 3}', '{"tariff": 1200.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('Семья+', '{"min": 2500, "sms": 2500, "internet": 3000, "max_members": 5}', '{"tariff": 1700.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('Только звонки', '{"min": 500, "sms": 100, "internet": 0, "max_members": 1}', '{"tariff": 150.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('Только интернет', '{"min": 0, "sms": 100, "internet": 1000, "max_members": 1}', '{"tariff": 200.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('Поминутный', '{"min": 0, "sms": 0, "internet": 0, "max_members": 1}', '{"tariff": 0.0, "extra_min": 0.5, "extra_sms": 1.0, "extra_internet": 1.5}');
	
	
INSERT INTO accounts(acc_balance, acc_history, acc_credit, acc_terms) VALUES
	(2557, '{"2024-01-21": 1575, "2024-01-22": -76, "2024-01-23": 958, "2024-01-24": -590, "2024-01-25": 690}', 300, '2024-03-26'),
	(975, '{"2024-01-21": 1648, "2024-01-22": -989, "2024-01-23": 779, "2024-01-24": -653, "2024-01-25": 190}', 100, '2024-06-05'),
	(165, '{"2024-01-21": 1370, "2024-01-22": -679, "2024-01-23": 143, "2024-01-24": -509, "2024-01-25": 741, "2024-01-26": -901}', 400, '2024-07-09'),
	(1483, '{"2024-01-21": 1586, "2024-01-21": -631, "2024-01-21": 528}', 1000, '2024-08-08'),
	(1515, '{"2024-01-21": 1838, "2024-01-21": -323}', 100, '2024-06-21');
	
INSERT INTO clients(client_org, client_name, client_info, acc_id) VALUES
	(1, 'Абрамов Михаил Андреевич', '{"phone": "89021417351", "email": "abram@mail.ru", "address": "Москва, ул. Пушкинская, д. 15"}', 1),
	(2, 'Валявкин Максим Александрович', '{"phone": "89021417352", "email": "val@mail.ru", "address": "Калуга, ул. Гурьянова, д. 50"}', 2),
	(3, 'Кабанов Иван Юрьевич', '{"phone": "89021417353", "email": "kaban@mail.ru", "address": "Ярославль, ул. Мирная, д. 14"}', 3),
	(4, 'Митрофанов Алексей Антонович', '{"phone": "89021417354", "email": "mitrofan@mail.ru", "address": "Москва, пр. Ломоносовский, д. 17"}', 4),
	(5, 'Артамонова Юлия Максимовна', '{"phone": "89021417355", "email": "yulik@mail.ru", "address": "Ульяновск, ул. Промышленная, д. 4"}', 5);
	
	
INSERT INTO client_service(client_id, service_id, contract_num, contract_begin) VALUES
	(1, 1, '1-24/01', '2024-01-21'),
	(2, 2, '1-23/05', '2023-05-23'),
	(3, 3, '1-24/02', '2024-02-10'),
	(4, 4, '2-24/02', '2024-02-05'),
	(5, 5, '3-24/02', '2024-02-11');