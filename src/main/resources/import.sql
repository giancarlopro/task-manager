insert into user (id, nome, email, authority, password, enabled) values (1, 'Giancarlo', 'gian@local.com', 'ADMIN', '$2a$10$pJTcFEcCRvps3khgCs71aemMQlQBc6T2pBJJFS/D96FBIp0V/jkNq', 1);
insert into user (id, nome, email, authority, password, enabled) values (2, 'Brendo', 'brendo@local.com', 'ADMIN', '$2a$10$pJTcFEcCRvps3khgCs71aemMQlQBc6T2pBJJFS/D96FBIp0V/jkNq', 1);
insert into user (id, nome, email, authority, password, enabled) values (3, 'Paulo', 'paulo@local.com', 'ADMIN', '$2a$10$pJTcFEcCRvps3khgCs71aemMQlQBc6T2pBJJFS/D96FBIp0V/jkNq', 1);
insert into task (id, nome, ativo, descricao, user_id) values (1, 'Estudar Spring', TRUE, 'Estudar material da prova', 1);
insert into task (id, nome, ativo, descricao, user_id) values (2, 'Estudar JPA', TRUE, 'Estudar relacionamentos', 2);
insert into task (id, nome, ativo, descricao, user_id) values (3, 'Estudar JPA', TRUE, 'Estudar anotações', 3);
insert into attachment (id, nome, url) values (1, 'Livro de Spring', 'http://s3.amazonaws.com/algaworks-assets/ebooks/algaworks-livro-spring-boot-v3.0.pdf');
insert into attachment (id, nome, url) values (2, 'Foto do Ted Mosby', 'https://upload.wikimedia.org/wikipedia/pt/thumb/e/e0/Ted_Mosby.jpg/220px-Ted_Mosby.jpg');
insert into attachment (id, nome, url) values (3, 'Foto do Barney Stinson', 'https://www.movenoticias.com/wp-content/uploads/2014/08/Neil-Patrick-Harris-635x450.jpg');