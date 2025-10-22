//Elisheva Goldfarb
//T00522464

public class SimProcess {

    private int pid;
    private String procName;
    private int totalInstructions;




    public SimProcess(int pid, String procName, int totalInstructions) {
        this.pid = pid;
        this.procName = procName;
        this.totalInstructions = totalInstructions;
    }

    public String getProcName() {
        return procName;
    }

    public ProcessState execute(int i) {

        System.out.println("Process " + procName + " PID: " + pid + " Executing Instruction: " + i);
        if (i >= totalInstructions) {
            return ProcessState.FINISHED;
        } else {
           int r = Main.random.nextInt(20); //Random to generate 15% probability
           if (r < 3) {
               return ProcessState.BLOCKED;
           }
           else {
               return ProcessState.READY;
           }
        }

    }

} //Close Class
