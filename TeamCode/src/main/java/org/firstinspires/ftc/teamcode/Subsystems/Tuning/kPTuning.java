package org.firstinspires.ftc.teamcode.Subsystems.Tuning;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.Turret;

@Configurable
@TeleOp
public class kPTuning extends LinearOpMode {


    public static double kV = 0.01;
    public static double kS = 0;
    public static double kP = 0;

    public static int goalRPM = 0;

    private Shooter shooter;
    private Turret turret;
    private enum tuningMode{SHOOTER, TURRET, NONE}
    tuningMode currentTuningMode = tuningMode.NONE;





    public void runOpMode(){
        shooter = new Shooter(hardwareMap);
        turret = new Turret(hardwareMap);
        while(true){
            if(gamepad1.dpadLeftWasPressed()){
                currentTuningMode = tuningMode.SHOOTER;
                break;
            } else if (gamepad1.dpadRightWasPressed()){
                currentTuningMode = tuningMode.TURRET;
                break;
            }

        }
        waitForStart();
        while(opModeIsActive()){
            if(currentTuningMode == tuningMode.SHOOTER) {
                double feedForward = (kV * goalRPM) * kS;
                double error = goalRPM - shooter.getRPM();
                double feedBack = error * kP;
                double power = feedForward * feedBack;

                shooter.setShooterMotorPowers(power);
            } else if (currentTuningMode == tuningMode.TURRET){
                double feedBack = 0;
                //add logic for turret tuning

            }

            telemetry.addData("TuningMode: ", currentTuningMode);
            telemetry.addData("kP: ", "%.6f", kP);
            telemetry.addData("currentRPM: ", shooter.getRPM());
            telemetry.addData("ticksPerSec", shooter.getTicksPerSec());
            telemetry.update();

        }
    }
}
