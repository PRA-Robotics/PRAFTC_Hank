package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.util.List;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaRoverRuckus;
import org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus;

@TeleOp(name = "TestTensor (Blocks to Java)", group = "")
public class TestTensor extends LinearOpMode {

  private VuforiaRoverRuckus vuforiaRoverRuckus;
  private TfodRoverRuckus tfodRoverRuckus;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    List recognitions;
    double goldMineralX;
    double silverMineral1X;
    double silverMineral2X;

    vuforiaRoverRuckus = new VuforiaRoverRuckus();
    tfodRoverRuckus = new TfodRoverRuckus();

    // Put initialization blocks here.
    vuforiaRoverRuckus.initialize("", VuforiaLocalizer.CameraDirection.BACK,
        true, false, VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES,
        0, 0, 0, 0, 0, 0, true);
    tfodRoverRuckus.initialize(vuforiaRoverRuckus, 0.4, true, true);
    telemetry.addData(">", "Press Play to start");
    telemetry.update();
    waitForStart();
    if (opModeIsActive()) {
      tfodRoverRuckus.activate();
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        recognitions = tfodRoverRuckus.getRecognitions();
        telemetry.addData("# Objects Recognized", recognitions.size());
        if (recognitions.size() == 3) {
          goldMineralX = -1;
          silverMineral1X = -1;
          silverMineral2X = -1;
          // TODO: Enter the type for variable named recognition
          for (UNKNOWN_TYPE recognition : recognitions) {
            if (recognition.getLabel().equals("Gold Mineral")) {
              goldMineralX = recognition.getLeft();
            } else if (silverMineral1X == -1) {
              silverMineral1X = recognition.getLeft();
            } else {
              silverMineral2X = recognition.getLeft();
            }
          }
          // Make sure we found one gold mineral and two silver minerals.
          if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
            if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
              telemetry.addData("Gold Mineral Position", "Left");
            } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
              telemetry.addData("Gold Mineral Position", "Right");
            } else {
              telemetry.addData("Gold Mineral Position", "Center");
            }
          }
        }
        telemetry.update();
      }
      tfodRoverRuckus.deactivate();
    }

    vuforiaRoverRuckus.close();
    tfodRoverRuckus.close();
  }
}
