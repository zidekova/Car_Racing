package predmetyNaCeste;

import fri.shapesge.Obrazok;

/**
 * Trieda Jelen implementuje interface CitliveNaDotyk.
 * Predstavuje jelena, ktoreho ked hraci zrazia, tak sa im znizi skore.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class Jelen implements CitliveNaDotyk {
    private Obrazok obrazokJelena;
    private Obrazok krv;
    private int pozXLava;
    private int pozYHorna;
    private boolean vpravo = true;
    private boolean zije = true;

    /**
     * Konstruktor vykresli obrazok jelena na nami urcenu poziciu.
     *
     * @param pozYHorna poloha jelena na Y
     */
    public Jelen(int pozYHorna) {
        this.pozXLava = 81;
        this.pozYHorna = pozYHorna;
        this.obrazokJelena = new Obrazok("jelenP.png", this.pozXLava, this.pozYHorna);
        this.obrazokJelena.zobraz();
    }

    /**
     * Metoda meni obrazok jelena na zaklade jeho pozicie.
     */
    public void otocJelena() {
        if (this.pozXLava >= 525) {
            this.obrazokJelena.skry();
            this.obrazokJelena = null;
            this.obrazokJelena = new Obrazok("jelenL.png", this.pozXLava, this.pozYHorna);
            this.obrazokJelena.zobraz();
            this.vpravo = false;
        }

        if (this.pozXLava <= 80) {
            this.obrazokJelena.skry();
            this.obrazokJelena = null;
            this.obrazokJelena = new Obrazok("jelenP.png", this.pozXLava, this.pozYHorna);
            this.obrazokJelena.zobraz();
            this.vpravo = true;
        }
    }

    /**
     * Metoda skryPredmet skryje vykresleny obrazok.
     */
    @Override
    public void skryPredmet() {
        this.obrazokJelena.skry();
    }

    /**
     * Metoda posunDole posunie obrazok predmetu zvisle o 5 pixelov.
     * Ak je vykresleny aj obrazok krvi, posuva tiez aj ten.
     */
    @Override
    public void posunDole() {
        this.obrazokJelena.posunZvisle(5);
        this.pozYHorna += 5;
        if (this.krv != null) {
            this.krv.posunZvisle(5);
        }
    }

    /**
     * Metoda posuva obrazok jelena vodorovne na zaklade jeho pozicie.
     */
    public void pohybJelena() {
        if (this.zije) {
            if (this.pozXLava < 560 && this.vpravo) {
                this.obrazokJelena.posunVodorovne(5);
                this.pozXLava += 5;
            }
            if (this.pozXLava > 80 && !this.vpravo) {
                this.obrazokJelena.posunVodorovne(-5);
                this.pozXLava -= 5;
            }
        }
    }

    /**
     * Metoda otaca obrazok jelena po zrazke s hracom a vykresluje obrazok krvi.
     */
    public void zabiJelena() {
        if (this.zije) {
            this.obrazokJelena.zmenUhol(180);
            this.krv = new Obrazok("krv.png", this.pozXLava, this.pozYHorna);
            this.krv.zobraz();
            this.zije = false;
        }
    }

    /**
     * Metoda vracia informaciu o tom, ci jelena este nezrazilo auto.
     * @return jelen zije
     *
     */
    public boolean zije() {
        return this.zije;
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
        return this.pozXLava + 100;
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
        return this.pozYHorna + 100;
    }
}
