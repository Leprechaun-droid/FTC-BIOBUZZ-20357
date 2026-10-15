package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;

@TeleOp
public class MainDrive extends LinearOpMode {






    private int fullIntakeConditionRPM = 0;

    private Drivetrain drivetrain;
    private  Intake intake;
    private Shooter shooter;
    private Robot robot;

    @Override
    public void runOpMode(){
        robot = new Robot(hardwareMap, gamepad1,gamepad2);
        //drivetrain = new Drivetrain(hardwareMap);
        //intake = new Intake(hardwareMap, gamepad2);
        //shooter = new Shooter(hardwareMap, gamepad2);

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
            robot.intake.intakeDriveUpdate();
            robot.shooter.shooterDriveUpdate();
            robot.drivetrain.updateDriveMotorPowers(vertical, horizontal, strafe);







            //robot.intake.justFrontIntake();
            //robot.intake.fullEject();
            //robot.intake.justFrontEject();


            //This is the method for shooting in teleOp
            //robot.shooter.calculateShooterRPM();
            //robot.shooter.loadAndBasicShoot();









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
