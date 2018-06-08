/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Component;
import java.awt.Container;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Admin
 */
public class Utility {

    public static String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static ResourceBundle getBoundle(String fileName, Locale loc) {
        return ResourceBundle.getBundle(fileName, loc);
    }

    public static Properties getProperties(String fileName) {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = Utility.class.getClassLoader().getResourceAsStream(fileName);
            if (input == null) {
                System.out.println("Sorry, unable to find " + fileName);
            } else {
                System.out.println("Properties loaded filename " + fileName);
                prop.load(input);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    public static Properties getPropertiesForMaven(String fileName) {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Properties prop = new Properties();

        try (InputStream resourceStream = loader.getResourceAsStream(fileName)) {
            prop.load(resourceStream);
        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("AJAJ a Maven ....");
        }
        return prop;
    }

    public static File getSelectedFile(String filename) {
        File selectedFile = null;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(filename));
        chooser.setDialogTitle("Select Xml files");
        chooser.setMultiSelectionEnabled(true);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.showOpenDialog(null);
        selectedFile = chooser.getSelectedFile();
        return selectedFile;
    }

    public static File[] getSelectedFileList(String filename) {
        File[] selectedFiles = null;
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new java.io.File(filename));
            chooser.setDialogTitle("Select Xml files");
            chooser.setMultiSelectionEnabled(true);
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.showOpenDialog(null);
            selectedFiles = chooser.getSelectedFile().listFiles();
        } catch (NullPointerException e) {
            System.out.println("Nem lett kiválasztva file");
        }
        return selectedFiles;
    }

    public static File setExercisesPath() {
        String name = "myfile.xml";
        String defaultPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsoluteFile() + File.separator + name;
        File defaultFile = new File(defaultPath);
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setSelectedFile(defaultFile);
            chooser.showOpenDialog(null);
            defaultFile = chooser.getSelectedFile();
            defaultFile.createNewFile();

        } catch (IOException ex) {
            Logger.getLogger(Utility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return defaultFile;
    }

}
