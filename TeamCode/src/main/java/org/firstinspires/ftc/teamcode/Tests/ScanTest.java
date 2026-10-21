package org.firstinspires.ftc.teamcode.Tests;

import com.pedropathing.paths.curves.Line;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Vision;

@TeleOp
public class ScanTest extends LinearOpMode {


    Vision vision;

    public void runOpMode(){



        vision.resetScanner();


        waitForStart();
        while(opModeIsActive()){
            vision.scanForClusters();




            telemetry.addData("Current Cluster Detection", vision.getCurrentCluster());

        }



    }


}
