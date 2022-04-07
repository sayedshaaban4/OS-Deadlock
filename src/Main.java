import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);

        System.out.println("Enter number of Processes !");
        int n=cin.nextInt();

        System.out.println("Enter number of Resources !");
        int m=cin.nextInt();
        Banker banker =new Banker(n,m);
        banker.initialize();

        while(true){
            System.out.println(" Please Enter The Operation number you want to do :");
            System.out.println("1- Request ");
            System.out.println("2- Release ");
            System.out.println("3- Quit ");
            int num=cin.nextInt();

            if(num==1){
                int [] requestRes=new int[m];
                System.out.println("Please Enter The Process Number you want to request :");
                int p=cin.nextInt();
                for(int i=0;i<m;i++){
                    System.out.print("Enter The Resource number "+ i +" value : ");
                    requestRes[i]=cin.nextInt();
                    System.out.println();
                }
                banker.Request(p,requestRes);
            }

            else if(num==2){
                int [] releaseRes=new int[m];
                System.out.println("Enter The number of process ");
                int process_num=cin.nextInt();
                for(int i=0;i<m;i++){
                    System.out.print("Enter The Resource number "+ i +" value : ");
                    releaseRes[i]=cin.nextInt();
                    System.out.println();
                }
                banker.Release(process_num,releaseRes);
            }

            else if (num==3){
                System.out.println("Quit");
                break;
            }

            else{
                System.out.println("Wrong Input");
            }
        }
    }
}
