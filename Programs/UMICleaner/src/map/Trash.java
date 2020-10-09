package map;

/**
 * Třída rozšiřuje třídu MapItem
 * Představuje dostupný prostor v mapě s odpadky
 */
public class Trash extends MapItem {

    /**
     * Komstruktor třídy
     * @param inLatitude - šířka
     * @param inLongitude - délka
     */
    public Trash(int inLatitude, int inLongitude) {
        super(inLatitude, inLongitude);
    }
}
