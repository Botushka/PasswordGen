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
/**
 * SalasanaGeneraattori luokka on JavaFX-sovellus jonka tarkoituksena on generoida
 * salasanoja käyttäjän asettamien valintojen perusteella. Käyttäjä voi valita, sisältääkö
 * salasana isoja kirjaimia, pieniä kirjaimia, numeroita ja/tai erikoismerkkejä. Käyttäjä voi myös
 * määrittää salasanan pituuden liukusäätimellä.
 * Luokka käyttää Salasana- ja Salasanatiedosto luokkia salasanojen generoimiseen ja
 * tallentamiseen tiedostoon. Generoidut salasanat näytetään käyttöliittymässä.
 */
public class SalasanaGeneraattori extends Application {

    /**
     * Alustetaan Salasana- ja Salasanatiedosto-oliot, määritellään JavaFX-käyttöliittymä
     * GridPane-rakenteella ja lisätään erilaisia komponentteja, kuten valintaruudut
     * (CheckBox) salasanan ominaisuuksille, liukusäädin (Slider) salasanan pituuden
     * määrittämiseen ja tarvittavat tekstikentät ja -kentät.
     */
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

        Slider pituusSlider = new Slider(10, 100, salasana.palautaPituus());
        pituusSlider.setShowTickLabels(true);
        pituusSlider.setShowTickMarks(true);
        pituusSlider.setMajorTickUnit(5);
        pituusSlider.setMinorTickCount(5);
        pituusSlider.setBlockIncrement(1);
        pituusSlider.setSnapToTicks(true);
        pituusSlider.setPrefWidth(220);
        gridPane.add(pituusSlider, 1, 2);

        TextField salasanaTextField = new TextField(salasana.palautaSalasana());
        salasanaTextField.setEditable(false);
        salasanaTextField.setPromptText("Generoitu salasana");
        gridPane.add(salasanaTextField, 0, 3, 2, 1);

        /**
         * Luodaan ChangeListener olio joka reagoi käyttäjän valintoihin (esim. valintaruutujen
         * valinta, liukusäätimen arvojen muutos) ja päivittää Salasana-olion asetuksia.
         * Kun asetukset on päivitetty, generoidaan uusi salasana ja näytetään se käyttöliittymän
         * tekstikentässä. Jos käyttäjä ei ole valinnut vähintään yhtä merkkityyppiä niin näytetään
         * virheviesti tekstikentässä.
         */
        ChangeListener<Object> valintaMuutinKuuntelija = (observable, oldValue, newValue) -> {
            try {
                salasana.asetaPituus((int) pituusSlider.getValue());
                salasana.asetaIsotKirjaimet(isotKirjaimetCheckBox.isSelected());
                salasana.asetaPienetKirjaimet(pienetKirjaimetCheckBox.isSelected());
                salasana.asetaNumerot(numerotCheckBox.isSelected());
                salasana.asetaErikoismerkit(erikoismerkitCheckBox.isSelected());
                salasanaTextField.setText(salasana.generoiSalasana());
                //Virheellinen valinnta mikäli henkilö ei ole valinnut vähintään yhtä checkboxia
            } catch (IllegalArgumentException e) {
                salasanaTextField.setText(e.getMessage());
            }
        };

        // Lisätään kuuntelija valintaruutujen ja liukusäätimen valintamuutosten seurantaan
        isotKirjaimetCheckBox.selectedProperty().addListener(valintaMuutinKuuntelija);
        pienetKirjaimetCheckBox.selectedProperty().addListener(valintaMuutinKuuntelija);
        numerotCheckBox.selectedProperty().addListener(valintaMuutinKuuntelija);
        erikoismerkitCheckBox.selectedProperty().addListener(valintaMuutinKuuntelija);
        pituusSlider.valueProperty().addListener(valintaMuutinKuuntelija);

        // Kenttä missä luodaan salasanan tallentamiseen tarkoitettu nappula.
        Button tallennaSalasanaButton = new Button("Tallenna salasana");
        tallennaSalasanaButton.setOnAction(e -> {
            salasanatiedosto.tallennaSalasana(salasanaTextField.getText());
        });
        gridPane.add(tallennaSalasanaButton, 0, 4);
        // Kenttä missä luodaan salasanaan palauttamiseen tarkoitettu nappula.
        Button kopioiSalasanaButton = new Button("Kopioi salasana");
        kopioiSalasanaButton.setOnAction(e -> {
            Clipboard kopio = Clipboard.getSystemClipboard();
            ClipboardContent sisalto = new ClipboardContent();
            sisalto.putString(salasana.palautaSalasana());
            kopio.setContent(sisalto);
        });
        gridPane.add(kopioiSalasanaButton, 1, 4);

        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Salasana Generaattori");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
