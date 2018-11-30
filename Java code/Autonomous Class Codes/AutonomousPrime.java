/**
*Autonomous OpMode for Commodore 64
*PRA Robotics Rookie Team
*Written by Noah Mulvaney
**/
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "AutonomousPrime NM", group = " ")

public class AutonomousPrime extends LinearOpMode {
  public void runOpMode() {
    RunSet.initalize();
    RunSet.settings(.85);
    waitForStart();
    if (opModeIsActive) {
      RunSet.start(2, 2, false, false);
      Test1.start(200);
      while(opModeIsActive) {
        Test1.run();
        RunSet.loop();
      }
    }
  }
}
