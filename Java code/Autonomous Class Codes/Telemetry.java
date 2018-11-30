public class Telemetry {
  public void navUpdate() {
    telemetry.addData("X Position", Navigation.xPos + " cm");
    telemetry.addData("Y Position", Navigation.yPos + " cm");
    telemetry.addData("Orientation", Navigation.angle + "°");
    telemetry.addData("Vuforia Reference", vRef);
  }
  public void deviceUpdate() {
    telemetry.addData("Left Motor Power", Hardware.leftPower());
    telemetry.addData("Right Motor Power", Hardware.rightPower());
    telemetry.addData("Drop Servo Position", Hardware.dropPosition());
    telemetry.addData("Lift Servo Position", Harware.liftPosition());
  }
  public void sendData() {
    telemetry.update();
  }
}
