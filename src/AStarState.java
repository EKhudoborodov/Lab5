import java.util.HashMap;
import java.util.Map;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;
    private HashMap<Location, Waypoint> opened_waypoints = new HashMap<>();
    private HashMap<Location, Waypoint> closed_waypoints = new HashMap<>();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint() // Выбирает вершины с минималиным значением prevCost
    {
        if (opened_waypoints.size() == 0){ // если нет открытых вершин
            return null;
        }
        Waypoint min = null;
        int check=0; // для проверки
        for (Map.Entry<Location, Waypoint> i : opened_waypoints.entrySet()){ // перебор элементов HashMap
            if(check == 0){ // проверка на первую итерацию
                min = i.getValue();
                check=1;
            }
            else if(i.getValue().getPreviousCost() < min.getPreviousCost()){ // если нашлось с меньшим значением
                min = i.getValue();
            }
        }
        return min;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP) // добавить открытую вершину
    {
        if (opened_waypoints.containsKey(newWP.getLocation())){ // если есть открытая вершина с ключом newWP
            if(newWP.getPreviousCost() < opened_waypoints.get(newWP.getLocation()).getPreviousCost()){
                opened_waypoints.put(newWP.getLocation(), newWP);
                return true;
            }else{
                return false;
            }
        }else{
            opened_waypoints.put(newWP.getLocation(), newWP);
            return true;
        }
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints() // выводит количество "открытых вершин"
    {
        return opened_waypoints.size(); // посчитать размер opened_waypoints
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc) // перемещает вершину из открытых в закрытые
    {
        opened_waypoints.remove(loc); // удалить из открытых вершин
        closed_waypoints.put(loc, opened_waypoints.get(loc)); // добавить в закрытые вершины
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc) // проверить является ли вершина закрытой
    {
        return closed_waypoints.containsKey(loc);
    }
}