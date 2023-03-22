package com.example.demo;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Salasana olioluokka joka generoi salasanoja annettujen valintojen perusteella.
 */
public class Salasana extends SalasanaOlio{

    /**
     * @param pituus          salasanan pituus
     * @param isotKirjaimet   sisältääkö salasana isoja kirjaimia
     * @param pienetKirjaimet sisältääkö salasana pieniä kirjaimia
     * @param numerot         sisältääkö salasana numeroita
     * @param erikoismerkit   sisältääkö salasana erikoismerkkejä
     */
    public Salasana(int pituus, boolean isotKirjaimet, boolean pienetKirjaimet, boolean numerot, boolean erikoismerkit) {
        super(pituus, isotKirjaimet, pienetKirjaimet, numerot, erikoismerkit);
    }

    /**
     * Generoi salasana annettujen valintojen perusteella
     * Jos kaikki ruudut tyhjiä niin virhe viestinä että pitää valita ainakin yksi
     * */

    public String generoiSalasana() {
        StringBuilder salasana = new StringBuilder();
        Random random = new Random();

        String isot = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String pienet = "abcdefghijklmnopqrstuvwxyz";
        String numerot = "0123456789";
        String erikoismerkit = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        String valittuMerkisto = "";
        if (this.isotKirjaimet) valittuMerkisto += isot;
        if (this.pienetKirjaimet) valittuMerkisto += pienet;
        if (this.numerot) valittuMerkisto += numerot;
        if (this.erikoismerkit) valittuMerkisto += erikoismerkit;

        if (valittuMerkisto.isEmpty()) {
            throw new IllegalArgumentException("Valitse vähintään yksi merkkityyppi.");
        }

        for (int i = 0; i < this.pituus; i++) {
            int randomIndex = random.nextInt(valittuMerkisto.length());
            salasana.append(valittuMerkisto.charAt(randomIndex));
        }

        return salasana.toString();
    }
}
