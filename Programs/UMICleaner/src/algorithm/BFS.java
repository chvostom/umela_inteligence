package algorithm;

import map.Coordinates;
import map.Map;
import java.util.*;

/**
 * Třída realizující algoritmus BFS
 */
public class BFS {

    /**
     * Konstruktor třídy
     * @param inMap - mapa
     */
    public BFS(Map inMap){
        this.map = inMap;
    }

    /**
     * Funkce, která vrátí nejkratší vzdálenost mezi dvěma zadanými souřadnicemi
     * Vysvětlení algoritmu je v kódu
     * @param startCoordinates - souřadnice počátečního bodu
     * @param stopCoordinates - souřadnice koncového bodu
     * @return nejkratší vzdálenost nebo -1 v případě nedosažitelnosti
     */
    public int getShortestDistance(Coordinates startCoordinates, Coordinates stopCoordinates){
        /*Pokud je start zároveň i cíl, vrátí vzdálenost 0*/
        if ( startCoordinates.match(stopCoordinates) ){
            return 0;
        }
        /*Inicializace proměnných (pole návštěv vrcholů, pole nejkratších vzdáleností)*/
        boolean found = false;
        boolean[] visited = new boolean[map.getMapSize()];
        int[] distances = new int[map.getMapSize()];
        for ( int i = 0 ; i < visited.length ; i++){
            visited[i] = false;
            distances[i] = 0;
        }
        /*Inicializace fronty*/
        Queue<Coordinates> queue = new LinkedList<>();
        visited[startCoordinates.getCoordinatesIndex(map.getMaxLongitude())] = true;
        queue.add(startCoordinates);
        /*Dokud není fronta prázdná a dokud není nalezen cílový vrchol, provádí algoritmus další iterace*/
        while ( ( !queue.isEmpty( ) ) && ( !found ) ) {
            /*Vyjme vrchol z fronty*/
            Coordinates actualPosition = queue.poll();
            /*Všechny následující dostupné dosud nenavštívené vrcholy přidá do fronty a aktualizuje pole visted a distances*/
            for ( Coordinates coord : map.removeInvalidCoordinates(actualPosition.getAdjacentCoordinates( map.getMaxLatitude( ), map.getMaxLongitude( ) )) ){
                if (!visited[coord.getCoordinatesIndex(map.getMaxLongitude())]){
                    visited[coord.getCoordinatesIndex(map.getMaxLongitude())] = true;
                    distances[coord.getCoordinatesIndex(map.getMaxLongitude())] = distances[actualPosition.getCoordinatesIndex(map.getMaxLongitude())] + 1;
                    queue.add(coord);
                    if ( coord.match(stopCoordinates)){
                        found = true;
                    }
                }
            }
        }
        /*Po skončení všech iterací vrátí nejkratší vzdálenost nebo -1 v případě, že hledaný vrchol nenalezne*/
        if ( found ){
            int stopIndex = stopCoordinates.getCoordinatesIndex(map.getMaxLongitude());
            return distances[stopIndex];
        }
        else{
            return -1;
        }
    }

    private final Map map;
}
