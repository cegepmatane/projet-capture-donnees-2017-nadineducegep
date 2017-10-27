package accesseur;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import modele.Mouton;

public class MoutonDAO 
{
	public Mouton trouverMouton(int numero)
	{		
		
		// Récupérer le xml
		String xml = ServiceWeb.consommerService("http://localhost/bergerie.service/mouton/?mouton=" + numero);
		
		// Interprétation du xml - construire les modeles
		if(xml != null)
		{
			Document document = ServiceWeb.parserXML(xml);
			if(document == null) return null;
			Element element = document.getDocumentElement();
			String id = ServiceWeb.lireBalise(element, "id");
			String nom = ServiceWeb.lireBalise(element,"nom");
			String description = element.getElementsByTagName("description").item(0).getTextContent();
			
			System.out.println("Variables trouvees " + id + " " + nom + " " + description);
			
			Mouton mouton = new Mouton(nom,description);
			mouton.setId(Integer.parseInt(id)); // TODO Ajouter robustesse
			return mouton;
		}		
		return null;
	}
	
	public List<Mouton> listerMoutons()
	{
		String xml = ServiceWeb.consommerService("http://localhost/bergerie.service/mouton/liste/");
	
		// Interprétation du xml - construire les modeles
		if(xml != null)
		{
			Document document = ServiceWeb.parserXML(xml);
			if(document == null) return null;		

			ArrayList<Mouton> listeMoutons = new ArrayList<Mouton>();
			NodeList listeNoeudsMoutons = document.getElementsByTagName("mouton");
			for(int position = 0; position < listeNoeudsMoutons.getLength(); position++)
			{
				Element elementMouton = (Element)listeNoeudsMoutons.item(position);
				//System.out.println("Tagname=" + elementMouton.getTagName());
				String id = ServiceWeb.lireBalise(elementMouton, "id");
				String nom = ServiceWeb.lireBalise(elementMouton,"nom");
				String description = elementMouton.getElementsByTagName("description").item(0).getTextContent();
				
				Mouton mouton = new Mouton(nom,description);
				mouton.setId(Integer.parseInt(id)); // TODO : robustesse 
				listeMoutons.add(mouton);
			}
			return listeMoutons;
		}		
		
		
		return null;
	}
}
