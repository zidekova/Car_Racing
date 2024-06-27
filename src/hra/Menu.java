package hra;

import fri.shapesge.Manazer;
import fri.shapesge.Obrazok;
import levely.Level;
import levely.Level1;

import javax.swing.JOptionPane;

/**
 * Trieda Menu vytvara uvodne menu hry.
 *
 * @author (Mária Žideková)
 * @version (január 2023)
 */
public class Menu {
    private final Manazer manazer;
    private final Obrazok menu;
    private final Obrazok napoveda;
    private boolean jeNapovedaViditelna;
    private boolean jeLevelViditelny;
    private boolean jeMenuViditelne;
    private boolean multiplayer = false;

    /**
     * Konstruktor inicializuje manazera, obrazok menu a napovedy.
     */
    public Menu() {
        this.manazer = new Manazer();
        this.manazer.spravujObjekt(this);
        this.menu = new Obrazok("menu.png", 0, 0);
        this.menu.zobraz();
        this.jeMenuViditelne = true;
        this.napoveda = new Obrazok("napoveda.png", 100, 50);
    }

    /**
     * Metoda na zaklade suradnic kliknutia mysi otvori napovedu, zavrie napovedu alebo spusti prvy level.
     * @param  poziciaMysiX   suradnica kliknutia na x
     * @param  poziciaMysiY   suradnica kliknutia na y
     */
    public void vyberSuradnice(int poziciaMysiX, int poziciaMysiY) {
        // klik na napovedu
        if (poziciaMysiX >= 275 && poziciaMysiX <= 430 && poziciaMysiY >= 250 && poziciaMysiY <= 300) {
            // kontrola, ci je napoveda a level viditelny
            if (!this.jeNapovedaViditelna && !this.jeLevelViditelny) {
                this.napoveda.zobraz();
                this.jeNapovedaViditelna = true;
            }
        }

        // zatvor napovedu
        if (poziciaMysiX >= 545 && poziciaMysiX <= 590 && poziciaMysiY >= 65 && poziciaMysiY <= 110) {
            // kontrola, ci je napoveda viditelna
            if (this.jeNapovedaViditelna) {
                this.napoveda.skry();
                this.jeNapovedaViditelna = false;
            }
        }

        // vyber si singleplayer alebo multiplayer
        if (poziciaMysiX >= 250 && poziciaMysiX <= 455 && poziciaMysiY >= 175 && poziciaMysiY <= 225) {
            // kontola, ci je menu viditelne
            if (this.jeMenuViditelne && !this.jeNapovedaViditelna && !this.jeLevelViditelny) {
                String [] moznosti = {"Singleplayer", "Multiplayer"};
                var vyber = JOptionPane.showOptionDialog(null, "Chceš hrať sám alebo chcete hrať dvaja?", "Zapínam level!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, moznosti, moznosti[0]);
                switch (vyber) {
                    case 0:
                        this.multiplayer = false;
                        break;
                    case 1:
                        this.multiplayer = true;
                        break;
                }
                this.menu.skry();
                this.jeMenuViditelne = false;
                Level level = new Level1(this.multiplayer);
                this.jeLevelViditelny = true;
            }
        }
    }

    /**
     * Metoda zatvori celu aplikaciu po stlaceni klavesy ESCAPE.
     */
    public void ukonciHru() {
        System.exit(0);
    }
}
