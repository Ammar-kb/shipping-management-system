package ConveyorSystem;

import Config.SystemConfiguration;
import Models.Conveyor;
import Models.RoutingStation;
import Models.RunnableRoutingStation;

import java.util.ArrayList;

public class SystemDriver {
    public static void main(String [] args)
    {
        SystemConfiguration  config =  new SystemConfiguration("/home/santanak/Git/Shipping-Management-System/src/Config/ConveyorInfo.txt");
        Conveyor[] conveyors = new Conveyor[config.get_NumberOfConveyors()];

        for(int j = 0; j < config.get_NumberOfConveyors(); j++) {
            conveyors[j] = new Conveyor(j);
        }
        ArrayList<Thread> stations = initializeConfig(config, conveyors);

        for(Thread rrs : stations)
        {
            rrs.start();
        }


    }
    public static ArrayList<Thread> initializeConfig(SystemConfiguration config, Conveyor [] conveyors)
    {
        ArrayList<Thread> threads = new ArrayList<>();

        for(int i = 0; i < config.get_NumberOfStations(); i++) {
            RunnableRoutingStation rrs = new  RunnableRoutingStation(new RoutingStation(i, config.get_Packages(i)), conveyors);

            if(i == 0) {
                rrs.station.inConveyor = 0;
                rrs.station.outConveyor = config.get_NumberOfStations() - 1;
            } else {
                rrs.station.inConveyor = i - 1;
                rrs.station.outConveyor = i;
            }
            Thread thread = new Thread(rrs);
            threads.add(thread);
        }
        return threads;
    }

}
