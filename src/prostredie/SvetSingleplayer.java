package prostredie;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import predmetyNaCeste.CitliveNaDotyk;
import predmetyNaCeste.Hrac;
import predmetyNaCeste.Jelen;
import predmetyNaCeste.ObrAuta;
import predmetyNaCeste.OstatneAuta;
import predmetyNaCeste.Zbierany;

import java.util.ArrayList;

/**
 * Trieda SvetSingleplayer predstavuje vykreslenie objektov, ktore su spolocne pre vsetky levely.
 * Je zaroven predkom triedy SvetMultiplayer.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class SvetSingleplayer {
    private final int velkostPlochy;
    private final Prostredie prostredie;
    private final Hrac hrac;
    private final Obrazok finalnaCiara;
    private int polohaYCiary;
    private final ArrayList<OstatneAuta> auta;
    private final Manazer manazer;
    private final ArrayList<Zbierany> mince;
    private final ArrayList<Zbierany> bonusy;
    private final Skore skoreHraca;
    private final ArrayList<Jelen> jelene;

    /**
     * Konstruktor inicializuje niektore objekty.
     * @param  velkostPlochy   velkost hracej plochy je v kazdom leveli odlisna
     */
    public SvetSingleplayer(int velkostPlochy) {
        this.velkostPlochy = velkostPlochy;
        this.prostredie = new Prostredie(this.velkostPlochy);

        // vykresli finalnu ciaru na polohu podla velkosti plochy
        this.polohaYCiary = -(this.velkostPlochy - 1400);
        this.finalnaCiara = new Obrazok("finalnaCiara.png", 80, this.polohaYCiary);
        this.finalnaCiara.zobraz();

        this.hrac = new Hrac(ObrAuta.HRAC, 528, 565);
        this.auta = new ArrayList<OstatneAuta>();

        this.mince = new ArrayList<Zbierany>();
        this.bonusy = new ArrayList<Zbierany>();

        this.jelene = new ArrayList<Jelen>();

        this.skoreHraca = new Skore(635, 20);

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    /**
     * Metoda getPolohaYCiary vracia polohu finalnej ciary.
     *
     * @return poloha finalnej ciary
     */
    public int getPolohaYCiary() {
        return this.polohaYCiary;
    }

    /**
     * Metoda getMince vracia ArrayList zberatelnych predmetov - minci.
     *
     * @return ArrayList minci
     */
    public ArrayList<Zbierany> getMince() {
        return this.mince;
    }

    /**
     * Metoda getBonusy vracia ArrayList zberatelnych predmetov - bonusov.
     *
     * @return ArrayList bonusov
     */
    public ArrayList<Zbierany> getBonusy() {
        return this.bonusy;
    }

    /**
     * Metoda getSkoreHraca vracia aktualne skore hraca.
     *
     * @return skore hraca
     */
    public Skore getSkoreHraca() {
        return this.skoreHraca;
    }

    /**
     * Metoda getAuta vracia ArrayList ostatnych aut.
     *
     * @return ArrayList aut
     */
    public ArrayList<OstatneAuta> getAuta() {
        return this.auta;
    }

    /**
     * Metoda getJelene vracia ArrayList jelenov.
     *
     * @return ArrayList jelenov
     */
    public ArrayList<Jelen> getJelene() {
        return this.jelene;
    }

    /**
     * Metoda getHrac vracia referenciu na hraca.
     *
     * @return referencia na hraca
     */
    public Hrac getHrac() {
        return this.hrac;
    }

    /**
     * Metoda getManazer vracia referenciu na manazera.
     *
     * @return referencia na manazera
     */
    public Manazer getManazer() {
        return this.manazer;
    }

    /**
     * Metoda vykresli a prida do zoznamu jelenov noveho jelena na nami urcenu poziciu Y.
     * @param polohaY   poloha jelena na Y
     */
    public void vykresliJelena(int polohaY) {
        this.jelene.add(new Jelen(polohaY));
    }

    /**
     * Metoda vykresli a prida do zoznamu aut nove auto na nami urcenu poziciu.
     * @param  polohaX   poloha auta na X
     * @param  polohaY   poloha auta na Y
     */
    public void vykresliAuto(int polohaX, int polohaY) {
        this.auta.add(new OstatneAuta(polohaX, polohaY));
    }

    /**
     * Metoda vykresli nami urceny pocet zberatelnych predmetov na hraciu plochu.
     * @param  pocet   pocet zberatelnych predmetov na zaklade levelu
     */
    public void vykresliZberatelnePredmety(int pocet) {
        for (int i = 0; i < pocet; i++) {
            this.mince.add(new Zbierany(this.velkostPlochy, "minca.png"));
        }

        for (int i = 0; i < (pocet / 2); i++) {
            this.bonusy.add(new Zbierany(this.velkostPlochy, "hviezda.png"));
        }
    }

    /**
     * Metoda skrySvet skryje vsetky vykreslene objekty.
     */
    public void skrySvet() {
        this.prostredie.skryProstredie();
        this.hrac.skryPredmet();
        this.skoreHraca.skrySkore();
        this.finalnaCiara.skry();

        for (int i = 0; i < this.auta.size(); i++) {
            this.auta.get(i).skryPredmet();
        }

        for (int i = 0; i < this.jelene.size(); i++) {
            this.jelene.get(i).skryPredmet();
        }

        for (int i = 0; i < this.mince.size(); i++) {
            this.mince.get(i).skryPredmet();
        }

        for (int i = 0; i < this.bonusy.size(); i++) {
            this.bonusy.get(i).skryPredmet();
        }
    }

    /**
     * Metoda posuva hraca hore po stlaceni klavesy UP.
     */
    public void posunAutaHore() {
        this.hrac.posunHore();
    }

    /**
     * Metoda posuva hraca dole po stlaceni klavesy DOWN.
     */
    public void posunAutaDole() {
        this.hrac.posunDole();
    }

    /**
     * Metoda posuva hraca vlavo po stlaceni klavesy LEFT.
     */
    public void posunAutaVlavo() {
        this.hrac.posunVlavo();
    }

    /**
     * Metoda posuva hraca vpravo po stlaceni klavesy RIGHT.
     */
    public void posunAutaVpravo() {
        this.hrac.posunVpravo();
    }

    /**
     * Metoda kontroluje ci hrac dosiahol finalnu ciaru na zaklade ich polohy.
     * @return     dosiahnutie finalnej ciary
     */
    public boolean dosiahnutieFinalnejCiary() {
        return this.hrac.getPozYHorna() <= this.polohaYCiary;
    }

    /**
     * Metoda posunDole posunie vsetky objekty zvisle o 5 pixelov, pricom kontroluje dosiahnutie finalnej ciary.
     */
    public void posunDole() {
        if (!this.dosiahnutieFinalnejCiary()) {
            // ak sa nedosiahla finalna ciara
            this.prostredie.posunDole();
            this.finalnaCiara.posunZvisle(5);
            this.polohaYCiary += 5;

            for (int i = 0; i < this.auta.size(); i++) {
                this.auta.get(i).posunDole();
            }

            for (int i = 0; i < this.jelene.size(); i++) {
                this.jelene.get(i).posunDole();
                this.jelene.get(i).pohybJelena();
                this.jelene.get(i).otocJelena();
            }

            for (int i = 0; i < this.mince.size(); i++) {
                this.mince.get(i).posunDole();
            }

            for (int i = 0; i < this.bonusy.size(); i++) {
                this.bonusy.get(i).posunDole();
            }
        } else {
            // ak sa dosiahla finalna ciara
            this.manazer.prestanSpravovatObjekt(this);
        }
    }

    /**
     * Metoda kontroluje polohu zberatelnych predmetov a hraca a na zaklade toho rozhoduje, ci mu zvysi skore a predmety zmiznu.
     */
    public void zbierajPredmety() {
        for (int i = 0; i < this.mince.size(); i++) {
            if (this.dotyk(this.hrac, this.mince.get(i))) {
                // kontrola polohy kazdej mince a hraca
                if (this.mince.get(i).jeViditelny()) {
                    // skore sa prida, len ak je minca viditelna
                    this.skoreHraca.zmenSkore(10);
                }
                // minca sa skryje
                this.mince.get(i).skryPredmet();
            }
        }

        for (int i = 0; i < this.bonusy.size(); i++) {
            if (this.dotyk(this.hrac, this.bonusy.get(i))) {
                // kontrola polohy kazdeho bonusu a hraca
                if (this.bonusy.get(i).jeViditelny()) {
                    // skore sa prida, len ak je bonus viditelny
                    this.skoreHraca.zmenSkore(30);
                }
                // bonus sa skryje
                this.bonusy.get(i).skryPredmet();
            }
        }
    }

    /**
     * Metoda vracia informaciu o tom, ci hrac dosiahol finalnu ciaru.
     *
     * @return dosiahnutie finalnej ciary
     */
    public boolean vyhra() {
        return this.dosiahnutieFinalnejCiary();
    }

    /**
     * Metoda kontroluje, ci sa hrac zrazil s ostatnymi autami na zaklade ich polohy.
     * @return     nastala zrazka aut
     */
    public boolean prehra() {
        boolean zrazka = false;
        for (int i = 0; i < this.auta.size(); i++) {
            if (this.dotyk(this.hrac, this.auta.get(i))) {
                this.manazer.prestanSpravovatObjekt(this);
                zrazka = true;
                Obrazok vybuch = new Obrazok("vybuch.png", this.hrac.getPozXLava(), this.hrac.getPozYHorna());
                vybuch.zobraz();
            }
        }
        return zrazka;
    }

    /**
     * Metoda kontroluje, ci hrac zrazil s jelenov na zaklade ich polohy a na zaklade toho mu odobera body.
     */
    public void zrazJelena() {
        for (int i = 0; i < this.jelene.size(); i++) {
            if (this.dotyk(this.hrac, this.jelene.get(i))) {
                if (this.jelene.get(i).zije()) {
                    this.skoreHraca.zmenSkore(-50);
                }
                this.jelene.get(i).zabiJelena();
            }
        }
    }

    /**
     * Metoda vracia informaciu o tom, ci sa dva predmety citlive na dotyk dotkli.
     *
     * @param prvy instancia triedy implementujucej interface CitliveNaDotyk
     * @param druhy instancia triedy implementujucej interface CitliveNaDotyk
     * @return dotyk dvoch predmetov
     */
    public boolean dotyk(CitliveNaDotyk prvy, CitliveNaDotyk druhy) {
        return prvy.getPozXPrava() - 5 > druhy.getPozXLava() + 5 && prvy.getPozXLava() + 5 < druhy.getPozXPrava() - 5 && prvy.getPozYDolna() - 5 > druhy.getPozYHorna() + 5 && prvy.getPozYHorna() + 5 < druhy.getPozYDolna() - 5;
    }
}
