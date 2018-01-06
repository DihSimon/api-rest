CREATE TABLE `categoria` (
	`codigo` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(250) NOT NULL,
	PRIMARY KEY (`codigo`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;

INSERT INTO `categoria` (`codigo`, `nome`) VALUES
	(1, 'Lazer'),
	(2, 'Alimentação'),
	(3, 'Supermercado'),
	(4, 'Farmácia'),
	(5, 'Outros');
