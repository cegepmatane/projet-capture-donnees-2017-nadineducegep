import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class MoutonDuService 
{
	public static void main(String[] args) 
	{
		try {
			// Récupérer le xml
			URL urlServiceMouton = new URL("http://localhost/bergerie.service/mouton/?mouton=2");
			URLConnection serviceMouton = urlServiceMouton.openConnection();
			InputStream fluxMouton = serviceMouton.getInputStream();
			
			Scanner lecteur = new Scanner(fluxMouton).useDelimiter("\\A");
			String xml = lecteur.hasNext() ? lecteur.next() : "";
			System.out.println(xml);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Parser le xml - construire les modeles
		
		
	}
}
