package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    DcMotor intakeMotor;
    Gamepad gamepad1;
    Gamepad gamepad2;

    // ADD OVERALL updateINTAKE class that includes all manual intake methods


    //find how to pair variables to gamepads in constructor
    public Intake(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2){
        //adjust deviceName to config if I give it a different name in the configuration
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
    }

    public void updateIntake(){
        //can put them in directly with imported Gamepad class
        manualIntake(gamepad1.right_trigger);
        manualEject(gamepad1.left_trigger);


    }



    public void autoIntake(){
        //adjust power sign based on direction
        intakeMotor.setPower(1);
    }


    public void autoEject(){
        //adjust power sign based on direction
        intakeMotor.setPower(-1);
    }

    public void manualIntake(double rightTrigger){
        if(gamepad1.right_trigger > 0.1){
            //ask if they want it to be paired to rightTrigger or just as -1
            intakeMotor.setPower(-rightTrigger);
        }
    }

    public void manualEject(double leftTrigger){
        if(gamepad1.left_trigger > 0.1){
            intakeMotor.setPower(leftTrigger);
        }
    }

}
