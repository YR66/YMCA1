package YMCa;

import java.util.ArrayList;

public class DatabaseHelper {

    public static Offerte ContainsOfferteId(ArrayList<Offerte> results, int offerteId)
    {
        for(Offerte offerte : results) {
            if(offerte.offerteId == offerteId) {
                return offerte;
            }
        }
        return null;
    }

}
