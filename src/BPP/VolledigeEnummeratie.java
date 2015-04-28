package BPP;

import java.util.ArrayList;

public class VolledigeEnummeratie
{
    private ArrayList<Pakket> pakketlijst;
    private ArrayList<Container> containerlijst;
    private int record;
    private ArrayList<Container> recordContainerLijst;
    private int mogelijkheid = 0;
    private boolean besteGevonden;

    public VolledigeEnummeratie(ArrayList<Pakket> pl)
    {
        pakketlijst = new ArrayList<>();
        containerlijst = new ArrayList<>();
        recordContainerLijst = new ArrayList<>();

        pakketlijst = pl;
        int containerTeller = 1;

        for (Pakket p : pakketlijst)
        {
            Container c = new Container(containerTeller);
            containerlijst.add(c);
            containerTeller++;
        }
        record = pakketlijst.size();

    }

    public void vul()
    {

        // Record bekijken
        if (allesGeplaatst())
        {
            // Mogelijkheid teller
            mogelijkheid++;

            // Kijken hoeveel containers er gevuld zijn met deze mogelijkheid
            int gevuldeContainers = aantalGevuld();

            // Lijn aan het begin van een container
            System.out.println("______________________________________________________");
            System.out.println("Combinatie: " + mogelijkheid);
            System.out.println("______________________________________________________");
            System.out.println("Record aantal containers: " + record);
            System.out.println(" Huidig aantal gevulde containers: " + gevuldeContainers);

            // Checken of het een record is
            checkRecord(gevuldeContainers);

            // Stippellijn onder record en huidig gevulde containers
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }

       
            for (Pakket p : pakketlijst)
            {
                if (!p.isGeplaatst())
                {
                    for (Container c : containerlijst)
                    {
                        if (c.getOvergeblevenHoogte() >= p.getHoogte())
                        {

                            c.voegPakketToe(p);
                            p.setGeplaatst(true);
                            vul();

                            c.verwijder(p);
                            p.setGeplaatst(false);
                        }
                    }
                }

            }
        
    }

    public boolean allesGeplaatst()
    {
        boolean b = true;
        for (Pakket p : pakketlijst)
        {
            if (!p.isGeplaatst())
            {
                b = false;
            }
        }
        return b;
    }

    public int aantalGevuld()
    {
        int gevuldeContainers = 0;
        for (Container c : containerlijst)
        {
//            System.out.println("CONTAINER " + c.getNummer() + ": Overgebleven hoogte:" + c.getOvergeblevenHoogte() + "< (kleiner dan) max. hoogte: " + c.getHoogte());
            if (c.getOvergeblevenHoogte() < c.getHoogte())
            {
                gevuldeContainers++;
            }
        }

        return gevuldeContainers;

    }

    public void checkRecord(int i)
    {
        // Check of er een record is, zo ja welke
        if (i < record)
        {
            record = i;
            recordContainerLijst = containerlijst;
            System.out.println("Nieuw Record, inhoud per container");
            for (Container c : recordContainerLijst)
            {
                System.out.println("CONTAINER: " + c.getNummer());
                System.out.println("Inhoud");
                int pakketnr = 1;
                for (Pakket p : c.getInhoudContainer())
                {
                    System.out.println("Pakket " + pakketnr + " hoogte: " + p.getHoogte());
                    pakketnr++;
                }
            }
        }

    }
}
