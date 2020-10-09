package algorithm;

import map.Coordinates;
import map.Map;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída realizující Jarníkův algoritmus na hledání minimální kostry
 */
public class Prims {

    /**
     * Konstruktor třídy si inicializuje BFS třídu
     * @param inMap - mapa
     */
    public Prims( Map inMap ){
        bfs = new BFS(inMap);
    }

    /**
     * Funkce vypočte minimální kostru a vrátí součet všech jejích ohodnocených hran
     * Popis jednotlivých kroků algoritmu je v kódu
     * @param actualPosition - Pozice robota - jeden z vrcholů grafu
     * @param inTrashList - List odpadků - další vrcholy grafu
     * @return velikost minimální kostry
     */
    public int getCostOfMinimalSpanningTree(Coordinates actualPosition, List<Coordinates> inTrashList){
        /*Inicializace proměnných přidá pozici robota mezi ostatní vrcholy a vytvoří pole pro jednotlivé hrany*/
        int costOfMinimalSpanningTree = 0;
        List<Coordinates> trashList = new ArrayList<>(inTrashList);
        trashList.add(actualPosition);
        int[][] edges = new int[trashList.size()][trashList.size()];
        for ( int i = 0 ; i < trashList.size( ) ; i++ ){
            for ( int j = i ; j < trashList.size() ; j++){
                /*Vzdálenost (ohodnocení) vrcholů je vypočtena pomocí BFS algoritmu*/
                edges[i][j] = bfs.getShortestDistance(trashList.get(i), trashList.get(j));
            }
        }
        List<Integer> visited = new ArrayList<>();
        visited.add(0);
        /*Dokud nejsou v kostře všechny vrcholy jsou, prováděny další iterace*/
        while( visited.size( ) != trashList.size( ) ){
            int minimum = -1 ;
            int minimumNode = -1;
            /*Je nalezena nejmenší hrana, která vede do nenavštíveného vrcholu a přidá ji do kostry*/
            for ( Integer node : visited ) {
                for ( int i = 0 ; i < trashList.size( ) ; i++  ) {
                    if ( !visited.contains( i ) ) {
                        if ( minimum != getMin( minimum, edges[getMin(node, i)][getMax(node, i)])){
                            minimumNode = i;
                            minimum = getMin( minimum, edges[getMin(node, i)][getMax(node, i)]);
                        }
                    }
                }
            }
            costOfMinimalSpanningTree += minimum;
            visited.add(minimumNode);
        }
        /*Vrátí velikost minimální kostry*/
        return costOfMinimalSpanningTree;
    }

    /**
     * Funkce vrátí minimum ze zadaných hodnot s tím, že ignoruje inicializovanou hodnotu -1
     * @param number1 - první číslo
     * @param number2 - druhé číslo
     * @return minimum
     */
    private int getMin( int number1, int number2 ){
        if ( number1 == -1 ){
            return number2;
        }
        else {
            return Math.min(number1, number2);
        }
    }

    /**
     * Vrátí maximum ze zadaných čísel
     * @param number1 - první číslo
     * @param number2 - druhé číslo
     * @return maximum
     */
    private int getMax( int number1, int number2 ){
        return Math.max( number1, number2 );
    }

    private final BFS bfs;
}
