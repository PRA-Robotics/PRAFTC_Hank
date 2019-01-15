package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@Autonomous(name = "Autonomous")

public class Commodore extends LinearOpMode {
  Vision vision = new Vision();
  Hardware robot = new Hardware();
  Config config = new Config(.2, 1, 16.505, 5.545);

  public void runOpMode() {
    vision.init();
    robot.init(hardwareMap);

    waitForStart();
    if (opModeIsActive()) {
      vision.open();
      robot.motorStart(config);

      int loop = 0;

      while(opModeIsActive()) {
        if (loop == 0) {
          robot.forward(config, 50);
        }

        //vision.update(); FIX
        loop++;
      }
      vision.shut();
    }
  }
}
