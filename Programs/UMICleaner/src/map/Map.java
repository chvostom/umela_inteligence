package map;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Třída reprezentující mapu zadaného plánku
 */
public class Map {

    /**
     * Konstruktor třídy inicializuje prázdnou mapu zadané velikosti
     * @param inMaxLatitude - šířka mapy
     * @param inMaxLongitude - délka mapy
     */
    public Map( int inMaxLatitude, int inMaxLongitude ){
        this.maxLatitude = inMaxLatitude;
        this.maxLongitude = inMaxLongitude;
        this.mapItems = new HashSet<>( );
        this.trashList = new ArrayList<>( );
    }

    /**
     * Metoda přidá prvek do mapy
     * @param mapItem - nový prvek mapy
     */
    public void add( MapItem mapItem ){
        int indexOfPosition = this.maxLongitude * mapItem.getLatitude( ) + mapItem.getLongitude( );
        if ( mapItem instanceof Trash ){
            this.trashList.add( mapItem.getCoordinates( ) );
                    }
        this.mapItems.add( indexOfPosition );
    }

    /**
     * Setter, který změní aktuální souřadnice robota
     * @param coordinates - souřadnice robota
     */
    public void changeRobotPosition( Coordinates coordinates){
        this.actualRobotPosition = coordinates;
    }

    /**
     * Getter, který vrátí aktuální souřadnice robota
     * @return - souřadnice robota
     */
    public Coordinates getActualRobotPosition ( ) {
        return this.actualRobotPosition;
    }

    /**
     * Getter, který vrátí šířku mapy
     * @return šířka mapy
     */
    public int getMaxLatitude() {
        return maxLatitude;
    }

    /**
     * Getter, který vrátí délku mapy
     * @return délku mapy
     */
    public int getMaxLongitude() {
        return maxLongitude;
    }

    /**
     * Funkce, která ze seznamu odstraní nedostupné souřadnice
     * @param coordinatesList - seznam souřadnic
     * @return seznam dostupných souřadnic
     */
    public List<Coordinates> removeInvalidCoordinates( List<Coordinates> coordinatesList ){
        List<Coordinates> result = new ArrayList<>();
        for ( Coordinates coord : coordinatesList ) {
            int indexOfPosition = this.maxLongitude * coord.getLatitude() + coord.getLongitude();
            if ( this.mapItems.contains( indexOfPosition ) ) {
                result.add( coord );
            }
        }
        return result;
    }

    /**
     * Funkce, který vrátí velikost mapy
     * @return velikost mapy
     */
    public int getMapSize( ){
        return this.maxLatitude * this.maxLongitude;
    }


    /**
     * Getter, který vrátí seznam odpadků
     * @return seznam odpadků
     */
    public List<Coordinates> getTrashList() {
        return this.trashList;
    }

    private Coordinates actualRobotPosition;
    private final HashSet<Integer> mapItems;
    private final List<Coordinates> trashList;
    private final int maxLatitude;
    private final int maxLongitude;
}
