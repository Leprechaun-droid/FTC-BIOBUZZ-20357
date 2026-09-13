package org.firstinspires.ftc.teamcode.OpModes.Auto;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous
public class FarRedAuto extends LinearOpMode {
    private Follower follower;
    private Robot robot;
    private PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose startPose = poseFactory.of();

    private Path path_toGardenFromStart(){
        return Paths.line(startPose, "secondPose").linear(startPose, );
    }

    //private Command command_toGardenFromStart(){
    //    return follow(follower, path_toGardenFromStart());
    //}






    @Override
    public void runOpMode(){
        robot = new Robot(hardwareMap);


        robot.setAlliance(Robot.Alliance.RED);
        Scheduler.reset();
        //make actually create function in the constants file
        follower = Constants.create(hardwareMap);
        //aprilTag for startPos must go before buildPaths, or buildPaths will have no values for startPos
        //set startPos manually if using AprilTags to check will not be allowed
        //follower.setPose(startPos);
        Scheduler.schedule();


        waitForStart();
        while(opModeIsActive()){



            follower.update();
            Scheduler.execute();











            telemetry.update();
        }
    }






}
