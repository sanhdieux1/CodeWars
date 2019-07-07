INSERT INTO company(company_name, address) VALUES ('Axon active', 'TanBinh'); -- id 1
INSERT INTO company(company_name, address) VALUES ('Company 1', 'Q1'); -- id 2
INSERT INTO company(company_name, address) VALUES ('Company 2', 'Q2'); -- id 3

INSERT INTO person(name, age) VALUES ('Kail', 20); -- id 1
INSERT INTO person(name, age) VALUES ('Yuri', 21); -- id 2
INSERT INTO person(name, age) VALUES ('Ace', 22); -- id 3

INSERT INTO employee(department, person_id, company_name) VALUES ('QA', 1, 'Axon active');
INSERT INTO employee(department, person_id, company_name) VALUES ('Dev', 2, 'Axon active');
INSERT INTO employee(department, person_id, company_name) VALUES ('Dev', 3, 'Company 1');