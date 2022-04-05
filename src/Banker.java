import java.util.ArrayList;
import java.util.Scanner;
public class Banker {
    Scanner cin = new Scanner(System.in);
    int [] available;
    int [] Temp_available;
    int [][] maximum;
    int [][] allocation;
    int [][] need;
    int [][] request;
    ArrayList<Integer>safe_Order = new ArrayList<Integer>();
    ArrayList<Integer>safeSequence = new ArrayList<Integer>();
    int P,R;
    Banker(int n,int m){
        P=n;
        R=m;
        available = new int[R];
        Temp_available = new int[R];
        maximum = new int[P][R];
        allocation = new int[P][R];
        need = new int[P][R];
    }

    void compute(){
        System.out.println("Enter the number of the available Resources respectively  : ");
        String s=cin.nextLine();
        String ss[]=s.split(" ");
        for(int i=0;i<R;i++) {
            available[i] = Integer.parseInt(ss[i]);
            Temp_available[i]=available[i];
        }

        for(int i=0;i<P;i++){
            System.out.println("Enter the number of the Maximum needs of Resources in process number ("+(i) +") respectively : ");
            String str=cin.nextLine();
            String temp[]=str.split(" ");
            for(int j=0;j<R;j++){
                maximum[i][j]=Integer.parseInt(temp[j]);
            }
        }

        for(int i=0;i<P;i++){
            System.out.println("Enter the number of the Allocation of Resources in process number ("+(i) +") respectively : ");
            String str=cin.nextLine();
            String temp[]=str.split(" ");
            for(int j=0;j<R;j++){
                allocation[i][j]= Integer.parseInt(temp[j]);
            }
        }

        for(int i=0;i<P;i++){
            for(int j=0;j<R;j++){
                need[i][j]= maximum[i][j]-allocation[i][j];
            }
        }

        //////////////////////////////////////////////////////

        Boolean [] vis = new Boolean[P];
        for (int i=0;i<P;i++)vis[i]=false;

        for(int i=0;i<P*P;i++){
            int cnt=0;

            for(int j=0;j<R;j++){
                if(need[i%P][j] <=Temp_available[j] && !vis[i%P])cnt++;
            }

            if(cnt==R){
                vis[i%P]=true;

                for(int j=0;j<R;j++){
                    Temp_available[j] = Temp_available[j] + allocation[i%P][j];
                }

                safe_Order.add(i%P);
            }
        }
        System.out.println("To make The System at Safe State, You Must Follow this processes order : ");
        for(int i=0;i<safe_Order.size();i++){
            System.out.println("Process number : "+(safe_Order.get(i)));
        }
    }


//    void Release(int process,int [] arr){
//
//            for(int i=0;i<R;i++){
//                allocation[process][i]=allocation[process][i]-arr[i];
//                available[i]=available[i]+arr[i];
//            }
//            System.out.println("The New available array will be : ");
//            for(int i=0;i<R;i++) System.out.print(available[i]+" ");
//            System.out.println();
//    }
    void Request(int process){
        System.out.println("Enter the number of the Resources you want to request respectively : ");
        String s=cin.nextLine();
        String ss[]=s.split(" ");
        request = new int[P][R];
        for(int i=0;i<R;i++) {
            request[process][i] = Integer.parseInt(ss[i]);
        }
        for(int i=0;i<R;i++){
            if(request[process][i]>need[process][i]){
                System.out.println("The Requested Resources are more than the Needed Resources");
                return;
            }
        }
        for(int i=0;i<R;i++){
            if(request[process][i]>available[i]){
                System.out.println("The Requested Resources are more than the Available Resources");
                return;
            }
        }
        for(int i=0;i<R;i++){
            available[i]=available[i]-request[process][i];
            allocation[process][i]=allocation[process][i]+request[process][i];
            need[process][i]=need[process][i]-request[process][i];
        }
        System.out.println("The New available array will be : ");
        for(int i=0;i<R;i++) System.out.print(available[i]+" ");
        System.out.println();
    }

    void Request(int process,int [] arr){
        boolean flag2=true;
        for(int i=0;i<R;i++){
            if(arr[i]>need[process][i]||arr[i]>available[i]){
                flag2=false;
                break;
            }
        }
        boolean flag=false;
        for(int i=0;i<R;i++){
            if(arr[i]<=need[process][i] && available[i]>=arr[i]&&flag2){
                flag=true;
                need[process][i]=need[process][i]-arr[i];
                available[i]=available[i]-arr[i];
                allocation[process][i]=allocation[process][i]+arr[i];
            }
            else{
                flag=false;
                System.out.println("The Requested Resources are not available");
                Recovery();
                break;

            }
        }
        if(flag) {
            Release(process);
        }
        System.out.println("The New available array will be : ");
        for(int i=0;i<R;i++) System.out.print(available[i]+" ");
        System.out.println();
    }

    void Release(int process){
        for(int i=0;i<R;i++){
            available[i]=available[i]+allocation[process][i];
            allocation[process][i]=0;
            need[process][i]=maximum[process][i];
        }
    }

    void Recovery() {
        for(int i=0;i<P;i++)
        {
            for(int j=0;j<R;j++)
            {
                need[i][j]=maximum[i][j];
                available[j]=available[j]+allocation[i][j];
                allocation[i][j]=0;
            }
        }
    }




}
