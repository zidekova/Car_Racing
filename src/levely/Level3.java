package levely;

/**
 * Trieda Level3 je potomkom triedy Level.
 * Predstavuje treti level hry.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class Level3 extends Level {
    /**
     * Konstruktor vyvolava konstruktor predka.
     * @param multiplayer informacia o tom, ci si hrac vybral sposob hry Multiplayer
     */
    public Level3(boolean multiplayer) {
        super(5600, "suradniceLevel3.txt", "jeleneLevel3.txt", multiplayer, 3);
    }

    /**
     * Metoda vyvolava povodnu metodu predka.
     * Navyse kontorluje, ci si hrac vybral moznost spustit dalsi level, ak ano, metoda vytvori dalsi level.
     */
    @Override
    public void vypisVyhry() {
        super.vypisVyhry();
        if (this.getVyber() == 0) {
            Level4 level4 = new Level4(this.jeMultiplayer());
            this.setVyber(-1);
        }
    }

    /**
     * Metoda vyvolava povodnu metodu predka.
     * Navyse kontorluje, ci si hrac vybral moznost opakovat level, ak ano, metoda vytvori novy level.
     */
    @Override
    public void vypisPrehry() {
        super.vypisPrehry();
        if (this.getVyber() == 0) {
            Level3 level3 = new Level3(this.jeMultiplayer());
            this.setVyber(-1);
        }
    }

    /**
     * Metoda vyvolava metodu spravujHru, ktoru spravuje manazer.
     */
    public void tikLevel3() {
        this.spravujHru();
    }
}
