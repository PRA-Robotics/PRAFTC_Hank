package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "Autonomous (Direct)")

public class Commodore_Direct extends LinearOpMode {
  Vision vision = new Vision();
  Hardware robot = new Hardware();
  Config config = new Config(.2, 16.505, 6);

  public void runOpMode() {
    vision.init();
    robot.init(hardwareMap);
    //dropPos, liftPos, tiltPos, clawPos
    robot.servo(.3, .3, .325, .39);

    waitForStart();
    if (opModeIsActive()) {
      vision.open();
      robot.motorStart(config);

      robot.servo(.3, .3, .335, .39);
      sleep(1000);
      robot.servo(.3, .84, .335, .39);
      sleep(6000);
      robot.turn(config, -8);
      robot.servo(.3, .84, 0, .39);
      sleep(3000);
      robot.servo(.3, .4, 0, .39);
      sleep(3000);
      robot.servo(.3, .4, .335, .39);
      sleep(3000);
      robot.forward(config, 10);
      robot.turn(config, -27.5);
      robot.forward(config, 150);
      robot.servo(0, .3, .335, .39);

      vision.shut();
    }
  }
}

//https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html
