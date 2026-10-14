package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;

public class Intake {

    Gamepad gamepad2;

    boolean fullEjectCondition = gamepad2.y; //same control
    boolean justFrontIntakeCondition = gamepad2.left_trigger > 0.1 && gamepad2.right_trigger < 0.1; //

    boolean justFrontEjectCondition = gamepad2.x;
    boolean stopFullIntakeAndShooterCondition = gamepad2.left_bumper; //
    boolean justTransIntakeCondition = gamepad2.y; //same control
    boolean justTransEjectCondition = gamepad2.b;


    DcMotor intakeMotor;
    DcMotor transferMotor;

    private Shooter shooter;

    //find how to pair variables to gamepads in constructor
    public Intake(HardwareMap hardwareMap, Gamepad gamepad2){
        //adjust deviceName to config if I give it a different name in the configuration

        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        transferMotor = hardwareMap.get(DcMotor.class, "transferMotor");

        intakeMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        transferMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        transferMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        transferMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.gamepad2 = gamepad2;

        shooter = new Shooter(hardwareMap, gamepad2);

    }

    //add fullIntakeConditionRPM as a velocity the flywheel must reach before the shooter runs (the pid target RPM)
    int fullIntakeConditionRPM = 0;


    public void justFrontIntake(){

        intakeMotor.setPower(1);
    }

    public void justFrontEject(){
            intakeMotor.setPower(-1);

    }

    public void justTransIntake(){
        transferMotor.setPower(1);
    }
    public void justTransEject(){ transferMotor.setPower(-1);}

    public void fullIntake(){
                //adjust directions of the setPower as fit
                intakeMotor.setPower(1);
                transferMotor.setPower(1);
    }

    public void fullEject(){
            intakeMotor.setPower(-1);
            transferMotor.setPower(-1);

    }


    double RPM_Error = Math.abs(fullIntakeConditionRPM - shooter.getRPM());
    public void loadShooter(){
        if(RPM_Error < 5){
            justTransIntake();
            //tune this 1.5 value until it is long enough for the ball to have already shot
        }

    }

    public void zeroTransfer(){
        transferMotor.setPower(0);
    }

    public void zeroIntake(){
        intakeMotor.setPower(0);
    }


    public void intakeDriveUpdate(){

        if(fullEjectCondition){
            fullEject();
        } else if(justFrontIntakeCondition){
            justFrontIntake();
            zeroTransfer();
        } else if(justFrontEjectCondition){
            justFrontEject();
            zeroTransfer();
        } else if(stopFullIntakeAndShooterCondition){
            zeroIntake();
            zeroTransfer();
            shooter.zeroBaseShoot(); //see if I can remove this
            //add stop flywheel after consulting second driver
        } else if (justTransIntakeCondition){
            zeroIntake();
            justTransIntake();
        } else if (justTransEjectCondition){
            zeroIntake();
            justTransEject();
        }

    }







}
