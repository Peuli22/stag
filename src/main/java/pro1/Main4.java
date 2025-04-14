package pro1;

import com.google.gson.Gson;
import pro1.apiDataModel.TeachersList;

import java.util.Comparator;

public class Main4 {

    public static void main(String[] args) {
         printShortestEmails("KIKM",5);
    }

    public static void printShortestEmails(String department, int count)
    {
        String json = Api.getTeachersByDepartment(department);
        TeachersList teachers = new Gson().fromJson(json, TeachersList.class);


        teachers.items.stream()
                .filter( teacher -> teacher.email != null)// filtruje učitele, kteří mají email
                .sorted(Comparator.comparing(t->t.email.length())) // seřadí podle délky emailu
                .limit(count)// vezme prvních "count" emailů
                .forEach(t -> System.out.println(t.email)); // vypíše do konzole
                //.forEach(p -> System.out.println()); // vypíše do konzole, ale jinak to nad tim je zkratka. Šel by tak .toList a pak udělat bokem klasický for cyklus, ale to je zbytečné



        // TODO 4.1: Vypiš do konzole "count" nejkratších učitelských emailových adres
    }
}