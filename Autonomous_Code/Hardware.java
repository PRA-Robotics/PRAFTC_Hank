package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.*;

public class Hardware {
  DcMotor rightMotor;
  DcMotor leftMotor;
  DcMotor liftMotor;
  Servo dropServo;
  Servo liftServo;
  Servo clawServo;
  public void init(HardwareMap hm){
    rightMotor = hm.get(DcMotor.class, "rightMotor");
    leftMotor = hm.get(DcMotor.class, "leftMotor");
    liftServo = hm.get(Servo.class, "liftServo");
    liftServo = hm.get(Servo.class, "tiltServo");
    dropServo = hm.get(Servo.class, "dropServo");
    clawServo = hm.get(Servo.class, "clawServo");
  }
  public void motorStart(Config config) {
    this.leftMotor.setDirection(DcMotor.Direction.REVERSE);
    this.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    this.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    this.rightMotor.setPower(config.SPEED);
    this.leftMotor.setPower(config.SPEED);
  }
  public void forward(Config config, double dist) {
    this.rightMotor.setTargetPosition(this.rightMotor.getCurrentPosition() + (int) (dist * config.FORWARD));
    this.leftMotor.setTargetPosition(this.leftMotor.getCurrentPosition() + (int) (dist * config.FORWARD));
    while (this.leftMotor.isBusy() || this.rightMotor.isBusy()) {
    }
  }
  public void turn(Config config, double angle) {
    this.rightMotor.setTargetPosition(this.rightMotor.getCurrentPosition() + (int) (angle * config.TURN));
    this.leftMotor.setTargetPosition(this.leftMotor.getCurrentPosition() - (int) (angle * config.TURN));
    while (this.leftMotor.isBusy() || this.rightMotor.isBusy()) {
    }
  }
}
