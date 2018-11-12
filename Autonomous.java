/**
*Autonomous OpMode for Commodore 64
*PRA Robotics Rookie Team
*Written by Noah Mulvaney
**/

//import packages
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Autonomous NM", group = " ")

void configure() {
  correctionFactor = .85;
  speedBase = 55 / 2;
}

void settings(double leftF, double rightF, boolean backF) {
  leftPower = leftF;
  rightPower = rightF;
  reverse = backF;
}

void timeUpdate() {
  time = count / 4;
}

void markerDrop() {
  if (dropOpen) {
    dropPosition = 1;
  } else {
    dropPosition = 0;
  }
}

void reverse() {
  if (reverse) {
    if (leftPower > 0) {
      leftPower = - leftPower;
    }
    if (rightPower > 0) {
      rightPower = - rightPower;
    }
  }
}

void stop() {
  if (stop) {
    leftPower = 0;
    rightPower = 0;
  }
}

void speedUpdate() {
  leftSpeed = leftPower * speedBase;
  rightSpeed = rightPower * speedBase;
}

void hardwareUpdate() {
  dropServo.setPosition(dropPosition);
  leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
  leftMotor.setPower(leftPower / 10);
  rightMotor.setPower((rightPower * correctionFactor) / 10);
}

void telemetryData() {
  telemetry.addData("X Position", xPosition + " cm");
  /*
  telemetry.addData("Y Position", yPosition + " cm");
  telemetry.addData("Orientation", orientation + "°");
  */
  telemetry.addData("Time", time + " sec");
  telemetry.addData("Drop Servo Position", dropPosition);
  telemetry.addData("Left Motor Power", leftPower);
  telemetry.addData("Right Motor Power", rightPower);
  telemetry.addData("Left Motor Speed", leftSpeed + " cm/sec");
  telemetry.addData("Right Motor Speed", rightSpeed + " cm/sec");
}

public class Autonomous extends LinearOpMode {
  public void runOpMode() {
    DcMotor leftMotor = hardwareMap.dcMotor.get("leftMotor");
    DcMotor rightMotor = hardwareMap.dcMotor.get("rightMotor");
    Servo dropServo = hardwareMap.dcMotor.get("testServo");
    boolean stop = false;
    boolean reverse = false;
    boolean dropOpen = false;
    int count = 0;
    double time = 0;
    double leftPower = 0;
    double rightPower = 0;
    double correctionFactor = 0;
    double speedBase = 0;
    double leftSpeed = 0;
    double rightSpeed = 0;
    double dropPosition = 0;
    double xPosition = 0;
    double yPosition = 0;
    double orientation = 0;

    waitForStart();

    if (opModeIsActive()) {
      configure();
      settings(2, 2, false);

      while (opModeIsActive()) {
        timeUpdate();

        xPosition = leftSpeed * time;
        if (xPosition > 200) {
          stop = true;
          dropOpen = true;
        }

        markerDrop();
        reverse();
        stop();
        speedUpdate();

        hardwareUpdate();
        telemetryData();
        telemetry.Update();

        sleep(250);
        count ++;
      }
    }
  }
}
