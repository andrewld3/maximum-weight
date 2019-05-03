import java.util.ArrayList;

public class Graph {

    private ArrayList<Vertex> jobList;
    private Vertex[] jobs;

    public Graph(ArrayList<Vertex> vertexList) {
        jobList = vertexList;
        jobs = new Vertex[jobList.size()];
        for(int i = 0; i < jobs.length; i++) {
            jobs[i] = jobList.get(i);
        }
    }

    public int checkProfit(ArrayList<Integer> profitList) {
        int test, result = 0;

        for(int i = 0; i < profitList.size(); i++) {
            test = profitList.get(i);
            for(int j = 0; j < jobs.length; j++) {
                if(jobs[j].getJobNumber() == test)
                    result = result + jobs[j].getJobWeight();
            }
        }
        return result;
    }

    public ArrayList<Integer> getSubset(int jobNum) {
        ArrayList<Integer> dependList = new ArrayList<>();
        for(int i = 0; i < jobs.length; i++) {
            if(jobs[i].getJobNumber() == jobNum) {
                dependList.add(jobNum);
                dependList = recurseSubset(dependList, jobNum);
            }
        }

        ArrayList<Integer> prunedList = new ArrayList<>();
        for(Integer i : dependList) {
            if(!prunedList.contains(i)) {
                prunedList.add(i);
            }
        }

        return prunedList;
    }

    private ArrayList<Integer> recurseSubset(ArrayList<Integer> list, int jobNum) {
        int[] temp;
        int req;
        for(int i = 0; i < jobs.length; i++) {
            if(jobs[i].getJobNumber() == jobNum) {
                temp = jobs[i].getPrereq();
                for(int j = 0; j < temp.length; j++) {
                    req = temp[j];
                    if(req != 0) {
                        list.add(req);
                        list = recurseSubset(list, req);
                    }
                }
            }
        }
        return list;
    }

    public ArrayList<Integer> findMaximumProfit() {
        int topWeight = 0;
        ArrayList<Integer> topJobs = null;

        //Gets all the possible subsets
        ArrayList<ArrayList<Integer>> workingSet = new ArrayList<>();
        for(int i = 1; i < jobs.length + 1; i++) {
            workingSet.add(getSubset(i));
        }

        int allWeight = 0;
        ArrayList<Integer> allJobs = new ArrayList<>();
        for(int i = 0; i < jobs.length; i++) {
            allWeight = allWeight + jobs[i].getJobWeight();
        }
        if(topWeight < allWeight) {
            topWeight = allWeight;
            for(int i = 0; i < jobs.length; i++) {
                allJobs.add(jobs[i].getJobNumber());
            }
            topJobs = allJobs;
        }

        //Checks if any subsets become top weight
        for(int i = 0; i < workingSet.size(); i++) {
            if(topWeight < checkProfit(workingSet.get(i))) {
                topWeight = checkProfit(workingSet.get(i));
                topJobs = workingSet.get(i);
            }
        }

        for(int i = 0; i < workingSet.size(); i++) {
            ArrayList<Integer> set1 = (ArrayList<Integer>) workingSet.get(i).clone();
            for(int j = 0; j < workingSet.size(); j++) {
                ArrayList<Integer> set2 = (ArrayList<Integer>) workingSet.get(j).clone();
                for(Integer k : set1) {
                    if(!set2.contains(k)) {
                        set2.add(k); //THIS CHANGED workingSet[1]..... FUCKING WHY!!!!
                    }
                }
                ArrayList<Integer> checkJob = set2;
                int weight;
                weight = checkProfit(set2);
                if(weight > topWeight) {
                    topWeight = weight;
                    topJobs = checkJob;
                }
            }
        }

        return topJobs;
    }
}
