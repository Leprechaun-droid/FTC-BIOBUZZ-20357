package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotContainer.DriveProcesses;
import org.firstinspires.ftc.teamcode.RobotContainer.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@TeleOp
public class MainDrive extends LinearOpMode {



    private Drivetrain drivetrain;
    private  Intake intake;
    private Shooter shooter;
    private Robot robot;
    private DriveProcesses driveProcesses;

    @Override
    public void runOpMode(){
        robot = new Robot(hardwareMap);
        driveProcesses = new DriveProcesses(hardwareMap, gamepad1, gamepad2);

        double vertical;
        double horizontal;
        double strafe;

        waitForStart();
        while(opModeIsActive()){
            vertical = gamepad1.left_stick_y;
            horizontal = gamepad1.right_stick_x;
            strafe = gamepad1.left_stick_x;


            //update PID
            robot.shooter.calculateShooterRPM();

            //updateSubsystems
            driveProcesses.intakeDriveUpdate();
            driveProcesses.shooterDriveUpdate();
            robot.drivetrain.updateDriveMotorPowers(vertical, horizontal, strafe);









            telemetry.addData("lf_motor_power", drivetrain.getLFMotorPower());
            telemetry.addData("lb_motor_power", drivetrain.getLBMotorPower());
            telemetry.addData("rf_motor_power", drivetrain.getRFMotorPower());
            telemetry.addData("rb_motor_power", drivetrain.getRBMotorPower());
            telemetry.addData("left_toggler_analog", gamepad1.left_trigger);
            telemetry.addData("ShooterRPM", shooter.getRPM());
            //telemetry.addData("shooterPower", shooter.getShooterPower());
            //telemetry.addData("shooterRPM", robot.shooter.getRPM());
            telemetry.update();
            //telemetry.addData("robotPose", "%2f, %2f", follower.pose().x(), follower.pose().y());


        }
    }





}
