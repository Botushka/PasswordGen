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
    private final Salasana SALASANA = new Salasana(12, true, true, true, true);
    private final Salasanatiedosto SALASANATIEDOSTO = new Salasanatiedosto("salasanat.txt");


    @Override
    public void start(Stage primaryStage) {
        GridPane gpaneeli = new GridPane();
        gpaneeli.setPadding(new Insets(10, 10, 10, 10));
        gpaneeli.setHgap(10);
        gpaneeli.setVgap(10);

        CheckBox isotKirjaimetCheckBox = new CheckBox("Isot kirjaimet");
        CheckBox pienetKirjaimetCheckBox = new CheckBox("Pienet kirjaimet");
        CheckBox numerotCheckBox = new CheckBox("Numerot");
        CheckBox erikoismerkitCheckBox = new CheckBox("Erikoismerkit");

        gpaneeli.add(isotKirjaimetCheckBox, 0, 0);
        gpaneeli.add(pienetKirjaimetCheckBox, 1, 0);
        gpaneeli.add(numerotCheckBox, 0, 1);
        gpaneeli.add(erikoismerkitCheckBox, 1, 1);

        Label pituusLabel = new Label("Pituus:");
        gpaneeli.add(pituusLabel, 0, 2);

        Slider pituusSlider = new Slider(10, 100, SALASANA.palautaPituus());
        pituusSlider.setShowTickLabels(true);
        pituusSlider.setShowTickMarks(true);
        pituusSlider.setMajorTickUnit(5);
        pituusSlider.setMinorTickCount(5);
        pituusSlider.setBlockIncrement(1);
        pituusSlider.setSnapToTicks(true);
        pituusSlider.setPrefWidth(220);
        gpaneeli.add(pituusSlider, 1, 2);

        TextField salasanaTextField = new TextField(SALASANA.palautaSalasana());
        salasanaTextField.setEditable(false);
        salasanaTextField.setPromptText("Generoitu salasana");
        gpaneeli.add(salasanaTextField, 0, 3, 2, 1);

        /**
         * Luodaan ChangeListener olio joka reagoi käyttäjän valintoihin (esim. valintaruutujen
         * valinta, liukusäätimen arvojen muutos) ja päivittää Salasana-olion asetuksia.
         * Kun asetukset on päivitetty, generoidaan uusi salasana ja näytetään se käyttöliittymän
         * tekstikentässä. Jos käyttäjä ei ole valinnut vähintään yhtä merkkityyppiä niin näytetään
         * virheviesti tekstikentässä.
         * @throws Virheellinen valinnta mikäli henkilö ei ole valinnut vähintään yhtä checkboxia
         */
        ChangeListener<Object> valintaMuutinKuuntelija = (observable, oldValue, newValue) -> {
            try {
                SALASANA.asetaPituus((int) pituusSlider.getValue());
                SALASANA.asetaIsotKirjaimet(isotKirjaimetCheckBox.isSelected());
                SALASANA.asetaPienetKirjaimet(pienetKirjaimetCheckBox.isSelected());
                SALASANA.asetaNumerot(numerotCheckBox.isSelected());
                SALASANA.asetaErikoismerkit(erikoismerkitCheckBox.isSelected());
                salasanaTextField.setText(SALASANA.generoiSalasana());
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
            SALASANA.viimeisinSalasana = salasanaTextField.getText();
            SALASANATIEDOSTO.tallennaSalasana(salasanaTextField.getText());
        });
        gpaneeli.add(tallennaSalasanaButton, 0, 4);
        // Kenttä missä luodaan salasanaan palauttamiseen tarkoitettu nappula.
        Button kopioiSalasanaButton = new Button("Kopioi salasana");
        kopioiSalasanaButton.setOnAction(e -> {
            Clipboard kopio = Clipboard.getSystemClipboard();
            ClipboardContent sisalto = new ClipboardContent();
            sisalto.putString(SALASANA.palautaSalasana());
            kopio.setContent(sisalto);
        });
        gpaneeli.add(kopioiSalasanaButton, 1, 4);

        Scene scene = new Scene(gpaneeli, 400, 250);
        primaryStage.setTitle("Salasana Generaattori");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Projektin pääohjelma
     * */
    public static void main(String[] args) {
        launch(args);
    }

}
