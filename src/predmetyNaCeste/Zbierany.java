package predmetyNaCeste;

import fri.shapesge.Obrazok;

import java.util.Random;

/**
 * Trieda Zbierany implementuje interface CitliveNaDotyk.
 * Predstavuje predmety, ktore hraci zbieraju s cielom zvysit svoje skore.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class Zbierany implements CitliveNaDotyk {
    private final Obrazok obrazokPredmetu;
    private final int pozXLava;
    private int pozYHorna;
    private boolean jeViditelny;

    /**
     * Konstuktor vykresli obrazok predmetu na nahodnej pozicii.
     * @param velkostPlochy   velkost hracej plochy je v kazdom leveli odlisna
     * @param cestaKObrazku   nazov suboru
     */
    public Zbierany(int velkostPlochy, String cestaKObrazku) {
        Random generatorNaX = new Random();
        Random generatorNaY = new Random();
        this.pozXLava = generatorNaX.nextInt(496) + 80;
        this.pozYHorna = generatorNaY.nextInt(velkostPlochy - 99) + 750 - velkostPlochy;
        this.obrazokPredmetu = new Obrazok(cestaKObrazku, this.pozXLava, this.pozYHorna);
        this.obrazokPredmetu.zobraz();
        this.jeViditelny = true;
    }

    /**
     * Metoda posunDole posunie obrazok predmetu zvisle o 5 pixelov.
     */
    @Override
    public void posunDole() {
        this.obrazokPredmetu.posunZvisle(5);
        this.pozYHorna += 5;
    }

    /**
     * Metoda skryPredmet skryje vykresleny obrazok.
     */
    @Override
    public void skryPredmet() {
        if (this.jeViditelny) {
            this.obrazokPredmetu.skry();
            this.jeViditelny = false;
        }
    }

    /**
     * Metoda jeViditelny vracia, ci je obrazok viditelny.
     * @return     je obrazok viditelny
     */
    public boolean jeViditelny() {
        return this.jeViditelny;
    }

    /**
     * Metoda getPozXLava vracia polohu laveho okraja obrazku.
     * @return     lavy okraj obrazku
     */
    @Override
    public int getPozXLava() {
        return this.pozXLava;
    }

    /**
     * Metoda getPozXPrava vracia polohu praveho okraja obrazku.
     * @return     pravy okraj obrazku
     */
    @Override
    public int getPozXPrava() {
        return this.pozXLava + 50;
    }

    /**
     * Metoda getPozYHorna vracia polohu horneho okraja obrazku.
     * @return     horny okraj obrazku
     */
    @Override
    public int getPozYHorna() {
        return this.pozYHorna;
    }

    /**
     * Metoda getPozYDolna vracia polohu dolneho okraja obrazku.
     * @return     dolny okraj obrazku
     */
    @Override
    public int getPozYDolna() {
        return this.pozYHorna + 50;
    }
}
