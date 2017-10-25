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
	private String lireBalise(Element element, String balise)
	{
		return element.getElementsByTagName("id").item(0).getTextContent();
	}
	
	private Document parserXML(String xml)
	{
		Document doc = null;
		DocumentBuilder parseur;
		try {
			parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = parseur.parse(new StringBufferInputStream(xml));		
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	private String consommerService(String url)
	{
		String xml = null;
		try {
			URL urlServiceMouton = new URL(url);
			//URL urlServiceMouton = new URL("http://localhost/bergerie.service/mouton.xml");
			URLConnection serviceMouton = urlServiceMouton.openConnection();
			InputStream fluxMouton = serviceMouton.getInputStream();
			
			Scanner lecteur = new Scanner(fluxMouton).useDelimiter("\\A");
			xml = lecteur.hasNext() ? lecteur.next() : "";
			System.out.println(xml);
			return xml;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	public Mouton trouverMouton(int numero)
	{		
		
		// Récupérer le xml
		String xml = consommerService("http://localhost/bergerie.service/mouton/?mouton=" + numero);
		
		// Interprétation du xml - construire les modeles
		if(xml != null)
		{
			Document document = parserXML(xml);
			if(document == null) return null;
			Element element = document.getDocumentElement();
			String id = lireBalise(element, "id");
			String nom = lireBalise(element,"nom");
			String description = lireBalise(element,"description");
			
			System.out.println("Variables trouvees " + id + " " + nom + " " + description);
			
			Mouton mouton = new Mouton(nom,description);
			mouton.setId(Integer.parseInt(id)); // TODO Ajouter robustesse
			return mouton;
		}		
		return null;
	}
	
	public List<Mouton> listerMoutons()
	{
		String xml = consommerService("http://localhost/bergerie.service/mouton/liste/");
	
		// Interprétation du xml - construire les modeles
		if(xml != null)
		{
			Document document = parserXML(xml);
			if(document == null) return null;		

			ArrayList<Mouton> listeMoutons = new ArrayList<Mouton>();
			NodeList listeNoeudsMoutons = document.getElementsByTagName("mouton");
			for(int position = 0; position < listeNoeudsMoutons.getLength(); position++)
			{
				Element elementMouton = (Element)listeNoeudsMoutons.item(position);
				//System.out.println("Tagname=" + elementMouton.getTagName());
				String id = lireBalise(elementMouton, "id");
				String nom = lireBalise(elementMouton,"nom");
				String description = lireBalise(elementMouton, "description");
				Mouton mouton = new Mouton(nom,description);
				mouton.setId(Integer.parseInt(id)); // TODO : robustesse 
				listeMoutons.add(mouton);
			}
			return listeMoutons;
		}		
		
		
		return null;
	}
}
