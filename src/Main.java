import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Loads all of the jobs into an Arraylist
        ArrayList jobs = new ArrayList<Vertex>();
        jobs = loadJobs();
        Graph g = new Graph(jobs);
        ArrayList<Integer> topJobs = g.findMaximumProfit();
        if(topJobs == null) {
            System.out.print("No jobs produce profit!");
        } else {
            System.out.print("Jobs to be Performed: [ ");
            for (int i = 0; i < topJobs.size(); i++) {
                System.out.print(topJobs.get(i) + " ");
            }
            System.out.print("]");
            System.out.println();
            System.out.println("Total Profit: " + g.checkProfit(topJobs));
        }
    }

    private static ArrayList loadJobs( ) {
        ArrayList jobs = new ArrayList<Vertex>();

        try {
            //Opens the file jobs.txt
            Scanner fileIn = new Scanner(new File("jobs.txt"));

            //Parses the line of jobs, separated by one or more spaces
            String line, delim;
            String[] parse;

            //Assumes file follows the following format: jobNum, weight, prereqs+
            while(fileIn.hasNextLine()) {

                //Breaks the line into an array of strings
                line = fileIn.nextLine();
                delim = "[ ]+";
                parse = line.split(delim);

                // Gets the id, weight, and prerequisites from each line
                int id, weight;
                int[] prereqs = new int[5];
                id = Integer.parseInt(parse[0]);
                weight = Integer.parseInt(parse[1]);
                for(int i = 2; i < parse.length; i++) {
                    prereqs[i - 2] = Integer.parseInt(parse[i]);
                }

                //Loads the object with id, weight, prereq
                Vertex temp = new Vertex(id, weight);
                temp.addPreReq(prereqs);

                //Adds the object to the Arraylist
                jobs.add(temp);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        //returns the array list
        return jobs;
    }
}
