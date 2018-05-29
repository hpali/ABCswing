/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Admin
 */
public class JaxbUtil {

    public static Object readXML(Class c, String fileName) {
        InputStream is = JaxbUtil.class.getClassLoader().getResourceAsStream(fileName);
        Object ll = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(c);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ll = jaxbUnmarshaller.unmarshal(is);
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static void writeXML(Object targetO, Class c, File fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(c);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(targetO, fileName);
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Object readXMLconf(Class c, File file) {
        Object ll = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(c);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            ll = (Object) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static void writeJSON(Object targetO, Class c, File fileName) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(c);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(targetO, fileName);
        } catch (JAXBException ex) {
            Logger.getLogger(JaxbUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
