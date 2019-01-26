package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.*;

public class Hardware {
  DcMotor rightMotor;
  DcMotor leftMotor;
  Servo dropServo;
  Servo liftServo;
  Servo tiltServo;
  Servo clawServo;
  public void init(HardwareMap hm){
    rightMotor = hm.get(DcMotor.class, "rightMotor");
    leftMotor = hm.get(DcMotor.class, "leftMotor");
    dropServo = hm.get(Servo.class, "dropServo");
    liftServo = hm.get(Servo.class, "liftServo");
    tiltServo = hm.get(Servo.class, "tiltServo");
    clawServo = hm.get(Servo.class, "colServo");
  }
  public void motorStart(Config config) {
    this.leftMotor.setDirection(DcMotor.Direction.REVERSE);
    this.rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    this.leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    this.rightMotor.setTargetPosition(this.rightMotor.getCurrentPosition());
    this.leftMotor.setTargetPosition(this.leftMotor.getCurrentPosition());
    this.rightMotor.setPower(config.SPEED);
    this.leftMotor.setPower(config.SPEED);
  }
  public void forward(Config config, double dist) {
    this.rightMotor.setPower(config.SPEED);
    this.leftMotor.setPower(config.SPEED * 1.14); //1.15, 1.1375, 1.2, 1.1, 1.05, 1
    this.rightMotor.setTargetPosition(this.rightMotor.getCurrentPosition() + (int) (dist * config.FORWARD));
    this.leftMotor.setTargetPosition(this.leftMotor.getCurrentPosition() + (int) (dist * config.FORWARD));
    while (this.leftMotor.isBusy() && this.rightMotor.isBusy()) {
    }
  }
  public void turn(Config config, double angle) {
    this.rightMotor.setPower(config.SPEED);
    this.leftMotor.setPower(config.SPEED);
    this.rightMotor.setTargetPosition(this.rightMotor.getCurrentPosition() + (int) (angle * config.TURN));
    this.leftMotor.setTargetPosition(this.leftMotor.getCurrentPosition() - (int) (angle * config.TURN));
    while (this.leftMotor.isBusy() || this.rightMotor.isBusy()) {
    }
  }
  public void servo(double dropPos, double liftPos, double tiltPos, double clawPos) {
    dropServo.setPosition(dropPos);
    liftServo.setPosition(liftPos);
    tiltServo.setPosition(tiltPos);
    clawServo.setPosition(clawPos);
  }
}
