package gz.azure.xml.figuremap;

import gz.azure.utils.Log;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Scott on 8/18/2015.
 */
public class FiguremapParser {
    public static Figuremap getFiguremap() {
        try {
            return getFiguremap(new URL("https://habboo-a.akamaihd.net/gordon/PRODUCTION-201508111205-320867585/figuremap.xml"));
        } catch (MalformedURLException e) {
            // This is a static URL. It should never be "malformed"
            return null;
        }
    }

    public static Figuremap getFiguremap(URL sourceURL) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Figuremap.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            StreamSource src = new StreamSource(sourceURL.openStream());
            return ((Figuremap) unmarshaller.unmarshal(src));
        } catch (JAXBException jaxbEx) {
            Log.error("Failed to create new JAXB instance", jaxbEx);
            return null;
        } catch (IOException ioEx) {
            Log.error("Failed to download furnidata XML for reading", ioEx);
            return null;
        }
    }
}
