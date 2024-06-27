package levely;

/**
 * Trieda Level5 je potomkom triedy Level.
 * Predstavuje piaty level hry.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public class Level5 extends Level {
    /**
     * Konstruktor vyvolava konstruktor predka.
     * @param multiplayer informacia o tom, ci si hrac vybral sposob hry Multiplayer
     */
    public Level5(boolean multiplayer) {
        super(8400, "suradniceLevel5.txt", "jeleneLevel5.txt", multiplayer, 5);
    }

    /**
     * Metoda vyvolava povodnu metodu predka.
     * Navyse kontorluje, ci si hrac vybral moznost ukoncit hru, ak ano, hra sa ukonci.
     */
    public void vypisVyhry() {
        super.vypisVyhry();
        if (this.getVyber() == 0) {
            System.exit(0);
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
            Level5 level5 = new Level5(this.jeMultiplayer());
            this.setVyber(-1);
        }
    }

    /**
     * Metoda vyvolava metodu spravujHru, ktoru spravuje manazer.
     */
    public void tikLevel5() {
        this.spravujHru();
    }
}
