package parsers;

import map.Coordinates;
import map.EmptySpace;
import map.Map;
import map.Trash;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Třída, která realizuje parsování vstupních plánků
 */
public class MapParser {

    /**
     * Konstruktor třídy převede obsah zadaného souboru do instance třídy Map
     * @param resourceName - jméno vstupního souboru ve složce resources
     * @throws URISyntaxException - výjimka vyhozená v případě zadání neexistujícího souboru
     * @throws IOException - vyjímka vyhozená při chyba I/O operací se souborem
     */
    public MapParser(String resourceName) throws URISyntaxException, IOException {
        URL res = getClass().getClassLoader().getResource(resourceName);
        File file = Paths.get(res.toURI()).toFile();
        String content = Files.readString(file.toPath(), StandardCharsets.US_ASCII);
        String[] lines = content.split("\n");
        int maxLatitude = Integer.parseInt(lines[0].split(" ")[0]);
        int maxLongitude = Integer.parseInt(lines[0].split(" ")[1]);
        this.map = new Map(maxLatitude, maxLongitude);
        for ( int i = 0 ; i < maxLatitude ; i++ ){
            String[] line = lines[i+1].split(" ");
            for ( int j = 0 ; j < maxLongitude ; j++ ){
                if ( line[j].equals("O") ) {
                    this.map.add(new EmptySpace( i ,j ));
                }
                else if ( line[j].equals("T") ){
                    this.map.add(new Trash( i ,j ));
                }
                else if ( line[j].equals("R") ){
                    this.map.changeRobotPosition( new Coordinates(i ,j ) );
                    this.map.add(new EmptySpace( i ,j ));
                }
            }
        }
    }

    /**
     * Getter, který vrátí vytvořenou instanci mapy
     * @return map - převedený soubor do mapy
     */
    public Map getMap( ) {
        return this.map;
    }

    private Map map;
}
