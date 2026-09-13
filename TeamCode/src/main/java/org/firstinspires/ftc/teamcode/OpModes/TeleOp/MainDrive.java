package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;

public class MainDrive extends LinearOpMode {
    private Robot robot;
    //private Follower follower;









    public void runOpMode(){
        robot = new Robot(hardwareMap);
        //follower = Constants.create(hardwareMap)
        //follower.setPose()
        /*
        using follower to track position probably won't work unless I can find a way to put its
        position at the end of the selected Auto
        */


        waitForStart();
        while(opModeIsActive()){
            double vertical = gamepad1.left_stick_y;
            double horizontal = gamepad1.right_stick_x;
            double strafe = gamepad1.left_stick_x;

            if(Math.abs(vertical) + Math.abs(horizontal) + Math.abs(strafe) != 0){
                robot.drivetrain.updateMotorPowers(vertical, horizontal, strafe);
            }








            telemetry.addData("lf_motor_power", robot.drivetrain.getLFMotorPower());
            telemetry.addData("lb_motor_power", robot.drivetrain.getLBMotorPower());
            telemetry.addData("rf_motor_power", robot.drivetrain.getRFMotorPower());
            telemetry.addData("rb_motor_power", robot.drivetrain.getRBMotorPower());

            //telemetry.addData("robotPose", "%2f, %2f", follower.pose().x(), follower.pose().y());


        }
    }










}
