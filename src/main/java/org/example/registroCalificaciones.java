package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class registroCalificaciones {
    private JTextField materiaField;
    private JTextField cal1Field;
    private JTextField cal2Field;
    private JButton REGISTRARButton;
    public JPanel registroPanel;
    private JButton IRAMODIFICARButton;

    public registroCalificaciones() {
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

                MongoDatabase database = mongoClient.getDatabase("registroCalificaciones");
                MongoCollection<Document> collection = collection = database.getCollection("materias");

                Document document = new Document("materia", materiaField.getText())
                        .append("calificacion 1",cal1Field.getText())
                        .append("calificacion 2", cal2Field.getText());

                collection.insertOne(document);
                System.out.println("Documento Insertado");

                JOptionPane.showMessageDialog(null,"Se han ingresado correctamente las calificaciones");
                materiaField.setText("");
                cal1Field.setText("");
                cal2Field.setText("");
            }
        });
        IRAMODIFICARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frameModificar = new JFrame("Modificar Calificacion");
                frameModificar.setContentPane(new modificarCalificaciones().modificarPanel);
                frameModificar.setSize(400,400);
                frameModificar.setVisible(true);
            }
        });
    }
}
