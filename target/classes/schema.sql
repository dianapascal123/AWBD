CREATE TABLE IF NOT EXISTS category (
    id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title varchar(50) NOT NULL
    );

CREATE TABLE product (
    id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    brand varchar(100) DEFAULT NULL,
    description varchar(255) DEFAULT NULL,
    fragrance_top_notes varchar(255) DEFAULT NULL,
    image_url varchar(255) DEFAULT NULL,
    price float NOT NULL,
    title varchar(50) NOT NULL,
    volume varchar(20) DEFAULT NULL,
    year int(11) DEFAULT NULL,
    category_id bigint(20) DEFAULT NULL
--     CONSTRAINT `cat_pk` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
    );

alter table product
add CONSTRAINT cat_pk FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

CREATE TABLE IF NOT EXISTS `product_view` (
    `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `views_number` int(11) DEFAULT NULL,
    `product_id` bigint(20) DEFAULT NULL
    );

alter table product_view
add CONSTRAINT `pv_pk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

CREATE TABLE IF NOT EXISTS `user` (
    `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `active` bit(1) DEFAULT NULL,
    `email` varchar(255) NOT NULL,
    `first_name` varchar(255) NOT NULL,
    `last_name` varchar(255) NOT NULL,
    `password` varchar(255) NOT NULL,
    `username` varchar(255) DEFAULT NULL
    );

CREATE TABLE IF NOT EXISTS `address` (
    `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `city` varchar(255) DEFAULT NULL,
    `county` varchar(255) DEFAULT NULL,
    `postal_code` varchar(255) DEFAULT NULL,
    `street` varchar(255) DEFAULT NULL,
    `user_id` bigint(20) DEFAULT NULL
    );

alter table address
    add CONSTRAINT `add_pk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

CREATE TABLE IF NOT EXISTS `perfume_order` (
    `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `order_date` DATE,
    `user_id` bigint(20) DEFAULT NULL
    );

alter table address
    add CONSTRAINT `ord_pk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

CREATE TABLE IF NOT EXISTS `product_order` (
    `product_id` bigint(20) NOT NULL,
    `order_id` bigint(20) NOT NULL
    );

alter table product_order
    add CONSTRAINT `poo_pk` FOREIGN KEY (`order_id`) REFERENCES `perfume_order` (`id`);

alter table product_order
    add CONSTRAINT `pop_pk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);


INSERT INTO `product` (`id`, `brand`, `description`, `fragrance_top_notes`, `image_url`, `price`, `title`, `volume`, `year`, `category_id`) VALUES
    (5, 'Carolina Herrera', 'Apa de parfum Carolina Herrera Good Girl inspirata din conceptul dualitatii femeii moderne, reflecta contradictii si contraste pentru orice personalitate', 'migdale', 'https://cdn.notinoimg.com/detail_zoom/carolina-herrera/8411061818961x_03/carolina-herrera-good-girl-eau-de-parfum-pentru-femei_.jpg', 449, 'Good Girl', '150 ml', 2020, NULL);


-- DELETE FROM `product_view`;
-- INSERT INTO `product_view` (`id`, `views_number`, `product_id`) VALUES
--     (4, 4, 5);

INSERT INTO `user` (`id`, `active`, `email`, `first_name`, `last_name`, `password`, `username`) VALUES
    (1, '1', 'test@test.com', 'test', 'test', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'test');

DELETE FROM `address`;

INSERT INTO `address` (`id`, `city`, `county`, `postal_code`, `street`, `user_id`) VALUES
    (4, 'test', 'test', 'test', 'test', 1);

DELETE FROM `category`;

INSERT INTO `category` (`id`, `title`) VALUES
    (2, 'Summer perfumes');

DELETE FROM `product`;

INSERT INTO `product` (`id`, `brand`, `description`, `fragrance_top_notes`, `image_url`, `price`, `title`, `volume`, `year`, `category_id`) VALUES
    (5, 'Carolina Herrera', 'Apa de parfum Carolina Herrera Good Girl inspirata din conceptul dualitatii femeii moderne, reflecta contradictii si contraste pentru orice personalitate', 'migdale', 'https://cdn.notinoimg.com/detail_zoom/carolina-herrera/8411061818961x_03/carolina-herrera-good-girl-eau-de-parfum-pentru-femei_.jpg', 449, 'Good Girl', '150 ml', 2020, NULL);

DELETE FROM `perfume_order`;

INSERT INTO `perfume_order` (`id`, `order_date`, `user_id`) VALUES
    (5, to_date('2022-05-24', 'Y-m-d'), 1);

DELETE FROM `product_order`;

INSERT INTO `product_order` (`product_id`, `order_id`) VALUES
    (5, 5);

CREATE TABLE IF NOT EXISTS `authorities` (
    `authority` varchar(50) NOT NULL,
    `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id` bigint(20) DEFAULT NULL,
    `email` varchar(50) NOT NULL
    );


-- ALTER TABLE authorities
--     ADD id BIGINT(20);
--
-- ALTER TABLE authorities
--     ADD PRIMARY KEY(id);
--
-- ALTER TABLE authorities MODIFY id BIGINT(20) NOT NULL AUTO_INCREMENT;
--
-- ALTER TABLE authorities
--     ADD user_id BIGINT(20);

ALTER TABLE authorities
    ADD constraint fk_auth1 FOREIGN KEY(user_id) REFERENCES user(id);

INSERT INTO `authorities` (`authority`, `id`, `user_id`, `email`) VALUES
                                                                      ('ROLE_ADMIN', 1, 1, 'test@test.com');




