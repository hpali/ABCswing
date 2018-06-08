/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import main.MainWindow;
import common.RootWindow;
import util.Utility;

/**
 *
 * @author Admin
 */
public class SaveToXmlJDOMPanel extends RootWindow {

    MainWindow mainFrame;
    private String defaultPathToKanguru;
    private File XmlFile = null;
    private String UrlDir;
    private String defaultUrl;
    private String placeOfXml;
    private ArrayList<String> filelist = new ArrayList<>();

    public SaveToXmlJDOMPanel() {
        initComponents();
    }

    public SaveToXmlJDOMPanel(MainWindow mainwindow) {
        initComponents();
        this.mainFrame = mainwindow;
        afterInitComponents();
    }

    private void afterInitComponents() {

        UrlDir = mainFrame.getSystemConfig().getProperty("urldir");
        placeOfXml = mainFrame.getSystemConfig().getProperty("placeOfXml");
        defaultUrl = mainFrame.getSystemConfig().getProperty("defaultUrl");
        defaultPathToKanguru = mainFrame.getSystemConfig().getProperty("defaultPathToKanguru");
        setSize(500, 500);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buSavaXmlCancel = new javax.swing.JButton();
        butSelFiles = new java.awt.Button();
        labSelFiles = new java.awt.Label();
        txtSelFiles = new javax.swing.JTextField();
        butSelToDb = new javax.swing.JButton();
        buSelFileJdom = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        buSavaXmlCancel.setText("Cancel");
        buSavaXmlCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buSavaXmlCancelActionPerformed(evt);
            }
        });

        butSelFiles.setLabel("Select Exercises");
        butSelFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSelFilesActionPerformed(evt);
            }
        });

        labSelFiles.setText("Select files:");

        butSelToDb.setText("JDOM XML");
        butSelToDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSelToDbActionPerformed(evt);
            }
        });

        buSelFileJdom.setText("...");
        buSelFileJdom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buSelFileJdomActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(butSelFiles, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSelFiles, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(buSelFileJdom)
                        .addContainerGap(71, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buSavaXmlCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butSelToDb)
                        .addGap(39, 39, 39))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buSelFileJdom)))
                .addGap(23, 23, 23)
                .addComponent(butSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butSelToDb)
                    .addComponent(buSavaXmlCancel))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buSavaXmlCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buSavaXmlCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buSavaXmlCancelActionPerformed

    private void butSelFilesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSelFilesActionPerformed
        File[] selectedFiles = Utility.getSelectedFileList(defaultPathToKanguru);
        for (File file : selectedFiles) {
            if (file.isDirectory()) {
                findPicInDirRek(file);
            } else {
                if (file.getName().endsWith(".png")) {
                    filelist.add(file.getAbsolutePath());
                }
            }
        }
    }//GEN-LAST:event_butSelFilesActionPerformed

    private void butSelToDbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSelToDbActionPerformed

        Element exercises = new Element("exercises");
        Document doc = new Document(exercises);
        for (String fileabspath : filelist) {
            String url = setUrl(fileabspath, defaultUrl);
            // String url = defaultUrl + "\\" + filename;
            Element staff = new Element("exercise");
            String[] partsAbsPath = fileabspath.split("\\\\");
            String filename = partsAbsPath[partsAbsPath.length - 1];
            String[] parts = filename.split("_");
            if (parts.length > 4) {
                staff = getStaff(staff, parts);
                staff.addContent(new Element("URL").setText(url));
                doc.getRootElement().addContent(staff);
            } else {
                System.out.println("hoppa, itt egy rossz file");
            }

        }

        XMLOutputter xmlOutput = new XMLOutputter();
        // display nice nice
        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(doc, new FileWriter(XmlFile));
        } catch (IOException ex) {
            Logger.getLogger(SaveToXmlJDOMPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_butSelToDbActionPerformed

    private void buSelFileJdomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buSelFileJdomActionPerformed
        XmlFile = Utility.setExercisesPath();
        txtSelFiles.setText(XmlFile.getName());
        txtSelFiles.updateUI();
    }//GEN-LAST:event_buSelFileJdomActionPerformed

    public void doXmlFile() {
        Element exercises = new Element("exercises");
        Document doc = new Document(exercises);
        for (String fileabspath : filelist) {
            String url = setUrl(fileabspath, defaultUrl);
            // String url = defaultUrl + "\\" + filename;
            Element staff = new Element("exercise");
            String[] partsAbsPath = fileabspath.split("\\\\");
            String filename = partsAbsPath[partsAbsPath.length - 1];
            String[] parts = filename.split("_");
            if (parts.length > 4) {
                staff = getStaff(staff, parts);
                staff.addContent(new Element("URL").setText(url));
                doc.getRootElement().addContent(staff);
            } else {
                System.out.println("hoppa, itt egy rossz file");
            }

        }

        XMLOutputter xmlOutput = new XMLOutputter();
        // display nice nice
        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(doc, new FileWriter(XmlFile));
        } catch (IOException ex) {
            Logger.getLogger(SaveToXmlJDOMPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Element getStaff(Element staff, String[] parts) {
        staff.addContent(new Element("AGE").setText(parts[0]));
        staff.addContent(new Element("YEAR").setText(parts[1]));
        staff.addContent(new Element("LANGUAGE").setText(parts[2]));
        staff.addContent(new Element("NUMBER").setText(parts[3]));
        staff.addContent(new Element("ANSWER").setText(parts[4]));
        return staff;
    }

    public void setExercisesPath() {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(placeOfXml));
        chooser.showOpenDialog(null);
        XmlFile = chooser.getSelectedFile();
        placeOfXml = XmlFile.getAbsolutePath();
    }

    public String setUrl(String txt, String defaultUrl) {
        String url = txt.substring(txt.indexOf(":") + 2);
        defaultUrl += "\\" + url;
        return defaultUrl;
    }

    public void findPicInDirRek(File directory) { // rekurzivan keresse meg a megadott könyvtárban a összes file -t
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                findPicInDirRek(file);
            } else {
                if (file.getName().endsWith(".png")) {
                    filelist.add(file.getAbsolutePath());
                }
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SaveToXmlJDOMPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaveToXmlJDOMPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaveToXmlJDOMPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaveToXmlJDOMPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaveToXmlJDOMPanel().setVisible(true);
            }
        });
    }

    @Override
    public void refresh() {
        System.out.println("TODO");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buSavaXmlCancel;
    private javax.swing.JButton buSelFileJdom;
    private java.awt.Button butSelFiles;
    private javax.swing.JButton butSelToDb;
    private java.awt.Label labSelFiles;
    private javax.swing.JTextField txtSelFiles;
    // End of variables declaration//GEN-END:variables
}
