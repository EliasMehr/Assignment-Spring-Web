INSERT INTO person (id, first_name, last_name, age, gender, social_status) VALUES
('92a9af9f-90f4-4b0e-b77b-1088fa929e69','Per', 'Persson', 20, 'MALE', 'Married'),
('3a5a01b2-215b-4322-9deb-5a4452145600','Malin', 'Malinsson', 32, 'FEMALE', 'Single'),
('664aa3b1-4926-4974-94b4-6e9c8d981a34','Bo', 'Bosson', 19, 'MALE', 'Mormon')
ON CONFLICT (id) DO NOTHING;