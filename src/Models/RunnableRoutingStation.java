package Models;

public class RunnableRoutingStation implements Runnable {

    public RoutingStation  station;
    public Conveyor [] conveyors;
    public RunnableRoutingStation(RoutingStation rs, Conveyor [] conveyors)
    {
        this.conveyors = conveyors;
        station = rs;
    }
    @Override
    public void run()
    {
        Conveyor in  = conveyors[station.inConveyor];
        Conveyor out  = conveyors[station.outConveyor];

        System.out.printf("routing station %d in conveyor %d\n", station.id, station.inConveyor);
        System.out.printf("routing station %d out conveyor %d\n", station.id, station.outConveyor);
        System.out.printf("routing station %d workload: %d\n", station.id, station.packages);

        while(station.packages > 0) {
            if(!in.beingUsed) {
                System.out.printf("routing station %d took control of conveyor %d\n", station.id, station.inConveyor);
                in.beingUsed = true;
            } else {
                continue;
            }
            if(!out.beingUsed) {
                System.out.printf("routing station %d took control of conveyor %d\n", station.id, station.outConveyor);
                out.beingUsed = true;
            } else {
                System.out.printf("routing station %d let go of conveyor %d\n", station.id, station.inConveyor);
                in.beingUsed  = false;
                continue;
            }
            station.packages--;
            System.out.printf("moved packages from conveyor %d\n",station.inConveyor);
            System.out.printf("moved packages from conveyor %d\n",station.outConveyor);
            in.beingUsed = false;
            System.out.printf("routing station %d let go of conveyor %d\n", station.id, station.inConveyor);
            out.beingUsed  = false;
            System.out.printf("routing station %d let go of conveyor %d\n", station.id, station.outConveyor);
            System.out.printf("%d packages left\n", station.packages);
        }
        System.out.printf("Routing station %d finished workload!\n",station.id);
    }
}
