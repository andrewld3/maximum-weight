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
        ArrayList<Vertex> bestJobs, tempList;

        //Check all of the jobs at once to find weight
        totalWeight = checkProfit(jobs);

        // Checks if all the jobs produce a positive profit.
        if(totalWeight > bestWeight) {
            bestJobs = jobs;
            bestWeight = totalWeight;
        }

        //Copies array to prepare for removal;
        tempList = jobs;

        while(tempList.size() > 0) {

        }


        //The prereqs length is always 5, check for non-0's for removal.

    }

    private static int checkProfit(ArrayList<Vertex> arrayList) {
        int weight = 0;
        for(int i = 0; i < arrayList.size(); i++) {
            Vertex temp = arrayList.get(i);
            weight = weight + temp.getJobWeight();
        }
        return weight;
    }

    private static boolean checkPrereq(ArrayList<Vertex> arraylist, int[] prereq) {
        boolean result = false;

        for(int i = 0; i < arraylist.size(); i++) {

        }
    }
}
