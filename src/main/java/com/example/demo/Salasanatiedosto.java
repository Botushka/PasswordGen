package com.example.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Salasanatiedosto olio-luokka tarjoaa toiminnallisuuden salasanojen tallentamiseen ja palauttamisen tiedostosta.
 */
public class Salasanatiedosto implements SalasanaGeneraattoriRajapinta {
    private String tiedostonNimi;

    /**
     * Luo uuden Salasanatiedosto olion joka käyttää annettua tiedostonimeä.
     *
     * @param tiedostonNimi tiedoston nimi jota käytetään salasanojen tallentamiseen ja palauttamiseen.
     */
    public Salasanatiedosto(String tiedostonNimi) {
        this.tiedostonNimi = tiedostonNimi;
    }

    /**
     * Tallentaa annetun salasanan tiedostoon.
     *
     * @param salasana tallennettava salasana.
     */
    public void tallennaSalasana(String salasana) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tiedostonNimi))) {
            writer.write(salasana);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Etsii salasanan tiedostosta ja palauttaa sen.
     *
     * @return palautettava salasana.
     */
    public String palautaSalasana() {
        StringBuilder salasana = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(tiedostonNimi))) {
            String line;
            while ((line = reader.readLine()) != null) {
                salasana.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salasana.toString();
    }
}