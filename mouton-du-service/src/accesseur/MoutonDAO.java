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
		String xml = null;
		try {
			URL urlServiceMouton = new URL("http://localhost/bergerie.service/mouton/?mouton=" + numero);
			//URL urlServiceMouton = new URL("http://localhost/bergerie.service/mouton.xml");
			URLConnection serviceMouton = urlServiceMouton.openConnection();
			InputStream fluxMouton = serviceMouton.getInputStream();
			
			Scanner lecteur = new Scanner(fluxMouton).useDelimiter("\\A");
			xml = lecteur.hasNext() ? lecteur.next() : "";
			System.out.println(xml);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Interprétation du xml - construire les modeles
		if(xml != null)
		{
			try 
			{
				DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				@SuppressWarnings("deprecation")
				Document document = parseur.parse(new StringBufferInputStream(xml));
				
				String id = document.getElementsByTagName("id").item(0).getTextContent();
				String nom = document.getElementsByTagName("nom").item(0).getTextContent();
				String description = document.getElementsByTagName("description").item(0).getTextContent();
				String troupeau = document.getElementsByTagName("troupeau").item(0).getTextContent();
				
				System.out.println("Variables trouvees " + id + " " + nom + " " + description + " " + troupeau);
				
				Mouton mouton = new Mouton(nom,description);
				mouton.setId(Integer.parseInt(id)); // TODO Ajouter robustesse
				return mouton;
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		return null;
	}
	
	public List<Mouton> listerMoutons()
	{
		// Récupérer le xml
		String xml = null;
		try {
			URL urlServiceMouton = new URL("http://localhost/bergerie.service/mouton/liste/");
			URLConnection serviceMouton = urlServiceMouton.openConnection();
			InputStream fluxMouton = serviceMouton.getInputStream();
			
			Scanner lecteur = new Scanner(fluxMouton).useDelimiter("\\A");
			xml = lecteur.hasNext() ? lecteur.next() : "";
			System.out.println(xml);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	
		// Interprétation du xml - construire les modeles
		if(xml != null)
		{
			try 
			{
				DocumentBuilder parseur = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				@SuppressWarnings("deprecation")
				Document document = parseur.parse(new StringBufferInputStream(xml));
				

				ArrayList<Mouton> listeMoutons = new ArrayList<Mouton>();
				NodeList listeNoeudsMoutons = document.getElementsByTagName("mouton");
				for(int position = 0; position < listeNoeudsMoutons.getLength(); position++)
				{
					Element elementMouton = (Element)listeNoeudsMoutons.item(position);
					//System.out.println("Tagname=" + elementMouton.getTagName());
					String id = elementMouton.getElementsByTagName("id").item(0).getTextContent();
					String nom = elementMouton.getElementsByTagName("nom").item(0).getTextContent();
					String description = elementMouton.getElementsByTagName("description").item(0).getTextContent();
					Mouton mouton = new Mouton(nom,description);
					mouton.setId(Integer.parseInt(id)); // TODO : robustesse 
					listeMoutons.add(mouton);
				}
				return listeMoutons;
				
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		
		return null;
	}
}
