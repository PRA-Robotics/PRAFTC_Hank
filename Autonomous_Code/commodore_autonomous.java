package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaBase;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaRoverRuckus;

@Autonomous(name = "Autonomous")

public class Vision {
  VuforiaRoverRuckus vuforia;
  VuforiaBase.TrackingResults vuFront;
  VuforiaBase.TrackingResults vuBack;
  VuforiaBase.TrackingResults vuRed;
  VuforiaBase.TrackingResults vuBlue;
  String markVis;
  public void init() {
    this.vuforia = new VuforiaRoverRuckus();
    this.vuforia.initialize(" ", VuforiaLocalizer.CameraDirection.BACK, false, true, VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES, 0, 0, 0, 0, 0, 0, true);
  }
  public void open() {
    this.vuforia.activate();
  }
  public void update() {
    this.vuFront = this.vuforia.track("FrontPerimeter");
    this.vuBack = this.vuforia.track("BackPerimeter");
    this.vuRed = this.vuforia.track("RedPerimeter");
    this.vuBlue = this.vuforia.track("BluePerimeter");
  }
  public VuforiaBase.TrackingResults visible() {
    if (this.vuFront.isVisible) {
      this.markVis = "Front";
      return this.vuFront;
    } else if (this.vuBack.isVisible) {
      this.markVis = "Back";
      return this.vuBack;
    } else if (this.vuRed.isVisible) {
      this.markVis = "Red";
      return this.vuRed;
    } else if (this.vuBlue.isVisible) {
      this.markVis = "Blue";
      return this.vuBlue;
    } else {
      this.markVis = "None";
      return null;
    }
  }
  public void find(Navigation nav, VuforiaBase.TrackingResults wall) {
    nav.xPos = 0;
    nav.yPos = 0;
    for (int i = 0; i < 12; i++) {
      nav.xPos += wall.x / 10;
      nav.yPos += wall.y / 10;
    }
    nav.xPos = nav.xPos / 12;
    nav.yPos = nav.yPos / 12;
    nav.rot = wall.xAngle;
  }
  public void shut() {
    this.vuforia.deactive();
    this.vuforia.close();
  }
}

public class Hardware {
  DcMotor rightMotor;
  DcMotor leftMotor;
  DcMotor liftMotor;
  Servo dropServo;
  Servo liftServo;
  Servo clawServo;
  public void init() {
    this.rightMotor = hardwareMap.dcMotor.get("rightMotor");
    this.leftMotor = hardwareMap.dcMotor.get("leftMotor");
    this.liftMotor = hardwareMap.dcMotor.get("liftMotor");
    this.dropServo = hardwareMap.servo.get("dropServo");
    this.liftServo = hardwareMap.servo.get("liftServo");
    this.clawServo = hardwareMap.servo.get("clawServo");
  }
  public void motorStart(RobotConfig config) {
    this.rightMotor.setDirection(DcMotor.Direction.REVERSE);
    this.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    this.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    this.rightMotor.setPower(config.SPEED * config.CORRECTION);
    this.leftMotor.setPower(config.SPEED);
  }
}

public class RobotConfig {
  static double SPEED;
  static double CORRECTION;
  static double DISTANCE;
  public void set(double speed, double correction, double distance) {
    this.SPEED = speed;
    this.CORRECTION = correction;
    this.DISTANCE = distance;
  }
}

public class Navigation {
  double xPos;
  double yPos;
  double rot;
  public void new() {

  }
}

public class Commodore_Autonomous extends LinearOpMode {
  Vision vision;
  Hardware robot;
  RobotConfig config;
  Navigation map;
  config.set(.3, 1, 0);
  public void runOpMode() {
    vision.init();
    robot.init();
    waitForStart();
    if (opModeIsActive()) {
      vision.open();
      robot.motorStart(config);
      while (opModeIsActive()) {

      }
      vision.shut();
    }
  }
}
