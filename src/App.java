
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
                    
                    break;
                case 9:
                    
                    break;
                case 10:
                   
                    break;
                case 11:
                  
                    break;
                case 12:
                  
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
    
    
    
    
}



