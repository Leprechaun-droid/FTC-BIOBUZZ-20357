package org.firstinspires.ftc.teamcode.Subsystems.Vision.VisionPortalContainer;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class VisionPortal {

    //consider making this static so it can be easily changed, may remove final keyword
    public final boolean USE_WEBCAM = true;
    private VisionPortal visionPortal;

    private final double cameraXOffset = "put X offset value";
    private final double cameraYOffset = "put Y Offset value";
    private final double cameraZOffset = "put Z Offset value";
    private final double cameraYaw = "put yaw of camera in degrees";
    private final double cameraPitch = "put pitch of camera in degrees";
    private final double cameraRoll = "put roll of camera in degrees";



    //choose best distanceUnit
    private Position cameraPosition = new Position(DistanceUnit.INCH,cameraXOffset, cameraYOffset, cameraZOffset, 0);
    private YawPitchRollAngles cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES, cameraYaw, cameraPitch, cameraRoll, 0);



    //find updated way to create visionPortal
    VisionPortal builder = new org.firstinspires.ftc.vision.VisionPortal.Builder()

    public void setVisionPortalProcessor(){


    }





}
