package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.*;

public class Hardware {
  DcMotor rightMotor;
  DcMotor leftMotor;
  DcMotor liftMotor;
  Servo dropServo;
  Servo liftServo;
  Servo clawServo;
  public Hardware(HardwareMap hardwareMap) {
    this.rightMotor = hardwareMap.dcMotor.get("rightMotor");
    this.leftMotor = hardwareMap.dcMotor.get("leftMotor");
    this.liftMotor = hardwareMap.dcMotor.get("liftMotor");
    this.dropServo = hardwareMap.servo.get("dropServo");
    this.liftServo = hardwareMap.servo.get("liftServo");
    this.clawServo = hardwareMap.servo.get("clawServo");
  }
  public void motorStart(Config config) {
    this.rightMotor.setDirection(DcMotor.Direction.REVERSE);
    this.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    this.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    this.rightMotor.setPower(config.SPEED * config.CORRECTION);
    this.leftMotor.setPower(config.SPEED);
  }
  public void forward(Config config, double dist) {
    this.rightMotor.setTargetPosition(this.rightMotor.getCurrentPosition() + (int) (dist * config.FORWARD));
    this.leftMotor.setTargetPosition(this.leftMotor.getCurrentPosition() + (int) (dist * config.FORWARD));
    while (this.leftMotor.isBusy() || this.rightMotor.isBusy()) {}
  }
  public void turn(Config config, double angle) {
    this.rightMotor.setTargetPosition(this.rightMotor.getCurrentPosition() + (int) (angle * config.TURN));
    this.leftMotor.setTargetPosition(this.leftMotor.getCurrentPosition() - (int) (angle * config.TURN));
    while (this.leftMotor.isBusy() || this.rightMotor.isBusy()) {}
  }
}
