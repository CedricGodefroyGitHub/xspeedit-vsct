Enoncé XspeedIt
========

XspeedIt est une société d'import / export ayant robotisé toute sa chaîne d'emballage de colis.  
Elle souhaite trouver un algorithme permettant à ses robots d'optimiser le nombre de cartons d'emballage utilisés.

Les articles à emballer sont de taille variable, représentée par un entier compris entre 1 et 9.  
Chaque carton a une capacité de contenance de 10.  
Ainsi, un carton peut par exemple contenir un article de taille 3, un article de taille 1, et un article de taille 6.

La chaîne d'articles à emballer est représentée par une suite de chiffres, chacun représentant un article par sa taille.  
Après traitement par le robot d'emballage, la chaîne est séparée par des "/" pour représenter les articles contenus dans un carton.

*Exemple*  
```python
Chaîne d'articles en entrée : 163841689525773  
Chaîne d'articles emballés  : 163/8/41/6/8/9/52/5/7/73
```

L'algorithme actuel du robot d'emballage est très basique.  
Il prend les articles les uns après les autres, et les mets dans un carton.  
Si la taille totale dépasse la contenance du carton, le robot met l'article dans le carton suivant.

Objectif
--------

Implémenter une application qui permettrait de maximiser le nombre d'articles par carton, en utilisant un langage pouvant être exécuté sur une JVM 1.7 minimum ou en node.js.  
L'ordre des cartons et des articles n'a pas d'importance.

*Exemple*  
```python
Articles      : 163841689525773  
Robot actuel  : 163/8/41/6/8/9/52/5/7/73 => 10 cartons utilisés  
Robot optimisé: 163/82/46/19/8/55/73/7   => 8  cartons utilisés
```


Solution
========

Choix technique
--------
Afin de permettre à la société Xspeedit de trier correctement ses articles, il a été décidé de mettre en place deux services REST. Par la suite, cela permettra de faciliter l'intégration de la solution aux différentes IHM de la société.

Le premier service réalise le tri basique déjà en place.

Le second service tri de manière optimisée les articles.

Cette solution est réalisée à l'aide de Spring Boot (facilitant la création de micro-service REST).

Les tests unitaires ont été réalisés via JUnit et les tests d'intégration via PostMan.

La solution finale est déployée sur AWS mais peut aussi être executée en local.


Présentation fonctionnelle des services
--------

*TriBasique* 

Ce service REST permet de retourner les articles donnés selon un tri basique :
- Les articles sont placés un par un dans un carton
- Si l'article ne rentre pas, on passe au carton suivant.

```
URL du service GET TriBasique : {{host}}/TriBasique/{articles}

{articles} : suite de chiffres représentant chacun la taille d'un article
```

*TriOptimise*  

Ce service REST permet de retourner les articles donnés selon un tri optimisé :
- On tri les articles par taille décroissante
- On place le premier de la liste dans un carton
- On sélectionne le plus grand article pouvant encore entrer et on le place dans le carton.
- S'il reste de la place, on passe à l'article suivant. 
- Si aucun article ne peut entrer ou si le carton est plein, on passe au carton suivant.
- Le traitement s'execute jusqu'à épuisement des articles.

```
URL du service GET TriOptimise : {{host}}/TriOptimise/{articles}

{articles} : suite de chiffres représentant chacun la taille d'un article
```

Mise en production
--------

L'intégralité des sources est disponible sur ce repository et la solution proposée est actuellement déployée sur AWS à l'adresse suivante :
```
http://xspeeditrestapp-env.gwamx5v7dz.us-east-2.elasticbeanstalk.com
```

Il est donc possible d'appeler directement les services REST via POSTMAN ou un simple navigateur. Exemple :

```
Tri basique :
http://xspeeditrestapp-env.gwamx5v7dz.us-east-2.elasticbeanstalk.com/TriBasique/163841689525773
```

```
Tri optimise :
http://xspeeditrestapp-env.gwamx5v7dz.us-east-2.elasticbeanstalk.com/TriOptimise/163841689525773
```
Dans l'éventualité d'un problème réseau, il est aussi possible d'executer localement la solution. Pour cela, il faut utiliser la commande suivante après avoir ouvert l'invite de commande dans le répertoire contenant le .jar de la solution (on peut générer le jar à partir des sources ou utiliser celui du zip contenant l'intégralité du projet) :

```
java -jar .\xspeedit-0.0.1.jar
```
Les services REST sont alors disponibles sur le serveur local aux adresses suivantes :
```
Tri basique :
http://localhost:5000/TriBasique/163841689525773
```

```
Tri optimise :
http://localhost:5000/TriOptimise/163841689525773
```
