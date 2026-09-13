package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drivetrain {

    private DcMotor lf_motor;
    private DcMotor lb_motor;
    private DcMotor rf_motor;
    private DcMotor rb_motor;
    private double absTotalPower;
    private double lf_motor_power;
    private double lb_motor_power;
    private double rf_motor_power;
    private double rb_motor_power;

    public Drivetrain(HardwareMap hardwareMap){
        lf_motor = hardwareMap.get(DcMotor.class, "");
        lb_motor = hardwareMap.get(DcMotor.class, "");
        rf_motor = hardwareMap.get(DcMotor.class, "");
        rb_motor = hardwareMap.get(DcMotor.class, "");

        lf_motor.setDirection(DcMotorSimple.Direction.REVERSE);
        lb_motor.setDirection(DcMotorSimple.Direction.REVERSE);

        //set runMode (with encoder, without encoder, etc.)
    }


    public void updateMotorPowers(double vertical, double horizontal, double strafe){

        absTotalPower = Math.max(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(strafe), 1);
        lf_motor_power = -vertical + horizontal + strafe;
        lb_motor_power = -vertical + horizontal - strafe;
        rf_motor_power = -vertical - horizontal + strafe;
        rb_motor_power = -vertical - horizontal + strafe;


        lf_motor.setPower(lf_motor_power / absTotalPower);
        lb_motor.setPower(lb_motor_power / absTotalPower);
        rf_motor.setPower(rf_motor_power / absTotalPower);
        rb_motor.setPower(rb_motor_power / absTotalPower);

    }









    public double getLFMotorPower(){
        return lf_motor_power;
    }
    public double getLBMotorPower(){
        return lb_motor_power;
    }
    public double getRFMotorPower(){
        return rf_motor_power;
    }
    public double getRBMotorPower(){
        return rb_motor_power;
    }

















}
