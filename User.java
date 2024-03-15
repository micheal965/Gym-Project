import java.io.*;

public class User{
    private String username;
    private String password;
    private int id=0;
    private static int no_of_users=0;
    MyFile my = new MyFile();
    User() {
        int i = 1;
        while(true) {
            File file = new File("Users/User " + i +".txt");
            if(file.exists()) {
                i++;
            }else {
                break;
            }
        }
        no_of_users = i-1;
    }
    public boolean register(String username,String password) {
        this.username = username;
        this.password = password;
        int id = this.no_of_users + 1;
        if (my.insert(id, this.username, this.password)) {
            this.no_of_users++;
            this.id = this.no_of_users;
            return true;
        }
        else
            return false;
    }

    public int login(int id,String password,int key) {
        this.id=id;
        this.password=password;
        File f = null;
        if((id < 1 || id > this.no_of_users)&&id!=-999){
            return 404;//user not found
        }

        if (key==3){
            if (id == -999 && password.equals("admin")) {
                return key;//you are an admin
            }
            return -1;//wrong input
        }
         else{
         if(key==0)
            f = new File("Users/User " + id + ".txt");
         if(key==1)
            f = new File("Coachs/Coach " + id + ".txt");
         if(key==2)
            f = new File("Members/Member " + my.search("Members",id) + ".txt");

        if(my.login_validation(f,password))
            return key;//"Login Successfully"
        else
            return -1;//"Wrong input!\n"
        }
    }

    public void logout() {
       new Usermenu();
    }

    public String updateUsername(int id,String newuser,int key) {
        File file,f;
        if(key==1){
            file = new File("Users/User "+ id +".txt");
            f = new File("Coachs/Coach " + id + ".txt");
        }
        else if(key==2){
            file = new File("Users/User "+ id +".txt");
            f = new File("Members/Member " + my.search("Members",id) + ".txt");
        }
        else{
        file = new File("Users/User "+ id +".txt");
        if(my.update(file,newuser,1)) {
            this.username = newuser;
            return "Username updated successfully!";
        }else {
            return "Couldn't update the username.";
        }
       }

        if(my.update(file,newuser,1)&&my.update(f,newuser,1)) {
            this.username = newuser;
            return "Username updated successfully!";
        }else {
            return "Couldn't update the username.";
        }
    }

    public String updatePassword(int id,String newpass,int key) {
        File file,f;
        if(key==1){
            f = new File("Coachs/Coach " + id + ".txt");
            file = new File("Users/User "+ id +".txt");
        }

        else if(key==2){
            f = new File("Members/Member " + my.search("Members",id) + ".txt");
            file = new File("Users/User "+ id +".txt");
        }
        else{
        file = new File("Users/User "+ id +".txt");
            if(my.update(file,newpass,2)){
                this.password=newpass;
                return "Password updated successfully!";
            }
            else
                return"Couldn't update the password.";
        }

        if(my.update(file,newpass,2)&&my.update(f,newpass,2)){
            this.password=newpass;
            return "Password updated successfully!";
        }
        else
            return"Couldn't update the password.";

    }

    public static int getNo_of_users() {
        return no_of_users;
    }
}