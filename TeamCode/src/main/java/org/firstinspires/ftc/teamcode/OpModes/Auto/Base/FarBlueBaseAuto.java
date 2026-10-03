package org.firstinspires.ftc.teamcode.OpModes.Auto.Base;

import static com.pedropathing.api.Paths.line;
import static com.pedropathing.ivy.groups.Groups.parallel;
import static com.pedropathing.ivy.groups.Groups.sequential;
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
public class FarBlueBaseAuto extends LinearOpMode {


    private Follower follower;
    private Robot robot;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    // -----
    // Poses
    // -----
    private final Pose startPos = poseFactory.of(15, 133, 270);
    // check if the distance from the cell is the same for each side. It should be relatively simple and just measure
    // the y difference
    private final Pose farCellShootPos = poseFactory.of(58, 110, 270);
    private final Pose park = poseFactory.of(9, 117, 270);

    // -------
    // Paths
    // -------
    private Path startToFarBlueShoot() {
        return line(startPos, farCellShootPos).linear(startPos, farCellShootPos);
    }
    private Path farBlueCellToPark() {
        return line(farCellShootPos, park).linear(farCellShootPos, park);
    }

    // ---------
    // Commands
    // ---------
    private Command goToFarBlue() {
        return follow(follower, startToFarBlueShoot());
    }

    private Command goToParkFromFarCell() {
        return follow(follower, farBlueCellToPark());
    }

    // ------------
    // AutoRoutine
    // ------------
    private Command autoRoutine(){
        return sequential(
                goToFarBlue(),
                //put targetRPM as argument in basicShoot
                //check that wait is an ivy command VVV
                wait(2000),
                robot.shooter.basicShoot(),
                //add shoot
                goToParkFromFarCell()
        );
    }




    @Override
    public void runOpMode() {
        Scheduler.reset();
        follower = Constants.create(hardwareMap);
        robot = new Robot(hardwareMap);
        follower.setPose(startPos);
        Scheduler.schedule(autoRoutine());
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
