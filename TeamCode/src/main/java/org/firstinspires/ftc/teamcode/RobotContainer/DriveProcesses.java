package org.firstinspires.ftc.teamcode.RobotContainer;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad2;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveProcesses {

    Gamepad gamepad1;
    Gamepad gamepad2;
    //add fullIntakeConditionRPM as a velocity the flywheel must reach before the shooter runs (the pid target RPM)
    int targetRPM = 1000;

    //KEEP TRACK OF THIS; I AM UNSURE IF CALLING THIS BOTH IN HERE AND IN THE OPMODE WILL CAUSE ISSUES (CALLING IT TWICE)
    //IT SHOULD NOT BECAUSE THEY DO NOT CREATE AN INFINITE LOOP OF CALLING, BUT STILL
    private Robot robot;

    public DriveProcesses(HardwareMap hardwareMap, Gamepad gamepad1, Gamepad gamepad2){
        this.gamepad1 = gamepad1;
        this.gamepad2 = gamepad2;
        robot = new Robot(hardwareMap);

    }




    public void loadShooter() {
        double RPM_Error = Math.abs(targetRPM - robot.shooter.getRPM());
        if (RPM_Error < 5) {
            robot.intake.justTransIntake();

            //tune this 1.5 value until it is long enough for the ball to have already shot
        }
    }





    public void intakeDriveUpdate(){
        boolean fullEjectCondition = gamepad2.y; //same control
        boolean justFrontIntakeCondition = gamepad2.left_trigger > 0.1 && gamepad2.right_trigger < 0.1; //

        boolean justFrontEjectCondition = gamepad2.x;
        boolean stopFullIntakeAndShooterCondition = gamepad2.left_bumper; //
        boolean justTransIntakeCondition = gamepad2.y; //same control
        boolean justTransEjectCondition = gamepad2.b;

        if(fullEjectCondition){
            robot.intake.fullEject();
        } else if(justFrontIntakeCondition){
            robot.intake.justFrontIntake();
            robot.intake.zeroTransfer();
        } else if(justFrontEjectCondition){
            robot.intake.justFrontEject();
            robot.intake.zeroTransfer();
        } else if(stopFullIntakeAndShooterCondition){
            robot.intake.zeroIntake();
            robot.intake.zeroTransfer();
            robot.shooter.zeroShoot(); //see if I can remove this
            //add stop flywheel after consulting second driver
        } else if (justTransIntakeCondition){
            robot.intake.zeroIntake();
            robot.intake.justTransIntake();
        } else if (justTransEjectCondition){
            robot.intake.zeroIntake();
            robot.intake.justTransEject();
        }

    }



    public void shooterDriveUpdate(){
        final boolean startShooterCondition = (gamepad2.right_trigger > 0.1) && (gamepad2.left_trigger < 0.1);
        final boolean zeroBaseShootCondition = gamepad2.right_bumper;

        //dont believe I can use commands here, should restrict to just methods

        if(startShooterCondition){
            robot.shooter.setBaseTargetRPM(targetRPM);
        }

        if(zeroBaseShootCondition){
            robot.shooter.setBaseTargetRPM(0);
        }



    }












}
