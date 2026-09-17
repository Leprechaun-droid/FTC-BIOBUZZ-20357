package org.firstinspires.ftc.teamcode.Subsystems;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;
import static org.firstinspires.ftc.teamcode.RobotContainer.Robot.Alliance.BLUE;

import android.graphics.Color;
import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;
import org.firstinspires.ftc.teamcode.RobotContainer.Robot;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;

import java.util.List;



public class Vision {

    //UPDATE OUTLINE AFTER EVERY COMMIT

    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------
    // File Outline
    // -------------------------------------------------------------------------------------
    // - Global Definitions [Lines 30-60]
    // - Building The Processors (AprilTag, Colors) [Lines 65 - 160]
    // - Building VisionPortal [Lines 165 - 200]
    // - Enabling and Disabling Processors and VisionPortal Streaming [Lines 205 - 250]
    // -------------------------------------------------------------------------------------
    // -------------------------------------------------------------------------------------


    private Robot robot;
    public Vision(HardwareMap hardwareMap){
        robot = new Robot(hardwareMap);
    }





    //may make this static so it can be changed, would have be changed to not final if changed to static
    public final boolean USE_WEBCAM = true;
    public final int RESOLUTION_WIDTH = 1280;
    public final int RESOLUTION_HEIGHT = 720;
    //adjust this based on the direction of the camera on the robot, will probably be useless and should
    //double check how this works if the Webcam fails, but rememember to double check.
    public final BuiltinCameraDirection CAMERA_DIRECTION = BuiltinCameraDirection.BACK;

    private AprilTagProcessor aprilTagProcessor;
    private ColorBlobLocatorProcessor redNectarLocator;
    private ColorBlobLocatorProcessor blueNectarLocator;
    private ColorBlobLocatorProcessor pollenLocator;
    private VisionPortal.Builder builder;
    private VisionPortal visionPortal;

    List<AprilTagDetection> currentDetections;



    //private final double cameraXOffset = "put X offset value";
    //private final double cameraYOffset = "put Y Offset value";
    //private final double cameraZOffset = "put Z Offset value";
    //private final double cameraYaw = "put yaw of camera in degrees";
    //private final double cameraPitch = "put pitch of camera in degrees";
    //private final double cameraRoll = "put roll of camera in degrees";



    //choose best distanceUnit
    private Position cameraPosition = new Position(DistanceUnit.INCH,cameraXOffset, cameraYOffset, cameraZOffset, 0);
    private YawPitchRollAngles cameraOrientation = new YawPitchRollAngles(AngleUnit.DEGREES, cameraYaw, cameraPitch, cameraRoll, 0);










    public void initColorProcessors(){
        //have been copied and pasted from sample files. adjust the parameters to our robot
        redNectarLocator = new ColorBlobLocatorProcessor.Builder()
                //figure out how to configure so it switches between YELLOW (Pollen) and NECTAR (Red and Blue)
                .setTargetColorRange(ColorRange.RED)   // Use a predefined color match
                //This is fine. It just means it ignores inside holes when detecting an object
                // This is very important because the holes in pollen and nectar could interfere with
                // color detection if it did not only map the external contours
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                //sets range of interest of webcam image (max is -1 and 1 in each direction)
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.75, 0.75, 0.75, -0.75))
                .setDrawContours(true)   // Show contours on the Stream Preview
                .setBoxFitColor(0)       // Disable the drawing of rectangles
                .setCircleFitColor(Color.rgb(255, 255, 0)) // Draw a circle
                .setBlurSize(5)          // Smooth the transitions between different colors in image

                // the following options have been added to fill in perimeter holes.
                .setDilateSize(15)       // Expand blobs to fill any divots on the edges
                .setErodeSize(15)        // Shrink blobs back to original size
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)

                .build();

        blueNectarLocator = new ColorBlobLocatorProcessor.Builder()
                //figure out how to configure so it switches between YELLOW (Pollen) and NECTAR (Red and Blue)
                .setTargetColorRange(ColorRange.BLUE)   // Use a predefined color match
                //This is fine. It just means it ignores inside holes when detecting an object
                // This is very important because the holes in pollen and nectar could interfere with
                // color detection if it did not only map the external contours
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                //sets range of interest of webcam image (max is -1 and 1 in each direction)
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.75, 0.75, 0.75, -0.75))
                .setDrawContours(true)   // Show contours on the Stream Preview
                .setBoxFitColor(0)       // Disable the drawing of rectangles
                .setCircleFitColor(Color.rgb(255, 255, 0)) // Draw a circle
                .setBlurSize(5)          // Smooth the transitions between different colors in image

                // the following options have been added to fill in perimeter holes.
                .setDilateSize(15)       // Expand blobs to fill any divots on the edges
                .setErodeSize(15)        // Shrink blobs back to original size
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)

                .build();
        pollenLocator = new ColorBlobLocatorProcessor.Builder()
                //figure out how to configure so it switches between YELLOW (Pollen) and NECTAR (Red and Blue)
                .setTargetColorRange(ColorRange.YELLOW)   // Use a predefined color match
                //This is fine. It just means it ignores inside holes when detecting an object
                // This is very important because the holes in pollen and nectar could interfere with
                // color detection if it did not only map the external contours
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                //sets range of interest of webcam image (max is -1 and 1 in each direction)
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.75, 0.75, 0.75, -0.75))
                .setDrawContours(true)   // Show contours on the Stream Preview
                .setBoxFitColor(0)       // Disable the drawing of rectangles
                .setCircleFitColor(Color.rgb(255, 255, 0)) // Draw a circle
                .setBlurSize(5)          // Smooth the transitions between different colors in image

                // the following options have been added to fill in perimeter holes.
                .setDilateSize(15)       // Expand blobs to fill any divots on the edges
                .setErodeSize(15)        // Shrink blobs back to original size
                .setMorphOperationType(ColorBlobLocatorProcessor.MorphOperationType.CLOSING)

                .build();




    }

    public void initAprilTag(){
        aprilTagProcessor = new AprilTagProcessor.Builder()

                // The following default settings are available to un-comment and edit as needed.
                //.setDrawAxes(false)
                //.setDrawCubeProjection(false)
                //.setDrawTagOutline(true)
                //.setTagFamily(AprilTagProcessor.TagFamily.TAG_36h11)
                //.setTagLibrary(AprilTagGameDatabase.getCenterStageTagLibrary())
                //.setOutputUnits(DistanceUnit.INCH, AngleUnit.DEGREES)
                .setCameraPose(cameraPosition, cameraOrientation)

                // == CAMERA CALIBRATION ==
                // If you do not manually specify calibration parameters, the SDK will attempt
                // to load a predefined calibration for your camera.
                //.setLensIntrinsics(578.272, 578.272, 402.145, 221.506)
                // ... these parameters are fx, fy, cx, cy.

                .build();
    }








    public void initVisionPortal(){
        // ---------------
        // BUILD PROCESSORS
        // ---------------
            initColorProcessors();
            initAprilTag();
        // ------------------
        // BUILD VISION PORTAL
        // ------------------

        if(USE_WEBCAM){
            //put whatever configuration name we end up using for the camera here
            builder.setCamera("Webcam 1");
        } else {
            //may not work because we dont have a camera phone, check if this is still valid
            builder.setCamera(BuiltinCameraDirection.BACK);
        }

        builder = new VisionPortal.Builder();



        //check resolution in case something needs to be changed or if the previous resolution was wrong
        builder.setCameraResolution(new Size(RESOLUTION_WIDTH, RESOLUTION_HEIGHT));
        builder.addProcessors(aprilTagProcessor, redNectarLocator, blueNectarLocator, pollenLocator);


        visionPortal = builder.build();

        visionPortal.setProcessorEnabled(aprilTagProcessor, false);
        visionPortal.setProcessorEnabled(redNectarLocator, false);
        visionPortal.setProcessorEnabled(blueNectarLocator, false);
        visionPortal.setProcessorEnabled(pollenLocator, false);






    }


    // may remove the Alliance parameter, right now it only exists to filter out the blue side aprilTags
    /*
    could also change it to enum. could put enum definition in Robot Hub file and change it each opMode
     I wouldn't need the parameter then if it was public and global across all files, I could just access the updated
     value here
    */

    //may have to put this method in a separate class. It is so large.
    public void useAprilTagsToSetCellState(Robot.Alliance alliance){
        currentDetections = aprilTagProcessor.getDetections();
        //Find the height a cell needs to be above to be considered raised
        double RAISED_CELL_HEIGHT_THRESHOLD = null;


        switch(alliance){
            case RED:
                /*
                figure out how to configure this so it does not divide by 0 or an undefined variable
                if it does not detect any aprilTags
                */
                double sumFarRedCellHeight = 0;
                double sumNearRedCellHeight = 0;
                double numOfNearRedCellTags = 0;
                double numOfFarRedCellTags = 0;


                for(AprilTagDetection detection : currentDetections)
                    if(detection.id == 34 || detection.id == 35 || detection.id == 36 || detection.id == 37){
                        sumNearRedCellHeight += detection.ftcPose.z;
                        numOfNearRedCellTags += 1;
                    } else if(detection.id == 30 || detection.id == 31 || detection.id == 32 || detection.id == 33){
                        sumFarRedCellHeight += detection.ftcPose.z;
                        numOfFarRedCellTags += 1;
                    } else {
                        //may put something here for the blue detections it will inevitably loop through, just so they are ommitted
                    }
                double avgNearRedCellHeight = sumNearRedCellHeight / numOfNearRedCellTags;
                double avgFarRedCellHeight = sumFarRedCellHeight / numOfNearRedCellTags;
                if ((avgNearRedCellHeight > RAISED_CELL_HEIGHT_THRESHOLD) || (avgFarRedCellHeight < RAISED_CELL_HEIGHT_THRESHOLD)){
                    // may create an enum for which side is raised over a string
                    robot.setNearRedCellState(Robot.Near_Red_Cell_State.RAISED);
                    robot.setFarRedCellState(Robot.Far_Red_Cell_State.LOWERED);
                } else if (avgNearRedCellHeight < RAISED_CELL_HEIGHT_THRESHOLD || (avgFarRedCellHeight > RAISED_CELL_HEIGHT_THRESHOLD)){
                    robot.setNearRedCellState(Robot.Near_Red_Cell_State.LOWERED);
                    robot.setFarRedCellState(Robot.Far_Red_Cell_State.RAISED);
                }

            case BLUE:
                /*
                figure out how to configure this so it does not divide by 0 or an undefined variable
                if it does not detect any aprilTags
                */
                double sumFarBlueCellHeight = 0;
                double sumNearBlueCellHeight = 0;
                double numOfNearBlueCellTags = 0;
                double numOfFarBlueCellTags = 0;


                for(AprilTagDetection detection : currentDetections)
                    if(detection.id == 38 || detection.id == 39 || detection.id == 40|| detection.id == 41){
                        sumNearBlueCellHeight += detection.ftcPose.z;
                        numOfNearBlueCellTags += 1;
                    } else if(detection.id == 42 || detection.id == 43 || detection.id == 44 || detection.id == 45){
                        sumFarBlueCellHeight += detection.ftcPose.z;
                        numOfFarBlueCellTags += 1;
                    } else {
                        //may put something here for the red detections it will inevitably loop through, just so they are ommitted
                    }
                double avgNearBlueCellHeight = sumNearBlueCellHeight / numOfNearBlueCellTags;
                double avgFarBlueCellHeight = sumFarBlueCellHeight / numOfNearBlueCellTags;
                if ((avgNearBlueCellHeight > RAISED_CELL_HEIGHT_THRESHOLD) || (avgFarBlueCellHeight < RAISED_CELL_HEIGHT_THRESHOLD)){
                    // may create an enum for which side is raised over a string
                    robot.setNearBlueCellState(Robot.Near_Blue_Cell_State.RAISED);
                    robot.setFarBlueCellState(Robot.Far_Blue_Cell_State.LOWERED);
                } else if (avgNearBlueCellHeight < RAISED_CELL_HEIGHT_THRESHOLD || (avgFarBlueCellHeight > RAISED_CELL_HEIGHT_THRESHOLD)){
                    robot.setNearBlueCellState(Robot.Near_Blue_Cell_State.LOWERED);
                    robot.setFarBlueCellState(Robot.Far_Blue_Cell_State.RAISED);
                }
                break;

        }





    }



    public double findCellDis(int AprTag1, int AprTag2, int AprTag3, int AprTag4, double robotPosX, double robotPosY){
        double sumX = 0;
        double sumY = 0;
        double numAprTags = 0;

        List<AprilTagDetection> cellDetections;

        for(AprilTagDetection d : currentDetections){
            if((d == AprTag1) || (d == AprTag2) || (d == AprTag3) || (d == AprTag4)){
                cellDetections.add(d);
            } else{
                //leave blank; should do nothing with the rest
            }
        }

            for(AprilTagDetection d : cellDetections) {
                sumX += d.ftcPose.x;
                sumY += d.ftcPose.y;
                numAprTags += 1;
            }

        double avgX = 0;
        double avgY = 0;
        if(numAprTags > 0){
            avgX = sumX/numAprTags;
            avgY = sumY/numAprTags;
        } else {
            //
        }

        double disFromRobot = Math.sqrt(Math.pow((robotPosX - avgX), 2) + Math.pow((robotPosY - avgY), 2));
        return disFromRobot;


    }
    public double getDisFromSpecifiedCell(Robot.Alliance alliance, double robotPosX, double robotPosY){
        if((alliance == Robot.Alliance.RED) & (robot.getNearRedCellState() == Robot.Near_Red_Cell_State.RAISED)){
            findCellDis(34, 35, 36, 37, robotPosX, robotPosY);
        } else if ((alliance == Robot.Alliance.RED) & (robot.getFarRedCellState() == Robot.Far_Red_Cell_State.RAISED)) {
            findCellDis(30, 31, 32, 33, robotPosX, robotPosY);
        } else if((alliance == Robot.Alliance.BLUE)){

        }


        }





    public void enableAprilTag(){
        visionPortal.setProcessorEnabled(aprilTagProcessor, true);
    }
    public void disableAprilTag(){
        visionPortal.setProcessorEnabled(aprilTagProcessor, false);
    }

    public void enableRedNectarLocator(){
        visionPortal.setProcessorEnabled(redNectarLocator, true);
    }

    public void disableRedNectarLocator(){
        visionPortal.setProcessorEnabled(redNectarLocator, false);
    }
    public void enableBlueNectarLocator(){
        visionPortal.setProcessorEnabled(blueNectarLocator, true);
    }

    public void disableBlueNectarLocator(){
        visionPortal.setProcessorEnabled(blueNectarLocator, false);
    }


    public void enablePollenLocator(){
        visionPortal.setProcessorEnabled(pollenLocator, true);
    }
    public void disablePollenLocator(){
        visionPortal.setProcessorEnabled(pollenLocator, false);
    }


    /*
    REMINDER: DO NOT CLOSE VISION PORTAL IN THE MIDDLE OF OPMODE UNLESS PERMANENTLY
    FINISHED WITH IT. IT WILL CAUSE CPU STRAIN TO CLOSE AND BUILD IT AGAIN.
     */
    public void restVisionPortal(){
        visionPortal.stopStreaming();
    }

    public void reactivateVisionPortal(){
        visionPortal.resumeStreaming();
    }


    public void closeVisionPortal(){
        visionPortal.close();
    }





    /*
    //Check how to get telemetry in a Subsystem class
    public void visionTelemetry(){

        telemetry.addData("Current Alliance: ", robot.getAlliance());
        tel


    }
    */



}
