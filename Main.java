import core.data.*;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        DataSource dsgo = DataSource.connect("/Users/mgoldzeo/Downloads/CSA_377_DataLab_StudentFiles/DataLab_Code/lib/data.csv");
        dsgo.setCacheTimeout(15*60);
        dsgo.load();

        ArrayList<Obs2> obgo = dsgo.fetchList("Obs2","Year", "Month", "Tornadoes", "Fatalities");
        det(obgo);
        System.out.println("The overall trend is shown to be a net growth of " + obgo.get(obgo.size()-1).growth + " tornadoes and a danger level of " + obgo.get(obgo.size()-1).danglev + " fatalities.");
    }
    public static void det(ArrayList<Obs2> ar)
    {
        for (int n = 0; n < ar.size()-1; n++)
        {
            Obs2 obs1 = ar.get(n);
            Obs2 obs2 = ar.get(n+1);
            int tempgro = obs2.tornadoes - obs1.tornadoes;
            int tempfat = obs2.fatalities - obs1.fatalities;
            obs2.growth += tempgro;
            obs2.danglev += tempfat;
        }
    }
}

class Obs2
{
    int year;
    int month;
    int tornadoes;
    int fatalities;
    public static int growth;
    public static int danglev;

    Obs2(int y, int m, int t, int f)
    {
        this.year = y;
        this.month = m;
        this.tornadoes = t;
        this.fatalities = f;
    }


    @Override
    public String toString()
    {
        return("In month " + month + " of " + year + ", " + tornadoes + " tornadoes killed " + fatalities + " people.");
    }
}
