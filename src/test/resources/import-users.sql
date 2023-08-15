/** USERS **/
INSERT INTO public.users
(id, first_name, last_name, email, enabled, password, created_date)
VALUES
    (1, 'Miguel', 'Sanchez', 'miguel@email.com', true, '12345', '11 05 2023 15:02:23'),
    (2, 'Alejandro', 'Rodriguez', 'ale@email.com', true, '12345', '11 05 2023 15:02:24');

/** ROLE **/
INSERT INTO public.role
(id, name)
VALUES
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_STAFF'),
    (3, 'ROLE_USER');

/** PRIVILEGE **/
INSERT INTO public.privilege
(id, name)
VALUES
    (1, 'WRITE_PRIVILEGE'),
    (2, 'READ_PRIVILEGE');

/** ROLE-PRIVILEGE **/
INSERT INTO public.role_privilege
(role_id, privilege_id)
VALUES
    (1,1),
    (1,2),
    (2,2),
    (3,2);

/** USER-ROLE **/
INSERT INTO public.user_role
(user_id, role_id)
VALUES
    (1,1),
    (2,2);
