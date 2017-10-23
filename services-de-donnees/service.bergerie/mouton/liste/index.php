<?php 

	// LECTURE des données
	include "../../basededonnees.php";
	$SQL_LISTE_MOUTON = "SELECT * FROM mouton";
	$requeteListeMoutons = $basededonnees->prepare($SQL_LISTE_MOUTON);
	$resultat = $requeteListeMoutons->execute();
	$listeMoutons = $requeteListeMoutons->fetchAll();
	//print_r($listeMoutons);
?>
<?php
	// AFFICHAGE des données
	foreach($listeMoutons as $mouton)
	{
		//print_r($mouton);
		?>
		<mouton>
			<id><?=$mouton['id_mouton']?></id>
			<nom><?=$mouton['nom']?></nom>
			<description><?=$mouton['description']?></description>
		</mouton>
		<?php
	}
?>
