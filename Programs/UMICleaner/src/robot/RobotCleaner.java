package robot;

import algorithm.AStar;
import algorithm.BFS;
import map.Coordinates;
import map.Map;
import parsers.MapParser;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Třída reprezentující vysávajícího robota
 */
public class RobotCleaner {

    /**
     * Konstruktor třídy inicializuje některé proměnné a použije parser pro získání mapy
     * @param planResource - název souboru ve složce resources
     */
    public RobotCleaner( String planResource ) {
        this.cleanCommands = new LinkedList<>();
        this.noSolutionFlag = false;
        try {
            MapParser mapParser = new MapParser(planResource);
            this.map = mapParser.getMap();
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    /**
     * Metoda nejprve zkontroluje pomocí BFS, zda jsou všechny odpadky dosažitelné
     * Následně pomocí AStar algoritmu najde nejkratší cestu v plánku, na které leží všechny odpadky
     * Nakonec připraví jednotlivé operace do fronty cleanCommands
     */
    public void computeCleaningPlan( ){
        BFS bfs = new BFS( map );
        for ( Coordinates coordinates : this.map.getTrashList( ) ){
            if ( bfs.getShortestDistance( this.map.getActualRobotPosition(), coordinates ) == -1 ){
                this.noSolutionFlag = true;
            }
        }
        if ( !noSolutionFlag ) {
            AStar aStar = new AStar(this.map);
            this.cleaningPlan = aStar.getCleaningPlan();
            List<Coordinates> trashList = this.map.getTrashList();
            this.remainingTrash = trashList.size();
            for ( Coordinates coordinates : this.cleaningPlan){
                boolean foundTrash = false;
                for ( int i = 0 ; i < trashList.size() ; i++ ){
                    if ( trashList.get( i ).match( coordinates ) ){
                        foundTrash = true;
                        trashList.remove( i );
                    }
                }
                cleanCommands.add(foundTrash);
            }
        }
    }

    /**
     * Metoda simuluje jednotlivé operace robota
     * Robot postupně projde mapou a uklidí odpadky
     * @throws NullPointerException - vyjmutí prvku z fronty může vyhodit vyjímku (jen je to nepravděpodobné)
     */
    public void cleanArea( ) throws NullPointerException{
        if ( noSolutionFlag ){
            System.out.println("Map contains unreachable trash! Cleaning can't start!");
            return;
        }
        System.out.println("Starting at position " + this.map.getActualRobotPosition( ).getStringCoordinates() + "..." );
        for ( Coordinates coordinates : this.cleaningPlan ) {
            System.out.println("Moving to position " + coordinates.getStringCoordinates() + "..." );
            if (this.cleanCommands.pollFirst()){
                this.remainingTrash--;
                System.out.println("Area cleaned! There are still " + this.remainingTrash +  " trash left...");
            }
        }
        System.out.println("Work is done! Traveled distance was " + this.cleaningPlan.size() + ".");
    }

    private Map map;
    private List<Coordinates> cleaningPlan;
    private final Deque<Boolean> cleanCommands;
    private int remainingTrash;
    private boolean noSolutionFlag;
}
