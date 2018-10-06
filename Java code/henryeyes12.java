package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaBase;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaRoverRuckus;

@TeleOp(name = "henryeyes12 (Blocks to Java)", group = "")
public class henryeyes12 extends LinearOpMode {

  private VuforiaRoverRuckus vuforiaRoverRuckus;

  VuforiaBase.TrackingResults vuMarkResult;
  double sumX;
  double sumY;
  double sumZ;
  double sumRotX;
  double sumRotY;
  double sumRotZ;
  double avgX;
  double avgY;
  double avgZ;
  double avgRotX;
  double avgRotY;
  double avgRotZ;
  String XYZstring;
  String RotXYZstring;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    VuforiaBase.TrackingResults vuMarkRed;
    VuforiaBase.TrackingResults vuMarkFront;
    VuforiaBase.TrackingResults vuMarkBack;
    VuforiaBase.TrackingResults vuMarkBlue;

    vuforiaRoverRuckus = new VuforiaRoverRuckus();

    vuforiaRoverRuckus.initialize("", VuforiaLocalizer.CameraDirection.BACK,
        true, true, VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES,
        0, 0, 0, 0, 0, 0, true);
    // Initialize Vuforia (use default settings).
    Initialize_vars();
    telemetry.addData("VuMark Example", "Press start to continue...");
    // Prompt user to push start button.
    telemetry.update();
    // Wait until user pushes start button.
    waitForStart();
    if (opModeIsActive()) {
      // Activate Vuforia software.
      vuforiaRoverRuckus.activate();
      while (opModeIsActive()) {
        // Get the tracking results.
        vuMarkBlue = vuforiaRoverRuckus.track("BluePerimeter");
        vuMarkRed = vuforiaRoverRuckus.track("RedPerimeter");
        vuMarkFront = vuforiaRoverRuckus.track("FrontPerimeter");
        vuMarkBack = vuforiaRoverRuckus.track("BackPerimeter");
        // Is a VuMark visible?
        //Checking for Blue Wall
        if (vuMarkBlue.isVisible) {
          // Yes, we see one.
          telemetry.addData("VuMark", "A Blue Wall is visible");
          vuMarkResult = vuMarkBlue;
          targetDisplay();
          Initialize_vars();
          sleep(1000);
          telemetry.update();
          //Checking for Red Wall
        } else if (vuMarkRed.isVisible) {
          // Yes, we see one.
          telemetry.addData("VuMark", "A Red Wall is visible");
          vuMarkResult = vuMarkRed;
          targetDisplay();
          Initialize_vars();
          sleep(1000);
          telemetry.update();
          //Checking for Front Wall
        } else if (vuMarkFront.isVisible) {
          // Yes, we see one.
          telemetry.addData("VuMark", "A Front Wall is visible");
          vuMarkResult = vuMarkFront;
          targetDisplay();
          Initialize_vars();
          sleep(1000);
          telemetry.update();
          //Checking for Back wall
        } else if (vuMarkBack.isVisible) {
          // Yes, we see one.
          telemetry.addData("VuMark", "A Back Wall is visible");
          vuMarkResult = vuMarkBack;
          targetDisplay();
          Initialize_vars();
          sleep(1000);
          telemetry.update();
        } else {
          // No, we don't see one.
          telemetry.addData("VuMark", "No VuMarks are visible.");
          sleep(1000);
          telemetry.update();
        }
      }
      // Deactivate before exiting.
      vuforiaRoverRuckus.deactivate();
    }

    vuforiaRoverRuckus.close();
  }

  /**
   * Describe this function...
   */
  private void accumXYZ() {
    for (int count = 0; count < 100; count++) {
      sumX = sumX + vuMarkResult.x;
      sumY = sumY + vuMarkResult.y;
      sumZ = sumZ + vuMarkResult.z;
      sumRotX = sumRotX + vuMarkResult.xAngle;
      sumRotY = sumRotY + vuMarkResult.yAngle;
      sumRotZ = sumRotZ + vuMarkResult.zAngle;
    }
  }

  /**
   * Describe this function...
   */
  private void targetDisplay() {
    averageXYZ();
    accumXYZ();
    FormatXYZ_string();
    telemetry.addData("X-Y-Z Values", XYZstring);
    FormatRotXYZ_string();
    telemetry.addData("Rot X-Y- Z Values", RotXYZstring);
  }

  /**
   * Describe this function...
   */
  private void Initialize_vars() {
    XYZstring = "";
    RotXYZstring = "";
    sumX = 0;
    sumY = 0;
    sumZ = 0;
    sumRotX = 0;
    sumRotY = 0;
    sumRotZ = 0;
  }

  /**
   * Describe this function...
   */
  private void FormatXYZ_string() {
    XYZstring += "X= " + JavaUtil.formatNumber(avgX, 0);
    XYZstring += ", Y= " + JavaUtil.formatNumber(avgY, 0);
    XYZstring += ", Z= " + JavaUtil.formatNumber(avgZ, 0);
  }

  /**
   * Describe this function...
   */
  private void FormatRotXYZ_string() {
    RotXYZstring += " Rot X= " + JavaUtil.formatNumber(avgRotX, 0);
    RotXYZstring += ", Rot Y= " + JavaUtil.formatNumber(avgRotY, 0);
    RotXYZstring += ", Rot Z= " + JavaUtil.formatNumber(avgRotZ, 0);
  }

  /**
   * Describe this function...
   */
  private void averageXYZ() {
    avgX = sumX / 100;
    avgY = sumY / 100;
    avgZ = sumZ / 100;
    avgRotX = sumRotX / 100;
    avgRotY = sumRotY / 100;
    avgRotZ = sumRotZ / 100;
  }
}
