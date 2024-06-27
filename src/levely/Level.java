package levely;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import hra.Menu;
import prostredie.SvetMultiplayer;
import prostredie.SvetSingleplayer;

import javax.swing.JOptionPane;
import java.util.Scanner;

/**
 * Abstraktna trieda Level je predkom vsetkych levelov.
 *
 * @author (Mária Žideková)
 * @version (január 2023)
 */
public abstract class Level {
    private final Obrazok uvod;
    private SvetSingleplayer level;
    private final Manazer manazer;
    private boolean jeHraAktivovana = false;
    private final int velkostPlochy;
    private boolean multiplayer = false;
    private int vyber = -1;

    /**
     * Konstruktor vykresli celu hraciu plochu na zaklade nami zvolenych udajov.
     *
     * @param velkostPlochy velkost hracej plochy je v kazdom leveli odlisna
     * @param suborSAutami cesta k textovemu suboru s poziciami aut
     * @param suborSJelenmi cesta k textovemu suboru s poziciami jelenov
     * @param multiplayer informacia o tom, ci je level multiplayer
     * @param cisloLevelu poradove cislo levelu
     */
    public Level(int velkostPlochy, String suborSAutami, String suborSJelenmi, boolean multiplayer, int cisloLevelu) {
        this.velkostPlochy = velkostPlochy;
        this.multiplayer = multiplayer;

        if (this.multiplayer) {
            this.level = new SvetMultiplayer(this.velkostPlochy);
        } else {
            this.level = new SvetSingleplayer(this.velkostPlochy);
        }

        this.level.vykresliZberatelnePredmety(this.velkostPlochy / 100);

        try {
            var suborAuta = ClassLoader.getSystemResourceAsStream(suborSAutami);
            Scanner scannerAuta = new Scanner(suborAuta);
            while (scannerAuta.hasNextLine()) {
                var x = scannerAuta.nextInt();
                var y = scannerAuta.nextInt();

                this.level.vykresliAuto(x, y);
            }
            scannerAuta.close();

            var suborJelene = ClassLoader.getSystemResourceAsStream(suborSJelenmi);
            Scanner scannerJelene = new Scanner(suborJelene);
            while (scannerJelene.hasNextLine()) {
                var y = scannerJelene.nextInt();
                this.level.vykresliJelena(y);
            }
            scannerJelene.close();

        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, "Došlo k chybe pri načítaní súboru!");
            System.exit(0);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("level" + cisloLevelu + "Uvod");
        if (this.multiplayer) {
            sb.append("M");
        }
        sb.append(".png");

        this.uvod = new Obrazok(sb.toString(), 200, 275);
        this.uvod.zobraz();

        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
    }

    /**
     * Metoda pri vyhre vytvara okienka a vypisuje dosihnute skore.
     */
    public void vypisVyhry() {
        if (this.level.vyhra()) {
            String spravaVyhra = "Vyhral si tento level :)\nSkóre: " + this.level.getSkoreHraca().getHodnotaSkore();

            if (this.level instanceof SvetMultiplayer) {
                // ak vyhral hrac
                if (this.level.getSkoreHraca().getHodnotaSkore() > ((SvetMultiplayer)this.level).getSkoreProtihraca().getHodnotaSkore()) {
                    spravaVyhra = "Vyhral hráč 1\nSkóre hráča 1: " + this.level.getSkoreHraca().getHodnotaSkore() + "\nSkóre hráča 2: " + ((SvetMultiplayer)this.level).getSkoreProtihraca().getHodnotaSkore();
                }
                // ak vyhral protihrac
                if (this.level.getSkoreHraca().getHodnotaSkore() < ((SvetMultiplayer)this.level).getSkoreProtihraca().getHodnotaSkore()) {
                    spravaVyhra = "Vyhral hráč 2\nSkóre hráča 2: " + ((SvetMultiplayer)this.level).getSkoreProtihraca().getHodnotaSkore() + "\nSkóre hráča 1: " + this.level.getSkoreHraca().getHodnotaSkore();
                }
                // ak nastala remiza
                if (this.level.getSkoreHraca().getHodnotaSkore() == ((SvetMultiplayer)this.level).getSkoreProtihraca().getHodnotaSkore()) {
                    spravaVyhra = "Je to remíza :D";
                }
            }

            String moznost = "Ďalší level";
            String moznost5 = "Ukončiť hru";

            String[] moznosti = new String[2];
            if (this instanceof Level5) {
                moznosti[0] = moznost5;
            } else {
                moznosti[0] = moznost;
            }
            moznosti[1] = "Späť do MENU";

            var vyberHraca = JOptionPane.showOptionDialog(null, spravaVyhra, "Gratulujem!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, moznosti, moznosti[0]);
            this.skryLevel();
            switch (vyberHraca) {
                case 0:
                    this.vyber = 0;
                    break;
                case 1:
                    Menu menu = new Menu();
                    break;
            }
        }
    }

    /**
     * Metoda pri prehre vytvara okienka.
     */
    public void vypisPrehry() {
        if (this.level.prehra()) {
            String spravaPrehra = "Tento level si prehral :(";

            if (this.level instanceof SvetMultiplayer) {
                spravaPrehra = "Tento level ste prehrali :(";
            }

            String[] moznosti = {"Opakovať level", "Späť do MENU"};

            var vyberHraca = JOptionPane.showOptionDialog(null, spravaPrehra, "Je mi to ľúto!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, moznosti, moznosti[0]);
            this.skryLevel();
            switch (vyberHraca) {
                case 0:
                    this.vyber = 0;
                    break;
                case 1:
                    Menu menu = new Menu();
                    break;
            }
        }
    }

    /**
     * Metoda predstavuje suhrn metod, ktore ma manazer vykonavat v metode tik, ktora je v kazom leveli odlisna.
     */
    public void spravujHru() {
        if (this.jeHraAktivovana) {
            if (!this.level.prehra() && !this.level.vyhra()) {
                this.level.posunDole();
                this.level.zbierajPredmety();
                this.level.zrazJelena();
            } else if (this.level.vyhra()) {
                this.jeHraAktivovana = false;
                this.manazer.prestanSpravovatObjekt(this);
                this.vypisVyhry();
            } else if (this.level.prehra()) {
                this.jeHraAktivovana = false;
                this.manazer.prestanSpravovatObjekt(this);
                this.vypisPrehry();
            }
        }
    }

    /**
     * Metoda poskytuje informaciu o tom, ci si hrac vybral sposob hry Multiplayer.
     * @return je hra Multiplayer
     */
    public boolean jeMultiplayer() {
        return this.multiplayer;
    }

    /**
     * Metoda poskytuje informaciu o tom, ktoru moznost si hrac vybral pri vyhre alebo prehre.
     * @return cislo moznosti
     */
    public int getVyber() {
        return this.vyber;
    }

    /**
     * Metoda zmeni cislo vyberu hraca.
     * @param vyber nove cislo vyberu
     */
    public void setVyber(int vyber) {
        this.vyber = vyber;
    }

    /**
     * Metoda skryLevel skryje vsetky vykreslene objekty.
     */
    public void skryLevel() {
        this.level.skrySvet();
    }

    /**
     * Metoda aktivuje hru po stlaceni klavesy ENTER.
     */
    public void aktivuj() {
        this.uvod.skry();
        this.jeHraAktivovana = true;
    }
}
