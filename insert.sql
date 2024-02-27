INSERT INTO billing.services(service_code, service_name, service_package, service_tariff) VALUES
	('KBKWNXW7', 'Всё за 500', '{"min": 300, "sms": 500, "internet": 800, "max_members": 1}', '{"tariff": 500.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('4RGP5JL6', 'Всё за 1000', '{"min": 500, "sms": 1000, "internet": 1500, "max_members": 1}', '{"tariff": 1000.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('089G7FGP', 'Семья', '{"min": 1500, "sms": 2000, "internet": 2000, "max_members": 3}', '{"tariff": 1200.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('X7DAVJLL', 'Семья+', '{"min": 2500, "sms": 2500, "internet": 3000, "max_members": 5}', '{"tariff": 1700.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('CKJ6KV4I', 'Только звонки', '{"min": 500, "sms": 100, "internet": 0, "max_members": 1}', '{"tariff": 150.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('WBRNXG5E', 'Только интернет', '{"min": 0, "smhs": 100, "internet": 1000, "max_members": 1}', '{"tariff": 200.0, "extra_min": 1.5, "extra_sms": 1.0, "extra_internet": 5.0}'),
	('E3GJLLLS', 'Поминутный', '{"min": 0, "sms": 0, "internet": 0, "max_members": 1}', '{"tariff": 0.0, "extra_min": 0.5, "extra_sms": 1.0, "extra_internet": 1.5}');
	
INSERT INTO billing.accounts(acc_balance, acc_history, acc_credit, acc_terms) VALUES
	(2557, '[1575, -76, 958, -590, 690]', 300, '2024-03-26'),
	(975, '[1648, -989, 779, -653, 190]', 100, '2024-06-05'),
	(165, '[1370, -679, 143, -509, 741, -901]', 400, '2024-07-09'),
	(710, '[1229, -572, 69, -114, 912, -814]', 1500, '2024-07-07'),
	(793, '[1371, -883, 845, -540]', 1000, '2024-06-22'),
	(1608, '[1870, -149, 145, -477, 963, -744]', 300, '2024-04-21'),
	(1483, '[1586, -631, 528]', 1000, '2024-08-08'),
	(495, '[1089, 278, -233, 194, -833]', 500, '2024-06-21'),
	(890, '[954, -998, 893, -504, 747, -202]', 500, '2024-05-11'),
	(1515, '[1838, -323]', 100, '2024-06-21');
	
	
INSERT INTO billing.clients(client_type, client_name, client_info, client_services, acc_id) VALUES
	('Физ. лицо', 'Абрамов Михаил Андреевич', '{"Абрамов Михаил Андреевич": {"phone": "89021417351", "email": "abram@mail.ru", "address": "Москва, ул. Пушкинская, д. 15"}}', '{"KBKWNXW7": {"begin": "2024-01-21", "end": "2024-02-21"}}', 1),
	('Физ. лицо', 'Валявкин Максим Александрович', '{"Валявкин Максим Александрович": {"phone": "89021417352", "email": "val@mail.ru", "address": "Калуга, ул. Гурьянова, д. 50"}}', '{"E3GJLLLS": {"begin": "2023-12-15", "end": "2024-02-15"}}', 2),
	('Физ. лицо', 'Кабанов Иван Юрьевич', '{"Кабанов Иван Юрьевич": {"phone": "89021417353", "email": "kaban@mail.ru", "address": "Ярославль, ул. Мирная, д. 14"}}', '{"WBRNXG5E": {"begin": "2022-01-07", "end": "2024-01-07"}}', 3),
	('Физ. лицо', 'Митрофанов Алексей Антонович', '{"Митрофанов Алексей Антонович": {"phone": "89021417354", "email": "mitrofan@mail.ru", "address": "Москва, пр. Ломоносовский, д. 17"}}', '{"089G7FGP": {"begin": "2023-05-24", "end": "2023-08-24"}}', 4),
	('Физ. лицо', 'Артамонова Юлия Максимовна', '{"Артамонова Юлия Максимовна": {"phone": "89021417355", "email": "yulik@mail.ru", "address": "Ульяновск, ул. Промышленная, д. 4"}}', '{"KBKWNXW7": {"begin": "2024-01-21", "end": "2023-01-21"}}', 5);
