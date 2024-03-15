import java.nio.file.Files;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MyFile {
    ArrayList<String> A =new ArrayList<>();

    MyFile() {

    }

    public boolean insert(int id, String username, String password) {
        try {

            FileWriter file = new FileWriter("Users/User " + id + ".txt");
            file.write(id + "\n");
            file.write(username + "\n");
            file.write(password + "\n");
            file.close();
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public boolean login_validation(File f, String password) {
        try {
            Scanner userf = new Scanner(f);
            userf.nextLine(); // id
            userf.nextLine();//username
            String pass = userf.nextLine();
            if (pass.equals(password)) {
                userf.close();
                return true;
            } else {
                userf.close();
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }
    //oldusername oldpassword
    public boolean update(File f, String newData,int key) {
        String oldData=null;
        Scanner scanf;
        /**username*/
        if(key==1){
            try {
               scanf=new Scanner(f);
                scanf.nextLine();
                oldData=scanf.nextLine();

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            scanf.close();
        }
        /**password*/
        else if(key==2){
            try {
                scanf=new Scanner(f);
                    scanf.nextLine();
                    scanf.nextLine();
                oldData=scanf.nextLine();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            scanf.close();
        }
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(oldData)) {
                    lines.add(newData);
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            return false;
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();

            }
            bw.close();
            return true;
            } catch (IOException e) {
            return false;
        }
    }

    public void append(File f,ArrayList<String> a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
            while (!a.isEmpty()){
                bw.write(a.get(0));
                a.remove(0);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(File f,ArrayList<String> a) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, false))) {
            while (!a.isEmpty()){
                bw.write(a.get(0));
                a.remove(0);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int search(String directoryPath,int id) {
        int fnum=0;
        Path dirPath = Paths.get(directoryPath);
        File[] files = dirPath.toFile().listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                fnum++;
                Scanner scan;
                try {
                    scan = new Scanner(file);
                    if(id == Integer.parseInt(scan.nextLine())) {
                        scan.close();
                        return fnum;
                    }
                    scan.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0; // == no_member false
    }

    public ArrayList<String> read (File f) {
        Scanner readd ;
        try {
            readd = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (readd.hasNextLine()) {
            A.add(readd.nextLine());
        }
        readd.close();
        return A;
    }
    public boolean delete(int id ,int total,String pathf,String path){
        int currentfile=search(path,id);
        if(currentfile!=0){
            for(int j=currentfile;j<total;j++) {
                File file = new File(pathf+ j + ".txt");
                File nextfile = new File(pathf+ (j+1)+ ".txt");
                A = (read(nextfile));
                write(file,A);
                A.clear();

            }
            try {
                Files.delete(Path.of(pathf + total + ".txt"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return true;
        }
        return false;
}
public  void seeyourcoach(int id){
    int i=0;
    String num = null;
    String member_file_path = "Members/Member " + this.search("Members",id) + ".txt";
    int your_coach_id;
    //Read the last line where the coach's id is
    try (Scanner scanner = new Scanner(new File(member_file_path))) {
        while(scanner.hasNextLine()){
            i++;
            if(i==5){
                break;
            }
            num =scanner.nextLine();
        }

    } catch (IOException e) {
        throw new RuntimeException(e);
    }
    String coach_name,coach_file_path;
    if(i==5) {
        your_coach_id=Integer.parseInt(num);
        coach_file_path = "Coachs/Coach " + your_coach_id + ".txt";
        try {
            // Create a Scanner object to read from the file
            Scanner scanner = new Scanner(new File(coach_file_path));
            scanner.nextLine(); // Skip the first line
            coach_name = scanner.nextLine(); // Read  the second line
            // Close the scanner
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println(coach_name);
    }
    else
        System.out.println("You dont have a coach!");
}

public boolean assignornot(int mid){
    String member_file_path = "Members/Member " + this.search("Members",mid) + ".txt";
    try {
        int c=0;
        Scanner scanner = new Scanner(new File(member_file_path));
        while(scanner.hasNextLine()){
            scanner.nextLine();
            c++;
        }
            scanner.close();
        if(c>=4)
            return true;
        else
            return false;
    } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
    }

}
}