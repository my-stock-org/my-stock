-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 26 jan. 2022 à 18:20
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `mystock`
--

-- --------------------------------------------------------

--
-- Structure de la table `caissier`
--

DROP TABLE IF EXISTS `caissier`;
CREATE TABLE IF NOT EXISTS `caissier` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `montant_vendu_Caissier` int(11) DEFAULT NULL,
  `telephone` varchar(10) NOT NULL,
  `id_patron` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`telephone`),
  KEY `id_patron` (`id_patron`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `caissier`
--

INSERT INTO `caissier` (`id`, `nom`, `password`, `montant_vendu_Caissier`, `telephone`, `id_patron`) VALUES
(1, 'abomo', '12345', 0, '699187769', 1);

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(30) DEFAULT NULL,
  `telephone` varchar(10) DEFAULT NULL,
  `addresse` varchar(10) DEFAULT NULL,
  `id_patron` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_patron` (`id_patron`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id`, `nom`, `telephone`, `addresse`, `id_patron`) VALUES
(1, 'Ken ', '671234400 ', 'Ebang ', 1);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reference` varchar(20) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `id_client` int(11) DEFAULT NULL,
  `id_produit` int(11) DEFAULT NULL,
  `create_at` date NOT NULL,
  `update_at` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_client` (`id_client`),
  KEY `id_produit` (`id_produit`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `patron`
--

DROP TABLE IF EXISTS `patron`;
CREATE TABLE IF NOT EXISTS `patron` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(20) NOT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`,`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `patron`
--

INSERT INTO `patron` (`id`, `email`, `nom`, `password`) VALUES
(1, 'kenne@gmail.com', 'KENNE Roosvelt', 'ken12345');
INSERT INTO `patron` (`id`, `email`, `nom`, `password`) VALUES
(2, 'ngnitedem@gmail.com', 'Oldrich Ngnitedem', '123456');
INSERT INTO `patron` (`id`, `email`, `nom`, `password`) VALUES
(3, 'kougaba@gmail.com', 'Kougaba Marilin', '123456');

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reference` varchar(30) DEFAULT NULL,
  `nom` varchar(30) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `prix` int(11) DEFAULT NULL,
  `description` text,
  `id_patron` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_patron` (`id_patron`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `produit`
--

INSERT INTO `produit` (`id`, `reference`, `nom`, `stock`, `prix`, `description`, `id_patron`) VALUES
(2, 'xx1', 'Nestle', -470, 500, 'Laie en boite ', 1),
(3, 'xx50', 'Elena', 184, 100, 'Tomate en sachet', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `caissier`
--
ALTER TABLE `caissier`
  ADD CONSTRAINT `caissier_ibfk_1` FOREIGN KEY (`id_patron`) REFERENCES `patron` (`id`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `client_ibfk_1` FOREIGN KEY (`id_patron`) REFERENCES `patron` (`id`);

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `commande_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`),
  ADD CONSTRAINT `commande_ibfk_2` FOREIGN KEY (`id_produit`) REFERENCES `produit` (`id`);

--
-- Contraintes pour la table `produit`
--
ALTER TABLE `produit`
  ADD CONSTRAINT `produit_ibfk_1` FOREIGN KEY (`id_patron`) REFERENCES `patron` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
