package levely;

/**
 * Trieda Level1 je potomkom triedy Level.
 * Predstavuje prvy level hry.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class Level1 extends Level {
    /**
     * Konstruktor vyvolava konstruktor predka.
     * @param multiplayer informacia o tom, ci si hrac vybral sposob hry Multiplayer
     */
    public Level1(boolean multiplayer) {
        super(2800, "suradniceLevel1.txt", "jeleneLevel1.txt", multiplayer, 1);
    }


    /**
     * Metoda vyvolava povodnu metodu predka.
     * Navyse kontorluje, ci si hrac vybral moznost spustit dalsi level, ak ano, metoda vytvori dalsi level.
     */
    @Override
    public void vypisVyhry() {
        super.vypisVyhry();
        if (this.getVyber() == 0) {
            Level2 level2 = new Level2(this.jeMultiplayer());
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
            Level1 level1 = new Level1(this.jeMultiplayer());
            this.setVyber(-1);
        }
    }


    /**
     * Metoda vyvolava metodu spravujHru, ktoru spravuje manazer.
     */
    public void tikLevel1() {
        this.spravujHru();
    }
}
