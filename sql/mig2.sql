INSERT INTO `blog_lite_db`.`role`
(`id`,
`name`)
VALUES
(1, 'admin'),
(2, 'reader'),
(3, 'registered_user');

INSERT INTO `blog_lite_db`.`product`
(`id`,
`name`,
`description`,
`price`)
VALUES
(1,'jeden wpis','jeden wpis płatny',50.00),
(2,'trzy wpisy','trzy wpisy płatne',120.00),
(3,'pięć wpisów','pięć wpisów płatnych',200.00);

INSERT INTO `blog_lite_db`.`tag`
(`id`,
`name`)
VALUES
(1,'podróże'),
(2,'jedzenie'),
(3,'polityka'),
(4,'dom'),
(5,'gry'),
(6,'filmy'),
(7,'finanse'),
(8,'rodzina'),
(9,'kultura');

INSERT INTO `blog_lite_db`.`user`
(`id`,
`name`,
`last_name`,
`email`,
`role_id`)
VALUES
(1,'Jan','Jakubowski','jjakubowski@wp.pl',2),
(2,'Rafał','Mazurek','kuei-jin@o2.pl',1),
(3,'John','Wayne','macho@gmail.com',2),
(4,'Krystian','Kapusta','kapucha9@vp.pl',3);

INSERT INTO `blog_lite_db`.`post`
(`id`,
`user_id`,
`title`,
`content`,
`picture_url`,
`post_date`)
VALUES
(1,2,'Powitanie','Tutaj będzie wszystko co mnie interesuje i fascynuje i ... wkurza.','','2022-07-01 13:00:00'),
(2,1,'Ale syf','Większego gówna dawno nie czytałem... ','','2022-08-01 13:00:00'),
(3,3,'RObisz posty za kaskę?','Nie wiem, czy to tu powinienem się zapytać, ale nie widzę cennika to nie wiem gdzie indziej, interesuje mnie płątny post. Priv jakby co.','',
'2022-07-12 08:12:55');

INSERT INTO `blog_lite_db`.`comment`
(`id`,
`post_id`,
`content`,
`user_id`,
`date`)
VALUES
(1,1,'Hej, bo nie wiem czy czytałeś mój post to chcę wiedzieć czy publikujesz za kasę?',3,'2022-08-23 12:00:00'),
(2,2,'Wal się na ryj trollu z Kociej Wólki',2,'2022-08-23 12:00:00'),
(3,3,'Nic tu nie kupuj, szkoda kasy, pieniądze w błoto...',1,'2022-09-23 12:50:00');

INSERT INTO `blog_lite_db`.`purchase`
(`id`,
`product_id`,
`buyer_user_id`,
`purchase_date`,
`delivered`,
`comment`)
VALUES
(1,2,3,'2022-08-24 11:30:00',0,'one of three done'),
(2,1,1,'2022-08-25 15:34:05',1,'one of three done'),
(3,3,4,'2022-07-30 23:35:59',0,'one of three done');

INSERT INTO `blog_lite_db`.`purchased_posts`
(`id`,
`purchase_id`,
`post_id`)
VALUES
(1,1,1),
(2,2,3),
(3,3,2);

INSERT INTO `blog_lite_db`.`subscriptions`
(`id`,
`subscriber_id`,
`tag_id`,
`start_date`,
`end_date`)
VALUES
(1,1,3,'2022-09-01 22:22:22',NULL),
(2,4,6,'2022-09-01 22:22:22','2022-10-01 22:22:22'),
(3,3,9,'2022-09-01 22:22:22',NULL);

INSERT INTO `blog_lite_db`.`tags_for_posts`
(`id`,
`tag_id`,
`post_id`)
VALUES
(1,1,1),
(2,2,2),
(3,3,3);
