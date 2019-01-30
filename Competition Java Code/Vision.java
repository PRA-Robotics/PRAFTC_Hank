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
  VuforiaBase.TrackingResults wall;
  String markVis;
  double xPos;
  double yPos;
  double rot;
  public Vision() {
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
    if (this.vuFront.isVisible) {
      this.markVis = "Front";
      this.wall = this.vuFront;
    } else if (this.vuBack.isVisible) {
      this.markVis = "Back";
      this.wall = this.vuBack;
    } else if (this.vuRed.isVisible) {
      this.markVis = "Red";
      this.wall = this.vuRed;
    } else if (this.vuBlue.isVisible) {
      this.markVis = "Blue";
      this.wall = this.vuBlue;
    } else {
      this.markVis = "None";
      this.wall = null;
    }
    this.xPos = 0;
    this.yPos = 0;
    for (int i = 0; i < 12; i++) {
      this.xPos += this.wall.x / 10;
      this.yPos += this.wall.y / 10;
    }
    this.xPos = this.xPos / 12;
    this.yPos = this.yPos / 12;
    this.rot = this.wall.xAngle;
  }
  public void shut() {
    this.vuforia.close();
  }
}
