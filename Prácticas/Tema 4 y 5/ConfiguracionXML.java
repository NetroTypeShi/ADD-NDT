
package tema4add;

import java.io.File;

//TODO: incluir los import necesarios
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ConfiguracionXML {

    private static String driver;
    private static String url;
    private static String usuario;
    private static String password;

    // Bloque estático para cargar la configuración al iniciar la clase 
    static {
        try {
            // TODO: Establecer la ruta relativa del fichero XML
            File file = new File("config.xml");

            // TODO: leer el contenido del fichero XML tal y como vimos en el tema 3
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            doc.getDocumentElement().normalize();

            Element root = doc.getDocumentElement();

            driver = root.getElementsByTagName("driver").item(0).getTextContent();
            url = root.getElementsByTagName("url").item(0).getTextContent();
            usuario = root.getElementsByTagName("user").item(0).getTextContent();
            password = root.getElementsByTagName("password").item(0).getTextContent();

        } catch (Exception e) {
            System.err.println("Error al leer el fichero config.xml");
            e.printStackTrace();
        }
    }

    public static String getDriver() {
        return driver;
    }

    public static String getUrl() {
        return url;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static String getPassword() {
        return password;
    }
}










