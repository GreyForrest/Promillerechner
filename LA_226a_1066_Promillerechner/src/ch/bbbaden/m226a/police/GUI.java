package ch.bbbaden.m226a.police;

import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static javax.swing.JOptionPane.showInputDialog;

public class GUI {

    /**
     * Fragt nach Gewicht in Kilogramm, Grösse in Zentimeter, dem Geburtstag und dem Geschlecht einer Person und gibt ein Objekt des Typs Person zurück
     *
     * Mithilfe von JOptionPane werden Gewicht, Grösse, Geburtstag und Geschlecht abgefragt und validiert. Wenn eine Eingabe nicht stimmt, wird sie erneut
     * abgefragt, immer und immer wieder bis es eine korrekte Eingabe ist. Diese Eingaben werden am Schluss genutzt, um eine Person zu erstellen.
     * @return gibt einer Person mit angegebenen Angaben zurück
     */
    private Person askPersonData(){

        //Gewicht
        boolean ErrorKoerpermasse;
        double koerpermasse = 0.0;
        do {
            try {
                //Versuche Eingabe in double umzuwandeln
                koerpermasse = Integer.parseInt(showInputDialog(null, "Geben Sie bitte Ihr Gewicht in Kilogramm an:", "Promillerechner", JOptionPane.QUESTION_MESSAGE));
                if (koerpermasse == JOptionPane.CANCEL_OPTION) { // Canceloption
                    System.exit(0);
                }
                ErrorKoerpermasse = false;
            } catch (Exception e){
                //Wiederhole falls Fehler
                JOptionPane.showMessageDialog(null, "Ihre Eingabe scheint ungültig zu sein, versuchen Sie es bitte erneut.", "Error", JOptionPane.DEFAULT_OPTION);
                ErrorKoerpermasse = true;
            }
        } while(ErrorKoerpermasse);

        //Grösse
        boolean ErrorGroesse;
        double koerpergroesse = 0.0;
        do {
            try {
                //Versuche Eingabe in double umzuwandeln
                koerpergroesse  = Integer.parseInt(showInputDialog(null, "Geben Sie bitte Ihre Körpergrösse in Zentimeter an", "Promillerechner", JOptionPane.QUESTION_MESSAGE));
                if (koerpergroesse == JOptionPane.CANCEL_OPTION) { // Canceloption
                    System.exit(0);
                }
                ErrorGroesse = false;
            } catch (Exception e){
                //Wiederhole falls Fehler
                JOptionPane.showMessageDialog(null, "Ihre Eingabe scheint ungültig zu sein, versuchen Sie es bitte erneut.", "Error", JOptionPane.DEFAULT_OPTION);
                ErrorGroesse = true;
            }
        } while(ErrorGroesse);

        //Geburtsdatum
        Date geburtsdatum = null;
        final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        final Date jetzt = new Date();
        do {
            //Eingabe wird gemacht
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie Ihr Geburtsdatum ein.",
                    "Promillerechner",
                    JOptionPane.QUESTION_MESSAGE);

            if (eingabe == null) { // Canceloption
                System.exit(0);
            }
            try {
                //Eingabe wird validiert
                geburtsdatum = formatter.parse(eingabe);
            } catch (ParseException ex) {
                //Wiederhole falls Fehler
                JOptionPane.showMessageDialog(null, eingabe + " ist kein gültiges Datum.");
            }
        } while (geburtsdatum == null || !jetzt.after(geburtsdatum));

        //Geschlecht
        //JOptionPane mit Optionen männlich und weiblich als Auswahl
        String[] options = new String[] {"männlich", "weiblich"};
        int geschlecht = JOptionPane.showOptionDialog(null, "Bitte wählen Sie ihr Geschlecht:", "Promillerechner", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        Person neuePerson = new Person(koerpermasse,koerpergroesse, geburtsdatum, geschlecht);
        return neuePerson;
    }

    /**
     * Fragt nach Alkoholgehält und Volumen eines Getränkes und gibt ein Objekt des Typs AlkoholischesGetränk mit diesen Daten zurück
     *
     * Mithilfe von JOptionPane wird nach Alkoholgehalt und Volumen in Milliliter des Getränks und validiert diese. Bei einer falschen Eingabe wird
     * erneut gefragt, bis die Eingabe stimmt. Danach werden diese Angaben gebraucht, um ein neues Objekt des Typs AlkoholischesGetränk zu erstellen.
     * @param trinkdatum Zeitpunkt der Getränkeeinnahme, Zeit und Datum
     * @return Objekt des Typs AlkoholischesGetränk mit den Daten, die eingegeben wurden
     */
    private AlkoholischesGetraenk askAlkoholischeGetraenkeData(Date trinkdatum) {

        //Volumen in Milliliter
        boolean ErrorMilliliter;
        int volumenInMilliliter = 0;
        do {
            try {
                //Versuche Eingabe in Int umzuwandeln
                volumenInMilliliter = Integer.parseInt(JOptionPane.showInputDialog(null, "Bitte geben Sie an, wie viel Sie getrunken haben (in Milliliter):", "Promillerechner", JOptionPane.PLAIN_MESSAGE));
                if (volumenInMilliliter == JOptionPane.CANCEL_OPTION) { // Cancel
                    System.exit(0);
                }
                ErrorMilliliter = false;
            } catch (Exception e){
                //Wiederhole falls Fehler
                JOptionPane.showMessageDialog(null, "Ihre Eingabe scheint ungültig zu sein, versuchen Sie es bitte erneut.", "Error", JOptionPane.DEFAULT_OPTION);
                ErrorMilliliter = true;
            }
        } while(ErrorMilliliter);

        //Alkoholgehalt
        boolean ErrorAlkoholgehalt = false;
        double alkoholgehalt = 0;
        do {
            try {
                alkoholgehalt = Double.parseDouble(JOptionPane.showInputDialog(null, "Bitte geben Sie  den Alkoholgehalt an (5 Prozent --> Eingabe = 0.05):", "Promillerechner", JOptionPane.PLAIN_MESSAGE));
                if (alkoholgehalt == JOptionPane.CANCEL_OPTION) { // Cancel
                    System.exit(0);
                }
                if(alkoholgehalt >= 1){
                    throw new Exception();
                }
                ErrorAlkoholgehalt = false;
            } catch (Exception e){
                JOptionPane.showMessageDialog(null, "Ihre Eingabe scheint ungültig zu sein, versuchen Sie es bitte erneut.", "Error", JOptionPane.DEFAULT_OPTION);
                ErrorAlkoholgehalt = true;
            }
        } while(ErrorAlkoholgehalt);
        AlkoholischesGetraenk getrunkenesGetraenk = new AlkoholischesGetraenk(volumenInMilliliter, alkoholgehalt, trinkdatum);
        return getrunkenesGetraenk;
    }

    /**
     * Verantwortlich für den Programmablauf  startet den Ablauf und gibt am Schluss die Promille aus
     *
     * Ruft die Methoden askPersonData und askAlkoholischesGetränkData auf und speichert die Objekte, auf welchen dann weitere Methoden zur Berechnung
     * der Promille aufgerufen werden. Am Schluss erscheint ein JOptionPane, welches die Promille und einen passenden Spruch ausgibt.
     */
    public void Promillerechner(){
        Person NeuePerson = askPersonData();

        //Trinkdatum und -zeit wird abgefragt
        Date trinkDatum = null;
        final DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy kk:mm");
        final Date jetzt = new Date();
        do {
            final String eingabe = JOptionPane.showInputDialog(null, "Geben Sie Trinkdatum und -zeit ein (Format: dd.mm.yyyy hh:mm):",
                    "Promillerechner",
                    JOptionPane.QUESTION_MESSAGE);
            if (eingabe == null) {
                System.exit(0);
            }
            try {
                //Eingabe wird validiert
                trinkDatum = formatter.parse(eingabe);
            } catch (ParseException ex) {
                //Wiederhole falls Fehler
                JOptionPane.showMessageDialog(null, eingabe + " ist keine gültige Trinkzeit.");
            }
        } while (trinkDatum == null || !jetzt.after(trinkDatum));

        AlkoholischesGetraenk getrunkenesGetraenk = askAlkoholischeGetraenkeData(trinkDatum);
        NeuePerson.trinke(getrunkenesGetraenk);
        double Promille = NeuePerson.getAlkoholpromille();
        Spruch spruch = new Spruch(Promille);
        //Resultat als JOptionePane
        JOptionPane.showMessageDialog(null, "Ihre Promille ist:\n" + Promille + "\n" + spruch.getSpruch(), "Promillerechner", JOptionPane.INFORMATION_MESSAGE);
    }
}
