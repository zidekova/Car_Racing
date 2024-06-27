package levely;

/**
 * Trieda Level2 je potomkom triedy Level.
 * Predstavuje druhy level hry.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class Level2 extends Level {
    /**
     * Konstruktor vyvolava konstruktor predka.
     * @param multiplayer informacia o tom, ci si hrac vybral sposob hry Multiplayer
     */
    public Level2(boolean multiplayer) {
        super(4200, "suradniceLevel2.txt", "jeleneLevel2.txt", multiplayer, 2);
    }

    /**
     * Metoda vyvolava povodnu metodu predka.
     * Navyse kontorluje, ci si hrac vybral moznost spustit dalsi level, ak ano, metoda vytvori dalsi level.
     */
    @Override
    public void vypisVyhry() {
        super.vypisVyhry();
        if (this.getVyber() == 0) {
            Level3 level3 = new Level3(this.jeMultiplayer());
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
            Level2 level2 = new Level2(this.jeMultiplayer());
            this.setVyber(-1);
        }
    }

    /**
     * Metoda vyvolava metodu spravujHru, ktoru spravuje manazer.
     */
    public void tikLevel2() {
        this.spravujHru();
    }
}
