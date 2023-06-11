INSERT INTO `users` (
    `id`,
    `dni_number`,
    `name`,
    `surname`,
    `mail`,
    `phone`,
    `birthdate`,
    `password`,
    `id_role`,
  )
VALUES
  (
    '1',
    '78640357',
    'Juan ',
    'Lopez',
    'juan@gmail.com',
    '+573002568749',
    '03/10/2002',
    '$2a$10$GlsGSNhkbVon6ZOSNMptOu5RikedRzlCAhMa7YpwvUSS0c88WT99S',
     '1'
  );


INSERT INTO `role` (`id`, `description`, `name`) VALUES ('1', 'ADMINISTRATOR_ROLE', 'ROLE_ADMIN');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('4', 'CLIENT_ROLE', 'ROLE_USER');


INSERT INTO `role` (`id`, `description`, `name`) VALUES ('1', 'ADMINISTRATOR_ROLE', 'ADMINISTRATOR_ROLE');
INSERT INTO `role` (`id`, `description`, `name`) VALUES ('2', 'CLIENT_ROLE', 'CLIENT_ROLE');

INSERT INTO `user` (`id_person`, `id_role`) VALUES ('1', '1');