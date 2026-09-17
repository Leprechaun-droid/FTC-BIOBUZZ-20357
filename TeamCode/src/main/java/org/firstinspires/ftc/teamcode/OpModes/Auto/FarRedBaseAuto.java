package org.firstinspires.ftc.teamcode.OpModes.Auto;

import static com.pedropathing.api.Paths.curve;
import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.RobotContainer.Robot;
import org.firstinspires.ftc.teamcode.pedro.Constants;

@Autonomous
public class FarRedBaseAuto extends LinearOpMode {


    private Follower follower;
    private Robot robot;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose startPose = poseFactory.of(85, 139, 270);
    private final Pose farCellShootPos = poseFactory.of(85, 109, 270);

    private Path fromStartToFarCellShoot() {
        return line(startPose, farCellShootPos).linear(startPose, farCellShootPos);
    }

    private final Pose farShootToParkControl = poseFactory.of(129, 106, 0); //control point, so negligible heading
    private final Pose park = poseFactory.of(134, 40, 270);


    private Path parkFromFarRedCell() {
        return curve(farCellShootPos, farShootToParkControl, park).linear(farCellShootPos, park);
    }

    private Command goToFar() {
        return follow(follower, fromStartToFarCellShoot());
    }

    private Command goToParkFromFarCell() {
        return follow(follower, parkFromFarRedCell());
    }


    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        robot = new Robot(hardwareMap);
        follower.setPose(startPose);
        Scheduler.schedule(goToFar(), goToParkFromFarCell());
        waitForStart();
        while (opModeIsActive()) {
            follower.update();
            Scheduler.execute();

            telemetry.update();
            telemetry.addData("Robot PosX", follower.pose().x());
            telemetry.addData("Robot PosY", follower.pose().y());
            telemetry.addData("Robot Heading", follower.pose().heading());

        }


    }
}
