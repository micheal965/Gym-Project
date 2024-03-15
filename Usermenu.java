import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Usermenu {
    private int id,key,option;
    private String password;
    int mid,cid;
    private User usr = new User();
    private Coach co = new Coach();
    private Member me = new Member();
    private Admin ad = new Admin();
    Plan plan = new Plan();
    Message message = new Message();
    MyFile f=new MyFile();
    ArrayList <String>arr;
    Scanner sc = new Scanner(System.in);

    public Usermenu() {
        do {
            System.out.println("choose option:");
            System.out.println("1:login");
            System.out.println("2:register");
            System.out.println("3:exit");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    System.out.println("0:login As User");
                    System.out.println("1:login As Coach");
                    System.out.println("2:login As Member");
                    System.out.println("3:login As Admin");
                    int choice = sc.nextInt();
                    /**Done user*/
                    if(choice==0){
                        key=0;
                        System.out.print("Enter User ID:");
                        id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter The Password:");
                        password = sc.nextLine();
                        if(usr.login(id, password,key)==key){
                            System.out.println("login successfully you are a user");
                            updateorlogout();
                        }
                        else{
                            System.out.println("Wrong input");
                        }
                    }
                    //coach
                    else if(choice==1){
                        key=1;
                            System.out.print("Enter Coach ID:");
                            id = sc.nextInt();
                            System.out.print("Enter The Password:");
                             sc.nextLine();
                            password = sc.nextLine();
                            if(co.login(id, password,key)==key){
                                System.out.println("Login successfully you are a coach");
                                while (true) {
                                    System.out.println("Choose an operation from the following menu:");
                                    System.out.println("(a) Put the plan for the member");
                                    System.out.println("(b) Send message for all members");
                                    System.out.println("(c) Update");
                                    System.out.println("(d) Logout");
                                    char inputChar = sc.next().charAt(0);
                                    sc.nextLine(); // consume the newline character
                                    switch (inputChar) {
                                        case 'a':
                                            System.out.println("Enter the member ID for which you want to create a plan:");
                                            String member_ID = sc.nextLine();
                                            System.out.println("Enter the plan you want to set for this member:");
                                            String plan_text = sc.nextLine();
                                            boolean plan_ck = plan.setPlan(plan_text, Integer.toString(this.id), member_ID);
                                            if (plan_ck)
                                                System.out.println("Successful operation");
                                            else
                                                System.out.println("Unsuccessful operation");

                                            break;
                                        case 'b':
                                            System.out.println("Enter the message you want to send to all members:");
                                            String message_text = sc.nextLine();
                                            try {
                                                System.out.println(message.send_messages(Integer.toString(this.id), message_text));
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                            break;
                                        case 'c':
                                            updateorlogout();
                                            break;
                                        case 'd':
                                          usr.logout();
                                        default:
                                            System.out.println("Wrong input");
                                    }
                                }
                            }
                            else{
                                System.out.println("wrong input");
                            }
                    }
                    //member
                    else if(choice==2){
                        key=2;
                            System.out.print("Enter Member ID:");
                            id = sc.nextInt();
                            System.out.print("Enter The Password:");
                            sc.nextLine();
                            password = sc.nextLine();
                            if(me.login(id, password,key)==key){
                                System.out.println("Login successfully you are a member");
                                while (true) {
                                    System.out.println("Choose an operation from the following menu:");
                                    System.out.println("(a) see Your Plan");
                                    System.out.println("(b) see Your Message");
                                    System.out.println("(c) See your coach");
                                    System.out.println("(d) Update");
                                    System.out.println("(e) Logout");
                                    char inputChar = sc.next().charAt(0);
                                    switch (inputChar) {
                                        case 'a':
                                            System.out.println(plan.getPlan(Integer.toString(this.id)));
                                            break;
                                        case 'b':
                                            System.out.println(message.get_message(Integer.toString(this.id)));
                                            break;
                                        case 'c':
                                            f.seeyourcoach(this.id);
                                            break;
                                        case 'd':
                                            updateorlogout();
                                            break;
                                        case 'e':
                                            this.usr.logout();

                                        default:
                                            System.out.println("Wrong input");
                                    }

                                }
                            }
                            else{
                                System.out.println("wrong input");
                            }
                    }
                    //admin
                    else if(choice==3){
                        key=3;
                            System.out.print("Enter Admin ID:");
                            id = sc.nextInt();
                            System.out.print("Enter The Password:");
                            sc.nextLine();
                            password = sc.nextLine();
                            if(ad.login(id, password,key)==key){
                                do{
                                System.out.println("1:Add Member\n" +
                                    "2:Add Coach\n" +
                                    "3:Delete Member\n" +
                                    "4:Delete Coach\n" +
                                    "5:List Members\n" +
                                    "6:List Coaches\n" +
                                    "7:Search Member\n" +
                                    "8:Search Coach\n" +
                                    "9:Manage Bill\n" +
                                    "10:Assign\n" +
                                    "11:Logout");
                                int option = sc.nextInt();
                                int id;
                                switch (option) {
                                    case 1:
                                        System.out.println("Enter member ID:");
                                        id = sc.nextInt();
                                        System.out.println(ad.addmember(id));
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Enter coach ID:");
                                        id = sc.nextInt();
                                        System.out.println(ad.addcoach(id));
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Enter member ID:");
                                        id = sc.nextInt();
                                        System.out.println(ad.deletemember(id));
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Enter coach ID:");
                                        id = sc.nextInt();
                                        System.out.println(ad.deletecoach(id));
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 5:
                                        System.out.println(ad.listMembers());
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 6:
                                        System.out.println(ad.listCoachs());
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 7:
                                        arr.clear();
                                        System.out.println("Enter Member ID:");
                                        id = sc.nextInt();
                                        arr=ad.searchMember(id);
                                        if(arr.isEmpty())
                                            System.out.println("Not found!");
                                        else
                                            System.out.println(arr);

                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 8:
                                        arr.clear();
                                        System.out.println("Enter Member ID:");
                                        id = sc.nextInt();
                                        arr=ad.searchCoach(id);
                                        if(arr.isEmpty())
                                            System.out.println("Not found!");
                                        else
                                            System.out.println(arr);
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 9:
                                        System.out.println("Enter 1 to set bill, 2 to get bill:");
                                        choice = sc.nextInt();
                                        System.out.print("Enter Member ID: ");
                                        mid = sc.nextInt();
                                            ad.manageBill(mid,choice);
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 10:
                                        System.out.print("Enter Coach ID: ");
                                        id = sc.nextInt();
                                        cid=id;
                                        System.out.print("Enter Member ID to be assigned to: "); // 11 2 3 4 5
                                        mid = sc.nextInt();
                                        System.out.println(ad.assign(id, mid));
                                        try {
                                            Thread.sleep(3000);
                                        } catch (InterruptedException e) {
                                            throw new RuntimeException(e);
                                        }
                                        break;
                                    case 11:
                                        usr.logout();
                                        break;
                                    default:
                                        System.out.println("Invalid option");
                                }}while(true);
                            }
                            else{
                                System.out.println("wrong input");
                            }
                    }

                    break;

                    /**done*/
                case 2:
                    sc.nextLine();
                    System.out.println("enter The User Name:");
                    String username =sc.nextLine();
                    System.out.println("enter The Password:");
                    String password = sc.nextLine();
                    if(usr.register(username, password))
                        System.out.println("Registration completed successfully!");
                    else
                        System.out.println("Registration failed!");

                    break;

                case 3:
                    return;
                default:
                    System.out.println("wrong choice");
            }
        } while(true);
}
public void updateorlogout(){
    System.out.println("Press 1 to Update press 2 to logout:");
    int upornot = sc.nextInt();
    if (upornot == 1) {
        System.out.println("1:updateusername");
        System.out.println("2:updatepassword");
        int secondchoice = sc.nextInt();//1 "\n"
        switch (secondchoice) {
            case 1:
                sc.nextLine();
                System.out.print("Enter The New User name: ");
                String newuser = sc.nextLine();
                System.out.println(usr.updateUsername(id,newuser,key));
                break;
            case 2:
                sc.nextLine();
                System.out.print("Enter The New Password: ");
                String newpass = sc.nextLine();
                System.out.println(usr.updatePassword(id,newpass,key));
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }
    } else if (upornot == 2) {
        usr.logout();
    }
}
}