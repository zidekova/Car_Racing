package predmetyNaCeste;

/**
 * Enum ObrAuta predstavuje vsetky nazvy obrazkov aut
 * 
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public enum ObrAuta {
    HRAC("zlteAuto.png"),
    PROTIHRAC("tyrkysoveAuto.png"),
    // auta v smere jazdy
    BIELE_S("bieleAutoS.png"),
    CERVENE_S("cerveneAutoS.png"),
    MODRE_S("modreAutoS.png"),
    ZELENE_S("zeleneAutoS.png"),
    // auta v protismere
    BIELE_P("bieleAutoP.png"),
    CERVENE_P("cerveneAutoP.png"),
    MODRE_P("modreAutoP.png"),
    ZELENE_P("zeleneAutoP.png");
    
    private final String subor;
    
    ObrAuta(String subor) {
        this.subor = subor;
    }
    
    /**
     * Metoda getNazovSuboru vracia cestu k suboru.
     * @return     cesta k suboru
     */
    public String getNazovSuboru() {
        return this.subor;
    }
}