package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "Autonomous (Indirect)")

public class Commodore_Indirect extends LinearOpMode {
  //Vision vision = new Vision();
  Hardware robot = new Hardware();
  Config config = new Config(.3, 16.505, 6);

  public void runOpMode() {
    //vision.init();
    robot.init(hardwareMap);
    //dropPos, liftPos, tiltPos, clawPos
    robot.servo(.4, .3, .335, .343);

    waitForStart();
    if (opModeIsActive()) {
      //vision.open();
      robot.motorStart(config);

      robot.servo(.4, .3, .335, .343);
      robot.servo(.4, .84, .335, .343);
      sleep(5000);
      robot.forward(config, -8);
      robot.servo(.4, .84, .2, .343);
      sleep(2000);
      robot.servo(.4, .4, .2, .343);
      sleep(2000);
      robot.servo(.4, .4, .335, .343);
      sleep(2000);

      robot.forward(config, 65);
      robot.turn(config, 64); //62.5, 60, 57.5, 55:::50, 60, 57.5, 55
      robot.forward(config, 70);
      robot.turn(config, 72); //75, 78, 82
      robot.forward(config, 180);
      robot.turn(config, -45);
      robot.servo(0, .3, .335, .343);
      sleep(1500);
      robot.forward(config, -5);

      //vision.shut();
    }
  }
}

//https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html
