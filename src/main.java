import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        System.out.println("Enter number of Processes !");
        int n=cin.nextInt();

        System.out.println("Enter number of Resources !");
        int m=cin.nextInt();
        int [] request;
        Banker banker =new Banker(n,m);
        banker.compute();


        while(true){
            System.out.println(" Please Enter The Operation number you want to do :");
            System.out.println("1- Request resources");
            System.out.println("2- Quit ");
            int num=cin.nextInt();

            if(num==1){

                System.out.println("Please Enter The Process Number you want to request :");
                int p=cin.nextInt();
                request = banker.need[p];
                banker.Request(p,request);

            }
            else{
                break;
            }
        }

    }
}
