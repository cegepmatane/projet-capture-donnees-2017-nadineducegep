import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import accesseur.MoutonDAO;
import modele.Mouton;

public class MoutonDuService 
{
	public static void main(String[] args) 
	{

		MoutonDAO moutonDAO = new MoutonDAO();
		moutonDAO.trouverMouton(1);
		moutonDAO.listerMoutons();
		
	}
}
