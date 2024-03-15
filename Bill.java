import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Bill {
    private double amount;
    private int mid;
    Bill(){

    }
    public void setBill(int mid,double amount) {
        this.mid=mid;
        this.amount=amount;
        ArrayList<String> arr=new ArrayList<String>();
        String directoryPath = "Members";
        Path dirPath = Paths.get(directoryPath);
        File[] files = dirPath.toFile().listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                try {
                    Scanner scan = new Scanner(file);
                    int fid = Integer.parseInt(scan.nextLine());
                    scan.close();
                    if(this.mid == fid) {
                        try {
                            Scanner fr = new Scanner(file);
                            int count = 0;
                            while(fr.hasNextLine()) {
                                fr.nextLine();
                                count++;
                            }
                            fr.close();
                            if(count >= 5) {
                                Scanner fr1 = new Scanner(file);
                                for(int j = 0; j < 4; j++) {
                                    arr.add(fr1.nextLine());
                                }
                                fr1.close();
                                FileWriter fw1 = new FileWriter(file,false);
                                fw1.write("");
                                for(String i : arr) {
                                    fw1.write(i+"\n");
                                }
                                fw1.append(Double.toString(this.amount));
                                fw1.close();
                            }else {
                                FileWriter fw = new FileWriter(file,true);
                                fw.append(Double.toString(this.amount));
                                fw.close();
                            }
                            System.out.println("Bill Added Successfully!");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No files found in the directory.");
        }
    }

    public String getBill(int mid) {
        int counter=0;
        this.mid=mid;
        String directoryPath = "Members";
        Path dirPath = Paths.get(directoryPath);
        File[] files = dirPath.toFile().listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                Scanner scan;
                try {
                    scan = new Scanner(file);
                    if(this.mid == Integer.parseInt(scan.nextLine())) {
                        String data = "";
                        while(scan.hasNextLine()) {
                            data = scan.nextLine();
                            counter++;
                        }										///////////////////
                        this.amount = Double.parseDouble(data);
                        scan.close();
                        if(counter>=4) {
                            return "This Members Bill is " + this.amount + "$";
                        }
                        else{
                            return "This member doesnt have a bill";
                        }
                    }
                    scan.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
            return "No files found in the directory.";
    }
}