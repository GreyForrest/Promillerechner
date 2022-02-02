package ch.bbbaden.m226a.police;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class AlkoholischesGetraenkTest {

    @Test
    void testGetStundenSeitEinnahme() {
        //Arrange
        Date trinkzeit = new Date(System.currentTimeMillis() - 9000000); // - 2.5h
        AlkoholischesGetraenk getraenk = new AlkoholischesGetraenk(1200, 0.05, trinkzeit);
        Date jetzt = new Date(System.currentTimeMillis());

        //Act
        double ist = getraenk.getStundenSeitEinnahme();
        long milliseconds = jetzt.getTime() - trinkzeit.getTime();
        double soll = milliseconds/3600000.0;

        //Assert
        assertEquals(soll, ist, 0.00001); //Differenz durch unterschiedliche jetzt Zeitpunkte, wenige Millisekunden

    }

    @Test
    void testGetMasseInGramm() {
        //Arrange
        Date trinkzeit = new Date(System.currentTimeMillis());
        AlkoholischesGetraenk getraenk = new AlkoholischesGetraenk(1200, 0.05, trinkzeit);

        //Act
        double ist = getraenk.getMasseInGramm();
        double soll = 1200 * 0.05 * 0.8; //Volumen * Alkoholgehalt * Alkoholdichte

        //Assert
        assertEquals(soll, ist, 0.0);

    }
}