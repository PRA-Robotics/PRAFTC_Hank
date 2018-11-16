public class Test1 {
  private double startX = 0;
  private double endDis = 0;
  public void start(distance) {
    startX = Navigation.xPos;
    endDis = distance;
  }
  private double distance() {
    return Navigation.xPos - startX;
  }
  private boolean endPos() {
    if (distance() > endDis) {
      return true;
    } else {
      return false;
    }
  }
  public void run() {
    if (endPos()) {
      MotorVar.stop();
      ServoVar.openDrop();
    } else {
      MotorVar.set(2, 2);
      ServoVar.closeDrop();
    }
  }
}
