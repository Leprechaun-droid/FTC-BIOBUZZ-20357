package org.firstinspires.ftc.teamcode.Subsystems.MicroSystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class TurretGate {


    Servo gate;
    public TurretGate(HardwareMap hardwareMap){
        gate = hardwareMap.get(Servo.class, "gate");

    }




    double openPos = 0.5;
    double closePos = 0;



    public void openGate(){
        gate.setPosition(openPos);

    }

    public void closeGate(){
        gate.setPosition(closePos);

    }




}
