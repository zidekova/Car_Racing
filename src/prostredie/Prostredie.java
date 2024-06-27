package prostredie;

import fri.shapesge.Obdlznik;

/**
 * Trieda Prostredie vykresli pomocou triedy Obdlznik travu, cestu a ciary.
 * 
 * @author (Mária Žideková) 
 * @version (máj 2023)
 */
public class Prostredie {
    private final Obdlznik travaVpravo;
    private final Obdlznik travaVlavo;
    private final Obdlznik cesta;
    private final Obdlznik plnaCiaraVpravo;
    private final Obdlznik plnaCiaraVlavo;
    private final Obdlznik[][] prerusovaneCiary;
    
    /**
     * Konstruktor vykresli vsetky tvary.
     * @param  velkostPlochy   velkost hracej plochy je v kazdom leveli odlisna
     */
    public Prostredie(int velkostPlochy) {
        this.travaVpravo = new Obdlznik(0, 700 - velkostPlochy);
        this.travaVpravo.zmenFarbu("green");
        this.travaVpravo.zmenStrany(80, velkostPlochy);
        this.travaVpravo.zobraz();
        
        this.travaVlavo = new Obdlznik(625, 700 - velkostPlochy);
        this.travaVlavo.zmenFarbu("green");
        this.travaVlavo.zmenStrany(80, velkostPlochy);
        this.travaVlavo.zobraz();
        
        this.cesta = new Obdlznik(80, 700 - velkostPlochy);
        this.cesta.zmenFarbu("gray");
        this.cesta.zmenStrany(545, velkostPlochy);
        this.cesta.zobraz();
        
        this.plnaCiaraVpravo = new Obdlznik(345, 700 - velkostPlochy);
        this.plnaCiaraVpravo.zmenFarbu("white");
        this.plnaCiaraVpravo.zmenStrany(5, velkostPlochy);
        this.plnaCiaraVpravo.zobraz();
        
        this.plnaCiaraVlavo = new Obdlznik(355, 700 - velkostPlochy);
        this.plnaCiaraVlavo.zmenFarbu("white");
        this.plnaCiaraVlavo.zmenStrany(5, velkostPlochy);
        this.plnaCiaraVlavo.zobraz();
        
        // prerusovane ciary sa vykreslia ako dvojrozmerne pole obdlznikov
        this.prerusovaneCiary = new Obdlznik[velkostPlochy / 100][2];
        int polohaXCiaryVlavo = 210;
        for (int i = 0; i < this.prerusovaneCiary.length; i++) {
            for (int j = 0; j < this.prerusovaneCiary[0].length; j++) {
                int posunNaX = 280 * j;
                int posunNaY = 100 * i;
                this.prerusovaneCiary[i][j] = new Obdlznik(polohaXCiaryVlavo + posunNaX , 700 - velkostPlochy + posunNaY);
                this.prerusovaneCiary[i][j].zmenFarbu("white");
                this.prerusovaneCiary[i][j].zmenStrany(5, 60);
                this.prerusovaneCiary[i][j].zobraz();
            }
        }
    }
    
    /**
     * Metoda posunDole posunie vsetky objekty zvisle o 5 pixelov.
     */
    public void posunDole() {
        this.travaVpravo.posunZvisle(5);
        this.travaVlavo.posunZvisle(5);
        this.cesta.posunZvisle(5);
        this.plnaCiaraVpravo.posunZvisle(5);
        this.plnaCiaraVlavo.posunZvisle(5);
        
        for (int i = 0; i < this.prerusovaneCiary.length; i++) {
            for (int j = 0; j < this.prerusovaneCiary[0].length; j++) {
                this.prerusovaneCiary[i][j].posunZvisle(5);
            }
        }
    }
    
    /**
     * Metoda skryProstredie skryje vsetky vykreslene objekty.
     */
    public void skryProstredie() {
        this.travaVlavo.skry();
        this.travaVpravo.skry();
        this.cesta.skry();
        this.plnaCiaraVpravo.skry();
        this.plnaCiaraVlavo.skry();
        
        for (int i = 0; i < this.prerusovaneCiary.length; i++) {
            for (int j = 0; j < this.prerusovaneCiary[0].length; j++) {
                this.prerusovaneCiary[i][j].skry();
            }
        }
    }
}
