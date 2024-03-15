import java.io.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.io.File;
import java.util.Scanner;

public class Plan
{
    // Method to get a plan
    public String getPlan(String member_ID) {
        String plan_path = "Plans/plan "+member_ID+".txt" ;
        File file = new File(plan_path);
        if (file.exists())
        {
            String the_plan = "";
            try (Scanner scanner = new Scanner(new File(plan_path))) {
                while (scanner.hasNext()) {
                    the_plan+=scanner.nextLine();
                    the_plan+="\n";
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return the_plan;
        }
        else
        {
            return "Plans are empty";
        }
    }

    // Method to make a new plan
    public boolean setPlan(String newPlan,String coach_ID, String member_ID) {

        String coach_path = "Coachs/Coach " +coach_ID+".txt" ;
        File file = new File(coach_path);
        if (file.exists())
        {
            String users_id_of_coach = null;
            try (Scanner scanner = new Scanner(new File(coach_path))) {
                while (scanner.hasNextLine()) {
                    users_id_of_coach = scanner.nextLine();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] members_IDs_of_coach = users_id_of_coach.split(",");
            boolean checker = false;
            for (String memberID :members_IDs_of_coach)
            {
                if (Objects.equals(memberID, member_ID)){
                    checker = true; // 2.3.4/45
                    break;
                }
            }if (!checker){
            return false;
        }
        }
        else
        {
            return false;
        }

        String plan_path = "Plans/plan "+member_ID+".txt" ;
        File fileobj = new File(plan_path);
        if (!fileobj.exists())
        {
            try {
                Files.createFile(Path.of(plan_path));
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        try {

            FileWriter fw = new FileWriter(plan_path);
            fw.write(newPlan);
            fw.close();
        }
        catch (Exception e) {
            e.getStackTrace();
        }

        return true;
    }

}