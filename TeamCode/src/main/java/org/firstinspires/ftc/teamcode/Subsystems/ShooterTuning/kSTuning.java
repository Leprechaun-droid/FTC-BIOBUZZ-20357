package org.firstinspires.ftc.teamcode.Subsystems.ShooterTuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@TeleOp
public class kSTuning extends LinearOpMode {



    private double kS = 0;

    private double[] increments = {0.00001, 0.0001, 0.001, 0.01};

    double incrementIDX = 4;


    double currentStep = increments[incrementIDX];


    public void runOpMode(){
        Robot robot = new Robot(hardwareMap);

        waitForStart();


        while(opModeIsActive()){
            robot.shooter.setShooterMotorPowers(kS);

            if(gamepad1.dpadLeftWasPressed() & incrementIDX < 4){
                incrementIDX++;
            }
            if(gamepad1.dpadRightWasPressed() & incrementIDX > 0){
                incrementIDX--;
            }

            if(gamepad1.dpadUpWasPressed()){ kS += currentStep};
            if(gamepad1.dpadDownWasPressed()){kS -= currentStep};

            telemetry.addData("currentStep: ", "%.6f", currentStep);
            telemetry.addData("kS: ", "%.6f", kS);
            telemetry.addData("currentRPM: ", robot.shooter.getRPM());
            telemetry.addData("ticksPerSec", robot.shooter.getTicksPerSec());
            telemetry.update();

        }
    }



}
