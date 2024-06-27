package predmetyNaCeste;

import fri.shapesge.Obrazok;

/**
 * Trieda Hrac implementuje interface CitliveNaDotyk.
 * Predstavuje obrazok auta, ktore ovlada pouzivatel pomocou klavesov.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class Hrac implements CitliveNaDotyk {
    private final Obrazok obrazokAuta;
    private int pozXLava;
    private int pozYHorna;

    /**
     * Konstruktor vykresli obrazok auta na nami urcenu poziciu.
     *
     * @param obrazok referencia na typ obrazku auta
     * @param pozXLava poloha auta na X
     * @param pozYHorna poloha auta na Y
     */
    public Hrac(ObrAuta obrazok, int pozXLava, int pozYHorna) {
        this.pozXLava = pozXLava;
        this.pozYHorna = pozYHorna;
        this.obrazokAuta = new Obrazok(obrazok.getNazovSuboru(), this.pozXLava, this.pozYHorna);
        this.obrazokAuta.zobraz();
    }

    /**
     * Metoda skryPredmet skryje vykresleny obrazok.
     */
    @Override
    public void skryPredmet() {
        this.obrazokAuta.skry();
    }

    /**
     * Metoda posunHore posunie obrazok hraca zvisle o -10 pixleov a zmeni poziciu na Y.
     * Zaroven sa kontroluje, aby obrazok nevysiel mimo hracej plochy.
     */
    public void posunHore() {
        if (this.pozYHorna > 0) {
            this.obrazokAuta.posunZvisle(-10);
            this.pozYHorna -= 10;
        }

    }

    /**
     * Metoda posunDole posunie obrazok hraca zvisle o 10 pixleov a zmeni poziciu na Y.
     * Zaroven sa kontroluje, aby obrazok nevysiel mimo hracej plochy.
     */
    @Override
    public void posunDole() {
        if (this.getPozYHorna() < 570) {
            this.obrazokAuta.posunZvisle(10);
            this.pozYHorna += 10;
        }
    }

    /**
     * Metoda posunVlavo posunie obrazok hraca vodorovne o -10 pixleov a zmeni poziciu na X.
     * Zaroven sa kontroluje, aby obrazok nevysiel mimo cesty.
     */
    public void posunVlavo() {
        if (this.pozXLava > 80) {
            this.obrazokAuta.posunVodorovne(-10);
            this.pozXLava -= 10;
        }

    }

    /**
     * Metoda posunVpravo posunie obrazok hraca vodorovne o 10 pixleov a zmeni poziciu na X.
     * Zaroven sa kontroluje, aby obrazok nevysiel mimo cesty.
     */
    public void posunVpravo() {
        if (this.pozXLava < 560) {
            this.obrazokAuta.posunVodorovne(10);
            this.pozXLava += 10;
        }

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
