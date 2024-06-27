package predmetyNaCeste;

import fri.shapesge.Obrazok;

import java.util.Random;

/**
 * Trieda OstatneAuta implementuje interface CitliveNaDotyk.
 * Predstavuje auto, s ktorym sa hraci nesmu zrazit, lebo by prehrali.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class OstatneAuta implements CitliveNaDotyk {
    private Obrazok obrAuta;
    private final int pozXLava;
    private int pozYHorna;

    /**
     * Konstruktor vykresli auto s nahodnou farbou na nami urcenu poziciu.
     * @param pozXLava poloha auta na X
     * @param pozYHorna poloha auta na Y
     */
    public OstatneAuta(int pozXLava, int pozYHorna) {
        this.pozXLava = pozXLava;
        this.pozYHorna = pozYHorna;

        Random generator = new Random();

        // pre auto v smere jazdy
        if (pozXLava == 248 || pozXLava == 528) {
            int poradie = generator.nextInt(4) + 2;
            ObrAuta obrazok = ObrAuta.values()[poradie];
            this.obrAuta = new Obrazok(obrazok.getNazovSuboru(), this.pozXLava, this.pozYHorna);
            this.obrAuta.zobraz();
        }

        // pre auto v protismere
        if (pozXLava == 113 || pozXLava == 393) {
            int poradie = generator.nextInt(4) + 6;
            ObrAuta obrazok = ObrAuta.values()[poradie];
            this.obrAuta = new Obrazok(obrazok.getNazovSuboru(), this.pozXLava, this.pozYHorna);
            this.obrAuta.zobraz();
        }
    }

    /**
     * Metoda skryPredmet skryje vykresleny obrazok.
     */
    @Override
    public void skryPredmet() {
        this.obrAuta.skry();
    }

    /**
     * Metoda posunDole posunie obrazok auta zvisle o 5 pixelov.
     */
    @Override
    public void posunDole() {
        this.obrAuta.posunZvisle(5);
        this.pozYHorna += 5;
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
        return this.pozXLava + 65;
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
        return this.pozYHorna + 130;
    }
}
