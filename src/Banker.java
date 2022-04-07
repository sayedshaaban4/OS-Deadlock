import java.util.ArrayList;
import java.util.Scanner;
public class Banker {
    Scanner cin = new Scanner(System.in);
    int [] available;
    int [] Temp_available;
    int [][] maximum;
    int [][] allocation;
    int [][] need;
    int [][] Temp_need;
    ArrayList<Integer> safeOrder = new ArrayList<>();
    int P,R;

    Banker(int n,int m){
        P=n;
        R=m;
        available = new int[R];
        Temp_available = new int[R];
        maximum = new int[P][R];
        allocation = new int[P][R];
        need = new int[P][R];
        Temp_need = new int[P][R];
    }

    void initialize(){
        System.out.println("Enter the number of the available Resources respectively  : ");
        String tempRes=cin.nextLine();
        String[] tempResArr =tempRes.split(" ");
        for(int i=0;i<R;i++) {
            available[i] = Integer.parseInt(tempResArr[i]);
            Temp_available[i]=available[i];
        }

        for(int i=0;i<P;i++){
            System.out.println("Enter the number of the Maximum needs of Resources in process number ("+(i) +") respectively : ");
            String tempMaxRes=cin.nextLine();
            String[] tempMaxResArr =tempMaxRes.split(" ");
            for(int j=0;j<R;j++){
                maximum[i][j]=Integer.parseInt(tempMaxResArr[j]);
            }
        }

        for(int i=0;i<P;i++){
            System.out.println("Enter the number of the Allocation of Resources in process number ("+(i) +") respectively : ");
            String tempAllo=cin.nextLine();
            String[] tempAlloArr =tempAllo.split(" ");

            for(int j=0;j<R;j++){
                allocation[i][j]= Integer.parseInt(tempAlloArr[j]);
            }
        }

        for(int i=0;i<P;i++){
            for(int j=0;j<R;j++){
                need[i][j]= maximum[i][j]-allocation[i][j];
            }
        }
    }

    boolean isSafe(){
        Boolean [] finish = new Boolean[P];
        ArrayList <Integer> safeState = new ArrayList<>();

        for (int i=0;i<P;i++)finish[i]=false;

        for(int i=0;i<P*P;i++){
            int cnt=0;
            for(int j=0;j<R;j++){
                if(Temp_need[i%P][j] <=Temp_available[j] && !finish[(i%P)])cnt++;
            }
            if(cnt==R){
                finish[(i%P)]=true;
                for(int j=0;j<R;j++){
                    Temp_available[j] = Temp_available[j] + allocation[i%P][j];
                }
                safeState.add(i%P);
            }
        }

        if(safeState.size()==P) {
            safeOrder.clear();
            for(int i=0;i<P;i++) {
                safeOrder.add(safeState.get(i));
            }
            need=Temp_need;
            System.out.println("To make The System at Safe State, You Must Follow this processes order : ");
            for (Integer Order : safeOrder) {
                System.out.println("Process number : " + Order);
            }
            return true;
        }
        return false;
    }

    void Request(int process,int [] arr){
        Temp_need=need;
        for(int i=0;i<R;i++){
            Temp_need[process][i]=Temp_need[process][i]+arr[i];
        }
        int count=0;
        boolean flag=true;

        while(!isSafe()){
            if(count==(P-1)){
                System.out.println("Unable to make request...");
                flag=false;
                break;
            }
            count++;
            Recovery(process);
        }

        if(flag){
            for (int i = 0; i < R; i++) {
                if (arr[i] <= need[process][i] && available[i] >= arr[i]) {
                    need[process][i] = need[process][i] - arr[i];
                    available[i] = available[i] - arr[i];
                    allocation[process][i] = allocation[process][i] + arr[i];
                }
            }

            System.out.println("The New available resources array will be : ");
            for (int i = 0; i < R; i++) System.out.print(available[i] + " ");
            System.out.println();
        }

    }

    void Release(int process,int [] arr){
        for(int i=0;i<R;i++){
            allocation[process][i]=allocation[process][i]-arr[i];
            available[i]=available[i]+arr[i];
        }
        System.out.println("The New available resources array will be : ");
        for(int i=0;i<R;i++) System.out.print(available[i]+" ");
        System.out.println();
    }

    void Recovery(int process) {
        int maximumSum = -1;
        int index=-1;
        for (int i = 0; i < P; i++) {
            if (i != process) {
                int sum = 0;
                for (int j = 0; j < R; j++)
                    sum += maximum[i][j];
                if(sum>maximumSum){
                    maximumSum=sum;
                    index=1;
                }
            }
        }
        for (int i = 0; i < R; i++) {
            allocation[index][i] = 0;
        }
    }
}