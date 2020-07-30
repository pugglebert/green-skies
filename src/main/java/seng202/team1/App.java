package seng202.team1;
import com.google.gson.Gson;
/**
 * Hello world!
 *
 */
public class App 
{



    public static void main( String[] args )
    {

        System.out.println("Hello Nathan Huynh ");
        System.out.println("Hayley Krippner");
        System.out.println("Tom He");
        System.out.println("Grace Hanlon");
        System.out.println("Ella Johnson");
        System.out.println("Enyang Zhang");

        MyObject myObject = new MyObject("chair", 3);
        Gson gson = new Gson();
        String jsonString = gson.toJson(myObject);

        System.out.println("myObject =" + myObject);
        System.out.println("myObject stringyied = " + jsonString);

        System.out.println("Goodbye");
    }
}
