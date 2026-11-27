import java.util.Arrays;
import java.util.Scanner;

public class Main{
    //Creating a Item class
    static class Item{
        int id;
        int profit;
        int weight;
        double ratio;

        Item(int id, int profit, int weight){
            this.id=id;
            this.profit=profit;
            this.weight=weight;
            this.ratio=(double) profit/weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        //Taking number of items
        System.out.println("Enter number of items: ");
        int n=sc.nextInt();

        if(n<=0){
            System.out.println("Invalid input");
            return;
        }

        //Creating an items array of type Item
        Item items[]= new Item[n];

        //Adding items to the array
        for(int i=0;i<n;i++){
            System.out.println("Enter profit and weight: ");

            int p=sc.nextInt();
            int w=sc.nextInt();

            if(w<=0){
                System.out.println("Invalid input");
                return;
            }

            items[i]=new Item(i+1,p,w);
        }

        //Taking knapsack capacity
        System.out.println("Enter knapsack capacity: ");
        int capacity=sc.nextInt();
        
        if(capacity<=0){
            System.out.println("Invalid input");
            return;
        }

        //Sorting the array in descending order of p/w ratio
        Arrays.sort(items,(a,b) ->
        Double.compare(b.ratio, a.ratio));

        //Initialising totalProfit and remaning
        double totalProfit=0;
        double remaining=capacity;

        //Displaying selected items
        System.out.println("\nSelected Items: ");

        //Using loop to traverse the array item-by-item
        for(Item m: items){
            if(remaining<=0){
                break;
            }

            if(m.profit<=0){
                continue;
            }

            //Calculating the actual amount of item taken and its corresponding profitupated
            double taken= Math.min(m.weight,remaining);
            double fraction= taken/m.weight;

            totalProfit+=fraction*m.profit;
            remaining-=taken;

            System.out.printf(
                "Item %d:  Weight=%.2f  Fraction=%.2f  Profit=%.2f%n",
                m.id, taken , fraction , fraction*m.profit);
        }
        System.out.printf("Maximum profit: %.2f%n",totalProfit);
    }
}
