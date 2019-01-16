package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "Autonomous")

public class Commodore extends LinearOpMode {
  Vision vision = new Vision();
  Hardware robot = new Hardware();
  Config config = new Config(.2, 16.505, 6); //.7 lift, .4 tilt

  public void runOpMode() {
    vision.init();
    robot.init(hardwareMap);

    waitForStart();
    if (opModeIsActive()) {
      vision.open();
      robot.motorStart(config);
      robot.servo(0, .3, .5, 0);

      int loop = 0;

      while(opModeIsActive()) {
        if (loop == 0) {
          robot.turn(config, 90);
        }

        loop++;
      }
      vision.shut();
    }
  }
}
