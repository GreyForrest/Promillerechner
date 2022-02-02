package ch.bbbaden.m226a.police;

import org.junit.jupiter.api.Test;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    //Alter, Grösse, Gewicht, Infos zum Getränk sowie Promillewert aus Aufgabenstellung übernommen

    @Test
    //Test für weibliche Person, Konsum gerade jetzt
    void testGetAlkoholpromille1() {
        //Arrange
        Date geburtstag = new Date(76, 9, 10); //Alter von 45 Jahren
        Date trinkZeit = new Date(System.currentTimeMillis());
        Person person = new Person(88, 176, geburtstag, 1);
        AlkoholischesGetraenk getraenk = new AlkoholischesGetraenk(1200, 0.05, trinkZeit);

        //Act
        person.trinke(getraenk);
        double ist = person.getAlkoholpromille();

        //Assert
        assertEquals(0.9688540911037222, ist, 0.0);
    }

    @Test
    //Test für männliche Person, Konsum gerade jetzt
    void testGetAlkoholpromille2() {
        //Arrange
        Date geburtstag = new Date(76, 9, 10); //Alter von 45 Jahren
        Date trinkZeit = new Date(System.currentTimeMillis());
        Person person = new Person(88, 176, geburtstag, 0);
        AlkoholischesGetraenk getraenk = new AlkoholischesGetraenk(1200, 0.05, trinkZeit);

        //Act
        person.trinke(getraenk);
        double ist = person.getAlkoholpromille();

        //Assert
        assertEquals(0.7801912053596537, ist, 0.0);
    }

    @Test
    //Test für männliche Person, Konsum vor 2.5 Stunden
    void testGetAlkoholpromille3() {
        //Arrange
        Date geburtstag = new Date(76, 9, 10); //Alter von 45 Jahren
        Date trinkZeit = new Date(System.currentTimeMillis() - 9000000); // - 2.5 Stunden
        Person person = new Person(88, 176, geburtstag, 0);
        AlkoholischesGetraenk getraenk = new AlkoholischesGetraenk(1200, 0.05, trinkZeit);

        //Act
        person.trinke(getraenk);
        double ist = person.getAlkoholpromille();

        //Assert
        assertEquals(0.6301912053596537, ist, 0.00001);
    }

    @Test
    //Test für weibliche Person, Konsum vor 2.5 Stunden
    void testGetAlkoholpromille4() {
        //Arrange
        Date geburtstag = new Date(76, 9, 10); //Alter von 45 Jahren
        Date trinkZeit = new Date(System.currentTimeMillis() - 9000000); // - 2.5 Stunden
        Person person = new Person(88, 176, geburtstag, 1);
        AlkoholischesGetraenk getraenk = new AlkoholischesGetraenk(1200, 0.05, trinkZeit);

        //Act
        person.trinke(getraenk);
        double ist = person.getAlkoholpromille();

        //Assert
        assertEquals(0.8188540911037222, ist, 0.00001);
    }

    @Test
    //Test, ob Promille unter 0 sinkt
    void testGetAlkoholpromille5() {
        //Arrange
        Date geburtstag = new Date(76, 9, 10); //Alter von 45 Jahren
        Date trinkZeit = new Date(System.currentTimeMillis() - 172800000); // - 2 Tage
        Person person = new Person(88, 176, geburtstag, 1);
        AlkoholischesGetraenk getraenk = new AlkoholischesGetraenk(1200, 0.05, trinkZeit);

        //Act
        person.trinke(getraenk);
        double ist = person.getAlkoholpromille();

        //Assert
        assertEquals(0, ist, 0.0);
    }
}