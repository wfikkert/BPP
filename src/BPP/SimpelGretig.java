package BPP;

import java.util.ArrayList;

public class SimpelGretig extends Algoritme
{

    private ArrayList<Artikel> pakketlijst;
    private ArrayList<Pakket> containerlijst;
    private ArrayList<String> richtingArray;
    private int hoeveelsteContainer = 0;
    private Pakket huidigeContainer;

    public SimpelGretig(ArrayList<Artikel> pl)
    {
        pakketlijst = new ArrayList<>();
        containerlijst = new ArrayList<>();
        richtingArray = new ArrayList<>();

        pakketlijst = pl;
    }

    public ArrayList<String> berekenVolgorde()
    {
        // Container aanmaken bij aanroepen van de functie
        maakContainer();

        // Pakketlijst array doorlopen
        for (int teller = 0; teller < pakketlijst.size(); teller++)
        {

            //Pakket en hoogte ervan opslaan in een variabele
            Artikel pakket = pakketlijst.get(teller);
            int hoogtePakket = pakket.getHoogte();

            // Overgebleven hoogte van de container uitrekenen
            int hoogteOverContainer = containerlijst.get(hoeveelsteContainer - 1).getOvergeblevenHoogte();

            // Kijken of de huidige container genoeg ruimte heeft
            if (hoogtePakket <= hoogteOverContainer)
            {
                richtingArray.add("Links");
                huidigeContainer.voegPakketToe(pakket);
            }

            // Zo niet, nieuwe container maken
            else
            {
                maakContainer();
                hoogteOverContainer = containerlijst.get(hoeveelsteContainer - 1).getOvergeblevenHoogte();
                if (hoogtePakket <= hoogteOverContainer)
                {
                    richtingArray.add("Rechts");
                    huidigeContainer.voegPakketToe(pakket);
                }

                else
                {
                    System.out.println("Containers vol!");

                }
            }

        }
        System.out.println(richtingArray.toString());
        return richtingArray;
    }

    public void maakContainer()
    {
        Pakket container = new Pakket();
        containerlijst.add(container);
        hoeveelsteContainer++;
    }
    
    public void soutrichtingArray()
    {
        for(String s : richtingArray)
        {
            System.out.println(s);
        }
    }

}
