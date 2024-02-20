package org.example;

import com.mongodb.Block;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.json.JsonObject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.mongodb.client.model.Accumulators.first;

public class modificarCalificaciones {
    public JPanel modificarPanel;
    private JTextField cal1Field;
    private JTextField cal2Field;
    private JTextField materiaField;
    private JButton BUSCARButton;
    private JButton MODIFICARButton;

    public modificarCalificaciones() {
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

                MongoDatabase database = mongoClient.getDatabase("registroCalificaciones");
                MongoCollection<Document> collection = database.getCollection("materias");
                Bson filter = Filters.eq("materia", materiaField.getText());
                Document result = collection.find(filter).first();
                if (result!=null){
                    System.out.println(result.toJson());
                }else {
                    System.out.println("No se encontró");
                }
            }
        });
        MODIFICARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");

                MongoDatabase database = mongoClient.getDatabase("registroCalificaciones");
                MongoCollection<Document> collection = database.getCollection("materias");
                Bson filter = Filters.eq("materia", materiaField.getText());
                collection.updateOne(filter, Updates.set("calificacion 1", cal1Field.getText()));
                collection.updateOne(filter, Updates.set("calificacion 2", cal2Field.getText()));
                System.out.println("Documento actualizado!");
            }
        });
    }
}
