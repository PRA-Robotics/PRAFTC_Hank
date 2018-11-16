public class ServoVar {
  public int dropTarget = 0;
  public int liftTarget = 0;
  public void closeDrop() {
    dropTarget = 1;
  }
  public void openDrop() {
    dropTarget = 0;
  }
  public void liftDown() {
    liftTarget = 0;
  }
  public void liftUp() {
    liftTarget = 1;);
  }
}
