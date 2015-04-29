package BPP;

import java.util.ArrayList;

public class VolledigeEnummeratie
{
    private ArrayList<Artikel> artikellijst;
    private ArrayList<Pakket> vollePakketten;
    private ArrayList<Pakket> actievePakketten;
    private ArrayList<Pakket> recordPakketLijst;
    private ArrayList<String> richtingArray;
    private int record;
    private int mogelijkheid = 0;
    private boolean besteGevonden;

    public VolledigeEnummeratie(ArrayList<Artikel> pl)
    {
        artikellijst = new ArrayList<>();
        actievePakketten = new ArrayList<>();
        recordPakketLijst = new ArrayList<>();
        vollePakketten = new ArrayList<>();
        richtingArray = new ArrayList<>();

        artikellijst = pl;

        actievePakketten.add(new Pakket());
        actievePakketten.add(new Pakket());

        record = artikellijst.size();

    }

    public void vul()
    {

        // Record bekijken
        if (allesGeplaatst())
        {
            // Mogelijkheid teller
            mogelijkheid++;

            // Kijken hoeveel pakketten er gevuld zijn met deze mogelijkheid
            int gevuldePakketten = aantalGevuld();

            // Lijn aan het begin van een container
            System.out.println("______________________________________________________");
            System.out.println("Combinatie: " + mogelijkheid);
            System.out.println("______________________________________________________");
            System.out.println("Record aantal pakketten: " + record);
            System.out.println(" Huidig aantal gevulde pakketten: " + gevuldePakketten);

            // Checken of het een record is
            checkRecord(gevuldePakketten);

            // Stippellijn onder record en huidig gevulde pakketten
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - -");

            for (Pakket p : recordPakketLijst)
            {
                System.out.println("PAKKETTEN: " + p.getNummer());
                System.out.println("Inhoud");
                int artikelnr = 1;
                for (Artikel a : p.getInhoudPakket())
                {
                    System.out.println("Artikel " + artikelnr + " hoogte: " + a.getHoogte());
                    artikelnr++;
                }
            }
        }

        for (Artikel a : artikellijst)
        {
            if (!a.isGeplaatst())
            {
                int teller = 1;
                for (Pakket p : actievePakketten)
                {
                    if (p.getOvergeblevenHoogte() >= a.getHoogte())
                    {

                        p.voegArtikelToe(a);
                        a.setGeplaatst(true);
                        vul();
                        
                        
                        p.verwijder(a);
                        vollePakketten.remove(p);
                        a.setGeplaatst(false);
                    }
                    else
                    {
                        if(teller == 1)
                        {
                            teller++;
                        }
                        else if (teller==2)
                        {
                            int overgeblevenPakketEen = actievePakketten.get(0).getOvergeblevenHoogte();
                            int overgeblevenPakketTwee = actievePakketten.get(1).getOvergeblevenHoogte();
                            
                            if(overgeblevenPakketEen > overgeblevenPakketTwee)
                            {
                                vollePakketten.add(actievePakketten.get(0));
                                richtingArray.add("links");
                                actievePakketten.set(0, new Pakket());
                                vul();
                            }
                            else
                            {
                                vollePakketten.add(actievePakketten.get(1));
                                richtingArray.add("rechts");
                                actievePakketten.set(1, new Pakket());
                                vul();
                            }
                            teller = 1;
                        }
                    }
                    
                }

//                for (Container c : artikellijst)
//                {
//                    
//                    if (c.getOvergeblevenHoogte() >= p.getHoogte())
//                    {
//
//                        c.voegPakketToe(p);
//                        p.setGeplaatst(true);
//                        vul();
//                        
//                        
//                        c.verwijder(p);
//                        p.setGeplaatst(false);
//                    }
//                    
//                }
            }

        }

    }

    public boolean allesGeplaatst()
    {
        boolean b = true;
        for (Artikel a : artikellijst)
        {
            if (!a.isGeplaatst())
            {
                b = false;
            }
        }
        return b;
    }

    public int aantalGevuld()
    {
        int gevuldePakketten = 0;
       
        for (Pakket p : actievePakketten)
        {
            if (p.getOvergeblevenHoogte() < p.getHoogte())
            {
                gevuldePakketten++;
            }
        }
        gevuldePakketten = gevuldePakketten + vollePakketten.size();

        return gevuldePakketten;

    }

    public void checkRecord(int i)
    {
        // Check of er een record is, zo ja welke
        if (i < record)
        {
            record = i;
            recordPakketLijst = vollePakketten;
            for(Pakket p : actievePakketten)
            {
                recordPakketLijst.add(p);
            }
            
            System.out.println("Nieuw Record, inhoud per pakket");
            for (Pakket p : recordPakketLijst)
            {
                System.out.println("Pakket: " + p.getNummer());
                System.out.println("Inhoud");
                int artikelnr = 1;
                for (Artikel a : p.getInhoudPakket())
                {
                    System.out.println("Pakket " + artikelnr + " hoogte: " + a.getHoogte());
                    artikelnr++;
                }
            }
            for (Pakket p : actievePakketten)
            {
                System.out.println("Pakket: " + p.getNummer());
                System.out.println("Inhoud");
                int artikelnr = 1;
                for (Artikel a : p.getInhoudPakket())
                {
                    System.out.println("Pakket " + artikelnr + " hoogte: " + a.getHoogte());
                    artikelnr++;
                }
            }
        }

    }
}
