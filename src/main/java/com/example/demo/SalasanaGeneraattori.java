package com.example.demo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class SalasanaGeneraattori extends Application {
    private Salasana salasana = new Salasana(12, true, true, true, true);
    private Salasanatiedosto salasanatiedosto = new Salasanatiedosto("salasanat.txt");

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        CheckBox isotKirjaimetCheckBox = new CheckBox("Isot kirjaimet");
        CheckBox pienetKirjaimetCheckBox = new CheckBox("Pienet kirjaimet");
        CheckBox numerotCheckBox = new CheckBox("Numerot");
        CheckBox erikoismerkitCheckBox = new CheckBox("Erikoismerkit");

        gridPane.add(isotKirjaimetCheckBox, 0, 0);
        gridPane.add(pienetKirjaimetCheckBox, 1, 0);
        gridPane.add(numerotCheckBox, 0, 1);
        gridPane.add(erikoismerkitCheckBox, 1, 1);

        Label pituusLabel = new Label("Pituus:");
        gridPane.add(pituusLabel, 0, 2);

        Slider pituusSlider = new Slider(10, 100, salasana.getPituus());
        pituusSlider.setShowTickLabels(true);
        pituusSlider.setShowTickMarks(true);
        pituusSlider.setMajorTickUnit(5);
        pituusSlider.setMinorTickCount(5);
        pituusSlider.setBlockIncrement(1);
        pituusSlider.setSnapToTicks(true);
        pituusSlider.setPrefWidth(220);
        gridPane.add(pituusSlider, 1, 2);

        TextField salasanaTextField = new TextField(salasana.getSalasana());
        salasanaTextField.setEditable(false);
        salasanaTextField.setPromptText("Generoitu salasana");
        gridPane.add(salasanaTextField, 0, 3, 2, 1);

        ChangeListener<Object> optionChangeListener = (observable, oldValue, newValue) -> {
            try {
                salasana.setPituus((int) pituusSlider.getValue());
                salasana.setIsotKirjaimet(isotKirjaimetCheckBox.isSelected());
                salasana.setPienetKirjaimet(pienetKirjaimetCheckBox.isSelected());
                salasana.setNumerot(numerotCheckBox.isSelected());
                salasana.setErikoismerkit(erikoismerkitCheckBox.isSelected());
                salasanaTextField.setText(salasana.generoiSalasana());
                //Virheellinen valinnta mikäli henkilö ei ole valinnut vähintään yhtä checkboxia
            } catch (IllegalArgumentException e) {
                salasanaTextField.setText(e.getMessage());
            }
        };

        isotKirjaimetCheckBox.selectedProperty().addListener(optionChangeListener);
        pienetKirjaimetCheckBox.selectedProperty().addListener(optionChangeListener);
        numerotCheckBox.selectedProperty().addListener(optionChangeListener);
        erikoismerkitCheckBox.selectedProperty().addListener(optionChangeListener);
        pituusSlider.valueProperty().addListener(optionChangeListener);


        Button tallennaSalasanaButton = new Button("Tallenna salasana");
        tallennaSalasanaButton.setOnAction(e -> {
            salasanatiedosto.tallennaSalasana(salasanaTextField.getText());
        });
        gridPane.add(tallennaSalasanaButton, 0, 4);

        Button kopioiSalasanaButton = new Button("Kopioi salasana");
        kopioiSalasanaButton.setOnAction(e -> {
            Clipboard clipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(salasana.getSalasana());
            clipboard.setContent(content);
        });
        gridPane.add(kopioiSalasanaButton, 1, 4);

        Scene scene = new Scene(gridPane, 500, 300);
        primaryStage.setTitle("Salasana Generaattori");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
