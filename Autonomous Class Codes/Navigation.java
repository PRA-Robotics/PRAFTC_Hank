public class Navigation {
  public int xPos = 0;
  public int yPos = 0;
  public int angle = 0;
  public String vRef = " ";
  private HenryEyes map = new HenryEyes();
  public void updateNav() {
    xPos = map.avgX * 10;
    yPos = map.avgY * 10;
    angle = map.avgRotX;
    vRef = map.vuMarkResult;
  }
}
