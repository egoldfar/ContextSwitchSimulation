//Elisheva Goldfarb
//T00522464


import java.util.*;

public class Main {

    public static Random random = new Random();

    public static void main(String[] args) {

        SimProcessor simProcessor = new SimProcessor();

        final int QUANTUM = 5;

        ArrayList<ProcessControlBlock> ready = setUpProcesses();
        ArrayList<ProcessControlBlock> blocked = new ArrayList<>();

        ProcessControlBlock pcb = null; //Will be the variable to hold the current pcb

        //booleans to keep track of when to do a context switch, why, and whether to save or to put up a new process
        boolean newProcess = true;
        boolean pBlocked = false, quantumOut = false;

        int j = 0; //to count steps before quantum

        for(int i = 0; i < 3000; i++){

            System.out.print("Step " + i + ": ");

            if(newProcess){
                j = 0;
                if(!ready.isEmpty()) {
                    pcb = newProcess(ready, simProcessor);
                    newProcess = false;
                }
                else {
                    System.out.println("No ready processes");
                }
            } else if (pBlocked || quantumOut) {
                saveProcess(pcb, simProcessor);

                if(pBlocked) {
                    blocked.add(pcb);
                    pBlocked = false;
                } else {
                    ready.add(pcb);
                    quantumOut = false;
                }

                newProcess = true;
            } else {
                ProcessState processState = simProcessor.executeNextInstruction();
                if(processState == ProcessState.BLOCKED) {
                    pBlocked = true;
                    System.out.println("\t***** PROCESS BLOCKED *****");
                } else if (processState == ProcessState.FINISHED) {
                    newProcess = true;
                    System.out.println("\t***** PROCESS FINISHED *****");
                } else {
                    j++;
                    if (j == QUANTUM) {
                        quantumOut = true;
                        System.out.println("\t***** QUANTUM EXPIRED *****");
                    }
                }
            }

            wakeProcesses(blocked, ready);

        }


    }

    private static ProcessControlBlock newProcess(ArrayList<ProcessControlBlock> ready,SimProcessor simProcessor) {

        ProcessControlBlock pcb = ready.removeFirst();
        simProcessor.setSimProcess(pcb.getSimProcess());
        simProcessor.setRegisters(pcb.getRegister1(), pcb.getRegister2(), pcb.getRegister3(), pcb.getRegister4());

        simProcessor.setCurrInstruction(pcb.getCurrInstruction());
        System.out.println("Context Switch. Restoring Process: " + simProcessor.getSimProcess().getProcName());
        System.out.println("\tInstruction: " + simProcessor.getCurrInstruction() + " Register 1: " +
                simProcessor.getRegister1() + " Register 2: " + simProcessor.getRegister2() + " Register 3: "
                + simProcessor.getRegister3() + " Register 4: " + simProcessor.getRegister4());
        return pcb;
    }

    private static void saveProcess(ProcessControlBlock pcb, SimProcessor simProcessor) {
        System.out.println("Context Switch. Saving Process: " + simProcessor.getSimProcess().getProcName());
        System.out.println("\tInstruction: " + simProcessor.getCurrInstruction() + " Register 1: " +
                simProcessor.getRegister1() + " Register 2: " + simProcessor.getRegister2() + " Register 3: "
                + simProcessor.getRegister3() + " Register 4: " + simProcessor.getRegister4());

        pcb.setCurrInstruction(simProcessor.getCurrInstruction());
        pcb.setRegisters(simProcessor.getRegister1(), simProcessor.getRegister2(),
                simProcessor.getRegister3(), simProcessor.getRegister4());
    }

    private static void wakeProcesses(ArrayList<ProcessControlBlock> blocked, ArrayList<ProcessControlBlock> ready) {
        for (int l = 0; l < blocked.size(); l++) {
            ProcessControlBlock p = blocked.get(l);
            int wake = random.nextInt(10);//random probability of 30%
            if (wake < 3) {
                blocked.remove(p);
                ready.add(p);
            }
        }
    }

    public static ArrayList<ProcessControlBlock> setUpProcesses(){
        SimProcess sp1 = new SimProcess(1, "Proc1", 321);
        ProcessControlBlock pcb1 = new ProcessControlBlock(sp1);
        pcb1.setRegisters(456985, 785214, 985621, 742358);
        pcb1.setCurrInstruction(1);
        SimProcess sp2 = new SimProcess(2, "Proc2", 277);
        ProcessControlBlock pcb2 = new ProcessControlBlock(sp2);
        pcb2.setRegisters(789654, 654123, 321456, 369852);
        pcb2.setCurrInstruction(1);
        SimProcess sp3 = new SimProcess(3, "Proc3", 238);
        ProcessControlBlock pcb3 = new ProcessControlBlock(sp3);
        pcb3.setRegisters(745632, 789652, 784512, 764859);
        pcb3.setCurrInstruction(1);
        SimProcess sp4 = new SimProcess(4, "Proc4", 145);
        ProcessControlBlock pcb4 = new ProcessControlBlock(sp4);
        pcb4.setRegisters(201453, 102365, 201478, 301256);
        pcb4.setCurrInstruction(1);
        SimProcess sp5 = new SimProcess(5, "Proc5", 400);
        ProcessControlBlock pcb5 = new ProcessControlBlock(sp5);
        pcb5.setRegisters(999999, 888888, 777777, 666666);
        pcb5.setCurrInstruction(1);
        SimProcess sp6 = new SimProcess(6, "Proc6", 354);
        ProcessControlBlock pcb6 = new ProcessControlBlock(sp6);
        pcb6.setRegisters(555555, 444444, 333333, 222222);
        pcb6.setCurrInstruction(1);
        SimProcess sp7 = new SimProcess(7, "Proc7", 299);
        ProcessControlBlock pcb7 = new ProcessControlBlock(sp7);
        pcb7.setRegisters(111111, 100000, 200000, 300000);
        pcb7.setCurrInstruction(1);
        SimProcess sp8 = new SimProcess(8, "Proc8", 305);
        ProcessControlBlock pcb8 = new ProcessControlBlock(sp8);
        pcb8.setRegisters(400000, 500000, 600000, 700000);
        pcb8.setCurrInstruction(1);
        SimProcess sp9 = new SimProcess(9, "Proc9", 362);
        ProcessControlBlock pcb9 = new ProcessControlBlock(sp9);
        pcb9.setRegisters(800000, 900000, 120000, 230000);
        pcb9.setCurrInstruction(1);
        SimProcess sp10 = new SimProcess(10, "Proc10", 287);
        ProcessControlBlock pcb10 = new ProcessControlBlock(sp10);
        pcb10.setRegisters(340000, 450000, 560000, 670000);
        pcb10.setCurrInstruction(1);

        ArrayList<ProcessControlBlock> ready = new ArrayList<>();

        //All processes start as ready
        ready.add(pcb1); ready.add(pcb2);  ready.add(pcb3); ready.add(pcb4); ready.add(pcb5);
        ready.add(pcb6); ready.add(pcb7); ready.add(pcb8); ready.add(pcb9); ready.add(pcb10);

        return ready;
    }
}//close class