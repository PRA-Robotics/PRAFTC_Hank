package org.firstinspires.ftc.teamcode;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaBase;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaRoverRuckus;

public class Vision {
  VuforiaRoverRuckus vuforia;
  VuforiaBase.TrackingResults vuFront;
  VuforiaBase.TrackingResults vuBack;
  VuforiaBase.TrackingResults vuRed;
  VuforiaBase.TrackingResults vuBlue;
  String markVis;
  double xPos;
  double yPos;
  double rot;
  public void Vision() {
    this.vuforia = new VuforiaRoverRuckus();
  }
  public void init() {
    this.vuforia.initialize(" ", VuforiaLocalizer.CameraDirection.BACK, false, true, VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES, 0, 0, 0, 0, 0, 0, true);
  }
  public void open() {
    this.vuforia.activate();
  }
  public void update() {
    this.vuFront = this.vuforia.track("FrontPerimeter");
    this.vuBack = this.vuforia.track("BackPerimeter");
    this.vuRed = this.vuforia.track("RedPerimeter");
    this.vuBlue = this.vuforia.track("BluePerimeter");
  }
  public VuforiaBase.TrackingResults visible() {
    if (this.vuFront.isVisible) {
      this.markVis = "Front";
      return this.vuFront;
    } else if (this.vuBack.isVisible) {
      this.markVis = "Back";
      return this.vuBack;
    } else if (this.vuRed.isVisible) {
      this.markVis = "Red";
      return this.vuRed;
    } else if (this.vuBlue.isVisible) {
      this.markVis = "Blue";
      return this.vuBlue;
    } else {
      this.markVis = "None";
      return null;
    }
  }
  public void find(VuforiaBase.TrackingResults wall) {
    this.xPos = 0;
    this.yPos = 0;
    for (int i = 0; i < 12; i++) {
      this.xPos += wall.x / 10;
      this.yPos += wall.y / 10;
    }
    this.xPos = this.xPos / 12;
    this.yPos = this.yPos / 12;
    this.rot = wall.xAngle;
  }
  public void shut() {
    this.vuforia.deactive();
    this.vuforia.close();
  }
}
