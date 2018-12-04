/**
*Autonomous Delta OpMode
*Commodore 64 Team
*PRA Rookie Robotics
*Written by Noah Mulvaney
**/
//packages and imports
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaBase;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaRoverRuckus;
//declare opMode and name
@Autonomous(name = "Autonomous")
public class AutonomousFTC extends LinearOpMode {
  //vuforia instances declared
  VuforiaRoverRuckus vuforia = null;
  VuforiaBase.TrackingResults vuRed = null;
  VuforiaBase.TrackingResults vuBlue = null;
  VuforiaBase.TrackingResults vuFront = null;
  VuforiaBase.TrackingResults vuBack = null;
  //hardware instances declared
  DcMotor left = null; //represents left drive motor
  DcMotor right = null; //represents right drive Motor
  DcMotor lift = null; //represents motor for the lift
  Servo drop = null; //represents the servo for dropping the marker
  Servo tilt = null; //represents the servo for tilting the lift arm
  Servo grab = null; //represents the servo for grabiing the particules
  //variables delcared and initialized
  double xPos = 0; //x position on the field with positive being toward forward wall
  double yPos = 0; //y position with positive being toward the blue wall
  double rot = 0; //orientation of the robot with zero being facing the forward wall
  String markVis = " "; //indicates which vuforia mark is visible
  double corFactor = 0; //corrects drive motor power to straighten
  double tickFactor = 33.95 / 560; //distance covered by each encorder tick
  double leftPow = 0; //left motor power
  double rightPow = 0; //right motor power
  public void runOpMode() {
    vuforia = new VuforiaRoverRuckus();
    left = hardwareMap.dcMotor.get("leftMotor");
    right = hardwareMap.dcMotor.get("rightMotor");
    drop = hardwareMap.servo.get("dropServo");
    lift = hardwareMap.dcMotor.get("liftMotor");
    tilt = hardwareMap.servo.get("tiltServo");
    grab = hardwareMap.servo.get("grabServo");
    vuforia.initialize(" ", VuforiaLocalizer.CameraDirection.BACK, false, true, VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES, 0, 0, 0, 0, 0, 0, true);
    waitForStart();
    if (opModeIsActive()) {
      vuforia.activate();
      left.setDirection(DcMotor.Direction.REVERSE);
      left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
      left.setTargetPosition(left.getCurrentPosition() + (int) ((11.09 * 40) / tickFactor));
      right.setTargetPosition(right.getCurrentPosition());
      leftPow = 4;
      rightPow = 4;
      while (opModeIsActive()) {
        vuRed = vuforia.track("RedPerimeter");
        vuBlue = vuforia.track("BluePerimeter");
        vuFront = vuforia.track("FrontPerimeter");
        vuBack = vuforia.track("BackPerimeter");
        if (vuRed.isVisible) {
          xPos = 0;
          yPos = 0;
          for (int i = 0; i < 12; i++) {
            xPos += vuRed.x / 10;
            yPos += vuRed.y / 10;
          }
          xPos = xPos / 12;
          yPos = yPos / 12;
          rot = vuRed.xAngle;
          markVis = "Red";
        } else if (vuBlue.isVisible) {
          xPos = 0;
          yPos = 0;
          for (int i = 0; i < 12; i++) {
            xPos += vuBlue.x / 10;
            yPos += vuBlue.y / 10;
          }
          xPos = xPos / 12;
          yPos = yPos / 12;
          rot = vuBlue.xAngle;
          markVis = "Blue";
        } else if (vuFront.isVisible) {
          xPos = 0;
          yPos = 0;
          for (int i = 0; i < 12; i++) {
            xPos += vuFront.x / 10;
            yPos += vuFront.y / 10;
          }
          xPos = xPos / 12;
          yPos = yPos / 12;
          rot = vuFront.xAngle;
          markVis = "Front";
        } else if (vuBack.isVisible) {
          xPos = 0;
          yPos = 0;
          for (int i = 0; i < 12; i++) {
            xPos += vuBack.x / 10;
            yPos += vuBack.y / 10;
          }
          xPos = xPos / 12;
          yPos = yPos / 12;
          rot = vuBack.xAngle;
          markVis = "Back";
        } else {
          markVis = "No";
        }
        while (left.isBusy() || right.isBusy()) {
          left.setPower((leftPow * corFactor) / 10);
          right.setPower(rightPow / 10);
        }
        telemetry.addData("X Position", xPos + " cm");
        telemetry.addData("Y Position", yPos + " cm");
        telemetry.addData("Orientation", rot + "°");
        telemetry.addData("Vuforia Mark Reference", markVis + " Mark Detected");
        telemetry.addData("Left Motor Power", left.getPower() * 10);
        telemetry.addData("Right Motor Power", right.getPower() * 10);
        telemetry.addData("Raise Lift Motor Power", lift.getPower() * 10);
        telemetry.addData("Marker Drop Servo", drop.getPosition());
        telemetry.addData("Rotate Lift Servo", tilt.getPosition());
        telemetry.addData("Grab Servo", grab.getPosition());
        telemetry.update();
      }
      vuforia.deactivate();
      vuforia.close();
    }
  }
}
