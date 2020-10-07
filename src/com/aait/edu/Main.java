package com.aait.edu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to our scheduling algorithms simulator");
        System.out.println("Choose to see: (1) FCFSA (2) to see SJR(shortest-job-first) (3) preemptive SJF (4) Priority Scheduling(Preemptive) (5) Round Robin ");
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
                    PreemptiveSJF premptiveSJF = new PreemptiveSJF();
                    premptiveSJF.initializePremptiveInputs();
                case 4:
                    PriorityScheduling priorityScheduling = new PriorityScheduling();
                    priorityScheduling.initializePrioritySchedulingInputs();
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
        processIDs = new ArrayList<>(numberOfProcess);


        for (int i = 0; i < numberOfProcess; i++) {

            processIDs.add(Integer.parseInt(in.readLine()));

        }


        //Burst time of all processes

        //burst_time = new int[numberOfProcess];
        burst_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter burst time for each process");

        for (int i = 0; i < numberOfProcess; i++) {
            burst_time.add(Integer.parseInt(in.readLine()));

        }

        //Arrival time of all processes

        //arrival_time = new int[numberOfProcess];
        arrival_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter arrival time for each process");
        for (int i = 0; i < numberOfProcess; i++) {
            arrival_time.add(Integer.parseInt(in.readLine()));
        }

        //Sort processes based on arrival time
        Utilities utilities = new Utilities();
        List<ArrayList<Integer>> processTimes = utilities.sortProcessesOnArrivalTime(arrival_time, processIDs, burst_time);
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
        turnAroundTime[0] = completionTime[0] - arrival_time.get(0);
        ArrayList<Integer> waitingProcessIDs = new ArrayList<>();
        ArrayList<Integer> waitingProcessBursts = new ArrayList<>();
        ArrayList<Integer> waitingProcessArrivalTimes = new ArrayList<>();


        System.out.println(" Waiting time for each process is:");

        System.out.println("Process ID" + "       " + "Waiting time" + "         " + "Completion time" + "       " + "Turnaround time");

        System.out.println("-----------" + "      " + "-------------" + "         " + "----------------" + "      " + "----------------");

        System.out.println(" " + "Process " + processIDs.get(0) + "               " + wt[0] + "              " + completionTime[0] + "                 " + turnAroundTime[0]);

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
                //Swap if current process is different from the current sorted process
                if (i < processIDs.size() - 1 && processIDs.get(i) != sortedByBurst.get(0).get(0)) {
                    int tempForId;
                    int tempForBurst;
                    int tempForArrival;
                    int indexOfId;
                    tempForId = processIDs.get(i);
                    tempForBurst = burst_time.get(i);
                    tempForArrival = arrival_time.get(i);
                    indexOfId = processIDs.indexOf(sortedByBurst.get(0).get(0));
                    processIDs.set(i, sortedByBurst.get(0).get(0));
                    burst_time.set(i, sortedByBurst.get(1).get(0));
                    arrival_time.set(i, sortedByBurst.get(2).get(0));

                    processIDs.set(indexOfId, tempForId);
                    burst_time.set(indexOfId, tempForBurst);
                    arrival_time.set(indexOfId, tempForArrival);

                }


                completionTime[i] = completionTime[i - 1] + sortedByBurst.get(1).get(0);
                wt[i] = completionTime[i - 1] - sortedByBurst.get(2).get(0);
                turnAroundTime[i] = completionTime[i] - sortedByBurst.get(2).get(0);
                System.out.println(" " + "Process " + sortedByBurst.get(0).get(0) + "           " + wt[i] + "                      " + completionTime[i] + "                  " + turnAroundTime[i]);
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
                turnAroundTime[i] = completionTime[i] - arrival_time.get(i);
                System.out.println(" " + "Process " + processIDs.get(i) + "      " + "      " + wt[i]);

                i++;
            }


        }
        int totalwt = 0;
        int totalTurnAroundTime = 0;
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
                processArrivalTimes.set(j + 1, processArrivalTimes.get(j));
                processIDs.set(j + 1, processIDs.get(j));
                processBursts.set(j + 1, processBursts.get(j));
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

class Process {
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time

    public Process(int pid, int bt, int art) {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
    }
}


class PreemptiveSJF {

    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(Process proc[], int n, int wt[]) {
        int rt[] = new int[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].bt;

        int complete = 0, t = 0, minm = Integer.MAX_VALUE;
        int shortest = 0, finish_time;
        boolean check = false;

        // Process until all processes gets
        // completed
        while (complete != n) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++) {
                if ((proc[j].art <= t) && (rt[j] < minm) && rt[j] > 0) {
                    minm = rt[j];
                    shortest = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[shortest]--;

            // Update minimum
            minm = rt[shortest];
            if (minm == 0)
                minm = Integer.MAX_VALUE;

            // If a process gets completely
            // executed
            if (rt[shortest] == 0) {

                // Increment complete
                complete++;
                check = false;

                // Find finish time of current process
                finish_time = t + 1;

                // Calculate waiting time
                wt[shortest] = finish_time - proc[shortest].bt - proc[shortest].art;

                if (wt[shortest] < 0)
                    wt[shortest] = 0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(Process proc[], int n, int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }

    // Method to calculate average time
    static void findAvgTime(Process proc[], int n) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(proc, n, wt);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(proc, n, wt, tat);

        // Display processes along with all
        // details
        System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + proc[i].pid + "\t\t\t" + proc[i].bt + "\t\t\t " + wt[i] + "\t\t\t" + tat[i]);
        }

        System.out.println("Average waiting time = " + (float) total_wt / (float) n);
        System.out.println("Average turn around time = " + (float) total_tat / (float) n);
    }

    void initializePremptiveInputs() throws IOException {
        ArrayList<Integer> processIDs;
        ArrayList<Integer> burst_time;
        ArrayList<Integer> arrival_time;
        /*
        Process proc[] = { new Process(1, 6, 1),
                new Process(2, 8, 1),
                new Process(3, 7, 2),
                new Process(4, 3, 3)};

         */

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(" Enter the number of processes");

        int numberOfProcess = Integer.parseInt(in.readLine());


        processIDs = new ArrayList<>(numberOfProcess);
        //Process ID's

        System.out.println("Enter processes IDs");

        //processIDs = new int[numberOfProcess];
        Process proc[] = new Process[numberOfProcess];

        for (int i = 0; i < numberOfProcess; i++) {

            processIDs.add(Integer.parseInt(in.readLine()));

        }


        //Burst time of all processes

        //burst_time = new int[numberOfProcess];
        burst_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter burst time for each process");

        for (int i = 0; i < numberOfProcess; i++) {
            burst_time.add(Integer.parseInt(in.readLine()));

        }

        //Arrival time of all processes

        //arrival_time = new int[numberOfProcess];
        arrival_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter arrival time for each process");
        for (int i = 0; i < numberOfProcess; i++) {
            arrival_time.add(Integer.parseInt(in.readLine()));
        }

        for (int i = 0; i < processIDs.size(); i++) {
            proc[i] = new Process(processIDs.get(i), burst_time.get(i), arrival_time.get(i));
        }

        int userChoice;
        do {
            System.out.println("Enter 1 to calculate waiting, turnaround and average times \n Enter 2 to exit");
            userChoice = Integer.parseInt(in.readLine());
            switch (userChoice) {
                case 1:
                    findAvgTime(proc, proc.length);
                case 2:
                    System.exit(0);

            }
        } while (userChoice != 2);
    }
}

class ProcessModelForPriority {
    int pid; // Process ID
    int bt; // Burst Time
    int art; // Arrival Time
    int priority;

    public ProcessModelForPriority(int pid, int bt, int art, int priority) {
        this.pid = pid;
        this.bt = bt;
        this.art = art;
        this.priority = priority;
    }
}

class PriorityScheduling {
    void initializePrioritySchedulingInputs() throws IOException {

        ArrayList<Integer> processIDs;
        ArrayList<Integer> burst_time;
        ArrayList<Integer> arrival_time;
        ArrayList<Integer> priority;
        /*
        Process proc[] = { new Process(1, 6, 1),
                new Process(2, 8, 1),
                new Process(3, 7, 2),
                new Process(4, 3, 3)};

         */

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(" Enter the number of processes");

        int numberOfProcess = Integer.parseInt(in.readLine());


        processIDs = new ArrayList<>(numberOfProcess);
        //Process ID's

        System.out.println("Enter processes IDs");

        //processIDs = new int[numberOfProcess];
        ProcessModelForPriority proc[] = new ProcessModelForPriority[numberOfProcess];

        for (int i = 0; i < numberOfProcess; i++) {

            processIDs.add(i, Integer.parseInt(in.readLine()));

        }


        //Burst time of all processes

        //burst_time = new int[numberOfProcess];
        burst_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter burst time for each process");

        for (int i = 0; i < numberOfProcess; i++) {
            burst_time.add(i, Integer.parseInt(in.readLine()));

        }

        //Arrival time of all processes

        //arrival_time = new int[numberOfProcess];
        arrival_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter arrival time for each process");
        for (int i = 0; i < numberOfProcess; i++) {
            arrival_time.add(i, Integer.parseInt(in.readLine()));
        }

        priority = new ArrayList<>(numberOfProcess);

        System.out.println("Enter priority for each process respectively");
        for (int i = 0; i < numberOfProcess; i++) {
            priority.add(i, Integer.parseInt(in.readLine()));
        }

        for (int i = 0; i < processIDs.size(); i++) {
            proc[i] = new ProcessModelForPriority(processIDs.get(i), burst_time.get(i), arrival_time.get(i), priority.get(i));
        }

        int userChoice;
        do {
            System.out.println("Enter 1 to calculate waiting, turnaround and average times \n Enter 2 to exit");
            userChoice = Integer.parseInt(in.readLine());
            switch (userChoice) {
                case 1:
                    findAvgTime(proc, proc.length);
                case 2:
                    System.exit(0);

            }
        } while (userChoice != 2);
    }

    // Method to calculate average time
    static void findAvgTime(ProcessModelForPriority proc[], int n) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all
        // processes
        findWaitingTime(proc, n, wt);

        // Function to find turn around time for
        // all processes
        findTurnAroundTime(proc, n, wt, tat);

        // Display processes along with all
        // details
        System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

        // Calculate total waiting time and
        // total turnaround time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + proc[i].pid + "\t\t\t" + proc[i].bt + "\t\t\t " + wt[i] + "\t\t\t" + tat[i]);
        }

        System.out.println("Average waiting time = " + (float) total_wt / (float) n);
        System.out.println("Average turn around time = " + (float) total_tat / (float) n);
    }

    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(ProcessModelForPriority proc[], int n, int wt[]) {
        int rt[] = new int[n];

        // Copy the burst time into rt[]
        for (int i = 0; i < n; i++)
            rt[i] = proc[i].bt;

        int complete = 0, t = 0, max = 0;
        int highestPriority = 0, finish_time;
        boolean check = false;

        //System.out.println("made it to waiting time method");
        // Process until all processes gets
        // completed
        while (complete != n) {

            // Find process with minimum
            // remaining time among the
            // processes that arrives till the
            // current time`
            for (int j = 0; j < n; j++) {
                if ((proc[j].art <= t) && (proc[j].priority >= max) && rt[j] > 0) {
                    max = proc[j].priority;
                    highestPriority = j;
                    check = true;
                }
            }

            if (check == false) {
                t++;
                continue;
            }

            // Reduce remaining time by one
            rt[highestPriority]--;

            /*
            // Update the maximum priority
            max = rt[highestPriority];
            if (max == 0)
                max = 0;

             */
            max = 0;

            // If a process gets completely
            // executed
            if (rt[highestPriority] == 0) {

                proc[highestPriority].priority = -1;
                // Increment complete
                complete++;
                check = false;

                // Find finish time of current process
                finish_time = t + 1;

                // Calculate waiting time
                wt[highestPriority] = finish_time - proc[highestPriority].bt - proc[highestPriority].art;

                if (wt[highestPriority] < 0)
                    wt[highestPriority] = 0;
            }
            // Increment time
            t++;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(ProcessModelForPriority proc[], int n, int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = proc[i].bt + wt[i];
    }


}


class RR {
    int[] processIDs;
    int[] burst_time;

    // Method to calculate average time
    static void findavgTime(int processes[], int n, int bt[], int timeQuantum) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        // Function to find waiting time of all processes
        findWaitingTime(processes, n, bt, wt, timeQuantum);

        // Function to find turn around time for all processes
        findTurnAroundTime(processes, n, bt, wt, tat);

        // Display processes along with all details
        System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.println(" " + (i + 1) + "\t\t\t" + bt[i] + "\t\t\t " + wt[i] + "\t\t\t " + tat[i]);
        }

        System.out.println("Average waiting time = " + (float) total_wt / (float) n);
        System.out.println("Average turn around time = " + (float) total_tat / (float) n);
    }

    // Method to find the waiting time for all
    // processes
    static void findWaitingTime(int processes[], int n, int bt[], int wt[], int timeQuantum) {
        // Make a copy of burst times bt[] to store remaining burst times.
        int rem_bt[] = new int[n];
        for (int i = 0; i < n; i++)
            rem_bt[i] = bt[i];

        int t = 0; // Current time

        // Keep traversing processes in rr manner
        // until all of them are not done.
        while (true) {
            boolean done = true;

            // Traverse all processes one by one repeatedly
            for (int i = 0; i < n; i++) {
                // If burst time of a process is greater than 0
                // then only need to process further
                if (rem_bt[i] > 0) {
                    done = false; // There is a pending process

                    if (rem_bt[i] > timeQuantum) {
                        // Increase the value of t i.e. shows
                        // how much time a process has been processed
                        t += timeQuantum;

                        // Decrease the burst_time of current process
                        // by time timeQuantum
                        rem_bt[i] -= timeQuantum;
                    }

                    // If burst time is smaller than or equal to
                    // time timeQuantum. Last cycle for this process
                    else {
                        // Increase the value of t i.e. shows
                        // how much time a process has been processed
                        t = t + rem_bt[i];

                        // Waiting time is current time minus time
                        // used by this process
                        wt[i] = t - bt[i];

                        // As the process gets fully executed
                        // make its remaining burst time = 0
                        rem_bt[i] = 0;
                    }
                }
            }

            // If all processes are done
            if (done == true)
                break;
        }
    }

    // Method to calculate turn around time
    static void findTurnAroundTime(int processes[], int n, int bt[], int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++)
            tat[i] = bt[i] + wt[i];
    }

    void initializeRRinputs() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(" Enter the number of processes");

        int numberOfProcess = Integer.parseInt(in.readLine());

        //Process ID's

        System.out.println("Enter processes IDs");

        //processIDs = new int[numberOfProcess];
        processIDs = new int[numberOfProcess];


        for (int i = 0; i < numberOfProcess; i++) {

            processIDs[i] = Integer.parseInt(in.readLine());

        }


        //Burst time of all processes

        //burst_time = new int[numberOfProcess];
        burst_time = new int[numberOfProcess];

        System.out.println("Enter burst time for each process");

        for (int i = 0; i < numberOfProcess; i++) {
            burst_time[i] = Integer.parseInt(in.readLine());

        }
        System.out.println("Enter Time Quantum");

        int timeQuantum = Integer.parseInt(in.readLine());

        int userChoice;
        do {
            System.out.println("Enter 1 to see waiting time, turnaround time and their averages");
            System.out.println("Enter 2 to exit");
            userChoice = Integer.parseInt(in.readLine());
            switch (userChoice) {
                case 1:
                    findavgTime(processIDs, numberOfProcess, burst_time, timeQuantum);
                case 2:
                    System.exit(0);
            }
        } while (userChoice != 2);
    }
}
/*
class RR {
    ArrayList<Integer> processIDs;
    ArrayList<Integer> burst_time;

    void initializeRRinputs() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(" Enter the number of processes");

        int numberOfProcess = Integer.parseInt(in.readLine());

        //Process ID's

        System.out.println("Enter processes IDs");

        //processIDs = new int[numberOfProcess];
        processIDs = new ArrayList<>(numberOfProcess);


        for (int i = 0; i < numberOfProcess; i++) {

            processIDs.add(Integer.parseInt(in.readLine()));

        }


        //Burst time of all processes

        //burst_time = new int[numberOfProcess];
        burst_time = new ArrayList<>(numberOfProcess);

        System.out.println("Enter burst time for each process");

        for (int i = 0; i < numberOfProcess; i++) {
            burst_time.add(Integer.parseInt(in.readLine()));

        }
        System.out.println("Enter Time Quantum");

        int timeQuantum = Integer.parseInt(in.readLine());

        int userChoice;

        do {

            System.out.println("Choose 1 to see waiting time and  turnaround time: 2 to exit");


            userChoice = Integer.parseInt(in.readLine());

            switch (userChoice) {

                case 1:

                    double average_wt = waitingTimeTurnaroundTimeRR(processIDs, burst_time, timeQuantum);


                    break;

                case 2:

                    System.out.println("Good bye!");

                    System.exit(-1);

                    break;

                default:

                    System.out.println(" U have chosen wrong choice");

                    break;

            }
        } while (userChoice != 2);


    }

    private double waitingTimeTurnaroundTimeRR(ArrayList<Integer> processIDs, ArrayList<Integer> burst_time, int timeQuantum) {
        int[] wt = new int[processIDs.size()];
        int[] turnAroundTimes = new int[processIDs.size()];
        int completionTimeOfAll = 0;
        List<Integer> time = new ArrayList<Integer>();
        List<Integer> completedProcesses = new ArrayList<>();

        for (int i = 0; i < wt.length; i++) {
            wt[i] = 0;

        }
        System.out.println("Process ID          Time When done");
        System.out.println("----------          ----");


        if (burst_time.get(0) - timeQuantum > 0) {
            // burst_time.add(burst_time.get(0) - timeQuantum);
            // processIDs.add(processIDs.get(0));
            time.add(0, timeQuantum);
            wt[0] = 0;
            System.out.println(processIDs.get(0) + "                    " + time.get(0));

        } else if (burst_time.get(0) - timeQuantum <= 0) {
            time.add(0, burst_time.get(0));
            wt[0] = 0;
            System.out.println(processIDs.get(0) + "                    " + time.get(0));

        }


        for (int i = 0; i < processIDs.size(); i++) {
            completionTimeOfAll += burst_time.get(i);

            if (burst_time.get(i) - timeQuantum > 0) {
                burst_time.add(burst_time.get(i) - timeQuantum);
                processIDs.add(processIDs.get(i));
                //System.out.println(processIDs.size());
                if (i != 0) {
                    time.add(i, time.get(i - 1) + timeQuantum);

                    //wt[processIDs.get(i)-1]+=wt[processIDs.get(i-1)-1] ;
                    for (int j = 0; j < processIDs.size(); j++) {
                        if (!completedProcesses.contains(processIDs.get(j)) && j != i) {
                            wt[processIDs.get(j) - 1] += timeQuantum;

                        }
                    }
                    System.out.println(processIDs.get(i) + "                    " + time.get(i));


                }


                //System.out.println(processIDs.get(i-1));

            } else if (burst_time.get(i) - timeQuantum <= 0) {

                if (i != 0) {
                    time.add(i, time.get(i - 1) + burst_time.get(i));

                    //wt[processIDs.get(i) - 1] += wt[processIDs.get(i - 1) - 1];
                    turnAroundTimes[processIDs.get(i) - 1] = time.get(i);
                    completedProcesses.add(processIDs.get(i));
                    for (int j = 0; j < processIDs.size(); j++) {
                        if (!completedProcesses.contains(processIDs.get(j)) && j != i) {

                            wt[processIDs.get(j) - 1] += burst_time.get(j);
                        }
                    }
                    System.out.println(processIDs.get(i) + "                    " + time.get(i));

                }
                //System.out.println(processIDs.size());
                //System.out.println(processIDs.get(i-1));

            }
        }
//        System.out.println("Waiting Times");
//        for (int i = 0; i < wt.length; i++) {
//            System.out.println(wt[i]);
//
//        }
        System.out.println("====================");
        System.out.println("TurnAround Times");
        int totalTurnaround = 0;
        for (int i = 0; i < turnAroundTimes.length; i++) {
            System.out.println(turnAroundTimes[i]);
            totalTurnaround += turnAroundTimes[i];
        }
        System.out.println("====================");
        System.out.println("Average TurnAround Time = " + totalTurnaround / turnAroundTimes.length);
        return 0;
    }
}


 */