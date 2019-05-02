import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Loads all of the jobs into an Arraylist
        ArrayList jobs = new ArrayList<Vertex>();
        jobs = loadJobs();

        //Finds the largest possible subset of positive weights
        getSubset(jobs);
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

    private static void getSubset(ArrayList<Vertex> jobs) {

        int totalWeight = 0, bestWeight = 0;
        ArrayList<Vertex> bestJobs;

        //Check all of the jobs at once to find weight
        totalWeight = checkProfit(jobs);

        // Sets the best weight and job list to all jobs
        bestJobs = jobs;
        bestWeight = totalWeight;

        int size = jobs.size();
        int[] jobCount = new int[size];

        //Get an array of job numbers to parse through
        for(int i = 0; i < size; i++) {
            Vertex temp = jobs.get(i);
            jobCount[i] = temp.getJobNumber();
        }


        //Create the Arraylist of Subsets
        ArrayList<ArrayList<Vertex>> subsets = new ArrayList<>();
        for(int i = 0; i < size; i++) {
            subsets.add(buildSubSet(jobs, i));
        }

        //Create the superset of subsets
        

        while(!subsets.isEmpty()) {
            for(int i = 0; i < subsets.size(); i++) {
                ArrayList<Vertex> temp = subsets.remove(i);
                totalWeight = checkProfit(temp);
                if(totalWeight > bestWeight) {
                    bestWeight = totalWeight;
                    bestJobs = temp;
                }
            }
        }


        // Checks if the weight is above 0, if it is, produces the list and the weight. If it isnt, doesn't do anything.
        if(bestWeight <= 0) {
            System.out.println("There is no profit in completing jobs.");
        } else {
            System.out.println(" The following jobs produces the most profit: ");
            System.out.print("[ ");
            for(int i = 0; i < bestJobs.size(); i++) {
                Vertex temp = bestJobs.get(i);
                System.out.print(temp.getJobNumber() + " ");
            }
            System.out.print("]");
            System.out.println();
            System.out.println("Total Profit from completing jobs: " + bestWeight);
        }


    }

    private static int checkProfit(ArrayList<Vertex> arrayList) {
        int weight = 0;
        for(int i = 0; i < arrayList.size(); i++) {
            Vertex temp = arrayList.get(i);
            weight = weight + temp.getJobWeight();
        }
        return weight;
    }

    private static ArrayList<Vertex> buildSubSet(ArrayList<Vertex> jobs, int jobNum) {

    }
}
