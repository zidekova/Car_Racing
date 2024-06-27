package levely;

/**
 * Trieda Level4 je potomkom triedy Level.
 * Predstavuje stvrty level hry.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class Level4 extends Level {
    /**
     * Konstruktor vyvolava konstruktor predka.
     * @param multiplayer informacia o tom, ci si hrac vybral sposob hry Multiplayer
     */
    public Level4(boolean multiplayer) {
        super(7000, "suradniceLevel4.txt", "jeleneLevel4.txt", multiplayer, 4);
    }

    /**
     * Metoda vyvolava povodnu metodu predka.
     * Navyse kontorluje, ci si hrac vybral moznost spustit dalsi level, ak ano, metoda vytvori dalsi level.
     */
    @Override
    public void vypisVyhry() {
        super.vypisVyhry();
        if (this.getVyber() == 0) {
            Level5 level5 = new Level5(this.jeMultiplayer());
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
            Level4 level4 = new Level4(this.jeMultiplayer());
            this.setVyber(-1);
        }
    }

    /**
     * Metoda vyvolava metodu spravujHru, ktoru spravuje manazer.
     */
    public void tikLevel4() {
        this.spravujHru();
    }
}
