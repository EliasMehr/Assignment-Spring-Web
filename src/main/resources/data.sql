INSERT INTO person (id, first_name, last_name, age, gender, social_status) VALUES
('92a9af9f-90f4-4b0e-b77b-1088fa929e69','Elias', 'Mehr', 31, 'MALE', 'Married'),
('3a5a01b2-215b-4322-9deb-5a4452145600','Fazli', 'Zekiqi', 26, 'MALE', 'Married'),
('664aa3b1-4926-4974-94b4-6e9c8d981a34','Daniel', 'Hughes', 31, 'MALE', 'Single'),
('235fafe3-14e8-461d-9ead-96b2fcc82be3','Johannes', 'Svensson', 23, 'MALE', 'In relationship'),
('373e0b56-ed10-49e7-8f65-5f7c05b8b4d0','Jessie', 'Eurenius', 40, 'MALE', 'In Relationship')
ON CONFLICT (id) DO NOTHING;