-- Adds the lookup data
INSERT INTO LOOKUPENTITY(id, name, ordinal, value, dtype) VALUES (1, 'Admin', 1, 'ROLE_ADMIN', 'UserRole');
INSERT INTO LOOKUPENTITY(id, name, ordinal, value, dtype) VALUES (2, 'User', 2, 'ROLE_USER', 'UserRole');

INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) VALUES (3, 'Web Service', 1, 'ServiceType');
INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) values (4, 'REST', 2, 'ServiceType');

INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) VALUES (5, 'Database', 1, 'DataSourceType');

INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) VALUES (8, 'Password', 1, 'WebServiceSecurityType');
INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) VALUES (9, 'Digest', 2, 'WebServiceSecurityType');
INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) VALUES (10, 'Signature', 3, 'WebServiceSecurityType');
INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) VALUES (11, 'Encryption', 4, 'WebServiceSecurityType');
-- INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) VALUES (12, 'HTTP Basic', 5, 'RestSecurityType');
-- INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) VALUES (13, 'HTTP Digest', 6, 'RestSecurityType');
-- INSERT INTO LOOKUPENTITY(id, name, ordinal, dtype) VALUES (14, 'OAuth', 7, 'SecurityType');

-- adds the default users
INSERT INTO USERINFO(userName, password, emailAddress, enabled, removed, userRole_id) VALUES ('admin', 'admin', 'admin@soa.org', 1, 0, 1);
INSERT INTO USERINFO(userName, password, emailAddress, enabled, removed, userRole_id) VALUES ('test', 'test', 'test@soa.org', 1, 0, 2);
