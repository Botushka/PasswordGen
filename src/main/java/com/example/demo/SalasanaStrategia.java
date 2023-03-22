package com.example.demo;

public abstract class SalasanaStrategia {

    protected int pituus;
    protected boolean isotKirjaimet;
    protected boolean pienetKirjaimet;
    protected boolean numerot;
    protected boolean erikoismerkit;
    private String viimeisinSalasana;

    public SalasanaStrategia(int pituus, boolean isotKirjaimet, boolean pienetKirjaimet, boolean numerot, boolean erikoismerkit) {
        this.pituus = pituus;
        this.isotKirjaimet = isotKirjaimet;
        this.pienetKirjaimet = pienetKirjaimet;
        this.numerot = numerot;
        this.erikoismerkit = erikoismerkit;
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
     *
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
    /**
     * Methodi joka palauttaa uuden salasanan kun sitä generoidaan
     *
     * @return viimeisin salasana
     * */
    public String getSalasana() {
        return viimeisinSalasana;
    }
}

