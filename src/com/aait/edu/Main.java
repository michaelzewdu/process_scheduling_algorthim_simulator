package com.aait.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to our scheduling algorithms simulator");
        System.out.println("Choose to see: (1) FCFSA (2) to see SJR(shortest-job-first) (3) preemptive SJF (4) Priority Scheduling (5) Round Robin ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int menuItemChosen = Integer.parseInt(in.readLine());
        do {
            switch (menuItemChosen) {
                case 1:
                    FCFSA fcfsa = new FCFSA();
                    fcfsa.initializeFcfsaInputs();
                case 2:
                    SJR sjr = new SJR();
                    sjr.initializeSjrInputs();
                case 3:

                case 4:

                case 5:
                    RR rr = new RR();
                    rr.initializeRRinputs();
                default:
                    System.out.println("Your choice is not available from the list of choices");
            }

        } while (menuItemChosen != 3);


    }


}

class FCFSA {
    public static double waitingTimeFcfsa(int[] process, int n, int[] bt) {

        // waiting time for first process is 0

        int[] wt = new int[n];

        wt[0] = 0;

        System.out.println(" Waiting time for each process is:");

        System.out.println("Process ID" + "       " + "Waiting time");

        System.out.println("-----------" + "      " + "-------------");

        System.out.println(" " + "Process 1" + "      " + "      " + wt[0]);

        // calculate waiting time for all other processes


        for (int i = 1; i < n; i++) {

            wt[i] = bt[i - 1] + wt[i - 1];

            System.out.println(" " + "Process" + " " + process[i] + "            " + wt[i]);


        }

        //calculate average waiting time

        double total_wt = 0;


        for (int j = 0; j < n; j++) {

            total_wt += wt[j];

        }

        double average_wt = total_wt / n;

        return average_wt;


    }


    //Function to calculate waiting time for all processes

    public static double turnAroundTimeFcfsa(int[] process, int numberOfProcesses, int[] bt) {


        int[] wt = new int[numberOfProcesses];

        wt[0] = 0;
        //tat stands for turn around time
        int[] tat = new int[numberOfProcesses];

        for (int i = 1; i < numberOfProcesses; i++) {

            wt[i] = bt[i - 1] + wt[i - 1];

        }

        for (int j = 0; j < numberOfProcesses; j++) {


            tat[j] = bt[j] + wt[j];

        }

        System.out.println("Turnaround time for each process is:");

        System.out.println("Process ID" + "       " + "Turnaround time");

        System.out.println("-----------" + "       " + "-------------");


        for (int j = 0; j < numberOfProcesses; j++) {

            System.out.println(" " + "Process" + " " + process[j] + "             " + tat[j]);

        }

        //Calculate average turnaround time

        double total_tat = 0;


        for (int k = 0; k < numberOfProcesses; k++) {

            total_tat += tat[k];

        }

        double average_tat = total_tat / numberOfProcesses;

        return average_tat;


    }


    //Function to calculate turn around time

    void initializeFcfsaInputs() throws IOException {


        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(" Enter the number of processes");

        int numberOfProcess = Integer.parseInt(in.readLine());

        //Process ID's

        System.out.println("Enter processes");

        int[] process = new int[numberOfProcess];


        for (int i = 0; i < process.length; i++) {

            process[i] = Integer.parseInt(in.readLine());

        }


        //Burst time of all processes


        int[] burst_time = new int[numberOfProcess];

        System.out.println("Enter burst time for each process");

        for (int i = 0; i < burst_time.length; i++) {

            burst_time[i] = Integer.parseInt(in.readLine());

        }

        int a;

        do {

            System.out.println("Choose 1 to see waiting time: 2 to see turnaround time: 3 to exit");


            a = Integer.parseInt(in.readLine());

            switch (a) {

                case 1:

                    double average_wt = waitingTimeFcfsa(process, numberOfProcess, burst_time);

                    System.out.println("The average waiting time is:" + average_wt);

                    break;

                case 2:


                    double average_tat = turnAroundTimeFcfsa(process, numberOfProcess, burst_time);

                    System.out.println("The average turnaround time is:" + average_tat);

                    break;

                case 3:

                    System.out.println("Good bye!");

                    System.exit(-1);

                    break;

                default:

                    System.out.println(" U have chosen wrong choice");

                    break;

            }
        } while (a != 3);

    }


}

class SJR {
    ArrayList<Integer> processIDs;
    ArrayList<Integer> burst_time;
    ArrayList<Integer> arrival_time;

    void initializeSjrInputs() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(" Enter the number of processes");

        int numberOfProcess = Integer.parseInt(in.readLine());

        //Process ID's

        System.out.println("Enter processes IDs");

        //processIDs = new int[numberOfProcess];
        processIDs= new ArrayList<>(numberOfProcess);


        for (int i = 0; i < numberOfProcess; i++) {

            processIDs.add( Integer.parseInt(in.readLine()));

        }


        //Burst time of all processes

        //burst_time = new int[numberOfProcess];
        burst_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter burst time for each process");

        for (int i = 0; i < numberOfProcess; i++) {
            burst_time.add( Integer.parseInt(in.readLine()));

        }

        //Arrival time of all processes

        //arrival_time = new int[numberOfProcess];
        arrival_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter arrival time for each process");
        for (int i = 0; i < numberOfProcess; i++) {
            arrival_time.add( Integer.parseInt(in.readLine()));
        }

        //Sort processes based on arrival time
        Utilities utilities = new Utilities();
        List<ArrayList<Integer>> processTimes = utilities.sortProcessesOnArrivalTime(arrival_time, processIDs,  burst_time);
        arrival_time = processTimes.get(0);
        processIDs = processTimes.get(1);
        burst_time = processTimes.get(2);
//        for (int i = 0; i < processIDs.length; i++) {
//            System.out.println(processIDs[i]);
//        }
        int userChoice;

        do {

            System.out.println("Choose 1 to see waiting time and  turnaround time: 2 to exit");


            userChoice = Integer.parseInt(in.readLine());

            switch (userChoice) {

                case 1:

                    double average_wt = waitingTimeTurnaroundTimeSJF(processIDs, arrival_time, burst_time);


                    break;

                case 2:

                    System.out.println("Good bye!");

                    System.exit(-1);

                    break;

                default:

                    System.out.println(" U have chosen wrong choice");

                    break;

            }
        } while (userChoice != 3);


    }

    private double waitingTimeTurnaroundTimeSJF(ArrayList<Integer> processIDs, ArrayList<Integer> arrival_time, ArrayList<Integer> burst_time) {

        // waiting time for first process is 0
        int[] wt = new int[processIDs.size()];
        wt[0] = 0;
        int[] completionTime = new int[processIDs.size()];
        int[] turnAroundTime = new int[processIDs.size()];

        completionTime[0] = burst_time.get(0);
        turnAroundTime[0] = completionTime[0]-arrival_time.get(0);
        ArrayList<Integer> waitingProcessIDs = new ArrayList<>();
        ArrayList<Integer> waitingProcessBursts = new ArrayList<>();
        ArrayList<Integer> waitingProcessArrivalTimes = new ArrayList<>();


        System.out.println(" Waiting time for each process is:");

        System.out.println("Process ID" + "       " + "Waiting time"+ "         "+"Completion time"+"       "+"Turnaround time");

        System.out.println("-----------" + "      " + "-------------"+"         "+"----------------"+"      "+"----------------");

        System.out.println(" " + "Process " + processIDs.get(0) + "               " + wt[0]+"              "+completionTime[0]+"                 "+turnAroundTime[0]);

        // calculate waiting time for all other processes
        int i = 1;
        int j = 1;
        while (i < processIDs.size()) {

            for (j = i; j < processIDs.size(); j++) {
                if (completionTime[i - 1] > arrival_time.get(j)) {
                    //System.out.println(processIDs.get(j));
                    //System.out.println(processIDs[j]);
                    waitingProcessIDs.add(processIDs.get(j));
                    waitingProcessBursts.add(burst_time.get(j));
                    waitingProcessArrivalTimes.add(arrival_time.get(j));
                }
            }
//            for (int k = 0; k < waitingProcessIDs.size(); k++) {
//                System.out.println(waitingProcessIDs.get(k));
//            }

            if (!waitingProcessIDs.isEmpty()) {
                Utilities utilities = new Utilities();
                List<ArrayList<Integer>> sortedByBurst = utilities.sortProcessesOnBurstTime(waitingProcessBursts, waitingProcessIDs, waitingProcessArrivalTimes);


//                for (int k = 0; k < sortedByBurst.get(0).size(); k++) {
//                    System.out.println(sortedByBurst.get(0).get(k));
//
//                }

                if(i<processIDs.size()-1 && processIDs.get(i) != sortedByBurst.get(0).get(0)){
                    int tempForId;
                    int tempForBurst;
                    int tempForArrival;
                    int indexOfId;
                    tempForId=processIDs.get(i);
                    tempForBurst=burst_time.get(i);
                    tempForArrival=arrival_time.get(i);
                    indexOfId= processIDs.indexOf(sortedByBurst.get(0).get(0));
                    processIDs.set(i,sortedByBurst.get(0).get(0));
                    burst_time.set(i,sortedByBurst.get(1).get(0));
                    arrival_time.set(i,sortedByBurst.get(2).get(0));

                    processIDs.set(indexOfId,tempForId);
                    burst_time.set(indexOfId,tempForBurst);
                    arrival_time.set(indexOfId,tempForArrival);

                }



                completionTime[i] = completionTime[i - 1] + sortedByBurst.get(1).get(0);
                wt[i] = completionTime[i - 1] - sortedByBurst.get(2).get(0);
                turnAroundTime[i] = completionTime[i]- sortedByBurst.get(2).get(0);
                System.out.println(" " + "Process " + sortedByBurst.get(0).get(0) + "           " + wt[i]+"                      "+completionTime[i]+"                  "+turnAroundTime[i]);
               // if(processIDs.get(i) != sortedByBurst.get(0).get(0)){
                //int currentIndex=processIDs.indexOf(sortedByBurst.get(0).get(0));
                /*
                for (int k = 0; k < processIDs.size(); k++) {
                    System.out.println("Process"+processIDs.get(k)+" Arrival time"+ arrival_time.get(k));

                }

                 */
               // }

                i++;

                waitingProcessIDs.clear();
                waitingProcessBursts.clear();
                waitingProcessArrivalTimes.clear();


            } else {
                completionTime[i] = completionTime[i - 1] + burst_time.get(i);
                wt[i] = completionTime[i] - arrival_time.get(i);
                turnAroundTime[i]=completionTime[i]-arrival_time.get(i);
                System.out.println(" " + "Process " + processIDs.get(i) + "      " + "      " + wt[i]);

                i++;
            }


        }
        int totalwt = 0;
        int totalTurnAroundTime =0;
        for (int k = 0; k < processIDs.size(); k++) {
            totalwt += wt[k];
            totalTurnAroundTime += turnAroundTime[k];

        }
        System.out.println("The average waiting time is:" + totalwt / processIDs.size());
        System.out.println("The average turnaround time is:" + totalTurnAroundTime / processIDs.size());




        return 0;
    }


}

class Utilities {
    //This method was created to sort processes based on their arrival time
    //It is an insertion sort algorithm
    List<ArrayList<Integer>> sortProcessesOnArrivalTime(ArrayList<Integer> processArrivalTimes, ArrayList<Integer> processIDs, ArrayList<Integer> processBursts) {
        int j;
        int nxt_element;
        int nxt_element_processID;
        int nxt_element_burst;
        for (int i = 1; i < processArrivalTimes.size(); i++) {
            j = i - 1;
            nxt_element = processArrivalTimes.get(i);
            nxt_element_processID = processIDs.get(i);
            nxt_element_burst = processBursts.get(i);

            // Compare the current element with the next one
            while (j > -1 && processArrivalTimes.get(j) > nxt_element) {
                processArrivalTimes.set(j + 1 , processArrivalTimes.get(j));
                processIDs.set(j + 1, processIDs.get(j));
                processBursts.set(j + 1 , processBursts.get(j));
                j = j - 1;

            }
            processArrivalTimes.set(j + 1, nxt_element);
            processIDs.set(j + 1, nxt_element_processID);
            processBursts.set(j + 1, nxt_element_burst);


        }
        List<ArrayList<Integer>> processTimes = new ArrayList<>(3);

        processTimes.add(processArrivalTimes);
        processTimes.add(processIDs);
        processTimes.add(processBursts);
        return processTimes;
    }

    //This method was created to sort processes based on their burst time
    //Insertion in here too
    List<ArrayList<Integer>> sortProcessesOnBurstTime(ArrayList<Integer> waitingBursts, ArrayList<Integer> waitingProcessIds, ArrayList<Integer> waitingArrivalTimes) {
        int j;
        int nxt_element;
        int nxt_element_processID;
        int nxt_element_burst;
        //System.out.println("waiting burst size is " + waitingBursts.size());
        for (int i = 1; i < waitingBursts.size(); i++) {
            j = i - 1;
            nxt_element = waitingBursts.get(i);
            nxt_element_processID = waitingProcessIds.get(i);
            nxt_element_burst = waitingArrivalTimes.get(i);

            // Compare the current element with the next one
            while (j > -1 && waitingBursts.get(j) > nxt_element) {
                waitingBursts.set(j + 1, waitingBursts.get(j));
                waitingProcessIds.set(j + 1, waitingProcessIds.get(j));
                waitingArrivalTimes.set(j + 1, waitingArrivalTimes.get(j));
                j = j - 1;

            }
            waitingBursts.set(j + 1, nxt_element);
            waitingProcessIds.set(j + 1, nxt_element_processID);
            waitingArrivalTimes.set(j + 1, nxt_element_burst);


        }
        List<ArrayList<Integer>> processTimes = new ArrayList<>(3);

        processTimes.add(waitingProcessIds);
        processTimes.add(waitingBursts);
        processTimes.add(waitingArrivalTimes);
        return processTimes;
    }
}

class PremptiveSJF{
    void initializePremptiveInputs(){

    }
}

class PriorityScheduling{
    void initializePrioritySchedulingInputs(){

    }
}
class RR{
    ArrayList<Integer> processIDs;
    ArrayList<Integer> burst_time;

    void initializeRRinputs() throws IOException{

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(" Enter the number of processes");

        int numberOfProcess = Integer.parseInt(in.readLine());

        //Process ID's

        System.out.println("Enter processes IDs");

        //processIDs = new int[numberOfProcess];
        processIDs= new ArrayList<>(numberOfProcess);


        for (int i = 0; i < numberOfProcess; i++) {

            processIDs.add( Integer.parseInt(in.readLine()));

        }


        //Burst time of all processes

        //burst_time = new int[numberOfProcess];
        burst_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter burst time for each process");

        for (int i = 0; i < numberOfProcess; i++) {
            burst_time.add( Integer.parseInt(in.readLine()));

        }

    }
}
