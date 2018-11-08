/**
*Autonomous (Commodore 64)
*Autonomous OpMode
*Rookie Team -- Commodore 64
*by Noah Mulvaney (NPM)
**/
/**
To Do: check wheels & forward, determine base speed, test power-speed linearity, test aurisNav
aurisNav -- simple test program for going foward 2 meters then stop and drop marker
**/
//import packages
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
//automonous opmode
@Autonomous(name = "Autonomous (Com64 11/2018 NPM)", group = "")
public class Com64_Autonomous extends LinearOpMode {
  //hardware variables declaration
  private DcMotor leftMotor; //creates DcMotor instance for the left motor
  private DcMotor rightMotor; //creates DcMotor instance for right motor
  private Servo testServo; //creates Servo intance for the marker drop servo
 //overide ??
  @Override
	public void runOpMode() {
		//general variables declarations
		double correctionFactor; //factor for correcting differences in motor speed and power
    boolean markerDropOpen; //tracks whether or not the marker drop servo is open
    double servoPosition; //target position for servo
    double leftPower; //sets left power from 0 to 10
    double rightPower; //sets right power
    boolean reverse; //state of reverse setting
    double speedBase; //calulating settings for calulating other speeds
    double rightSpeed; //calulated speed for right motor in centimeter per second
    double leftSpeed; //calulated speed for left motor
    double xPosition; //foward backward from start
    double yPosition; //left right from start //not in use
    double angle; //oriantation of robot //not in use
    double time; //time in seconds
    boolean stop; //variable for bringing robot to stop
    int count;
		//hardware variables initalization
    leftMotor = hardwareMap.dcMotor.get("leftMotor");
    rightMotor = hardwareMap.dcMotor.get("rightMotor");
    testServo = hardwareMap.servo.get("testServo");
    waitForStart(); //waits for start o be selected
    if (opModeIsActive()) {
      //non-control variable initalization
      markerDropOpen = false; //sets marker drop door to closed
      servoPosition = 0; //redundent initalization
      stop = false; //start with unrestricted movement
      xPosition = 0; //inital position
      yPosition = 0; //inital position
      angle = 0; //inital oriantation
      time = 0; //initalization
      speedBase = 55 / 2; //base speed setting
      rightSpeed = 0; //initalization
      leftSpeed = 0; //initalization
      correctionFactor = .85; //correction factor setting
      count = 0; //initalization
      //inital control variable initalization
      leftPower = 2; //inital power
      rightPower = 2; //inital power
      reverse = false; //inital reverse setting
	    while (opModeIsActive()) {
        //aurisNav
        xPosition = leftSpeed * time; //position based on set speed
        if (xPosition > 200) { //after robot passes two meters
          stop = true; //stop robot
          markerDropOpen = true; //drop marker
        }
				//general control and variable updates
	      if (markerDropOpen) { //if drop door should be open
	        servoPosition = 1; //open
	      } else {
	        servoPosition = 0; //close
	      }
        if (reverse) { //reverse setting
          if (leftPower > 0) { //if left motor is forward
            leftPower = - leftPower; //make backwards
          }
          if (rightPower > 0) { //if right motor is forward
            rightPower = - rightPower; //make backwards
          }
        }
        if (stop) { //stop setting
          leftPower = 0; //cut power
          rightPower = 0;
        }
        time = count / 4;
        leftSpeed = leftPower * speedBase; //speed calculation
        rightSpeed = rightPower * speedBase;
				//updating hardware
        testServo.setPosition(servoPosition);
		    leftMotor.setDirection(DcMotorSimple.Direction.REVERSE); //reverse so foward is forward
		    leftMotor.setPower(leftPower / 10); //converts power to between 0 and 1
		    rightMotor.setPower((rightPower * correctionFactor) / 10); //accounts for correction and converts to range
				//telemetry data
        telemetry.addData("X Position", xPosition); //robot position
        /*telemetry.addData("Y Position", yPosition); */ //not in use
        /*telemetry.addData("Orientation", angle); */ //robot orientation //not in use
        telemetry.addData("Time", time); //system time in seconds
        telemetry.addData("Servo Position", servoPosition); //servo position
		    telemetry.addData("Left Motor Power", leftPower); //power from 0 to 10
        /*telemetry.addData("Left Motor Speed", leftSpeed); */ //calculated speed in centimeters per second //not in use
		    telemetry.addData("Right Motor Power", rightPower);
        /*telemetry.addData("Right Motor Speed", rightSpeed); */ //not in use
        telemetry.addData("Reverse Setting", reverse); //reverse setting
 				telemetry.addData("Correction Factor", correctionFactor); //correction factor //calabrate and remove
        //update telemetry
		    telemetry.update();
        //count & timing
        sleep(250);
        count ++;
    	}
  	}
	}
}
