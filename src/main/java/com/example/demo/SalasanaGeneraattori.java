package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SalasanaGeneraattori extends Application implements SalasanaGeneraattoriRajapinta{

    private Salasana salasana;

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

        Slider pituusSlider = new Slider(10, 100, 12);
        pituusSlider.setShowTickLabels(true);
        pituusSlider.setShowTickMarks(true);
        pituusSlider.setMajorTickUnit(5);
        pituusSlider.setMinorTickCount(5);
        pituusSlider.setBlockIncrement(1);
        pituusSlider.setSnapToTicks(true);
        pituusSlider.setPrefWidth(200);
        gridPane.add(pituusSlider, 1, 2);

        TextField salasanaTextField = new TextField();
        salasanaTextField.setEditable(false);
        salasanaTextField.setPromptText("Generoitu salasana");
        gridPane.add(salasanaTextField, 0, 3, 2, 1);


        Runnable paivitaSalasana = () -> {
            try {
                salasana = new Salasana((int) pituusSlider.getValue(),
                        isotKirjaimetCheckBox.isSelected(),
                        pienetKirjaimetCheckBox.isSelected(),
                        numerotCheckBox.isSelected(),
                        erikoismerkitCheckBox.isSelected());
                salasanaTextField.setText(salasana.getSalasana());
            } catch (IllegalArgumentException e) {
                salasanaTextField.setText("Virheellinen valinta: Valitse vähintään yksi merkkityyppi");
            }
        };

        isotKirjaimetCheckBox.setOnAction(e -> paivitaSalasana.run());
        pienetKirjaimetCheckBox.setOnAction(e -> paivitaSalasana.run());
        numerotCheckBox.setOnAction(e -> paivitaSalasana.run());
        erikoismerkitCheckBox.setOnAction(e -> paivitaSalasana.run());
        pituusSlider.valueProperty().addListener((observable, oldValue, newValue) -> paivitaSalasana.run());

        Scene scene = new Scene(gridPane, 500, 300);
        primaryStage.setTitle("Salasana Generaattori");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Tallentaa annetun salasanan tiedostoon.
     *
     * @param salasana tallennettava salasana.
     */
    @Override
    public void tallennaSalasana(String salasana) {

    }

    /**
     * Etsii salasanan tiedostosta ja palauttaa sen.
     *
     * @return palautettava salasana.
     */
    @Override
    public String palautaSalasana() {
        return null;
    }
}