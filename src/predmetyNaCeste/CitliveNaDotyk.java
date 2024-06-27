package predmetyNaCeste;

/**
 * Interface CitliveNaDotyk predstavuje predmety, ktore reaguju po dotyku s inym predmetom.
 *
 * @author (Mária Žideková)
 * @version (máj 2023)
 */
public interface CitliveNaDotyk {
    /**
     * Metoda skryPredmet skryje vykresleny obrazok.
     */
    void skryPredmet();
    /**
     * Metoda posunDole posunie obrazok predmetu zvisle.
     */
    void posunDole();
    /**
     * Metoda getPozXLava vracia polohu laveho okraja obrazku.
     * @return     lavy okraj obrazku
     */
    int getPozXLava();
    /**
     * Metoda getPozXPrava vracia polohu praveho okraja obrazku.
     * @return     pravy okraj obrazku
     */
    int getPozXPrava();
    /**
     * Metoda getPozYHorna vracia polohu horneho okraja obrazku.
     * @return     horny okraj obrazku
     */
    int getPozYHorna();
    /**
     * Metoda getPozYDolna vracia polohu dolneho okraja obrazku.
     * @return     dolny okraj obrazku
     */
    int getPozYDolna();
}
