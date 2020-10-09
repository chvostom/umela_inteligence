package algorithm;

import map.Coordinates;
import map.Map;
import java.util.ArrayList;
import java.util.List;

/**
 * Třída reprezentující algoritmus AStar
 */
public class AStar {

    /**
     * Konstruktor třídy
     * @param inMap - mapa
     */
    public AStar( Map inMap ) {
        this.map = inMap;
    }

    /**
     * Funkce, která vrátí nejlepší cestu (která je nejkratší a vyčistí celou mapu) pro robota
     * Jednotlivé kroky algoritmu jsou popsány v kódu
     * @return seznam souřadnic jednotlivých navštívených bodů
     */
    public List<Coordinates> getCleaningPlan( ){
        /*Inicializace proměnných, algoritmus pro hledání minimálních koster a prioritní fronta realizována pomocí seznamu a třídy AStarNode*/
        List<AStarNode> aStarNodes = new ArrayList<>();
        Prims prims = new Prims(this.map);
        /*Do prázdné množiny je přidána cesta nulové délky obsahující počáteční uzel*/
        aStarNodes.add(new AStarNode(this.map.getActualRobotPosition(), this.map.getTrashList(), this.map.getTrashList().size() + prims.getCostOfMinimalSpanningTree(this.map.getActualRobotPosition(), this.map.getTrashList()), new ArrayList<>()));
        /*Dokud není množina prázdná, jsou prováděny jednotlivé iterace*/
        while( !aStarNodes.isEmpty( ) ) {
            /*Je vyhledána a z množiny vyjmuta cesta s nejnižší hodnotou f(x)*/
            int minimalHeuristicValue = aStarNodes.get(0).getHeuristicValue();
            int minimumIndex = 0;
            for ( int i = 1 ; i < aStarNodes.size( ) ; i++ ) {
                if ( aStarNodes.get( i ).getHeuristicValue() < minimalHeuristicValue ) {
                    minimalHeuristicValue = aStarNodes.get( i ).getHeuristicValue();
                    minimumIndex = i;
                }
            }
            AStarNode aStarNode = aStarNodes.remove(minimumIndex);
            /*Pokud cesta končí v cílovém vrcholu (cílový vrchol = žádné zbývající odpaadky), pak je tato cesta vrácena a výpočet končí*/
            if ( aStarNode.getCountOfRemainingTrash() == 0 ){
                return aStarNode.getSteps();
            }
            /*Jinak jsou vytvořeny nové cesty přidáním dalších dostupných vrcholů do vyjmuté cesty*/
            for ( Coordinates coord : this.map.removeInvalidCoordinates(aStarNode.getCoordinates().getAdjacentCoordinates(this.map.getMaxLatitude(), this.map.getMaxLongitude()))){
                boolean insertNewNode = true;
                List<Coordinates> newTrashList = aStarNode.getTrashList();
                /*Pokud se na novém koncovém vrcholu nachází odpadky, pak jsou tyto odpadky odstraněny*/
                for ( int i = 0 ; i < newTrashList.size( ) ; i++ ) {
                    if ( coord.match(newTrashList.get(i)) ){
                        newTrashList.remove(i);
                        break;
                    }
                }
                /*Je spočítáno f(x) = h(x) + g(x), kde h(x) je heuristická funkce (minimální kostra z podgrafu odpadků a aktuální pozice) a g(x) je délka cesty + počet zbývajících odpadků*/
                int newHeuristicValue = aStarNode.getCountOfRemainingTrash() + aStarNode.getSteps().size() + prims.getCostOfMinimalSpanningTree(coord, newTrashList);
                List<Coordinates> newSteps = aStarNode.getSteps();
                newSteps.add(coord);
                /*Jestliže více cest končí ve stejném vrcholu, pak jsou smazány ty s nejnižší hodnotou f(x) */
                for ( int i = 0 ; i < aStarNodes.size( ) ; i++ ){
                    if ( aStarNodes.get(i).getCoordinates().match(coord)){
                        if ( aStarNodes.get(i).getHeuristicValue() > newHeuristicValue ) {
                            aStarNodes.remove(i);
                        }
                        else {
                            insertNewNode = false;
                        }
                    }
                }
                /*Pokud v množině není lepší cesta končící ve stejném vrcholu, pak je nová cesta přidána*/
                if ( insertNewNode ) {
                    aStarNodes.add(new AStarNode(coord, newTrashList, newHeuristicValue, newSteps));
                }
            }
        }
        /*Je-li množina prázdná, pak je vrácena prázdná množina značící neexistenci řešení*/
        return new ArrayList<>();
    }

    private final Map map;

}
