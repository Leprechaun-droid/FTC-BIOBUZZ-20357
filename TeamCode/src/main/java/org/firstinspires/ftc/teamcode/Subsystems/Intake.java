package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;

public class Intake {
    DcMotor intakeMotor;
    DcMotor transferMotor;
    Gamepad gamepad1;
    Gamepad gamepad2;
    private Robot robot;
    //add fullIntakeConditionRPM as a velocity the flywheel must reach before the shooter runs
    double fullIntakeConditionRPM = 0;


    //find how to pair variables to gamepads in constructor
    public Intake(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2){
        //adjust deviceName to config if I give it a different name in the configuration

        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        transferMotor = hardwareMap.get(DcMotor.class, "transferMotor");
        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        transferMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        transferMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transferMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        robot = new Robot(HardwareMap hardwareMap);

    }




    public void justFrontIntake(){
        if(gamepad2.right_trigger > 0.1 && gamepad2.left_trigger < 0.1) {
            intakeMotor.setPower(1);
        }
    }

    public void justFrontEject(){
        if(gamepad2.left_trigger > 0.1 && gamepad2.right_trigger < 0.1){
            intakeMotor.setPower(-1);
        }
    }

    public void fullIntake(){
        if(robot.shooter.getRPM() > fullIntakeConditionRPM) {
            //adjust directions of the setPower as fit
                intakeMotor.setPower(1);
                transferMotor.setPower(1);

        }
    }

    public void fullEject(){
        if(gamepad2.leftBumperWasPressed()){
            intakeMotor.setPower(-1);
        }


    }

    public void autoIntake(){
        //adjust power sign based on direction
        intakeMotor.setPower(1);
    }


    public void autoEject(){
        //adjust power sign based on direction
        intakeMotor.setPower(-1);
    }



    //public void raiseIntakeBar(){
    //    //if(gamepad1.)
    //
    //}

}
