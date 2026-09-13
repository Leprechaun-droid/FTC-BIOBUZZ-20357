package org.firstinspires.ftc.teamcode.RobotContainer;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Vision;

public class Robot {

    public enum Alliance {RED, BLUE, NONE}
    public enum Near_Red_Cell_State {RAISED, LOWERED, NONE}
    public enum Far_Red_Cell_State {RAISED, LOWERED, NONE}
    public enum Near_Blue_Cell_State {RAISED, LOWERED, NONE}
    public enum Far_Blue_Cell_State {RAISED, LOWERED, NONE}
    private Alliance alliance = Alliance.NONE;
    private Near_Red_Cell_State nearRedCellState = Near_Red_Cell_State.NONE;
    private Far_Red_Cell_State farRedCellState = Far_Red_Cell_State.NONE;
    private Near_Blue_Cell_State nearBlueCellState = Near_Blue_Cell_State.NONE;
    private Far_Blue_Cell_State farBlueCellState = Far_Blue_Cell_State.NONE;






    public Drivetrain drivetrain;
    public Vision vision;


    public Robot(HardwareMap hardwareMap) {
        drivetrain = new Drivetrain(hardwareMap);
        vision = new Vision();
    }













    public void setNearRedCellState(Near_Red_Cell_State newCellState) {
        nearRedCellState = newCellState;
    }
    public void setFarRedCellState(Far_Red_Cell_State newCellState) {
        farRedCellState = newCellState;
    }
    public void setNearBlueCellState(Near_Blue_Cell_State newCellState) {
        nearBlueCellState = newCellState;
    }
    public void setFarBlueCellState(Far_Blue_Cell_State newCellState) {
        farBlueCellState = newCellState;
    }





    public void setAlliance(Alliance newAlliance){
        alliance = newAlliance;
    }


}
