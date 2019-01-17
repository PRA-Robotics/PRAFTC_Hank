package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "Autonomous")

public class Commodore extends LinearOpMode {
  Vision vision = new Vision();
  Hardware robot = new Hardware();
  Config config = new Config(.2, 16.505, 6);

  public void runOpMode() {
    vision.init();
    robot.init(hardwareMap);

    waitForStart();
    if (opModeIsActive()) {
      vision.open();
      robot.motorStart(config);

      robot.servo(0, .3, .5, 0);
      //sleep();
      robot.servo(0, .7, .5, 0);
      //sleep();
      robot.servo(0, .7, .4, 0);
      //sleep();
      robot.servo(0, .3, .4, 0);
      //sleep();
      robot.servo(0, .3, .5, 0);
      robot.turn(config, -45);
      robot.forward(config, 250);
      robot.turn(config, 90);
      robot.forward(config, 200);
      robot.servo(.8, .3, .5, 0);

      vision.shut();
    }
  }
}

//https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html
