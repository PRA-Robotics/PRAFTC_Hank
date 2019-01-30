package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "Autonomous (Direct)")

public class Commodore_Direct extends LinearOpMode {
  //Vision vision = new Vision();
  Hardware robot = new Hardware();
  Config config = new Config(.3, 16.505, 6);

  public void runOpMode() {
    //vision.init();
    robot.init(hardwareMap);
    //dropPos, liftPos, tiltPos, clawPos
    robot.servo(.4, .15, .335, .343);

    waitForStart();
    if (opModeIsActive()) {
      //vision.open();
      robot.motorStart(config);

      robot.servo(.4, .15, .335, .343);
      robot.servo(.4, .84, .335, .343);
      sleep(7000);
      robot.forward(config, -8);
      robot.servo(.4, .84, .2, .343);
      sleep(2000);
      robot.servo(.4, .4, .2, .343);
      sleep(2000);
      robot.servo(.4, .4, .335, .343);
      sleep(2000);

      robot.turn(config, -1.6); //1.5, 2, -4.2, -5.3, -5.5, -7, -5, -2, 1
      robot.forward(config, 180);
      robot.servo(0, .4, .335, .343);
      sleep(3000);
      robot.turn(config, -10);
      robot.forward(config, -30);
      robot.turn(config, 90);
      robot.servo(.4, .4, .335, .343);
      robot.forward(config, 30);
      //vision.shut();
    }
  }
}

//https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html
