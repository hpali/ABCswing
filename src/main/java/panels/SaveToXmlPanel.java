/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import main.MainWindow;
import common.RootWindow;
import java.awt.Insets;
import java.io.IOException;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import object.Exercise;
import object.ExercisesList;
import util.Utility;

/**
 *
 * @author Admin
 */
public class SaveToXmlPanel extends RootWindow {

    MainWindow mainFrame;
    private String defaultPathToKanguru;
    private File XmlFile = null;
    private String UrlDir;
    private String defaultUrl;
    private String placeOfXml;
    private ArrayList<String> filelist = new ArrayList<>();

    public SaveToXmlPanel() {
        initComponents();
    }

    public SaveToXmlPanel(MainWindow mainwindow) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        butSelFiles = new java.awt.Button();
        labSelFiles = new java.awt.Label();
        txtSelFiles = new javax.swing.JTextField();
        butSelToDb = new javax.swing.JButton();
        buSavaXmlCancel = new javax.swing.JButton();
        buXmlChooseJaxb = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        butSelFiles.setLabel("Select Exercises");
        butSelFiles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSelFilesActionPerformed(evt);
            }
        });

        labSelFiles.setText(" Xml  file:");

        butSelToDb.setText("JAXB XML");
        butSelToDb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSelToDbActionPerformed(evt);
            }
        });

        buSavaXmlCancel.setText("Cancel");
        buSavaXmlCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buSavaXmlCancelActionPerformed(evt);
            }
        });

        buXmlChooseJaxb.setText("...");
        buXmlChooseJaxb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buXmlChooseJaxbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buSavaXmlCancel)
                        .addGap(139, 139, 139)
                        .addComponent(butSelToDb))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(butSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buXmlChooseJaxb)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buXmlChooseJaxb)))
                .addGap(21, 21, 21)
                .addComponent(butSelFiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butSelToDb)
                    .addComponent(buSavaXmlCancel))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        butSelFiles.getAccessibleContext().setAccessibleName("Select Exercises");

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

        ExercisesList exercises = new ExercisesList();
        exercises.setExercises(new ArrayList<Exercise>());

        for (String fileabspath : filelist) {
            String url = setUrl(fileabspath, defaultUrl);
            //String url = defaultUrl + "\\" + filename;
            Exercise exercise = new Exercise();
            String[] partsAbsPath = fileabspath.split("\\\\");
            String filename = partsAbsPath[partsAbsPath.length - 1];
            String[] parts = filename.split("_");
            if (parts.length > 4) {
                exercise.setAge(parts[0]);
                exercise.setYear(Integer.parseInt(parts[1]));
                exercise.setLanguage(parts[2]);
                if (Integer.parseInt(parts[3]) == 0) {
                    System.out.println("ajaj");
                }
                exercise.setAnswer(parts[4]);
                exercise.setNumber(Integer.parseInt(parts[3]));
                exercise.setUrl(url);

            } else {
                System.out.println("HIBA: " + fileabspath);
            }
            exercises.getExercises().add(exercise);
        }
        if (XmlFile.exists() && exercises.getExercises().size() > 0) {
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(ExercisesList.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.marshal(exercises, System.out);
                jaxbMarshaller.marshal(exercises, XmlFile);
            } catch (JAXBException ex) {
                Logger.getLogger(SaveToXmlPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_butSelToDbActionPerformed

    private void buSavaXmlCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buSavaXmlCancelActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buSavaXmlCancelActionPerformed

    private void buXmlChooseJaxbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buXmlChooseJaxbActionPerformed
        XmlFile = Utility.setExercisesPath();
        txtSelFiles.setText(XmlFile.getName());
        txtSelFiles.updateUI();
    }//GEN-LAST:event_buXmlChooseJaxbActionPerformed

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
            java.util.logging.Logger.getLogger(SaveToXmlPanel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SaveToXmlPanel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SaveToXmlPanel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SaveToXmlPanel.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaveToXmlPanel().setVisible(true);
            }
        });
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

    @Override
    public void refresh() {
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buSavaXmlCancel;
    private javax.swing.JButton buXmlChooseJaxb;
    private java.awt.Button butSelFiles;
    private javax.swing.JButton butSelToDb;
    private java.awt.Label labSelFiles;
    private javax.swing.JTextField txtSelFiles;
    // End of variables declaration//GEN-END:variables
}
