package ch.bbbaden.m226a.police;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Person {

    //Konstanten zur Person
    public static final int MAENNLICH = 0;
    private static final double ABBAU_WARTEZEIT_STUNDEN = 1.0;
    private static final double ABBAU_PRO_STUNDE = 0.1;
    private static final double ANTEIL_WASSER_IM_BLUT = 0.8;
    private static final double DICHTE_BLUT_GRAMM_PRO_CCM = 1.055;

    //Variablen zur Person
    private double koerpermasse;
    private double koerpergroesseInCm;
    private java.util.Date geburtsdatum;
    private int geschlecht;
    private double Alkoholpromille = 0.0;

    /**
     * Konstruktor
     *
     * @param koerpermasse Körpermasse in Kilogramm einer Person
     * @param koerpergroesseInCm Körpergrösse in Zentimeter einer Person
     * @param geburtsdatum Geburtstdatum einer Person
     * @param geschlecht Geschlecht einer Person, angegeben mit einem Int, 0 = männlich, 1 = weiblich
     */
    public Person(double koerpermasse, double koerpergroesseInCm, Date geburtsdatum, int geschlecht) {
        this.koerpermasse = koerpermasse;
        this.koerpergroesseInCm = koerpergroesseInCm;
        this.geburtsdatum = geburtsdatum;
        this.geschlecht = geschlecht;
    }

    /**
     * Gibt das Alter in Jahren zurück
     *
     * Rechnet die Differenz zwischen jetzt und dem Geburtstag und gibt sie als Jahre aus
     * @return Alter in Jahren als double
     */
    private double getAlterInJahren(){
        final Date jetzt = new Date(System.currentTimeMillis());
        long timeDifference = jetzt.getTime() - geburtsdatum.getTime();
        long days = timeDifference/86400000; //1000 millisec * 60 sec * 60 min * 24h
        return days/365;
    }

    /**
     * Ein Getränk wird getrunken und die Promille passt sich an
     *
     * Ein Getränk wird übergeben, dessen Werte werden genutzt um die Promille einer Person zu berechnen. Falls bereits Zeit vergangen ist, wird
     * diese Promille vielleicht auch schon wieder abgebaut.
     *
     * @param alkoholischesGetraenk Objekt der Klasse AlkoholischesGetränk mit Alkoholgehalt, Volumen in Millilitern und einem Trinkdatum
     */
    public void trinke(AlkoholischesGetraenk alkoholischesGetraenk){
        double promille = (alkoholischesGetraenk.getMasseInGramm() * ANTEIL_WASSER_IM_BLUT) / (DICHTE_BLUT_GRAMM_PRO_CCM * this.getGKW());
        double stunden = alkoholischesGetraenk.getStundenSeitEinnahme();
        if(stunden > 1) {
            //Falls Abbauwartezeit bereits überschritten ist
            Alkoholpromille = promille - (ABBAU_PRO_STUNDE * (stunden - ABBAU_WARTEZEIT_STUNDEN));
        } else {
            Alkoholpromille = promille;
        }
        if(Alkoholpromille < 0){
            Alkoholpromille = 0;
        }
    }

    /**
     *
     * @return gibt die Alkoholpromille der Person zurück
     */
    public double getAlkoholpromille() {
        return Alkoholpromille;
    }

    /**
     * Rechnet den GKW aus, der für die Berechnung der Promille gebraucht wird
     *
     * Der GWK wird je nach Geschlecht anders berechnet, dafür werden Körpergrösse und -masse verwendet, so wie das Alter.
     *
     * @return gibt Gesamtkörperwasserindex zurück
     */
    private double getGKW(){
        double GKW;
        if(geschlecht == MAENNLICH){
            GKW = 2.447 - (0.09516 * this.getAlterInJahren()) + (0.1074 * koerpergroesseInCm) + (0.3362 * koerpermasse);
        } else {
            GKW = 0.203 - (0.07 * this.getAlterInJahren()) + (0.1069 * koerpergroesseInCm) + (0.2466 * koerpermasse);
        }
        return GKW;
    }
}
