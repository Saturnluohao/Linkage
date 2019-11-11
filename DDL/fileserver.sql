CREATE TABLE `weblog` (
                          `blog_id` int(11) NOT NULL AUTO_INCREMENT,
                          `poster_id` int(11) NOT NULL,
                          `text` text NOT NULL,
                          `picture_num` int(2) DEFAULT NULL,
                          `video_num` int(2) DEFAULT NULL,
                          PRIMARY KEY (`blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;