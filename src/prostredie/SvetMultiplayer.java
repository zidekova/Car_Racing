package prostredie;

import predmetyNaCeste.Hrac;
import predmetyNaCeste.ObrAuta;
import fri.shapesge.Obrazok;

/**
 * Trieda SvetMultiplayer je potomkom triedy SvetSingleplayer.
 * Prekryva niektore jej metody a pridava do sveta druheho hraca.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class SvetMultiplayer extends SvetSingleplayer {
    private final Hrac protihrac;
    private final Skore skoreProtihraca;
    /**
     * Konstruktor inicializuje vyvolava konstruktor predka.
     * Okrem toho tiez inicializuje druheho hraca a jeho skore.
     *
     * @param velkostPlochy velkost hracej plochy je v kazdom leveli odlisna
     */
    public SvetMultiplayer(int velkostPlochy) {
        super(velkostPlochy);
        this.protihrac = new Hrac(ObrAuta.PROTIHRAC, 248, 565);
        this.skoreProtihraca = new Skore(5, 20);
    }

    /**
     * Metoda skryje hraca 2 aj jeho skore.
     */
    @Override
    public void skrySvet() {
        this.protihrac.skryPredmet();
        this.skoreProtihraca.skrySkore();
        super.skrySvet();
    }

    /**
     * Metoda posuva protihraca hore po stlaceni klavesy W.
     */
    public void posunProtihracaHore() {
        this.protihrac.posunHore();
    }

    /**
     * Metoda posuva protihraca dole po stlaceni klavesy S.
     */
    public void posunProtihracaDole() {
        this.protihrac.posunDole();
    }

    /**
     * Metoda posuva protihraca vlavo po stlaceni klavesy A.
     */
    public void posunProtihracaVlavo() {
        this.protihrac.posunVlavo();
    }

    /**
     * Metoda posuva protihraca vpravo po stlaceni klavesy D.
     */
    public void posunProtihracaVpravo() {
        this.protihrac.posunVpravo();
    }

    /**
     * Metoda kontroluje, ci protihrac dosiahol finalnu ciaru.
     *
     * @return dosiahnutie finalnej ciary
     */
    @Override
    public boolean dosiahnutieFinalnejCiary() {
        return super.dosiahnutieFinalnejCiary() || this.protihrac.getPozYHorna() <= this.getPolohaYCiary();
    }

    /**
     * Metoda getSkoreProtihraca vracia aktualne skore hraca 2.
     *
     * @return skore protihraca
     */
    public Skore getSkoreProtihraca() {
        return this.skoreProtihraca;
    }

    /**
     * Metoda kontroluje polohu zberatelnych predmetov a protihraca.
     */
    @Override
    public void zbierajPredmety() {
        for (int i = 0; i < this.getMince().size(); i++) {
            if (this.dotyk(this.protihrac, this.getMince().get(i))) {
                // kontrola polohy kazdej mince a protihraca
                if (this.getMince().get(i).jeViditelny()) {
                    // skore sa prida, len ak je minca viditelna
                    this.skoreProtihraca.zmenSkore(10);
                }
                // minca sa skryje
                this.getMince().get(i).skryPredmet();
            }
        }

        for (int i = 0; i < this.getBonusy().size(); i++) {
            if (this.dotyk(this.protihrac, this.getBonusy().get(i))) {
                // kontrola polohy kazdeho bonusu a protihraca
                if (this.getBonusy().get(i).jeViditelny()) {
                    // skore sa prida, len ak je bonus viditelny
                    this.skoreProtihraca.zmenSkore(30);
                }
                // bonus sa skryje
                this.getBonusy().get(i).skryPredmet();
            }
        }
        super.zbierajPredmety();
    }

    /**
     * Metoda kontroluje, ci doslo k zrazke aut.
     *
     * @return nastala zrazka aut
     */
    @Override
    public boolean prehra() {
        boolean zrazka = false;

        for (int i = 0; i < this.getAuta().size(); i++) {
            // kontrola polohy hraca a ostatnych aut
            if (this.dotyk(this.getHrac(), this.getAuta().get(i))) {
                this.getManazer().prestanSpravovatObjekt(this);
                zrazka = true;
                Obrazok vybuch = new Obrazok("vybuch.png", this.getHrac().getPozXLava(), this.getHrac().getPozYHorna());
                vybuch.zobraz();
            }

            // kontrola polohy protihraca a ostatnych aut
            if (this.dotyk(this.protihrac, this.getAuta().get(i))) {
                this.getManazer().prestanSpravovatObjekt(this);
                zrazka = true;
                Obrazok vybuch = new Obrazok("vybuch.png", this.protihrac.getPozXLava(), this.protihrac.getPozYHorna());
                vybuch.zobraz();
            }
        }

        // kontrola polohy hraca a protihraca
        if (this.dotyk(this.getHrac(), this.protihrac)) {
            this.getManazer().prestanSpravovatObjekt(this);
            zrazka = true;
            Obrazok vybuch = new Obrazok("vybuch.png", this.getHrac().getPozXLava(), this.getHrac().getPozYHorna());
            vybuch.zobraz();
        }
        return zrazka;
    }

    /**
     * Metoda kontroluje zrazku protihraca s jelenom.
     */
    @Override
    public void zrazJelena() {
        for (int i = 0; i < this.getJelene().size(); i++) {
            if (this.dotyk(this.protihrac, this.getJelene().get(i))) {
                if (this.getJelene().get(i).zije()) {
                    this.skoreProtihraca.zmenSkore(-50);
                }
                this.getJelene().get(i).zabiJelena();
            }
        }
        super.zrazJelena();
    }
}
