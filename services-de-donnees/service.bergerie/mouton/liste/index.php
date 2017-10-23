<?php 
	include "../../basededonnees.php";

	$SQL_LISTE_MOUTON = "SELECT * FROM mouton";
	$requeteListeMoutons = $basededonnees->prepare($SQL_LISTE_MOUTON);
	$resultat = $requeteListeMoutons->execute();
	$listeMoutons = $requeteListeMoutons->fetchAll();
	//print_r($listeMoutons);
?>
coucou