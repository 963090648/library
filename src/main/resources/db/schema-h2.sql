DROP TABLE IF EXISTS book;
CREATE TABLE book(
                     `ID` INT PRIMARY KEY AUTO_INCREMENT,
                     `author` VARCHAR(255) DEFAULT NULL,
                     `isbn` varchar(255) DEFAULT NULL,
                     `name` varchar(255) DEFAULT NULL,
                     `pages` int DEFAULT NULL,
                     `price` double DEFAULT NULL,
                     `publish` varchar(255) DEFAULT NULL,
                     `publish_time` datetime(6) DEFAULT NULL,
                     `size` int DEFAULT NULL,
                     `translate` varchar(255) DEFAULT NULL,
                     `type` varchar(255) DEFAULT NULL
);

DROP TABLE IF EXISTS borrow;
CREATE TABLE borrow (
                        `id` int PRIMARY KEY AUTO_INCREMENT,
                        `book_id` int DEFAULT NULL,
                        `create_time` datetime(6) DEFAULT NULL,
                        `update_time` datetime(6) DEFAULT NULL,
                        `user_id` int DEFAULT NULL,
                        `end_time` datetime(6) DEFAULT NULL,
                        `ret` int DEFAULT NULL
);


DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` int PRIMARY KEY AUTO_INCREMENT,
                         `address` varchar(255) DEFAULT NULL,
                         `avatar` varchar(255) DEFAULT NULL,
                         `birthday` datetime(6) DEFAULT NULL,
                         `email` varchar(255) DEFAULT NULL,
                         `identity` int DEFAULT NULL,
                         `is_admin` int DEFAULT NULL,
                         `nickname` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `size` int DEFAULT NULL,
                         `tel` varchar(255) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL
) ;