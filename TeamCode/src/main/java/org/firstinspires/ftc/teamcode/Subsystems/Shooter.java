package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.CommandBuilder;
import com.pedropathing.ivy.commands.Commands;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;


public class Shooter {

    //CONSTANTS
        //put in actual shooterAngle when designers tells me
        private double shooterAngle = 25;
        //g is not negative because the negative was put outside of variable value
        private double g = 9.80665;
        //fill in with actual height relative to camera unless I find a way to do it from shooter
        //believe I put a heightThreshold variable in the vision class. can use that to define h.

        //
        // plugging in 43 for now. It is the number I measured from the ground to a midpoint between
        // the displacement of the top and bottom cell; need to measure the distance from the camera to the ground and subtract it from this
        // because the camera measures the apriltag's height from the camera, not the ground
        private double h = 43;
        //fill in with actual diameter

        private double flywheelDiameter = 0;
        //adjust (usually a little larger than 1, like 1.1) to account for loss of KE from flywheel to ball
        double kRPM = 0;
        //FIND the motor ticks per revolution; find motor first. ask design team what it is
        double TICKS_PER_SHOOTER_MOTOR_REV = 0;
        //find encoderCPM (
        private double encoderCPM = 0;

        private double gearRatio = 0;
    //PID Tuning Values

        private double kP, kS, kV;

    //Objects
        //put tuning constants in class parameters when I make the PIDController class
        private DcMotorEx shooterMotor;
        //private DcMotorEx rightShooterMotor;
        private int baseTargetRPM;
        private Intake intake;
        Gamepad gamepad2;
    public Shooter(HardwareMap hardwareMap, Gamepad gamepad2) {
            shooterMotor = hardwareMap.get(DcMotorEx.class, "leftShooterMotor");
            //rightShooterMotor = hardwareMap.get(DcMotorEx.class, "rightShooterMotor");

            shooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            //rightShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

            shooterMotor.setDirection(DcMotorSimple.Direction.FORWARD);
            //rightShooterMotor.setDirection(DcMotorSimple.Direction.FORWARD);

            this.gamepad2 = gamepad2;

            intake = new Intake(hardwareMap, gamepad2);
    }

    boolean startShooterCondition = (gamepad2.right_trigger > 0.1) && (gamepad2.left_trigger < 0.1);
    boolean zeroBaseShootCondition = gamepad2.right_bumper;


        public double getTicksPerSec(){
            //Check if this is the right Vel
            return shooterMotor.getVelocity();

        }

        public void setShooterMotorPowers(double power){
            shooterMotor.setPower(power);
            //rightShooterMotor.setPower(power);
        }

        public void emergencyShooterStop(){
            shooterMotor.setPower(0);
            //rightShooterMotor.setPower(0);

        }


        public double getRPM(){
            return (((getTicksPerSec()/encoderCPM) * 60) / gearRatio);

        }

        public void calculateShooterRPM(){

            double error = baseTargetRPM - getRPM();
            //double check the logic behind this
            double ff = (kV * baseTargetRPM) * kS;
            double fb = error * kP;
            double power = ff + fb;

            setShooterMotorPowers(power);

        }

        private void setBaseTargetRPM(int targRPMBase){
            baseTargetRPM = targRPMBase;
        }


    // --------------
    // Basic Shooting
    // --------------



    public Command basicShoot(){return Commands.instant(() -> setBaseTargetRPM(1000));}
    public Command zeroShoot(){return Commands.instant(() -> setBaseTargetRPM(0));}


    public void setShooterPower(){
            shooterMotor.setPower(0.46);
    }

    public void zeroBaseShoot(){
            shooterMotor.setPower(shooterMotor.getPower() * 0.98);
    }


    public void shooterDriveUpdate(){
        if(startShooterCondition){
            setBaseTargetRPM(1000);
        }

        if(gamepad2.right_bumper){
            zeroBaseShoot();
        }



    }













    //THIS IS IRRELEVANT FOR THE FIRST COMPETITION. THIS IS A WORK IN PROGRESS.

    //make method for calculating distance from cell
    //I'm assuming we're using a dual flywheel, the kind with a wheel on both sides of the ball
    private double calcOptimalTargetRPM(double disFromCell, double heightThreshold){
        //Finding Target Velocity
        double d = disFromCell;
        double h = heightThreshold;
        double initVel = (d / (Math.cos(shooterAngle))) * Math.sqrt(g/(2*(Math.tan(shooterAngle)*d - h)));

        //change to wheelVel = 2initVel if single flywheel or the non-mirrored dual flywheel
        // may change this
        double wheelVel = 2 * initVel;
        double D = flywheelDiameter;
        double initRPM = (wheelVel * 60)/(Math.PI * D);
        double targetRPM = initRPM * kRPM;

        return targetRPM;
    }

    public void advancedShoot(){
        //fill arguments with disFromCell and heightThreshold
        //double targetRPM = calcOptimalTargetRPM(robot.vision.findCellDis(), h);
        //double targetVelocity = (targetRPM * TICKS_PER_SHOOTER_MOTOR_REV) / 60;
        //double check this line with the addition of the dual motors
        double currentVelocity = shooterMotor.getVelocity();
        //replace with actual own PID class; get rid of FTCLib
        //double power = shooterPID.calculate(currentVelocity, targetVelocity) * kF;
        //shooterMotor.setPower(power);

    }








}

