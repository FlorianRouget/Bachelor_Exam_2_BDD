
Examen de 2eme Trimestre de 3eme Année - Florian Rouget

Objectif du devoir :

L'objectif de cet examen était de créer une application mobile permettant de gérer l'ensemble 
des projets du studio.
Pour ce faire, il faut mettre en place un WebService auquel pourra s'adresser l'application 
mobile pour accéder à la base de donnée depuis n'importe quel endroit.

-------------------------------------------------------------------------------------------

Lorsque vous téléchargez le dossier du projet, veillez à ce que la tous ces éléments soient 
présents et ordonnés selon cette hiérarchie de dossier :

Racine du projet
  |
  -- Database (dossier contenant les documents et les fichiers sql concernant la Base de Donnée)
  |	  |
  |	  -- exam3_bdd_android.sql (le zip contenant un export de la BDD)
  |	  |
  |	  -- MCD.vsdx (un fichier Visio contenant le MCD imaginé pour les besoins du projet)
  |	  |
  |	  -- MLD.txt (le MLD en rapport avec le MCD ci-dessus)
  |
  -- Sources_Android (dossier contenant l'application Android pouvant contacter le WebService)
  |	  |
  |	  -- MyApplication (dossier racine de l'application)
  |
  -- Sources_WebService (dossier contenant les différents fichiers PHP formant le WebService)
  |	  |
  |	  -- rest (dossier racine du WebService)
  |		  |
  |		  -- .htaccess (fichier HTACCESS établissant les règles de conversion entre les URL entrant et les URL internes)
  |		  |
  |		  -- dbcontroller.php (fichier PHP contenant les fonction permettant de s'adresser à la BDD)
  |		  |
  |		  -- RestController.php (fichier PHP qui sert de "hub" au WebService, c'est vers celui-ci que redirige systématiquement le HTACCESS)
  |		  |
  |		  -- RestHandler.php (fichier contenant les fonction d'appel de classes et les fonction de conversion en JSON)
  |		  |
  |		  -- User.php (classe User, en rapport à la table USER de la BDD)
  |		  |
  |		  -- Projet.php (classe Projet, en rapport à la table PROJET de la BDD)
  |		  |
  |		  -- SimpleRest.php (fichier PHP contenant les différents état et la gestion du HTTP)
  |
  -- README.txt (ce fichier même)
  
-------------------------------------------------------------------------------------------

L'application Mobile : 

L'application Android qui se trouve dans le dossier Sources_Android repose sur 3 activités principales.
Sur la MainActivity, l'utilisateur peut renseigner son Login et son Mot de Passe, et si un compte est lié à
ces informations, alors il peut accéder à une autre activité affichant tous les projets existants.
Depuis MainActivity, l'utilisateur peut aussi accéder à l'activité Register qui lui permet de créer un compte.
La troisième activité, Projet, affiche dans un ListView les projets renseignés dans la BDD.

-------------------------------------------------------------------------------------------

Le WebService : 

Le WebService mis en place pour accueillir les requêtes en provenance de l'application est 
constitué en respectant l'architecture REST. A la base, un .htaccess établi les règle de 
conversion entre les URL entrants et les URL internes. Suivant l'URL avec lequel le WebService
a été contacté, le .htaccess appelle RestController.php en donnant une valeur différente à une 
variable View. C'est en fonction de la valeur de View que le RestController peut executer la fonction
nécessaire contenue dans le RestHandler. Ce même RestHandler fait appel plus spécifiquement aux classes
en fonction des besoins, exécute les requête PHP et converti les résultat en JSON pour les renvoyer à
l'application mobile.

-------------------------------------------------------------------------------------------

Etat global du projet : 

Le projet est loin d'être terminé. Bien que la Base de données soit complète, on ne peut pas en dire
autant du WebService et de l'Application Android. Pour ce qui est du WebService, il est fonctionnel,
il suffirait d'ajouter les classes des tables manquantes ainsi que les fonction reliée dans RestHandler
pour qu'il soit totalement fonctionnel. L'application mobile parvient à se connecter sans problèmes à
la base de données, à en récupérer les informations et à en ajouter. Il manque cependant beaucoup de 
fonctionnalités clés pour la rendre utile. Il faudrait mettre en place des protocole de gestion d'erreur
et de vérification pour les saisis utilisateur, et surtout ajouter la possibilité d'éditer les projets
qui sont affichés dans la troisième activité, sans parler des notes d'état et des tâches que l'utilisateur
était censé pouvoir rajouter. D'autres améliorations auraient été les bienvenues avec plus de temps, comme
une distinction visuelle entre les projets sur lesquels l'utilisateur travaille et ceux auxquels il ne
participe pas.