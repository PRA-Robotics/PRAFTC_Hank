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
//set opMode and program name displayed on FTC app
@Autonomous(name = "Autonomous NM", group = " ")
//for configuring static varaiables
void configure() {
  correctionFactor = .85; //adjustment to rightMotor power to straighen course, determined expirementally
  speedBase = 55 / 2; //speed in centimeters per second, determined expirementally
  interval = 4; //time accuaracy for wait, count, and time system, a larger value results in less error from run time but reduces rate of commanded changes
}
//for setting intial motor power
void settings(double leftF, double rightF, int intervalF) {
  leftPower = leftF; //inital left power
  rightPower = rightF; //inital right power
}
//updates time and adds count
void timeUpdate() {
  time = count / interval; //calculate time in seconds
  count ++; //add loop count
}
//lower door to drop marker
void dropMarker() {
  dropPosition = 1; //full open
}
//close door on marker drop
void closeDrop() {
  dropPosition = 0; //closed
}
//reverse both motors
void reverse() {
  if (leftPower > 0) { //if left power is foward
    leftPower = - leftPower; //make backwards
  }
  if (rightPower > 0) { //if right power is foward
    rightPower = - rightPower; //make backwards
  }
}
//stop robot by cutting power
void stop() {
  leftPower = 0; //brake left motor
  rightPower = 0; //brake right motor
}
//recalculates speed values
void speedUpdate() {
  leftSpeed = leftPower * speedBase; //left speed
  rightSpeed = rightPower * speedBase; //right speed
}
//variables to harware update
void hardwareUpdate() {
  dropServo.setPosition(dropPosition); //marker drop servo position
  leftMotor.setDirection(DcMotorSimple.Direction.REVERSE); //reverse left motor to make positive forward
  leftMotor.setPower(leftPower / 10); //left motor adjusted for correct range
  rightMotor.setPower((rightPower * correctionFactor) / 10); //right motor adjusted for straightness and correct range
}
//variables to telemetry data
void telemetryData() {
  telemetry.addData("X Position", xPosition + " cm"); //forward backwards position on field
  telemetry.addData("Y Position", yPosition + " cm"); //left right from start position
  telemetry.addData("Orientation", orientation + "°"); //orientation where start is 0°
  telemetry.addData("Time", time + " sec"); //time in seconds
  telemetry.addData("Drop Servo Position", dropPosition); //position of servo on marker drop
  telemetry.addData("Left Motor Power", leftPower); //left motor power
  telemetry.addData("Right Motor Power", rightPower); //right motor power
  telemetry.addData("Left Motor Speed", leftSpeed + " cm/sec"); //calculated left speed
  telemetry.addData("Right Motor Speed", rightSpeed + " cm/sec"); //calcualated right speed
}
//chack variables for correct range
void variableCheck() {
  //drop position between 0 and 1
  if (dropPosition < 0) {
    dropPosition = 0;
  }
  if (dropPosition > 1) {
    dropPosition = 1;
  }
  //power between -10 and 10
  if (leftPower < -10) {
    leftPower = -10;
  }
  if (leftPower > 10) {
    leftPower = 10;
  }
  if (rightPower < -10) {
    rightPower = -10;
  }
  if (rightPower > 10) {
    rightPower = 10;
  }
}
//primary class constructor
public class Autonomous extends LinearOpMode {
  //when program is selected
  public void runOpMode() {
    //declare and initalize variables
    DcMotor leftMotor = hardwareMap.dcMotor.get("leftMotor");
    DcMotor rightMotor = hardwareMap.dcMotor.get("rightMotor");
    Servo dropServo = hardwareMap.dcMotor.get("testServo");
    int count = 0;
    int interval = 0;
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
    waitForStart(); //wait for start button to be pressed
    if (opModeIsActive()) { //when running
      //inital servo and motor positions
      closeDrop();
      stop();
      //configurations and settings
      configure();
      settings(2, 2);
      while (opModeIsActive()) { //loop while running
        //updated time
        timeUpdate();
        //test program
        xPosition = leftSpeed * time; //calculate position for linear motion at constant speed
        if (xPosition > 200) { //after two meters
          dropMarker(); //drop marker
          stop(); //stop moving
          wait(2000); //wait two seconds
          closeDrop(); //close drop door
        }
        //update variables
        speedUpdate();
        variableCheck();
        //update hardware and telemetry
        hardwareUpdate();
        telemetryData();
        telemetry.Update();
        //sleep
        sleep(1000 / interval);
      }
    }
  }
}
