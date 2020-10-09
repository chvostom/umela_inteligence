package algorithm;

import map.Coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * Třída realizující aktuální cesty v AStar algoritmu
 */
public class AStarNode {

    /**
     * Konstruktor třídy
     * @param inCoordinates - cílové souřadnice
     * @param inTrashList - seznam zbývajících odpadků
     * @param inHeuristicValue - f(x) = h(x) + g(x)
     * @param inSteps - seznam kroků cesty
     */
    public AStarNode(Coordinates inCoordinates, List<Coordinates> inTrashList, int inHeuristicValue, List<Coordinates> inSteps) {
        this.coordinates = inCoordinates;
        this.trashList = inTrashList;
        this.heuristicValue = inHeuristicValue;
        this.steps = inSteps;
    }

    /**
     * Getter, který vrací cílové souřadnice cesty
     * @return - souřadnice
     */
    public Coordinates getCoordinates() {
        return this.coordinates;
    }

    /**
     * Getter, který vrací kopii seznamu zbývající odpadků
     * @return seznam odpadků
     */
    public List<Coordinates> getTrashList() {
        return new ArrayList<>(this.trashList);
    }

    /**
     * Getter, který vrací hodnotu f(x)
     * @return f(x)
     */
    public int getHeuristicValue() {
        return this.heuristicValue;
    }

    /**
     * Getter, který vrací seznam jednotlivých kroků cesty
     * @return seznam kroků cesty
     */
    public List<Coordinates> getSteps() {
        return new ArrayList<>(this.steps);
    }

    /**
     * Getter, který vrací počet zbývajících odpadků
     * @return počet zbývajících odpadků
     */
    public int getCountOfRemainingTrash( ){
        return this.trashList.size();
    }


    private final Coordinates coordinates;
    private final List<Coordinates> trashList;
    private final int heuristicValue;
    private final List<Coordinates> steps;

}
