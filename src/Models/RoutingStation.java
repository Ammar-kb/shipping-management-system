package Models;

public class RoutingStation {
    public int id;
    public int packages;
    public int inConveyor;
    public int outConveyor;
    public RoutingStation(int stationId, int stationPackages)
    {
        id = stationId;
        packages = stationPackages;
    }
}
