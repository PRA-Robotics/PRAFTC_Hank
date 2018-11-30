public class RunSet {
  public void initalize() {
    Hardware.configure();
  }
  public void settings(double correctionFactor) {
    Harware.adjust(correctionFactor)
  }
  public void start(double left, double right, boolean dropOpen, boolean liftUp) {
    Navigation.updateNav();
    MotorVar.set(left, right);
    if (dropOpen) {
      ServoVar.openDrop();
    } else {
      ServoVar.closeDrop();
    }
    if (liftUp) {
      ServoVar.liftUp();
    } else {
      ServoVar.liftDown();
    }
  }
  public void loop() {
    Hardware.updateMotor(MotorVar.powerLeft, MotorVar.powerRight);
    Hardware.updateServo(ServoVar.dropTarget, ServoVar.liftTarget);
    Telemetry.navUpdate();
    Telemetry.deviceUpdate();
    Telemetry.sendData();
    Navigation.updateNav();
  }
}
