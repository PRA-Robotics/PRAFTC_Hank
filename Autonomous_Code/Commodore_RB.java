package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "Autonomous (Rear Blue)")

public class Commodore_Model extends LinearOpMode {
  Vision vision = new Vision();
  Hardware robot = new Hardware();
  Config config = new Config(.2, 16.505, 6);

  public void runOpMode() {
    vision.init();
    robot.init(hardwareMap);
    //dropPos, liftPos, tiltPos, clawPos
    robot.servo(.3, .3, .325, 0);

    waitForStart();
    if (opModeIsActive()) {
      vision.open();
      robot.motorStart(config);

      robot.servo(.3, .3, .335, 0);
      sleep(1000);
      robot.servo(.3, .8, .335, 0);
      sleep(6000);
      robot.servo(.3, .8, .25, 0);
      sleep(3000);
      robot.servo(.3, .4, .25, 0);
      sleep(3000);
      robot.servo(.3, .4, .335, 0);
      sleep(3000);
      robot.turn(config, -45);
      robot.forward(config, 250);
      robot.turn(config, 90);
      robot.forward(config, 200);
      robot.servo(1, .3, .5, 0);

      vision.shut();
    }
  }
}

//https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html
