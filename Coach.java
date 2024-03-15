import java.io.File;
import java.util.Scanner;
public class Coach extends User {
    private Scanner scanner;
    private static int no_of_coachs;

    Coach(){
        this.scanner = new Scanner(System.in);
        int i = 1;
        while(true) {
            File file = new File("Coachs/Coach " + i +".txt");
            if(file.exists())
                i++;
            else
                break;
        }
        no_of_coachs = i-1;//5
    }
}