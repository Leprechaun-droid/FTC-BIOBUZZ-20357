package org.firstinspires.ftc.teamcode.Tests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;

public class aprilTagHeightAlgorithm extends LinearOpMode {

    private Robot robot;







    public void runOpMode(){
        robot = new Robot(hardwareMap);
        robot.vision.initVisionPortal();
        robot.setAlliance(Robot.Alliance.RED);
        waitForStart();
        while(opModeIsActive()){
            robot.drivetrain.updateMotorPowers();
            robot.vision.useAprilTagsToSetCellState(robot.getAlliance());


            telemetry.update();


        }
    }



}
