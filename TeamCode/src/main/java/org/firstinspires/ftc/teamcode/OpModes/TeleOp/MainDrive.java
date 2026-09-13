package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;

public class MainDrive extends LinearOpMode {
    private Robot robot;









    public void runOpMode(){
        robot = new Robot(hardwareMap);



        waitForStart();
        while(opModeIsActive()){
            double vertical = gamepad1.left_stick_y;
            double horizontal = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;

            if(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(strafe) != 0){
                robot.drivetrain.updateMotorPowers(vertical, horizontal, strafe);
            }









        }
    }










}
