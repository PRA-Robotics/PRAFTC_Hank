public class MotorVar {
  public double powerRight = 0;
  public double powerLeft = 0;
  public void set(left, right) {
    powerLeft = left;
    powerRight = right;
  }
  public void turn(left, right) {
    powerLeft += left;
    powerRight += right;
  }
  public void stop() {
    powerLeft = 0;
    powerRight = 0;
  }
}
