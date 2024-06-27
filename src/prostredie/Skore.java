package prostredie;

import fri.shapesge.Text;
import fri.shapesge.FontStyle;

/**
 * Trieda Skore zobrazuje skore hracov na plochu.
 * 
 * @author (Mária Žideková) 
 * @version (máj 2023)
 */
public class Skore {
    private final Text text;
    private final Text cislo;
    private int skore;
    
    /**
     * Konstruktor vykresli skore na nami urcenu poziciu.
     * @param  polohaX   poloha textu na x
     * @param  polohaY   poloha textu na y
     */
    public Skore(int polohaX, int polohaY) {
        this.skore = 0;
        
        this.text = new Text("Skóre:", polohaX, polohaY);
        this.text.zmenFarbu("white");
        this.text.zmenFont("Skóre: " + this.skore, FontStyle.BOLD, 20);
        this.text.zobraz();
        
        this.cislo = new Text("" + this.skore, polohaX, polohaY + 20);
        this.cislo.zmenFarbu("white");
        this.cislo.zmenFont("" + this.skore, FontStyle.BOLD, 20);
        this.cislo.zobraz();
    }
    
    /**
     * Metoda getHodnotaSkore vrati aktualnu hodnotu skore.
     * @return     aktualna hodnota skore
     */
    public int getHodnotaSkore() {
        return this.skore;
    }
    
    /**
     * Metoda skrySkore skryje text z hracej plochy.
     */
    public void skrySkore() {
        this.text.skry();
        this.cislo.skry();
    }
    
    /**
     * Metoda zmenSkore zmeni hodnotu skore.
     * @param  oKolko   hodnota, o ktoru zmenime skore
     */
    public void zmenSkore(int oKolko) {
        this.skore += oKolko;
        this.cislo.changeText("" + this.skore);
    }
}
