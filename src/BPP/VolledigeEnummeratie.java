package BPP;

import java.util.ArrayList;

public class VolledigeEnummeratie
{
    private ArrayList<Pakket> pakketlijst;
    private ArrayList<Container> containerlijst;
    private int record;
    private ArrayList<Container> recordContainerLijst;
    private int mogelijkheid = 0;

    public VolledigeEnummeratie(ArrayList<Pakket> pl)
    {
        pakketlijst = new ArrayList<>();
        containerlijst = new ArrayList<>();
        recordContainerLijst = new ArrayList<>();

        System.out.println(record);

        pakketlijst = pl;
        int containerTeller = 1;

        for (Pakket p : pakketlijst)
        {
            Container c = new Container(containerTeller);
            containerlijst.add(c);
            containerTeller++;
        }
        record = pakketlijst.size();
//        System.out.println(pakketlijst.toString());
//        System.out.println(containerlijst.toString());

    }

    public void vul()
    {

        // Record bekijken
        if (allesGeplaatst())
        {
            int gevuldeContainers = aantalGevuld();
            System.out.println("______________________________________________________");
            System.out.println("RECORD: " + record + " GEVULD: " + gevuldeContainers);

            checkRecord(gevuldeContainers);

            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");
        }

        for (Pakket p : pakketlijst)
        {
//            System.out.println(p.toString());
            if (!p.isGeplaatst())
            {
                for (Container c : containerlijst)
                {
//                    System.out.println(c.toString());
                    if (c.getOvergeblevenHoogte() >= p.getHoogte())
                    {
                        mogelijkheid++;
                        System.out.println("Combinatie " + mogelijkheid);
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
            System.out.println("CONTAINER " + c.getNummer() + ": Overgebleven hoogte:" + c.getOvergeblevenHoogte() + "< (kleiner dan) max. hoogte: " + c.getHoogte());
            if (c.getOvergeblevenHoogte() < c.getHoogte())
            {
                gevuldeContainers++;
            }
        }

        return gevuldeContainers;

    }

    public void checkRecord(int i)
    {
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
