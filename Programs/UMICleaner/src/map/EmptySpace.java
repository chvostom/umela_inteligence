package map;

/**
 * Třída rozšiřuje třídu MapItem
 * Představuje dostupný prostor v mapě bez odpadku
 */
public class EmptySpace extends MapItem {

    /**
     * Komstruktor třídy
     * @param inLatitude - šířka
     * @param inLongitude - délka
     */
    public EmptySpace(int inLatitude, int inLongitude) {
        super(inLatitude, inLongitude);
    }
}
