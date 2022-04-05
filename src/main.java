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

        //Boolean ok = true;

        while(true){
            System.out.println(" Please Enter The Operation number you want to do :");
            System.out.println("1- Request ");
            System.out.println("2- Release ");
            System.out.println("3- Quit ");
            int num=cin.nextInt();
            if(num==1){
                System.out.println("Please Enter The Process Number you want to request :");
                int p=cin.nextInt();
                request = banker.need[p];
                banker.Request(p,request);

            }
            else if(num==2){
                int [] arrr=new int[m];
                System.out.println("Enter The number of process ");
                int process_num=cin.nextInt();
                for(int i=0;i<m;i++){
                    System.out.print("Enter The Resource number "+ i +" value : ");
                    arrr[i]=cin.nextInt();
                    System.out.println();
                }
                banker.Release(process_num);

            }
            else{
                break;
            }
        }

    }
}
