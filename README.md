# TP-POO
Travail Pratique de Programmation Orientée Objet.

# Application de Gestion de Contacts

Cette application de gestion de contacts est conçue pour permettre aux utilisateurs de gérer facilement un répertoire de contacts comprenant différents types tels que les étudiants, les agents et les enseignants. L'application offre une interface conviviale permettant d'ajouter, de supprimer, de modifier et de rechercher des contacts, ainsi que d'afficher une liste des contacts selon le type spécifié.

## Fonctionnalités - Mode Console

- **Ajouter un contact** : Permet d'ajouter un nouveau contact au répertoire en spécifiant ses informations telles que le nom, la date de naissance, l'adresse, l'e-mail, le numéro de téléphone, etc.
  
- **Supprimer un contact** : Permet de supprimer un contact existant du répertoire en fonction de son identifiant unique (code).
  
- **Modifier un contact** : Permet de mettre à jour les informations d'un contact existant dans le répertoire en spécifiant son identifiant unique (code) et en saisissant les nouvelles informations.
  
- **Rechercher un contact** : Permet de rechercher un contact dans le répertoire en fonction de son nom.
  
- **Afficher les contacts par type** : Permet d'afficher une liste de contacts en fonction du type spécifié, tels que les étudiants, les agents et les enseignants.

## Fonctionnalités - Mode Graphique
- **Ajouter un contact**
- **Enregistrer un contact**

## Utilisation

1. **Lancer l'application** : Exécutez l'application en lançant la classe `MainFrmApplication`.

2. **Choisir l'action** : Créer un contact ou afficher les contacts déjà crées.

  - **Créer un contact** : Remplir les informations souhaitées et cliquer sur **Créer**
  - **Afficher les contacts :** Affiche les contacts enregistrés dans un tableau selon le type de contact choisi

## Classes Principales

- **Contact** : Représente un contact générique avec des attributs tels que le nom, la date de naissance, l'adresse, l'e-mail, le numéro de téléphone, etc.
  
- **Etudiant** : Représente un contact de type étudiant, dérivé de la classe `Contact`, avec des attributs supplémentaires tels que le cycle et le niveau.
  
- **Agent** : Représente un contact de type agent, dérivé de la classe `Contact`, avec des attributs supplémentaires tels que le salaire, le statut, la catégorie, l'indice de salaire et l'occupation.
  
- **Enseignant** : Représente un contact de type enseignant, dérivé de la classe `Contact`, avec des attributs supplémentaires tels que le statut.

- **Repertoire** : Représente un répertoire de contacts avec des méthodes pour ajouter, supprimer, modifier, rechercher et afficher des contacts.

- **MainFrmApplication** : Classe principale de l'application avec une interface utilisateur graphique permettant d'interagir avec le répertoire de contacts.

- **ContactsDialog** : Boîte de dialogue affichant une liste de contacts en fonction du type spécifié.

- **ContactsFactory** : Boîte de dialogue permettant à l'utilisateur de créer un nouveau contact en saisissant ses informations.

## Dépendances

L'application utilise les bibliothèques suivantes :

- **Java Swing** : Pour créer une interface utilisateur graphique.
  
- **Java JDBC** : Pour interagir avec une base de données SQL.
