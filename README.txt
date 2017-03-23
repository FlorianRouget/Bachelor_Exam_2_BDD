
Examen de 2eme Trimestre de 3eme Ann�e - Florian Rouget

Objectif du devoir :

L'objectif de cet examen �tait de cr�er une application mobile permettant de g�rer l'ensemble 
des projets du studio.
Pour ce faire, il faut mettre en place un WebService auquel pourra s'adresser l'application 
mobile pour acc�der � la base de donn�e depuis n'importe quel endroit.

-------------------------------------------------------------------------------------------

Lorsque vous t�l�chargez le dossier du projet, veillez � ce que la tous ces �l�ments soient 
pr�sents et ordonn�s selon cette hi�rarchie de dossier :

Racine du projet
  |
  -- Database (dossier contenant les documents et les fichiers sql concernant la Base de Donn�e)
  |	  |
  |	  -- exam3_bdd_android.sql (le zip contenant un export de la BDD)
  |	  |
  |	  -- MCD.vsdx (un fichier Visio contenant le MCD imagin� pour les besoins du projet)
  |	  |
  |	  -- MLD.txt (le MLD en rapport avec le MCD ci-dessus)
  |
  -- Sources_Android (dossier contenant l'application Android pouvant contacter le WebService)
  |	  |
  |	  -- MyApplication (dossier racine de l'application)
  |
  -- Sources_WebService (dossier contenant les diff�rents fichiers PHP formant le WebService)
  |	  |
  |	  -- rest (dossier racine du WebService)
  |		  |
  |		  -- .htaccess (fichier HTACCESS �tablissant les r�gles de conversion entre les URL entrant et les URL internes)
  |		  |
  |		  -- dbcontroller.php (fichier PHP contenant les fonction permettant de s'adresser � la BDD)
  |		  |
  |		  -- RestController.php (fichier PHP qui sert de "hub" au WebService, c'est vers celui-ci que redirige syst�matiquement le HTACCESS)
  |		  |
  |		  -- RestHandler.php (fichier contenant les fonction d'appel de classes et les fonction de conversion en JSON)
  |		  |
  |		  -- User.php (classe User, en rapport � la table USER de la BDD)
  |		  |
  |		  -- Projet.php (classe Projet, en rapport � la table PROJET de la BDD)
  |		  |
  |		  -- SimpleRest.php (fichier PHP contenant les diff�rents �tat et la gestion du HTTP)
  |
  -- README.txt (ce fichier m�me)
  
-------------------------------------------------------------------------------------------

L'application Mobile : 

L'application Android qui se trouve dans le dossier Sources_Android repose sur 3 activit�s principales.
Sur la MainActivity, l'utilisateur peut renseigner son Login et son Mot de Passe, et si un compte est li� �
ces informations, alors il peut acc�der � une autre activit� affichant tous les projets existants.
Depuis MainActivity, l'utilisateur peut aussi acc�der � l'activit� Register qui lui permet de cr�er un compte.
La troisi�me activit�, Projet, affiche dans un ListView les projets renseign�s dans la BDD.

-------------------------------------------------------------------------------------------

Le WebService : 

Le WebService mis en place pour accueillir les requ�tes en provenance de l'application est 
constitu� en respectant l'architecture REST. A la base, un .htaccess �tabli les r�gle de 
conversion entre les URL entrants et les URL internes. Suivant l'URL avec lequel le WebService
a �t� contact�, le .htaccess appelle RestController.php en donnant une valeur diff�rente � une 
variable View. C'est en fonction de la valeur de View que le RestController peut executer la fonction
n�cessaire contenue dans le RestHandler. Ce m�me RestHandler fait appel plus sp�cifiquement aux classes
en fonction des besoins, ex�cute les requ�te PHP et converti les r�sultat en JSON pour les renvoyer �
l'application mobile.

-------------------------------------------------------------------------------------------

Etat global du projet : 

Le projet est loin d'�tre termin�. Bien que la Base de donn�es soit compl�te, on ne peut pas en dire
autant du WebService et de l'Application Android. Pour ce qui est du WebService, il est fonctionnel,
il suffirait d'ajouter les classes des tables manquantes ainsi que les fonction reli�e dans RestHandler
pour qu'il soit totalement fonctionnel. L'application mobile parvient � se connecter sans probl�mes �
la base de donn�es, � en r�cup�rer les informations et � en ajouter. Il manque cependant beaucoup de 
fonctionnalit�s cl�s pour la rendre utile. Il faudrait mettre en place des protocole de gestion d'erreur
et de v�rification pour les saisis utilisateur, et surtout ajouter la possibilit� d'�diter les projets
qui sont affich�s dans la troisi�me activit�, sans parler des notes d'�tat et des t�ches que l'utilisateur
�tait cens� pouvoir rajouter. D'autres am�liorations auraient �t� les bienvenues avec plus de temps, comme
une distinction visuelle entre les projets sur lesquels l'utilisateur travaille et ceux auxquels il ne
participe pas.