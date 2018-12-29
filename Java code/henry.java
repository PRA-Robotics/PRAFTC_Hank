package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Henry (Blocks to Java)", group = "")
public class Henry extends LinearOpMode {

  private DcMotor rightMotor;
  private DcMotor leftMotor;
  private Servo testServo;

  /**
   * Describe this function...
   */
  private void runOpmode2() {
  }

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    double ServoPosition;
    double ServoSpeed;
    boolean servostop;
    double BoostAmt;

    rightMotor = hardwareMap.dcMotor.get("rightMotor");
    leftMotor = hardwareMap.dcMotor.get("leftMotor");
    testServo = hardwareMap.servo.get("testServo");

    // Reverse one of the drive motors.
    // Set servo to mid position
    ServoPosition = 0;
    ServoSpeed = 0.05;
    servostop = false;
    // You will have to determine which motor to reverse for your robot.
    // In this example, the right motor was reversed so that positive
    // applied power makes it move the robot in the forward direction.
    rightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    waitForStart();
    if (opModeIsActive()) {
      // Put run blocks here.
      while (opModeIsActive()) {
        // Put loop blocks here.
        if (gamepad1.left_trigger > 0) {
          BoostAmt = 1 / (0.7 * gamepad1.left_trigger) + 0.16666;
          // The Y axis of a joystick ranges from -1 in its topmost position
          // to +1 in its bottommost position. We negate this value so that
          // the topmost position corresponds to maximum forward power.
          leftMotor.setPower(gamepad1.left_stick_y / BoostAmt);
          rightMotor.setPower(gamepad1.right_stick_y / BoostAmt);
        } else {
          BoostAmt = 6;
          // The Y axis of a joystick ranges from -1 in its topmost position
          // to +1 in its bottommost position. We negate this value so that
          // the topmost position corresponds to maximum forward power.
          leftMotor.setPower(gamepad1.left_stick_y / BoostAmt);
          rightMotor.setPower(gamepad1.right_stick_y / BoostAmt);
        }
        telemetry.addData("Trigger", gamepad1.left_trigger);
        telemetry.addData("BoostAMT", BoostAmt);
        telemetry.addData("Left Pow", leftMotor.getPower());
        telemetry.addData("Right Pow", rightMotor.getPower());
        // Use gamepad X and B to open close servo
        if (gamepad1.b) {
          ServoPosition += ServoSpeed;
        }
        if (gamepad1.x) {
          ServoPosition += -ServoSpeed;
        }
        // Keep Servo position in valid range
        ServoPosition = Math.min(Math.max(ServoPosition, 0), 1);
        testServo.setPosition(ServoPosition);
        telemetry.addData("Servo", ServoPosition);
        telemetry.update();
        sleep(20);
        if (gamepad1.a) {
          servostop = true;
        }
        if (servostop) {
          ServoPosition = 0.5;
          telemetry.addData("key", "resetting");
          telemetry.update();
          sleep(5000);
        }
      }
    }
  }
}
