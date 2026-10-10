package org.firstinspires.ftc.teamcode.Subsystems.ShooterTuning;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;

//@Configurable
@TeleOp
public class kVTuning extends LinearOpMode {



    private static double kV = 0;
    //replace with tuned values
    private static double kS = 0;


    private static int goalRPM;


    public void runOpMode(){
        Robot robot = new Robot(hardwareMap, gamepad1, gamepad2);

        waitForStart();


        while(opModeIsActive()){


            double power = (kV * goalRPM) * kS;

            robot.shooter.setShooterMotorPowers(power);

            telemetry.addData("kV: ", "%.6f", kV);
            telemetry.addData("TargetRPM", goalRPM);
            telemetry.addData("currentRPM: ", robot.shooter.getRPM());
            telemetry.addData("ticksPerSec", robot.shooter.getTicksPerSec());
            telemetry.update();

        }
    }



}
