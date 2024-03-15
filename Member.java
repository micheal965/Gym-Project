import java.io.*;
import java.util.Scanner;

public class Member extends User {
    private Scanner scanner;
    private static int no_of_members;
    Member() {
        this.scanner = new Scanner(System.in);
        int i = 1;
        while (true) {
            File file = new File("Members/Member " + i + ".txt");
            if (file.exists())
                i++;
            else
                break;
        }
        no_of_members = i - 1;//5
    }
}
