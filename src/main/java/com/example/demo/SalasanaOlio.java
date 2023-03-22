package com.example.demo;


/**
 * SalasanaOlio on abstrakti luokka, joka määrittää perusominaisuudet ja rakenteen
 * salasanojen generoimiseksi. Tämä luokka sisältää yhteiset attribuutit
 * ja konstruktorin, joka alustaa nämä attribuutit.
 * Perivät luokat voivat määrittää oman toteutuksensa salasanan generoimiseksi
 * käyttämällä näitä attribuutteja ja luomalla konkreettisen generoiSalasana()-metodin.
 */
public abstract class SalasanaOlio {

    //salasanan pituus
    protected int pituus;

    //salasanassa isoja kirjaimia true false
    protected boolean isotKirjaimet;
    //salasanassa pieniä kirjaimia true false
    protected boolean pienetKirjaimet;
    //salasanassa numeroita true false
    protected boolean numerot;
    //salasanassa erikoismerkit true false
    protected boolean erikoismerkit;
    //salasanan viimeisinsalasanan palautusta varten
    private String viimeisinSalasana;
    /**
     *@param pituus salasanan pituus
     *@param isotKirjaimet sisältääkö salasana isoja kirjaimia
     *@param pienetKirjaimet sisältääkö salasana pieniä kirjaimia
     *@param numerot sisältääkö salasana numeroita
     *@param erikoismerkit sisältääkö salasana erikoismerkkejä
     *
     */
    public SalasanaOlio(int pituus,
                        boolean isotKirjaimet,
                        boolean pienetKirjaimet,
                        boolean numerot,
                        boolean erikoismerkit)
    {
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
    public int palautaPituus() {
        return pituus;
    }

    /**
     * Asettaa salasanan pituuden.
     *
     * @param pituus salasanan pituus
     */
    public void asetaPituus(int pituus) {
        this.pituus = pituus;
    }

    /**
     * Tarkistaa sisältääkö salasana isoja kirjaimia.
     *
     * @return true jos salasana sisältää isoja kirjaimia muuten false
     */
    public boolean onkoIsotKirjaimet() {
        return isotKirjaimet;
    }

    /**
     * Asettaa salasanaan isoja kirjaimia.
     *
     * @param isotKirjaimet true jos salasana sisältää isoja kirjaimia muuten false
     */
    public void asetaIsotKirjaimet(boolean isotKirjaimet) {
        this.isotKirjaimet = isotKirjaimet;
    }

    /**
     * Tarkistaa sisältääkö salasana pieniä kirjaimia.
     *
     * @return true jos salasana sisältää pieniä kirjaimia muuten false
     */
    public boolean onkoPienetKirjaimet() {
        return pienetKirjaimet;
    }

    /**
     * asetetaan pieniä kirjaimia jos true
     *
     * @param pienetKirjaimet true jos salasana sisältää pieniä kirjaimia muuten false
     */
    public void asetaPienetKirjaimet(boolean pienetKirjaimet) {
        this.pienetKirjaimet = pienetKirjaimet;
    }

    /**
     * @return true jos salasana sisältää numeroita muuten false
     */
    public boolean onkoNumerot() {
        return numerot;
    }

    /**
     * Asettaa salasanaan numeroita jos true.
     *
     * @param numerot true jos salasana sisältää numeroita muuten false
     */
    public void asetaNumerot(boolean numerot) {
        this.numerot = numerot;
    }

    /**
     * Tarkistaa sisältääkö salasana erikoismerkkejä.
     *
     * @return true jos salasana sisältää erikoismerkkejä muuten false
     */
    public boolean onkoErikoismerkit() {
        return erikoismerkit;
    }

    /**
     * Asettaa salasanaan erikoismerkkejä jos true.
     *
     * @param erikoismerkit true jos salasana sisältää erikoismerkkejä muuten false
     */
    public void asetaErikoismerkit(boolean erikoismerkit) {
        this.erikoismerkit = erikoismerkit;
    }
    /**
     * Methodi joka palauttaa uuden salasanan kun sitä generoidaan
     *
     * @return viimeisin salasana
     * */
    public String palautaSalasana() {
        return viimeisinSalasana;
    }
}

