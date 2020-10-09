package map;

/**
 * Abstraktní třída reprezentující prvek mapy
 */
public abstract class MapItem {

    /**
     * Konstruktor třídy
     * @param inLatitude - šířka
     * @param inLongitude - délka
     */
    public MapItem( int inLatitude, int inLongitude){
        this.coordinates = new Coordinates( inLatitude, inLongitude);
    }

    /**
     * Getter, který vrátí souřadnice prvku
     * @return souřadnice
     */
    public Coordinates getCoordinates( ) {
        return this.coordinates;
    }

    /**
     * Getter, který vrátí šířku ze souřadnic
     * @return šířka
     */
    public int getLatitude( ) {
        return this.coordinates.getLatitude();
    }

    /**
     * Getter, který vrátí délku ze souřadnic
     * @return délka
     */
    public int getLongitude( ) {
        return this.coordinates.getLongitude();
    }

    private final Coordinates coordinates;
}
