package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static java.lang.reflect.Array.get;
import static java.util.Collections.max;

public class Main6 {

    public static void main(String[] args) {
        System.out.println(idOfBestTeacher("KIKM",2024));
    }

    public static long idOfBestTeacher(String department, int year)
    {
        String json = Api.getActionsByDepartment(department,year);
        ActionsList actions = new Gson().fromJson(json, ActionsList.class);
        HashMap<Long,Integer> hashMap = new HashMap<>();

        actions.items.stream()
                .forEach(action -> {
                    Integer current = hashMap.getOrDefault(action.teacherId, 0); // vezme si aktuální počet studentů, kteří se přihlásili na akce daného učitele
                    hashMap.put(action.teacherId, current + action.personsCount); // přičte počet studentů, kteří se přihlásili na akce daného učitele
                }); // udělá mapu, kde klíčem je ID učitele a hodnotou je počet studentů, kteří se přihlásili na akce daného učitele


        // TODO 6.1 (navazuje na TODO 3):
        //  - Stáhni seznam akcí na katedře (jiná data nepoužívat)
        //  - Najdi učitele s nejvyšším "score" a vrať jeho ID

        return hashMap.entrySet().stream()
                .max(Comparator.comparing(e -> e.getValue())) // vezme max z mapy podle hodnoty
                .get().getKey(); // vezme max z mapy podle hodnoty a vrátí klíč

    }
}