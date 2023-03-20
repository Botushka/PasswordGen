package com.example.demo;

import java.security.SecureRandom;
/**
 * Salasana-luokka joka generoi salasanoja annettujen valintojen perusteella.
 */
public class Salasana {
    private int pituus;
    private boolean isotKirjaimet;
    private boolean pienetKirjaimet;
    private boolean numerot;
    private boolean erikoismerkit;
    private String salasana;
    /**
     * Luo uuden Salasana-olion ja generoi salasanan annettujen valintojen perusteella.
     *
     * @param pituus salasanan pituus
     * @param isotKirjaimet sisältääkö salasana isoja kirjaimia
     * @param pienetKirjaimet sisältääkö salasana pieniä kirjaimia
     * @param numerot sisältääkö salasana numeroita
     * @param erikoismerkit sisältääkö salasana erikoismerkkejä
     */
    public Salasana(int pituus, boolean isotKirjaimet, boolean pienetKirjaimet, boolean numerot,
                    boolean erikoismerkit) {
        this.pituus = pituus;
        this.isotKirjaimet = isotKirjaimet;
        this.pienetKirjaimet = pienetKirjaimet;
        this.numerot = numerot;
        this.erikoismerkit = erikoismerkit;
        this.salasana = generoiSalasana();
    }
    // Getterit ja setterit

    /**
     * Palauttaa salasanan pituuden.
     *
     * @return salasanan pituus
     */
    public int getPituus() {
        return pituus;
    }

    /**
     * Asettaa salasanan pituuden.
     *
     * @param pituus salasanan pituus
     */
    public void setPituus(int pituus) {
        this.pituus = pituus;
    }

    /**
     * Tarkistaa sisältääkö salasana isoja kirjaimia.
     *
     * @return true jos salasana sisältää isoja kirjaimia muuten false
     */
    public boolean isIsotKirjaimet() {
        return isotKirjaimet;
    }

    /**
     * Asettaa salasanaan isoja kirjaimia.
     *
     * @param isotKirjaimet true jos salasana sisältää isoja kirjaimia muuten false
     */
    public void setIsotKirjaimet(boolean isotKirjaimet) {
        this.isotKirjaimet = isotKirjaimet;
    }

    /**
     * Tarkistaa sisältääkö salasana pieniä kirjaimia.
     *
     * @return true jos salasana sisältää pieniä kirjaimia muuten false
     */
    public boolean isPienetKirjaimet() {
        return pienetKirjaimet;
    }

    /**
     * asetetaan pieniä kirjaimia jos true
     * @param pienetKirjaimet true jos salasana sisältää pieniä kirjaimia muuten false
     */
    public void setPienetKirjaimet(boolean pienetKirjaimet) {
        this.pienetKirjaimet = pienetKirjaimet;
    }

    /**
     * @return true jos salasana sisältää numeroita muuten false
     */
    public boolean isNumerot() {
        return numerot;
    }

    /**
     * Asettaa salasanaan numeroita jos true.
     *
     * @param numerot true jos salasana sisältää numeroita muuten false
     */
    public void setNumerot(boolean numerot) {
        this.numerot = numerot;
    }

    /**
     * Tarkistaa sisältääkö salasana erikoismerkkejä.
     *
     * @return true jos salasana sisältää erikoismerkkejä muuten false
     */
    public boolean isErikoismerkit() {
        return erikoismerkit;
    }

    /**
     * Asettaa salasanaan erikoismerkkejä jos true.
     *
     * @param erikoismerkit true jos salasana sisältää erikoismerkkejä muuten false
     */
    public void setErikoismerkit(boolean erikoismerkit) {
        this.erikoismerkit = erikoismerkit;
    }

    public String getSalasana() {
        return salasana;
    }
    /**
     * Generoi salasanan annettujen valintojen perusteella.
     * Inspiraatiota minun aikasemmista vko1-3 tehtävistä
     * @return generoitu salasana
     */
    private String generoiSalasana() {
        String isot = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String pienet = "abcdefghijklmnopqrstuvwxyz";
        String numerotMerkit = "0123456789";
        String erikoismerkitMerkisto = "!@#$%^&*()_+-=[]{}|;':\",.<>?/";
        StringBuilder merkistoBuilder = new StringBuilder();
        if (isotKirjaimet) {
            merkistoBuilder.append(isot);
        }
        if (pienetKirjaimet) {
            merkistoBuilder.append(pienet);
        }
        if (numerot) {
            merkistoBuilder.append(numerotMerkit);
        }
        if (erikoismerkit) {
            merkistoBuilder.append(erikoismerkitMerkisto);
        }

        String merkisto = merkistoBuilder.toString();

        //Virheellinen valinnta
        if (merkisto.isEmpty()) {
            throw new IllegalArgumentException("Valitse vähintään yksi merkkityyppi (isot kirjaimet, pienet kirjaimet, numerot tai erikoismerkit");
        }
        SecureRandom random = new SecureRandom();
        StringBuilder salasanaBuilder = new StringBuilder(pituus);
        for (int i = 0; i < pituus; i++) {
            int maara = random.nextInt(merkisto.length());
            salasanaBuilder.append(merkisto.charAt(maara));
        }

        return salasanaBuilder.toString();

    }
}