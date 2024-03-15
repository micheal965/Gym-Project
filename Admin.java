import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    ArrayList<String> arr = new ArrayList<String>();
    private static int no_of_members = 0;
    private static int no_of_coachs = 0;

    Scanner obj = new Scanner(System.in);
    MyFile my = new MyFile();
    Bill b = new Bill();

    public Admin() {
        int i = 1, j = 1;
        while (true) {
            File file = new File("Members/Member " + i + ".txt");
            if (file.exists())
                i++;
            else
                break;
        }
        no_of_members = i - 1;
        while (true) {
            File file = new File("Coachs/Coach " + j + ".txt");
            if (file.exists())
                j++;
            else
                break;
        }
        no_of_coachs = j - 1;
    }
    public String addmember(int id) {
            if (id < 1 || id > getNo_of_users()) {
                return "User not found!";
            }

            if (my.search("Coachs", id) != 0) {
                return "This user is already a Coach";
            }
            else if (my.search("Members", id) != 0) {
                return "This user is already a Coach";
            }
            else{
            File fu = new File("Users/User " + id + ".txt");
            File f = new File("Members/Member " + (no_of_members + 1) + ".txt");
            arr = (my.read(fu));
            my.append(f, arr);
            arr.clear();
            return "Added Successfully!";
            }

    }

    public String addcoach(int id) {
            if (id < 1 || id > getNo_of_users()) {
                return "User not found!";
            }
            if (my.search("Members", id) != 0) {
                return "This user is already a Member";
            }
           else if (my.search("Coachs", id) != 0) {
                return "This user is already a Coach";
            }
           else {
                File fu = new File("Users/User " + id + ".txt");
                File f = new File("Coachs/Coach " + (no_of_coachs + 1) + ".txt");
                arr = (my.read(fu));
                my.write(f, arr);      //append or write
                arr.clear();
                return "Added Successfully!";
            }
    }

    public String deletemember(int id) {
        if (my.delete(id, no_of_members, "Members/Member ", "Members")) {
            no_of_members--;
            return "Deleted Successfully";
        } else {
            return "couldn't delete";
        }
    }

    public String deletecoach(int id) {
        if (my.delete(id, no_of_coachs, "Coachs/Coach ", "Coachs")) {
            no_of_coachs--;
           return "Deleted Successfully";
        } else {
            return "couldn't delete";
        }
    }

    public ArrayList<String> listMembers() {
        Path dirPath = Paths.get("Members");
        File[] files = dirPath.toFile().listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                arr = my.read(file);
                arr.add("/");
            }
        }
        return arr;
    }


    public ArrayList<String> listCoachs() {
        Path dirPath = Paths.get("Coachs");
        File[] files = dirPath.toFile().listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                arr = my.read(file);
                arr.add("/");
            }
        }
        return arr;
    }

    public ArrayList<String> searchMember(int id) {
        try {
            File f = new File("Members/Member " + my.search("Members", id) + ".txt");
            arr = my.read(f);
        }catch (Exception Ignored){
        }
            return arr;

    }

    public ArrayList<String> searchCoach(int id) {
        File f = new File("Coachs/Coach " + my.search("Coachs", id) + ".txt");
        arr = my.read(f);
        return arr;
    }

    public void manageBill(int mid,int choice) {
         double amount;
        switch (choice){
            case 1:
                if(my.assignornot(mid)){
                System.out.print("Enter The Amount: ");
                amount = Double.parseDouble(obj.nextLine());
                b.setBill(mid,amount);
                }
                else{
                    System.out.println("You have to assign first");
                }
                break;
            case 2:
                System.out.println(b.getBill(mid));
                break;
            default:
                System.out.println("invalid option");
        }
    }

    public String assign(int cid,int mid) {
        if (my.search("Coachs", cid) == 0) {
            return "Coach not found";
        }
        else {
            if (my.search("Members", mid) == 0) {
                return "Member not found";
            } else {
                File fmid = new File("Members/Member " + (my.search("Members", mid) + ".txt"));
                arr = my.read(fmid);
                if (arr.size() == 4) {
                    return "Member already has a Coach"; // has a coach
                } else {
                    File fcid = new File("Coachs/Coach " + (my.search("Coachs", cid) + ".txt"));
                    arr.add(Integer.toString(cid));
                    my.write(fmid, arr);
                    arr.clear();
                    my.append(fcid, arr);
                    arr = my.read(fcid);
                    try {
                        FileWriter writeC ;
                        writeC = new FileWriter(fcid, true);
                        if (arr.size() == 3) {
                            arr.add("\n");
                            writeC.append(arr.get(arr.size() - 1));
                            arr.remove(arr.size() - 1);
                        }
                        arr.add(Integer.toString(mid));
                        writeC.append((arr.get(arr.size() - 1)) + ","); // 1,2,3,
                        writeC.close();
                        arr.clear();
                        return "Assigned Successfully";// found
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}