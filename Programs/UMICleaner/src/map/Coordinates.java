package map;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída reprezentující jednotlivé pozice v mapě
 * Formát je 'N latitude E longitude'
 */
public class Coordinates {

    /**
     * Kontruktor třídy
     * @param inLatitude - šířka
     * @param inLongitude - délka
     */
    public Coordinates( int inLatitude, int inLongitude){
        this.latitude = inLatitude;
        this.longitude = inLongitude;
    }

    /**
     * Getter, který vrací šířku pozice
     * @return latitude - souřadnice šířky
     */
    public int getLatitude( ) {
        return this.latitude;
    }

    /**
     * Getter, který vrací délku pozice
     * @return longitude - souřadnice délky
     */
    public int getLongitude( ) {
        return this.longitude;
    }

    /**
     * Funkce vrátí seznam všech sousedních souřadnic
     * @param maxLatitude - šířka mapy
     * @param maxLongitude - délka mapy
     * @return coordinatesList - seznam sousedních souřadnic
     */
    public List<Coordinates> getAdjacentCoordinates( int maxLatitude, int maxLongitude ){
        List<Coordinates> coordinatesList = new ArrayList<>();
        if (this.latitude + 1 < maxLatitude){
            coordinatesList.add( new Coordinates(this.latitude + 1, this.longitude ) );
        }
        if (this.latitude - 1 >= 0){
            coordinatesList.add( new Coordinates(this.latitude - 1, this.longitude ) );
        }
        if (this.longitude + 1 < maxLongitude){
            coordinatesList.add( new Coordinates(this.latitude, this.longitude + 1 ) );
        }
        if (this.longitude - 1 >= 0){
            coordinatesList.add( new Coordinates(this.latitude, this.longitude - 1 ) );
        }
        return coordinatesList;
    }

    /**
     * Vrátí souřadnice jako řetězec
     * @return řetězec se souřadnicemi
     */
    public String getStringCoordinates( ){
        return "N" + this.latitude + " E" + this.longitude;
    }

    /**
     * Funkce pro porovnání souřadnic
     * @param coordinates - souřadnice, se kterými chceme porovnávat
     * @return true (shodné), nebo false (neshodné)
     */
    public boolean match( Coordinates coordinates){
        return ( ( coordinates.getLongitude( ) == this.longitude ) && ( coordinates.getLatitude( ) == this.latitude ) );
    }

    /**
     * Funkce vrátí unikátní index pro každé souřadnice
     * @param maxLongitude - délka mapy
     * @return index souřadnic
     */
    public int getCoordinatesIndex( int maxLongitude ){
        return maxLongitude * this.latitude + this.longitude;
    }

    private final int latitude;
    private final int longitude;
}
