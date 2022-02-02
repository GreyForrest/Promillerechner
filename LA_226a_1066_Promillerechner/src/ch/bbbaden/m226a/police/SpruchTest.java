package ch.bbbaden.m226a.police;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpruchTest {

    @Test
    void testGetSpruch1() {
        //Arrange
        double promille = 1.1;
        Spruch testSpruch = new Spruch(promille);
        String soll = "Vielleicht solltest du aufhören zu trinken...";

        //Act
        String ist = testSpruch.getSpruch();

        //Assert
        assertEquals(soll, ist);
    }

    @Test
    void testGetSpruch2() {
        //Arrange
        double promille = 0.3;
        Spruch testSpruch = new Spruch(promille);
        String soll = "Kanpai!";

        //Act
        String ist = testSpruch.getSpruch();

        //Assert
        assertEquals(soll, ist);
    }

    @Test
    void testGetSpruch3() {
        //Arrange
        double promille = 0.6;
        Spruch testSpruch = new Spruch(promille);
        String soll = "Du solltest nicht mehr Autofahren.";

        //Act
        String ist = testSpruch.getSpruch();

        //Assert
        assertEquals(soll, ist);
    }
    //Grenztest
    @Test
    void testGetSpruch4() {
        //Arrange
        double promille = 0.5;
        Spruch testSpruch = new Spruch(promille);
        String soll = "Kanpai!";

        //Act
        String ist = testSpruch.getSpruch();

        //Assert
        assertEquals(soll, ist);
    }

    //Grenztest
    @Test
    void testGetSpruch5() {
        //Arrange
        double promille = 1;
        Spruch testSpruch = new Spruch(promille);
        String soll = "Du solltest nicht mehr Autofahren.";

        //Act
        String ist = testSpruch.getSpruch();

        //Assert
        assertEquals(soll, ist);
    }
}