package org.firstinspires.ftc.teamcode.OpModes.Auto;

import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.pedro.Constants;

public class FarRedAutoHelp extends LinearOpMode {
    private Follower follower;
    private PoseFactory poseFactory = PoseFactory.degrees();

    private double startX;
    private double startY;
    private double startHeading;


    private final Pose startPose;

    private Path path_toGardenFromStart(){
        return Paths.line(startPose, "secondPose").linear(startPose, );
    }

    private Command command_toGardenFromStart(){
        return follow(follower, path_toGardenFromStart());
    }






    public void runOpMode(){


        Scheduler.reset();
        //make actually create function in the constants file
        follower = Constants.create(hardwareMap);
        //aprilTag for startPos must go before buildPaths, or buildPaths will have no values for startPos
            //set startPos manually if using AprilTags to check will not be allowed
        buildPaths();
        follower.setPose(startPos);
        Scheduler.schedule();


        waitForStart();
    }







}
