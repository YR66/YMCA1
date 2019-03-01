package YMCa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

@RestController
public class ControllerYMC {
    private int updated;

    @Autowired
    private DataSource dataSource;

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/relatie")
    public void createRelatie( @RequestParam(name = "naam") String naam,
                               @RequestParam(name = "adres") String adres,
                               @RequestParam(name = "postcode") String postcode,
                               @RequestParam(name = "woonplaats") String woonplaats,
                               @RequestParam(name = "telefoonnummer") String telefoonnummer,
                               @RequestParam(name = "email") String email,
                               @RequestParam(name = "organisatie") String organisatie
    ) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(
                    "insert into relaties (naam, adres, postcode, woonplaats, telefoonnummer, email, organisatie) value(?,?,?,?,?,?,?)");
            stmt.setString(1, naam);
            stmt.setString(2, adres);
            stmt.setString(3, postcode);
            stmt.setString(4, woonplaats);
            stmt.setString(5, telefoonnummer);
            stmt.setString(6, email);
            stmt.setString(7, organisatie);

            updated = stmt.executeUpdate();
            if (updated == 0) {
                throw new IllegalArgumentException("No relation inserted");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/relatie")
    public int deleteRelatie(@RequestParam(name="relatieId") int relatieId) throws SQLException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(
                    "DELETE FROM relaties WHERE relatieId = ?");
            stmt.setInt(1, relatieId);
            return stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/relatie")
    protected Relatie getRelatieDetails(@RequestParam(name="relatieId") int relatieId) throws SQLException {
        return DatabaseManager.getRelatie(relatieId);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/relatie")
    protected Relatie updateRelatie(@RequestParam(name = "relatieId") int relatieId,
                                    @RequestParam(name = "naam") String naam,
                                    @RequestParam(name = "adres") String adres,
                                    @RequestParam(name = "postcode") String postcode,
                                    @RequestParam(name = "woonplaats") String woonplaats,
                                    @RequestParam(name = "telefoonnummer") String telefoonnummer,
                                    @RequestParam(name = "email") String email,
                                    @RequestParam(name = "organisatie") String organisatie)
            throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement("UPDATE relaties SET naam = ?, adres = ?, postcode=?, woonplaats =?, telefoonnummer=?, email = ?, organisatie = ? WHERE relatieId = ?")) {

            stmt.setString(1, naam);
            stmt.setString(2, adres);
            stmt.setString(3, postcode);
            stmt.setString(4, woonplaats);
            stmt.setString(5, telefoonnummer);
            stmt.setString(6, email);
            stmt.setString(7, organisatie);
            stmt.setInt(8, relatieId);
            stmt.executeUpdate();
        }
        return DatabaseManager.getRelatie(relatieId);
    }


    @CrossOrigin(origins = "*")
    @GetMapping("relatie/{relatieId}")
    public ResponseEntity<Relatie> getRelatie(@PathVariable(value="relatieId") int relatieId) throws SQLException  {
        Relatie rel = DatabaseManager.getRelatie(relatieId);
        HttpHeaders head = new HttpHeaders();
        if (rel != null) {
            head.set("Status-Code", "200 Ok");
            head.set("Content-Type", "application/json");
            return ResponseEntity.ok().headers(head).body(rel);
        } else {
            head.set("Status-Code","404 Not Found");
            return ResponseEntity.notFound().headers(head).build();
        }

    }

    @CrossOrigin(origins = "*")
    @GetMapping("relaties")
    public ArrayList<Relatie> getRelaties() throws SQLException {
        return DatabaseManager.getRelaties();
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/activiteit")
    public void createActiviteit(@RequestParam(name = "activiteitId") int activiteitId,
                                 @RequestParam(name = "naam") String naam,
                                 @RequestParam(name = "omschrijving") String omschrijving,
                                 @RequestParam(name = "tarief") Double tarief,
                                 @RequestParam(name = "tijdsduur") int tijdsduur,
                                 @RequestParam(name = "activiteitSoort") String activiteitSoort
    ) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(
                    "insert into activiteiten (activiteitId, naam, omschrijving, tarief, tijdsduur, activiteitSoort) value(?,?,?,?,?,?)");
            stmt.setInt(1, activiteitId);
            stmt.setString(2, naam);
            stmt.setString(3, omschrijving);
            stmt.setDouble(4, tarief);
            stmt.setInt(5, tijdsduur);
            stmt.setString(6, activiteitSoort);

            updated = stmt.executeUpdate();
            if (updated == 0) {
                throw new IllegalArgumentException("Geen activiteit toegevoegd");
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/activiteit")
    public int deleteActiviteit(@RequestParam(name="activiteitId") int activiteitId) throws SQLException {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(
                    "DELETE FROM activiteiten WHERE activiteitId = ?");
            stmt.setInt(1, activiteitId);
            return stmt.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/activiteit")
    protected ArrayList<Activiteit> getActiviteitDetails(@RequestParam(name="activiteitId") int activiteitId) throws SQLException {
        return DatabaseManager.getActiviteitDetails(activiteitId);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/activiteit")
    protected ArrayList<Activiteit> updateActiviteit(@RequestParam(name = "activiteitId") int activiteitId,
                                                     @RequestParam(name = "naam") String naam,
                                                     @RequestParam(name = "omschrijving") String omschrijving,
                                                     @RequestParam(name = "tarief") Double tarief,
                                                     @RequestParam(name = "tijdsduur") int tijdsduur,
                                                     @RequestParam(name = "activiteitSoort") String activiteitSoort)
            throws SQLException {
        try (PreparedStatement stmt = getConnection().prepareStatement("UPDATE activiteiten SET naam = ?, omschrijving = ?, tarief=?, tijdsduur =?, activiteitSoort=? WHERE activiteitId = ?")) {

            stmt.setString(1, naam);
            stmt.setString(2, omschrijving);
            stmt.setDouble(3, tarief);
            stmt.setInt(4, tijdsduur);
            stmt.setString(5, activiteitSoort);
            stmt.setInt(6, activiteitId);

            stmt.executeUpdate();
        }
        return DatabaseManager.getActiviteit();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("activiteit/{activiteitId}")
    public ResponseEntity<Activiteit> getActiviteit(@PathVariable(value="activiteitId") int activiteitId) throws SQLException  {
        Activiteit act = DatabaseManager.getActiviteitDetail(activiteitId);
        HttpHeaders head = new HttpHeaders();
        if (act != null) {
            head.set("Status-Code", "200 Ok");
            head.set("Content-Type", "application/json");
            return ResponseEntity.ok().headers(head).body(act);
        } else {
            head.set("Status-Code","404 Not Found");
            return ResponseEntity.notFound().headers(head).build();
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("activiteiten")
    public ArrayList<Activiteit> getActiviteit() throws SQLException {
        return DatabaseManager.getActiviteit();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("activiteiten/{activiteitSoort}")
    public ArrayList<Activiteit> getActiviteit(@PathVariable(name="activiteitSoort") String activiteitSoort) throws SQLException {
        return DatabaseManager.getActiviteit(activiteitSoort);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/programma")
    public ResponseEntity<Programma> createProgramma(@RequestBody Programma programma) throws SQLException {
        int created = DatabaseManager.setProgramma(programma);
        HttpHeaders head = new HttpHeaders();
        if (created != -1) {
            programma.programmaId = created;
            head.set("Status-Code", "201 Created");
            return ResponseEntity.created(URI.create("programma/"+created)).headers(head).body(programma);
        } else {
            head.set("Status-Code", "400 Bad Request");
            return ResponseEntity.badRequest().headers(head).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/offerte")
    public ResponseEntity<Offerte> createOfferte(@RequestBody Offerte offerte) throws SQLException {
        int created = DatabaseManager.setOfferte(offerte);
        HttpHeaders head = new HttpHeaders();
        if (created != -1) {
            offerte.offerteId = created;
            head.set("Status-Code", "201 Created");
            return ResponseEntity.created(URI.create("offerte/"+created)).headers(head).body(offerte);
        } else {
            head.set("Status-Code", "400 Bad Request");
            return ResponseEntity.badRequest().headers(head).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/offerte")
    public ResponseEntity<Offerte> updateOfferte(@RequestBody Offerte offerte) throws SQLException {
        int created = DatabaseManager.updateOfferte(offerte);
        HttpHeaders head = new HttpHeaders();
        if (created != -1) {
            offerte.offerteId = created;
            head.set("Status-Code", "201 Created");
            return ResponseEntity.created(URI.create("offerte/"+created)).headers(head).body(offerte);
        } else {
            head.set("Status-Code", "400 Bad Request");
            return ResponseEntity.badRequest().headers(head).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @PatchMapping("/offerte")
    public ResponseEntity<Offerte> updateStatusOfferte(@RequestBody Offerte offerte) throws SQLException {
        int created = DatabaseManager.updateStatusOfferte(offerte);
        HttpHeaders head = new HttpHeaders();
        if (created != -1) {
            offerte.offerteId = created;
            head.set("Status-Code", "201 Created");
            return ResponseEntity.created(URI.create("offerte/"+created)).headers(head).body(offerte);
        } else {
            head.set("Status-Code", "400 Bad Request");
            return ResponseEntity.badRequest().headers(head).body(null);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("offertes")
    public ArrayList<Offerte> getOfferte() throws SQLException {
        return DatabaseManager.getOfferte();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("offertes/{offerteId}") //offertes/1233  //offertes?foo=1234
    public Offerte getOfferte(@PathVariable(name="offerteId") int offerteId) throws SQLException {
        return DatabaseManager.getOfferte(offerteId);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("offertestatus")
    public ArrayList<Status> getStatus() throws SQLException {
        return DatabaseManager.getStatus();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("medewerkers")
    public ArrayList<Medewerker> getMedewerker() throws SQLException {
        return DatabaseManager.getMedewerker();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("extra/status/{status}")
    public ResponseEntity<Integer> getNumberOffertesOnStatus(@PathVariable(value="status") String status) throws SQLException  {
        int nummer = DatabaseManager.getNumberOffertes(status);
        HttpHeaders head = new HttpHeaders();
        head.set("Status-Code", "200 Ok");
        head.set("Content-Type", "application/json");
        return ResponseEntity.ok().headers(head).body(nummer);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("extra/date/{status}")
    public ResponseEntity<Integer> getNumberOffertesOnDate(@PathVariable(value="status") String status) throws SQLException  {
        Date start = new Date();
        Date end = new Date();
        String startString = "";
        String endString = "";
        int countOnDate = 0;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat deformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            start = formatter.parse(status);
            end = formatter.parse(status);
            int dateint = start.getDate();
            start.setDate(dateint - 1);
            start.setHours(23);
            start.setMinutes(59);
            start.setSeconds(59);
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            startString = deformatter.format(start);
            endString = deformatter.format(end);
            countOnDate = DatabaseManager.getOffertesOnDate(startString, endString);
            HttpHeaders head = new HttpHeaders();
            head.set("Status-Code", "200 Ok");
            head.set("Content-Type", "application/json");
            return ResponseEntity.ok().headers(head).body(countOnDate);
        }
        catch (Exception e)
        {
            //TODO bij exception foutcode return
            HttpHeaders head = new HttpHeaders();
            head.set("Status-Code", "200 Ok");
            head.set("Content-Type", "application/json");
            return ResponseEntity.ok().headers(head).body(countOnDate);
        }
    }

}

