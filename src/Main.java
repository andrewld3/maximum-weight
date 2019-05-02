import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Loads all of the jobs into an Arraylist
        ArrayList jobs = new ArrayList<Vertex>();
        jobs = loadJobs();
        getSubset();
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

                int id, weight, end;
                int[] prereqs = new int[5];

                id = Integer.parseInt(parse[0]);
                weight = Integer.parseInt(parse[1]);
                for(int i = 2; i < parse.length; i++) {
                    prereqs[i - 2] = Integer.parseInt(parse[i]);
                }
                Vertex temp = new Vertex(id, weight);
                temp.addPreReq(prereqs);
                jobs.add(temp);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        //returns the array list
        return jobs;
    }

    private static void getSubset( ) {
        //The prereqa length is always 5, check for non-0's for removal.
    }
}
