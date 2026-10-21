package org.firstinspires.ftc.teamcode.Subsystems.ShooterTuning;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;

@Configurable
@TeleOp
public class kSTuning extends LinearOpMode {

    private static double kS = 0;


    //{0.00001, 0.0001, 0.001, 0.01};

    public void runOpMode(){
        Robot robot = new Robot(hardwareMap);

        waitForStart();


        while(opModeIsActive()){
            robot.shooter.setShooterMotorPowers(kS);


            telemetry.addData("kS: ", "%.6f", kS);
            telemetry.addData("currentRPM: ", robot.shooter.getRPM());
            telemetry.addData("ticksPerSec", robot.shooter.getTicksPerSec());
            telemetry.update();

        }
    }



}
