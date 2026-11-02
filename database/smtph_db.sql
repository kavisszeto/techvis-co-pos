
DROP DATABASE IF EXISTS `smtph_db`;
CREATE DATABASE IF NOT EXISTS `smtph_db`;
USE `smtph_db`;


DROP TABLE IF EXISTS `smtph`;
CREATE TABLE IF NOT EXISTS `smtph` (
  `smtph_id` varchar(20) NOT NULL,
  `brand` varchar(50) NOT NULL,
  `name` varchar(150) NOT NULL,
  `price` int(11) NOT NULL,
  `photo` varchar(200) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`smtph_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `smtph` (`smtph_id`, `brand`, `name`, `price`, `photo`, `description`) VALUES
        ('map1401', 'Apple', 'iPhone 14', 27900, 'iphone14.png', 'Big and bigger. Wonderfull. A total powerhouse.'),
        ('map1402', 'Apple', 'iPhone 14 Plus', 31900, 'iphone14plus.png', 'Big and bigger. Wonderfull. A total powerhouse.'),
        ('map1411', 'Apple', 'iPhone 14 Pro', 34900, 'iphone14pro.png', 'Pro. Beyond. The ultimate iPhone.'),
        ('map1412', 'Apple', 'iPhone 14 Pro Max', 38900, 'iphone14promax.png', 'Pro. Beyond. The ultimate iPhone.'),
        ('map1301', 'Apple', 'iPhone 13 mini', 20900, 'iphone13mini.png', 'Your new superpower. As amazing as ever.'),
        ('map1302', 'Apple', 'iPhone 13', 24900, 'iphone13.png', 'Your new superpower. As amazing as ever.'),
        ('map1201', 'Apple', 'iPhone 12', 20900, 'iphone12.png', 'Blast past fast.'),
        ('map0561', 'Apple', 'iPhone SE (3rd Gen.)', 14900, 'iphonese3.png', 'Love the power. Love the price.\nSerious power. Serious value.'),
        ('mss2301', 'Samsung', 'Galaxy S23', 26900, 'galaxys23.png', 'Experience NOOOW. Galaxy evolved.'),
        ('mss2302', 'Samsung', 'Galaxy S23+', 33900, 'galaxys23plus.png', 'Experience NOOOW. Galaxy evolved.'),
        ('mss2311', 'Samsung', 'Galaxy S23 Ultra', 40900, 'galaxys23ultra.png', 'Experience NOOOW. Ultra evolved.'),
        ('mss2211', 'Samsung', 'Galaxy S22 Ultra', 38900, 'galaxys22ultra.png', 'The Epic Standard.\nSamsung Galaxy S22 Ultra is the New Note.'),
        ('mss2121', 'Samsung', 'Galaxy S21 FE', 21990, 'galaxys21fe.png', 'Everyday Epic. The Fan Edition is back.'),
        ('msz0401', 'Samsung', 'Galaxy Z Fold4', 56888, 'galaxyzfold4.png', 'Unfold Your World.\nThe Big Bang deserves a bigger screen.'),
        ('msz0411', 'Samsung', 'Galaxy Z Flip4', 32888, 'galaxyzflip4.png', 'Unfold Your World. Wefies by Flip.'),            
        ('msz0311', 'Samsung', 'Galaxy Z Flip3', 30888, 'galaxyzflip3.png', 'We\'ve rethought the phone. Now it\'s a statement.'),
        ('mgp0701', 'Google', 'Pixel 7', 18990, 'pixel7.png', 'Simply powerful. Super helpful.'),
        ('mgp0711', 'Google', 'Pixel 7 Pro', 22990, 'pixel7pro.png', 'The all-pro Google phone.'),
        ('mgp0621', 'Google', 'Pixel 6a', 11490, 'pixel6a.png', 'So much Google. For less than you think.'),
        ('oth0001', 'Techvis Co.','Gift Voucher', -1000, '', 'Membership\'s Welcome Gift - $1000 Voucher.');



DROP TABLE IF EXISTS `sale_order`;
CREATE TABLE IF NOT EXISTS `sale_order` (
  `order_no` varchar(20) NOT NULL,
  `order_date` datetime NOT NULL DEFAULT current_timestamp(),
  `total_price` double(22,0) NOT NULL DEFAULT 0,
  `customer_name` varchar(150) DEFAULT NULL,
  `customer_address` varchar(250) DEFAULT NULL,
  `customer_phone` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sale_order` (`order_no`, `order_date`, `total_price`, `customer_name`, `customer_address`, `customer_phone`) VALUES
	('ord101', '2023-06-08 20:55:07', 13900, 'John', 'Hong Kong', '61234567'),
	('ord102', '2023-06-09 16:21:45', 44378, 'Amy', 'Taiwan', '0912345678');

DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE IF NOT EXISTS `order_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_no` varchar(20) NOT NULL,
  `smtph_id` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL DEFAULT 0,
  `smtph_price` int(11) DEFAULT NULL,
  `smtph_name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_detail_smtph` (`smtph_id`),
  KEY `FK_order_detail_order` (`order_no`),
  CONSTRAINT `FK_order_detail_sale_order` FOREIGN KEY (`order_no`) REFERENCES `sale_order` (`order_no`),
  CONSTRAINT `FK_order_detail_smtph` FOREIGN KEY (`smtph_id`) REFERENCES `smtph` (`smtph_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;


INSERT INTO `order_detail` (`id`, `order_no`, `smtph_id`, `quantity`, `smtph_price`, `smtph_name`) VALUES
	(10, 'ord101', 'map0561', 1, 14900, 'iPhone SE (3rd Gen.)'),
        (11, 'ord101', 'map0561', 1, -1000, 'Gift Voucher'),
	(12, 'ord102', 'msz0411', 1, 32888, 'Galaxy Z Flip4'),
	(13, 'ord102', 'mgp0701', 1, 11490, 'Pixel 6a');

