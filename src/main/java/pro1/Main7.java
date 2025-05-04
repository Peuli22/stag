package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.SpecializaceList;

import java.util.Comparator;
import java.util.stream.Collectors;

public class Main7 {

    public static void main(String[] args) {
        String result = specializationDeadlines(2025);
        if (result == null || result.isEmpty()) {
            System.out.println("Metoda specializationDeadlines nevrátila žádná data");
        } else {
            System.out.println(result);
        }
    }

    public static String specializationDeadlines(int year) {
        // Získání JSON dat
        String json = Api.getSpecializations(year);
        if (json == null || json.isEmpty()) {
            System.out.println("Api.getSpecializations vrátil null nebo prázdný řetězec.");
            return "";
        }

        // Deserializace JSONu
        SpecializaceList specializaceList = new Gson().fromJson(json, SpecializaceList.class);
        if (specializaceList == null || specializaceList.prijimaciObor == null) {
            System.out.println("Deserializace JSONu selhala nebo neobsahuje žádná data.");
            return "";
        }

        // Zpracování, odstranění duplicit a správné seřazení datumů
        return specializaceList.prijimaciObor.stream()
                .map(s -> s.eprDeadlinePrihlaska.value) // Extrakce hodnot datumů
                .distinct() // abych nemel 150x stejné datumy
                .sorted((d1, d2) -> { // Porovnání datumů
                    String[] parts1 = d1.split("\\.");
                    String[] parts2 = d2.split("\\.");
                    int porovnaniRoku = Integer.parseInt(parts1[2]) - Integer.parseInt(parts2[2]); // Porovnání roků
                    if (porovnaniRoku != 0) {
                        return porovnaniRoku;
                    }
                    int porovnaniMesicu = Integer.parseInt(parts1[1]) - Integer.parseInt(parts2[1]); // Porovnání měsíců
                    if (porovnaniMesicu != 0) {
                        return porovnaniMesicu;
                    }
                    return Integer.parseInt(parts1[0]) - Integer.parseInt(parts2[0]); // Porovnání dnů
                })
                .collect(Collectors.joining(",")); // Spojení dohromady
    }
}