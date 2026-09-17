package org.firstinspires.ftc.teamcode.Subsystems.Components;

public class ShooterPIDController {
    private double kP, kI, kD, kF;



    public ShooterPIDController(double p, double i, double d, double f){
        kP = p;
        kI = i;
        kD = d;
        kF = f;
    }

    public double calculate(){}




}
