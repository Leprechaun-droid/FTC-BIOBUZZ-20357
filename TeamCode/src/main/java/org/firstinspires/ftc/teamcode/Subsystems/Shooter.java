package org.firstinspires.ftc.teamcode.Subsystems;

import com.pedropathing.controllers.PIDController;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Components.ShooterPIDController;


public class Shooter {

    //CONSTANTS
        //put in actual shooterAngle when designers tells me
        private double shooterAngle = 0;
        //g is not negative because the negative was put outside of variable value
        private double g = 9.80665;
        //fill in with actual height relative to camera unless I find a way to do it from shooter
        //believe I put a heightThreshold variable in the vision class. can use that to define h.
        private double h = 0;
        //fill in with actual diameter
        private double flywheelDiameter = 0;
        //adjust (usually a little larger than 1, like 1.1) to account for loss of KE from flywheel to ball
        double kRPM = 0;
        //FIND the motor ticks per revolution; find motor first. ask design team what it is
        double TICKS_PER_SHOOTER_MOTOR_REV = 0;
    //PID Tuning Values
        private double kP = 0.00;
        private double kI = 0.00;
        private double kD = 0.00;
        //tune based on gravity
        private double kF = 0.00;
    //Objects
        //put tuning constants in class parameters when I make the PIDController class
        ShooterPIDController shooterPID;
        private DcMotorEx shooterMotor;
        private Robot robot;
        public Shooter(HardwareMap hardwareMap) {
            shooterPID = new ShooterPIDController(kP, kI, kD);
            shooterMotor = hardwareMap.get(DcMotorEx.class, "shooterMotor");
            robot = new Robot(hardwareMap);
    }

    // --------------
    // Basic Shooting
    // --------------

    public Command basicShoot(double targetVel){
        //Vel is inputed as rpm
        return Command.build()
                .setStart(shooterPID.setTargetVel(targetVel))
                .setExecute(shooterMotor.setPower(shooterPID.calculate(shooterMotor.getVelocity(), targetVel) * kF))
                //Add a condition for which the command ends (the hive tips or all the color sensors register none)
                .setDone(() -> )
                .setEnd(endCondition -> shooterMotor.setPower(0));
    }


















    //make method for calculating distance from cell
    //I'm assuming we're using a dual flywheel, the kind with a wheel on both sides of the ball
    private double calcTargetRPM(double disFromCell, double heightThreshold){
        //Finding Target Velocity
        double d = disFromCell;
        double h = heightThreshold;
        double initVel = (d / (Math.cos(shooterAngle))) * Math.sqrt(g/(2*(Math.tan(shooterAngle)*d - h)));

        //change to wheelVel = 2initVel if single flywheel or the non-mirrored dual flywheel
        double wheelVel = initVel;
        double D = flywheelDiameter;
        double initRPM = (wheelVel * 60)/(Math.PI * D);
        double targetRPM = initRPM * kRPM;

        return targetRPM;
    }

    public void shoot(){
        //fill arguments with disFromCell and heightThreshold
        double targetRPM = calcTargetRPM(robot.vision.findCellDis(), robot.vision.);
        double targetVelocity = (targetRPM * TICKS_PER_SHOOTER_MOTOR_REV) / 60;
        double currentVelocity = shooterMotor.getVelocity();
        //replace with actual own PID class; get rid of FTCLib
        double power = shooterPID.calculate(currentVelocity, targetVelocity) * kF;
        shooterMotor.setPower(power);

    }






}

