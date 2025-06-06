package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.ActionsList;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(maxPersonsCount("KIKM",2024));
    }

    public static long maxPersonsCount(String department, int year)
    {
        String json = Api.getActionsByDepartment(department,year);
        ActionsList actions= new Gson().fromJson(json, ActionsList.class);

        return actions.items.stream().mapToInt(a -> a.personsCount).max().orElse(0); // TODO 2.1: Vrať nejvyšší dosažený počet přihlášených studentů na jedné akci
    }
}