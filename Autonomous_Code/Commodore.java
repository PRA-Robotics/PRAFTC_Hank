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
  double xPos;
  double yPos;
  double rot;
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
  public void find(VuforiaBase.TrackingResults wall) {
    this.xPos = 0;
    this.yPos = 0;
    for (int i = 0; i < 12; i++) {
      this.xPos += wall.x / 10;
      this.yPos += wall.y / 10;
    }
    this.xPos = this.xPos / 12;
    this.yPos = this.yPos / 12;
    this.rot = wall.xAngle;
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
  public void forward(RobotConfig config, double dist) {
    this.rightMotor.setTargetPosition(this.rightMotor.getCurrentPosition() + (int) (dist * config.FORWARD));
    this.leftMotor.setTargetPosition(this.leftMotor.getCurrentPosition() + (int) (dist * config.FORWARD));
    while (this.leftMotor.isBusy() || this.rightMotor.isBusy()) {}
  }
  public void turn(RobotConfig config, double angle) {
    this.rightMotor.setTargetPosition(this.rightMotor.getCurrentPosition() + (int) (angle * config.TURN));
    this.leftMotor.setTargetPosition(this.leftMotor.getCurrentPosition() - (int) (angle * config.TURN));
    while (this.leftMotor.isBusy() || this.rightMotor.isBusy()) {}
  }
}

public class RobotConfig {
  static double SPEED;
  static double CORRECTION;
  static double FORWARD;
  static double TURN;
  public RobotConfig(double speed, double correction, double forward, double turn) {
    this.SPEED = speed;
    this.CORRECTION = correction;
    this.FORWARD = forward;
    this.TURN = turn;
  }
}

public class Commodore_Autonomous extends LinearOpMode {
  Vision vision;
  Hardware robot;
  RobotConfig config = new RobotConfig(0.2, 1, 16.505, 0);
  int count;

  public void runOpMode() {
    vision.init();
    robot.init();
    count = 0;
    waitForStart();
    if (opModeIsActive()) {
      vision.open();
      robot.motorStart(config);
      while (opModeIsActive()) {
        if (count = 1) {
          robot.forward(config, 50);
        }
        count++;
      }
      vision.shut();
    }
  }
}
