package ch.bbbaden.m226a.police;

import java.util.Date;

public class AlkoholischesGetraenk {
    //Variablen und Konstanten
    private static final double DICHTE_ALKOHOL  = 0.8;
    private int volumenInMilliLiter;
    private double alkoholgehalt;
    //Uhrzeit und Datum des Trinkzeitpunkts
    private java.util.Date getrunkenAm;

    /**
     * Konstruktor
     *
     * @param volumenInMilliLiter Volumen in Milliliter des Getränkes
     * @param alkoholgehalt Alkoholgehalt des Getränkes in 1, also 5% = 0.05
     * @param getrunkenAm Trinkdatum und -zeit des Getränkes
     */
    public AlkoholischesGetraenk(int volumenInMilliLiter, double alkoholgehalt, Date getrunkenAm) {
        this.volumenInMilliLiter = volumenInMilliLiter;
        this.alkoholgehalt = alkoholgehalt;
        this.getrunkenAm = getrunkenAm;
    }

    /**
     * Berechnet die Stunden seit der Einnahme des Getränkes
     *
     * Berechnet die Zeit zwischen Einnahme und jetzt, angegeben als Stunden, z.B. 2.5h
     *
     * @return gibt die Stunden seit der Einnahme in einer Kommazahl aus
     */
    public double getStundenSeitEinnahme(){
        java.util.Date jetzt = new Date(System.currentTimeMillis());
        long DiffInMillis = jetzt.getTime() - getrunkenAm.getTime();
        //1000 milisec * 60 sec * 60 min = 3600000
        return DiffInMillis / 3600000.0;
    }

    /**
     * @return Gibt die Masse des Alkohols in Gramm zurück
     */
    public double getMasseInGramm(){
        return volumenInMilliLiter * alkoholgehalt * DICHTE_ALKOHOL;

    }
}
