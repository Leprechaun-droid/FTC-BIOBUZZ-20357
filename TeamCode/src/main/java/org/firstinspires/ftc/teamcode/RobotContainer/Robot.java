package org.firstinspires.ftc.teamcode.RobotContainer;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
//import org.firstinspires.ftc.teamcode.Subsystems.Vision;
import org.firstinspires.ftc.teamcode.pedro.Constants;

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

    //public Follower follower;


    public Drivetrain drivetrain;
    //public Vision vision;
    public Intake intake;
    public Shooter shooter;

    //intake & transfer is 2nd
    //drive & shoot is 1st


    private int fullIntakeConditionRPM = 0;

    Gamepad gamepad1;
    Gamepad gamepad2;


    public Robot(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2) {
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;

        drivetrain = new Drivetrain(hardwareMap);
        //vision = new Vision(hardwareMap, gamepad1, gamepad2);
        intake = new Intake(hardwareMap, gamepad2);
        shooter = new Shooter(hardwareMap, gamepad2);



    }

    //figure out a way to either make the follower public in this file or make follower public in
    // the op mode files and allow the advanced shoot formula methods to access the robot position




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

    public Near_Red_Cell_State getNearRedCellState(){
        return nearRedCellState;
    }

    public Far_Red_Cell_State getFarRedCellState(){
        return farRedCellState;
    }

    public Near_Blue_Cell_State getNearBlueCellState(){
        return nearBlueCellState;
    }

    public Far_Blue_Cell_State getFarBlueCellState(){
        return farBlueCellState;
    }





    public void setAlliance(Alliance newAlliance){
        alliance = newAlliance;
    }
    public Alliance getAlliance(){return alliance;}

    //public void initRobotFollower(HardwareMap hardwareMap, Pose startPos){
    //    follower = Constants.create(hardwareMap);
    //    follower.setPose(startPos);
    //}


}
