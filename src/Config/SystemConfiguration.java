package Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SystemConfiguration {
    private int _numberOfStations;
    private int [] _stationPackage;

    public SystemConfiguration(String fileName)
    {
        String line;
        boolean firstLine = true;
        int lineNumber = 0;
        try
        {

            FileReader fr = new FileReader(fileName);

            BufferedReader br = new BufferedReader(fr);


            while((line = br.readLine()) != null)
            {
                if(firstLine)
                {
                    _numberOfStations = Integer.valueOf(line);
                    _stationPackage = new int [_numberOfStations];
                    firstLine  = false;
                    continue;
                }
                _stationPackage[lineNumber] = Integer.valueOf(line);
                lineNumber++;

            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }


    }
    public int get_NumberOfStations()
    {
        return _numberOfStations;
    }
    public int get_NumberOfConveyors() { return _numberOfStations; }
    public int get_Packages(int position)
    {
        if (position >= _numberOfStations) return -1;

        return _stationPackage[position];
    }
}
