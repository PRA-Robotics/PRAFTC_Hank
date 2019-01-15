package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

import Config.java;
import Hardware.java;
import Vision.java;

@Autonomous(name = "Autonomous")

public class Commodore extends LinearOpMode {
  Vision vision = new Vision();
  Hardware robot = new Hardware();
  Config config = new Config(.2, 1, 16.505, 0);

  public void runOpMode() {
    vision.init();

    waitForStart();
    if (opModeIsActive()) {
      vision.open();
      robot.motorStart(config);

      int loop = 0;

      while(opModeIsActive()) {
        if (loop == 0) {
          robot.forward(config, 50);
        }
        vision.update();
      }
      vision.shut();
    }
  }
}
