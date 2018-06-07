/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package panels;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import main.MainWindow;
import common.RootWindow;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import object.Exercise;
import object.ExercisesList;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import util.Utility;

/**
 *
 * @author Admin
 */
public class XmlToDBHibernatePanel extends RootWindow {

    MainWindow mainFrame;
    private File xmlFile = null;
    static Session sessionObj;
    static SessionFactory sessionFactoryObj;

    private String placeOfXml;
    ArrayList<Exercise> exercises = new ArrayList<>();

    public XmlToDBHibernatePanel() {
        initComponents();
    }

    public XmlToDBHibernatePanel(MainWindow mainwindow) {
        initComponents();
        this.mainFrame = mainwindow;
        afterInitComponents();
    }

    private void afterInitComponents() {
        placeOfXml = mainFrame.getSystemConfig().getProperty("placeOfXml");

        setSize(400, 400);
        refresh();
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

        txtSelFilesToDBHib = new javax.swing.JTextField();
        butXmltoDBOKHib = new javax.swing.JButton();
        buSavaXmlCancelToDBHib = new javax.swing.JButton();
        butSelFilesToDBHib = new java.awt.Button();
        labSelFilesToDBHib = new java.awt.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        butXmltoDBOKHib.setText("Xml To DB");
        butXmltoDBOKHib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butXmltoDBOKHibActionPerformed(evt);
            }
        });

        buSavaXmlCancelToDBHib.setText("Cancel");
        buSavaXmlCancelToDBHib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buSavaXmlCancelToDBHibActionPerformed(evt);
            }
        });

        butSelFilesToDBHib.setLabel(".....");
        butSelFilesToDBHib.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                butSelFilesToDBHibActionPerformed(evt);
            }
        });

        labSelFilesToDBHib.setText("Select files:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buSavaXmlCancelToDBHib, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(butXmltoDBOKHib, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labSelFilesToDBHib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(txtSelFilesToDBHib, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(butSelFilesToDBHib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labSelFilesToDBHib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(butSelFilesToDBHib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSelFilesToDBHib, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(butXmltoDBOKHib)
                    .addComponent(buSavaXmlCancelToDBHib))
                .addContainerGap(145, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private static SessionFactory buildSessionFactory() {
        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configObj = new Configuration();
        configObj.configure("hibernate.cfg.xml");
        configObj.addAnnotatedClass(object.Exercise.class); 

        // Since Hibernate Version 4.x, ServiceRegistry Is Being Used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build();

        // Creating Hibernate SessionFactory Instance
        sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
        return sessionFactoryObj;
    }


    private void butXmltoDBOKHibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butXmltoDBOKHibActionPerformed

          try {
            sessionObj = buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            for (Exercise exercise : exercises) {
                sessionObj.save(exercise);
            }
            System.out.println("\n.......Records Saved Successfully To The Database.......\n");
            // Committing The Transactions To The Database
            sessionObj.getTransaction().commit();
        } catch (Exception sqlException) {
            if (null != sessionObj.getTransaction()) {
                System.out.println("\n.......Transaction Is Being Rolled Back.......");
                sessionObj.getTransaction().rollback();
            }
            sqlException.printStackTrace();
        } finally {
            if (sessionObj != null) {
                sessionObj.close();
            }
        }
    }//GEN-LAST:event_butXmltoDBOKHibActionPerformed

    private void buSavaXmlCancelToDBHibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buSavaXmlCancelToDBHibActionPerformed
        setVisible(false);
    }//GEN-LAST:event_buSavaXmlCancelToDBHibActionPerformed

    private void butSelFilesToDBHibActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_butSelFilesToDBHibActionPerformed
        File selectedFile = Utility.getSelectedFile(placeOfXml);
        ExercisesList exercisesList = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ExercisesList.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            exercisesList = (ExercisesList) jaxbUnmarshaller.unmarshal(selectedFile);
        } catch (JAXBException ex) {
            Logger.getLogger(XmlToDBPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Exercise exercise : exercisesList.getExercises()) {
            System.out.println(exercise);
            exercises.add(exercise);
        }
    }//GEN-LAST:event_butSelFilesToDBHibActionPerformed

    public List<Exercise> doListFromXml() {
        SAXBuilder builder = new SAXBuilder();
        File f = new File(xmlFile.getAbsolutePath());
        List<Exercise> exerciselist = new ArrayList<>();
        Document document;
        try {
            document = builder.build(f);
            Element root = document.getRootElement();
            List<Element> exercises = root.getChildren();
            for (Element exercise : exercises) {
                String age = exercise.getChildText("AGE");
                int year = Integer.parseInt(exercise.getChildText("YEAR"));
                String language = exercise.getChildText("LANGUAGE");
                int number = Integer.parseInt(exercise.getChildText("NUMBER"));
                String answer = exercise.getChildText("ANSWER");
                String url = exercise.getChildText("URL");
                Exercise newExercise = new Exercise(age, year, language, number, answer, url);
                exerciselist.add(newExercise);
            }

        } catch (JDOMException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return exerciselist;
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
            java.util.logging.Logger.getLogger(XmlToDBHibernatePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(XmlToDBHibernatePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(XmlToDBHibernatePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(XmlToDBHibernatePanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new XmlToDBHibernatePanel().setVisible(true);
            }
        });
    }

    @Override
    public void refresh() {
        repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buSavaXmlCancelToDBHib;
    private java.awt.Button butSelFilesToDBHib;
    private javax.swing.JButton butXmltoDBOKHib;
    private java.awt.Label labSelFilesToDBHib;
    private javax.swing.JTextField txtSelFilesToDBHib;
    // End of variables declaration//GEN-END:variables
}
