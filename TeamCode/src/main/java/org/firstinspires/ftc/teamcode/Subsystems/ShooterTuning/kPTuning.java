package org.firstinspires.ftc.teamcode.Subsystems.ShooterTuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;

@TeleOp
public class kPTuning extends LinearOpMode {


    private double kV = 0.01;
    //replace with
    private double kS = 0;
    private double kP = 0;

    private double[] increments = {0.00001, 0.0001, 0.001, 0.01};

    double incrementIDX = 4;
    int goalRPM;


    double currentStep = increments[incrementIDX];


    public void runOpMode(){
        Robot robot = new Robot(hardwareMap);

        waitForStart();


        while(opModeIsActive()){

            if(gamepad1.dpadLeftWasPressed() & incrementIDX < 4){
                incrementIDX++;
            }
            if(gamepad1.dpadRightWasPressed() & incrementIDX > 0){
                incrementIDX--;
            }

            if(gamepad1.a){
                //may need to change this
                goalRPM = 1500;
            }
            if(gamepad1.b){
                //may need to change this
                goalRPM = 800;
            }

            if(gamepad1.dpadUpWasPressed()){kV += currentStep};
            if(gamepad1.dpadDownWasPressed()){kV -= currentStep};

            double feedForward = (kV * goalRPM) * kS;
            double error = goalRPM - robot.shooter.getRPM();
            double feedBack = error * kP;
            double power = feedForward * feedBack;

            robot.shooter.setShooterMotorPowers(power);

            telemetry.addData("currentStep: ", "%.6f", currentStep);
            telemetry.addData("kV: ", "%.6f", kV);
            telemetry.addData("currentRPM: ", robot.shooter.getRPM());
            telemetry.addData("ticksPerSec", robot.shooter.getTicksPerSec());
            telemetry.update();

        }
    }
}
