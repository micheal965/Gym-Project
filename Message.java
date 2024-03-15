import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
public class Message {
    public static String send_messages(String coach_ID,String message) throws IOException {
        String coach_path = "Coachs/Coach " + coach_ID +".txt" ;
        File file = new File(coach_path);
        if (file.exists()) {
            String lastLine;
            lastLine = null;
            try (Scanner scanner = new Scanner(new File(coach_path))) {
                while (scanner.hasNextLine()) {
                    lastLine = scanner.nextLine();
                }
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

            String[] members_IDs = lastLine.split(",");
            for (String member_ID :members_IDs)
            {
                String messagepath = "messages/message " +member_ID+".txt" ;
                File messagefile = new File(messagepath);
                if (!messagefile.exists())
                {
                    try {
                        Files.createFile(Path.of(messagepath));
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
                try {
                    FileWriter fw = new FileWriter(messagepath);
                    fw.write(message);
                    fw.close();
                }
                catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        else
        {
            return "coach not found";
        }
        return "Messages were sent successfully";
    }

    public  String get_message(String member_ID) {
        String filePath = "messages/message "+member_ID+".txt" ;
        File file = new File(filePath);
        if (file.exists())
        {
            String the_message="";
            try (Scanner scanner = new Scanner(new File(filePath))) {
                while (scanner.hasNext()) {
                    the_message+=scanner.nextLine();
                    if (scanner.hasNext())
                        the_message+="\n";
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            return the_message;
        }
        else{
            return "Messages are empty";
        }
    }
}
