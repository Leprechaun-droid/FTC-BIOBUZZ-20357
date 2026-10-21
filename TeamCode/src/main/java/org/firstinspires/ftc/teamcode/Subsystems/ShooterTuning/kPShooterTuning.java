package org.firstinspires.ftc.teamcode.Subsystems.ShooterTuning;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
import org.firstinspires.ftc.teamcode.Subsystems.Turret;

@Configurable
@TeleOp
public class kPShooterTuning extends LinearOpMode {

    // -------------------------
    // SHOOTER
    // -------------------------
    public static double kV = 0.01;
    public static double kS = 0;
    public static double kP = 0;

    //tune this
    public static int goalRPM = 0;

    private Shooter shooter;

    // ------------------------------------
    // Turret
    // ------------------------------------

    private Turret turret;





    public void runOpMode(){
        shooter = new Shooter(hardwareMap);
        turret = new Turret(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
                double feedForward = (kV * goalRPM) * kS;
                double error = goalRPM - shooter.getRPM();
                double feedBack = error * kP;
                double power = feedForward * feedBack;

                shooter.setShooterMotorPowers(power);


            telemetry.addData("kP: ", "%.6f", kP);
            telemetry.addData("currentRPM: ", shooter.getRPM());
            telemetry.addData("ticksPerSec", shooter.getTicksPerSec());
            telemetry.update();

        }
    }
}
