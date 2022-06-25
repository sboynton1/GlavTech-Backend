package tech.GlavTech.SD2022.model.post;

import javax.persistence.*;

@Entity
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // IDENTITY VS AUTO
    @Column(nullable = false, updatable = false)
    private int instructionID;

    private String instruct;
    private String time;
    private String unitTime;

    public Instruction() {

    }

    public Instruction(int instructionID, String instruct, String time, String unitTime) {
        this.instructionID = instructionID;
        this.instruct = instruct;
        this.time = time;
        this.unitTime = unitTime;
    }


    public String getInstruct() {
        return instruct;
    }

    public void setInstruct(String instruct) {
        this.instruct = instruct;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(String unitTime) {
        this.unitTime = unitTime;
    }

    public int getInstructionID() {
        return instructionID;
    }

    public void setInstructionID(int instructionID) {
        this.instructionID = instructionID;
    }
}
