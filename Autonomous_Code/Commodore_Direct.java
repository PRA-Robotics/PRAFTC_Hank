package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "Autonomous (Direct)")

public class Commodore_Direct extends LinearOpMode {
  //Vision vision = new Vision();
  Hardware robot = new Hardware();
  Config config = new Config(.2, 16.505, 6);

  public void runOpMode() {
    //vision.init();
    robot.init(hardwareMap);
    //dropPos, liftPos, tiltPos, clawPos
    robot.servo(.3, .3, .325, .343);

    waitForStart();
    if (opModeIsActive()) {
      //vision.open();
      robot.motorStart(config);

      robot.servo(.34, .3, .335, .343);
      sleep(1000);
      robot.servo(.34, .84, .335, .343);
      sleep(6000);
      robot.forward(config, -8);
      robot.servo(.34, .84, .2, .343);
      sleep(3000);
      robot.servo(.34, .4, .2, .343);
      sleep(3000);
      robot.servo(.34, .4, .335, .343);
      sleep(3000);
      robot.forward(config, 10);
      robot.turn(config, -45);
      robot.forward(config, 100);
      robot.turn(config, 90);
      robot.forward(config, 125);
      robot.servo(0, .3, .335, .343);
      sleep(10000);
      robot.servo(.34, .3, .335, .343);
      //vision.shut();
    }
  }
}

//https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html
