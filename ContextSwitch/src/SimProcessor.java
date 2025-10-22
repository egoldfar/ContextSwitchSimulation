//Elisheva Goldfarb
//T00522464


public class SimProcessor {

    private SimProcess sp;
    private int register1, register2, register3, register4;
    private int currInstruction;

    public SimProcessor() {}

    public void setSimProcess(SimProcess sp) {
        this.sp = sp;
    }

    public void setRegisters(int register1, int register2, int register3, int register4) {
        this.register1 = register1;
        this.register2 = register2;
        this.register3 = register3;
        this.register4 = register4;
    }

    public void setCurrInstruction(int currInstruction) {
        this.currInstruction = currInstruction;
    }

    public SimProcess getSimProcess() {
        return sp;
    }

    public int getRegister1() {
        return register1;
    }
    public int getRegister2() {
        return register2;
    }
    public int getRegister3() {
        return register3;
    }
    public int getRegister4() {
        return register4;
    }

    public int getCurrInstruction() {
        return currInstruction;
    }

    public ProcessState executeNextInstruction() {

        ProcessState ps = sp.execute(currInstruction);
        currInstruction++;
        setRegisters(Main.random.nextInt(999999), Main.random.nextInt(999999),
                     Main.random.nextInt(999999),Main.random.nextInt(999999));
        return ps;

    }

}//Close Class
