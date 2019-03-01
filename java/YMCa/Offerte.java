package YMCa;

import java.sql.Date;
import java.util.ArrayList;

public class Offerte {
    public int offerteId;
    public String naam ;
    public String adres;
    public String postcode;
    public String woonplaats;
    public String telefoonnummer;
    public String email;
    public String organisatie;
    public double prijs;
    public String medewerker;
    public String status;
    public Date datum;
    public Date activiteitDatum;
    public int relatieId;
    public int programmaId;
    public int deelnemers;
    public ArrayList<Activiteit> activiteiten = new ArrayList<Activiteit>();
}
