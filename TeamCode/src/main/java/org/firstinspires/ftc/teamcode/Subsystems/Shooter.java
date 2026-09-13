package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.controllers.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private double kP = 0.00;
    private double kI = 0.00;
    private double kD = 0.00;
    private double kF = 0.00;
    //tune based on gravity



    private PIDController shooterPID = new PIDController(p, i, d);
    private DcMotorEx shooterMotor;


    public Shooter(HardwareMap hardwareMap){
        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
    }


    /*
    - put the gamepad control for shooter in the parameters for update shooter
    - will use rightTrigger for shoot until told otherwise

     */
    public void updateShooter(double rightTrigger){
        if(rightTrigger) > 0.1) {
            shoot(rightTrigger);
        }

    }

    public void shoot(double rightTrigger){
        double targetVelocity = 0;
        double currentVelocity = shooterMotor.getVelocity();
        double power = shooterPID.calculate(currentVelocity, targetVelocity);
        shooterMotor.setPower();





    }



}
