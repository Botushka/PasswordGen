package com.example.demo;

/**
 * SalasanaGeneraattoriRajapinta määrittelee toiminnot salasanojen käsittelyyn.
 */
public interface SalasanaGeneraattoriRajapinta {

    /**
     * Tallentaa annetun salasanan tiedostoon.
     *
     * @param salasana tallennettava salasana.
     */
    void tallennaSalasana(String salasana);

    /**
     * Etsii salasanan tiedostosta ja palauttaa sen.
     *
     * @return palautettava salasana.
     */
    String palautaSalasana();
}