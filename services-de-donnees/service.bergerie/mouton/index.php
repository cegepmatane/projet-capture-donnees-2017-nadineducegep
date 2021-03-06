<?php 
	//print_r($_GET);
	$idMouton = filter_input(INPUT_GET, 'mouton', FILTER_VALIDATE_INT);
	//echo $idMouton;
	
	// LECTURE des données
	include "../basededonnees.php";
	$SQL_MOUTON = "SELECT * FROM mouton WHERE id_mouton = :id_mouton";
	//echo $SQL_MOUTON;
	$requeteMouton = $basededonnees->prepare($SQL_MOUTON);
	$requeteMouton->bindParam(':id_mouton', $idMouton, PDO::PARAM_INT);
	$requeteMouton->execute();
	$mouton = $requeteMouton->fetch(PDO::FETCH_OBJ);
	//print_r($mouton);
	
	// AFFICHAGE des données
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';	
?>

<mouton>
	<id><?=$mouton->id_mouton?></id>
	<nom><?=$mouton->nom?></nom>
	<description><?=$mouton->description?></description>
	<naissance><?=$mouton->naissance?></naissance>
	<sexe><?=$mouton->sexe?></sexe>
	<couleur><?=$mouton->sexe?></couleur>
	<sousespece><?=$mouton->sousespece?></sousespece>
	<troupeau><?=$mouton->id_troupeau?></troupeau>
</mouton>
