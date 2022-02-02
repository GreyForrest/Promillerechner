package ch.bbbaden.m226a.police;

public class Spruch {
    double alkoholPromille;

    /**
     * Konstruktor
     *
     * @param alkoholPromille Promille einer Person, um den passenden Spruch zu finden
     */
    public Spruch(double alkoholPromille){
        this.alkoholPromille = alkoholPromille;
    }

    /**
     * Gibt eine Spruch aus
     *
     * Mithilfe der Promille wird ein passender Spruch ausgesucht, welcher zurückgegeben wird.
     *
     * @return gibt einen String mit einem Spruch passend zur Promille aus
     */
    public String getSpruch(){
        if(alkoholPromille <= 0.5){
            return "Kanpai!";
        } else if(alkoholPromille <= 1) { //Promille grösser als 0.5 und bis und mit 1
        return "Du solltest nicht mehr Autofahren.";
        } else {
            return "Vielleicht solltest du aufhören zu trinken...";
        }
    }
}
