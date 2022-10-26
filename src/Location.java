/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{

    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    public boolean equals(Object object) {
        if(this == object) return true;
        if(getClass() != object.getClass()) return false;
        Location s = (Location) object;
        return (xCoord==s.xCoord && yCoord==s.yCoord);
    }

    public int hashCode(){
        return 38*xCoord+yCoord;
    }
}