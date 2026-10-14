package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import dalvik.system.DelegateLastClassLoader;

public class Turret {

    //check GearRatio
    private int turretGearRatio = 45/155;

    private double kP = 0.001;
    private double kD = 0.0001;

    //adjust the headingOffsetTolerance
    private double headingOffsetTolerance = 0.4;

    private double goalX = 0;
    private double lastError = 0;

    //tune the MAX_POWER
    private final double MAX_POWER = 0.5;

    private double power = 0;

    private final ElapsedTime timer = new ElapsedTime();

    DcMotorEx pinionMotor;








    public Turret(HardwareMap hardwareMap){
        pinionMotor = hardwareMap.get(DcMotorEx.class, "");
        pinionMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }


    public void calculateTurretPIDPower(){
        //put logic for the PD controller in here

        

    }








}
