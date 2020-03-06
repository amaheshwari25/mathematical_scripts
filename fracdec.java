import java.io.*;
import java.util.*;


/*
 * convert a fraction N/D to a decimal, with repeating segments denoted by parentheses
 * currently just brute-force checking: O(D) time
 */

public class fracdec {

    static int[] digs;
    static TreeMap<Integer, Integer> seen = new TreeMap<>();

    public static void main(String[] args) throws IOException {

        BufferedReader f = new BufferedReader(new FileReader("fracdec.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
        StringTokenizer st = new StringTokenizer(f.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int predec = N/D;
        digs=new int[D+1]; 

        int postdecn = N%D;
        int rem = postdecn*10; // append the first 0 in division

        int i =0;
        boolean flag = false;
        int startparen = -1;
        if(rem==0){ // for integer= "x.0" case
            digs[i]=0; i++;
        }
        while(rem != 0 && !flag){
            if(seen.containsKey(rem)){
                flag=true;
                startparen=seen.get(rem);
                continue;
            }
            digs[i] = rem/D;
            seen.put(rem, i);

            rem=(rem%D)*10;
            i++;
        }

        int linelength=76;
        int charct = 0;
        String pre=String.valueOf(predec)+".";
        for(int j = 0; j < pre.length(); j++){
            pw.print(pre.charAt(j));
            charct++;
            if(charct%linelength==0) pw.print("\n");
        }

        for(int j = 0; j < i; j++){
            if(j==startparen){ pw.print("("); charct++;if(charct%linelength==0) pw.print("\n");}
            pw.print(digs[j]);
            charct++;
            if(charct%linelength==0) pw.print("\n");
        }
        if(flag){ pw.println(")");}
        else{if(charct%linelength!=0)pw.println();}
        pw.close();

    }


}
