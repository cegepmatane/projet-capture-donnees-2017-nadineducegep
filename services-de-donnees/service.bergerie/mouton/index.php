<?php 
	//print_r($_GET);
	$idMouton = $_GET['mouton'];
	//echo $idMouton;
	
	// LECTURE des données
	include "../basededonnees.php";
	$SQL_MOUTON = "SELECT * FROM mouton WHERE id_mouton = " . $idMouton;
	//echo $SQL_MOUTON;
	$requeteMouton = $basededonnees->prepare($SQL_MOUTON);
	$requeteMouton->execute();
	$mouton = $requeteMouton->fetch();
	print_r($mouton);
?>