
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class App {
    
    Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        App Program = new App();
        Program.run();
    }
    
    private JSONArray loadSeries() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/tvs.json"));
            if (obj instanceof JSONArray) {
                return (JSONArray) obj;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private JSONObject loadLanguages() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("src/languages.json"));
            if (obj instanceof JSONObject) {
                return (JSONObject) obj;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void run() {
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n================================");
            System.out.println("Benvingut a la Gestió de Sèries!");
            System.out.println("================================");
            System.out.println("\nMENU EXERCICIS (12)");
            System.out.println("1. Carrega les dades del fitxer");
            System.out.println("2. Nombre de sèries");
            System.out.println("3. Nom i nom original de cada sèrie");
            System.out.println("4. Idioma original de cada sèrie");
            System.out.println("5. Països d'origen de cada sèrie");
            System.out.println("6. Gèneres de cada sèrie");
            System.out.println("7. Dades de 'Breaking Bad'");
            System.out.println("8. Sèrie amb millor puntuació");
            System.out.println("9. Sèrie amb pitjor puntuació");
            System.out.println("10. Dada interessant del dataset");
            System.out.println("11. Canvia idiomes amb languages.json");
            System.out.println("12. Escriu fitxer tvs_modificat.json");
            System.out.println("0. Sortir");
            System.out.print("\nEscull una opció: ");

            int opcio = Integer.parseInt(sc.nextLine());

            switch (opcio) {
                case 1:
                    exercici1();
                    break;
                case 2:
                    exercici2();
                    break;
                case 3:
                    exercici3();
                    break;
                case 4:
                    exercici4();
                    break;
                case 5:
                    exercici5();
                    break;
                case 6:
                    exercici6();
                    break;
                case 7:
                    exercici7();
                    break;
                case 8:
                    exercici8();
                    break;
                case 9:
                    exercici9();
                    break;
                case 10:
                    exercici10();
                    break;
                case 11:
                    exercici11();
                    break;
                case 12:
                    exercici12();
                    break;
                case 0:
                    System.out.println("Fins aviat!");
                    salir = true;
                    break;
                default:
                    System.out.println("Opció no vàlida! - Torna-ho a intentar.");
                    break;
            }
        }
    }

    public void exercici1() {
        System.out.println("\n=== Exercici 1: Carrega les dades ===");
        JSONArray series = loadSeries();
        if (series != null) {
            System.out.println("Les dades s'han carregat correctament!");
            System.out.println("S'han carregat un total de " + series.size() + " sèries.");
        } else {
            System.out.println("Error al carregat les dades.");
        }
    }
    
    public void exercici2() {
        System.out.println("\n=== Exercici 2: Nombre de sèries ===");
        JSONArray series = loadSeries();
        if (series != null) {
            System.out.println("Nombre de sèries: " + series.size());
        }
        else {
            System.out.println("No s'ha detectat cap sèrie.");
        }
    }
    
    public void exercici3() {
        System.out.println("\n=== Exercici 3: Nom i nom original ===");
        JSONArray series = loadSeries();
        if (series != null) {
            for (int i = 0; i < series.size(); i++) {
                JSONObject serie = (JSONObject) series.get(i);
                System.out.println("Sèrie " + (i + 1) + ":");
                System.out.println("  Nom: " + serie.get("name"));
                System.out.println("  Nom original: " + serie.get("original_name"));
            }
        }
    }
    
    public void exercici4() {
        System.out.println("\n=== Exercici 4: Idioma original ===");
        JSONArray series = loadSeries();
        if (series != null) {
            for (int i = 0; i < series.size(); i++) {
                JSONObject serie = (JSONObject) series.get(i);
                if (serie.get("original_language") != null) {
                    System.out.println("Idioma original " + (i + 1) + ": " + serie.get("original_language"));  
                }
                else{
                    System.out.println("Idioma original " + (i + 1) + ": No hi ha informació disponible");
                }
            }
        
        }
    }
    
    public void exercici5() {
        System.out.println("\n=== Exercici 5: Països d'origen ===");
        JSONArray series = loadSeries();
        if (series != null) {
            for (int i = 0; i < series.size(); i++) {
                JSONObject serie = (JSONObject) series.get(i);
                JSONArray paisos = (JSONArray) serie.get("origin_country");
                System.out.print("Països " + (i + 1) + ": ");
                if (paisos != null) {
                    for (int j = 0; j < paisos.size(); j++) {
                        System.out.print(paisos.get(j));
                        if (j < paisos.size() - 1) System.out.print(", ");
                    }
                }
                else{
                    System.out.print("No hi ha països d'origen disponibles");
                }
                System.out.println();
            }
        }
    }
    
    public void exercici6() {
        System.out.println("\n=== Exercici 6: Gèneres ===");
        JSONArray series = loadSeries();
        if (series != null) {
            for (int i = 0; i < series.size(); i++) {
                JSONObject serie = (JSONObject) series.get(i);
                JSONArray genres = (JSONArray) serie.get("genres");
                System.out.print("Gèneres " + (i + 1) + ": ");
                if (genres != null) {
                    for (int j = 0; j < genres.size(); j++) {
                        JSONObject genre = (JSONObject) genres.get(j);
                        System.out.print(genre.get("name"));
                        if (j < genres.size() - 1) System.out.print(", ");
                    }
                }
                else {
                    System.out.print("No hi ha gèneres disponibles");
                }
                System.out.println();
            }
        }
    }
    
    public void exercici7() {
        System.out.println("\n=== Exercici 7: Breaking Bad ===");
        JSONArray series = loadSeries();
        if (series != null) {
            
            for (int i = 0; i < series.size(); i++) {
                JSONObject serie = (JSONObject) series.get(i);
                //Filtrar per nom per trobar Breaking Bad
                if (serie.get("name").equals("Breaking Bad")) {
                    System.out.println("Sèrie: " + serie.get("name"));
                    System.out.println("Idioma original: " + serie.get("original_language"));
                    System.out.println("Descripció: " + serie.get("overview"));
                    
                    JSONArray paisos = (JSONArray) serie.get("origin_country");
                    System.out.print("Països d'origen: ");
                    if (paisos != null) {
                        for (int j = 0; j < paisos.size(); j++) {
                            System.out.print(paisos.get(j));
                            if (j < paisos.size() - 1) System.out.print(", ");
                        }
                    }
                    else{
                        System.out.print("No hi ha països d'origen disponibles");
                    }
                    System.out.println();
                    
                    JSONArray genres = (JSONArray) serie.get("genres");
                    System.out.print("Gèneres: ");
                    if (genres != null) {
                        for (int j = 0; j < genres.size(); j++) {
                            JSONObject genre = (JSONObject) genres.get(j);
                            System.out.print(genre.get("name"));
                            if (j < genres.size() - 1) System.out.print(", ");
                        }
                    }
                    else {
                        System.out.print("No hi ha gèneres disponibles");
                    }
                    System.out.println("\nPuntuació: " + serie.get("vote_average"));
                    
                    
                    //break;  sortir del bucle un cop trobada la sèrie 
                }
            }
            
        }
    }
    
    public void exercici8() {
        System.out.println("\n=== Exercici 8: Sèrie amb millor puntuació ===");
        JSONArray series = loadSeries();
        if (series != null) {
            JSONObject best = null;
            double maxVote = -1;
            for (int i = 0; i < series.size(); i++) {
                JSONObject serie = (JSONObject) series.get(i);
                Number voteAvg = (Number) serie.get("vote_average");
                if (voteAvg != null && voteAvg.doubleValue() > maxVote) {
                    maxVote = voteAvg.doubleValue();
                    best = serie;
                }
            }
            if (best != null) {
                System.out.println("Sèrie amb millor puntuació:");
                System.out.println("  Nom: " + best.get("name"));
                System.out.println("  Puntuació: " + best.get("vote_average"));
            }
        }
    }
    
    public void exercici9() {
        System.out.println("\n=== Exercici 9: Sèrie amb pitjor puntuació ===");
        JSONArray series = loadSeries();
        if (series != null) {
            JSONObject worst = null;
            double minVote = Double.MAX_VALUE;
            for (int i = 0; i < series.size(); i++) {
                JSONObject serie = (JSONObject) series.get(i);
                Number voteAvg = (Number) serie.get("vote_average");
                if (voteAvg != null && voteAvg.doubleValue() < minVote) {
                    minVote = voteAvg.doubleValue();
                    worst = serie;
                }
            }
            if (worst != null) {
                System.out.println("Sèrie amb pitjor puntuació:");
                System.out.println("  Nom: " + worst.get("name"));
                System.out.println("  Puntuació: " + worst.get("vote_average"));
            }
        }
    }
    
    public void exercici10() {
        System.out.println("\n=== Exercici 10: Dada interessant del dataset ===");
        JSONArray series = loadSeries();
        if (series != null) {
            int totalEpisodes = 0;
            int totalSeasons = 0;
            for (int i = 0; i < series.size(); i++) {
                JSONObject serie = (JSONObject) series.get(i);
                Number episodes = (Number) serie.get("number_of_episodes");
                Number seasons = (Number) serie.get("number_of_seasons");
                if (episodes != null) totalEpisodes += episodes.intValue();
                if (seasons != null) totalSeasons += seasons.intValue();
            }
            System.out.println("Total d'episodis en el dataset: " + totalEpisodes);
            System.out.println("Total de temporades en el dataset: " + totalSeasons);
        }
    }
    
    public void exercici11() {
        System.out.println("\n=== Exercici 11: Canvia idiomes amb languages.json ===");
        JSONArray series = loadSeries();
        JSONObject languages = loadLanguages();
        
        if (series != null && languages != null) {
            for (Object obj : series) {
                JSONObject serie = (JSONObject) obj;
                String langCode = (String) serie.get("original_language");
                if (langCode != null && languages.containsKey(langCode)) {
                    serie.put("original_language", languages.get(langCode));
                }
            }
            System.out.println("✓ Els idiomes s'han canviat correctament!");
            System.out.println("Es mostra una mostra del canvi:");
            for (int i = 0; i < Math.min(3, series.size()); i++) {
                JSONObject serie = (JSONObject) series.get(i);
                System.out.println("  Sèrie " + (i + 1) + ": " + serie.get("name") + " - " + serie.get("original_language"));
            }
        }
    }
    
    public void exercici12() {
        System.out.println("\n=== Exercici 12: Escriu fitxer tvs_modificat.json ===");
        JSONArray series = loadSeries();
        JSONObject languages = loadLanguages();
        
        if (series != null) {
            if (languages != null) {
                for (Object obj : series) {
                    JSONObject serie = (JSONObject) obj;
                    String langCode = (String) serie.get("original_language");
                    if (langCode != null && languages.containsKey(langCode)) {
                        serie.put("original_language", languages.get(langCode));
                    }
                }
            }
            
            try (FileWriter writer = new FileWriter("src/tvs_modificat.json")) {
                writer.write(series.toJSONString());
                System.out.println("Fitxer 'tvs_modificat.json' creat correctament!");
            } catch (IOException e) {
                System.out.println("Error al crear el fitxer: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}



