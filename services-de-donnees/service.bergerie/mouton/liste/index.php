<?php 

	// LECTURE des données
	include "../../basededonnees.php";
	$SQL_LISTE_MOUTON = "SELECT * FROM mouton";
	$requeteListeMoutons = $basededonnees->prepare($SQL_LISTE_MOUTON);
	$resultat = $requeteListeMoutons->execute();
	$listeMoutons = $requeteListeMoutons->fetchAll(PDO::FETCH_OBJ);
	//print_r($listeMoutons);
	
	// AFFICHAGE des données
	header("Content-type: text/xml");
	echo '<?xml version="1.0" encoding="UTF-8"?>';
?>

<listeMoutons>
<?php
	foreach($listeMoutons as $mouton)
	{
		//print_r($mouton);
		?>
		<mouton>
			<id><?=$mouton->id_mouton?></id>
			<nom><?=$mouton->nom?></nom>
			<description><?=$mouton->description?></description>
		</mouton>
		<?php
	}
?>
</listeMoutons>
