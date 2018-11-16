import com.qualcomm.robotcore.hardware.*;

public class Hardware {
  private double adjust = 0;
  private DcMotor mLeft = null;
  private DcMotor mRight = null;
  private Servo sDrop = null;
  private Servo sLift = null;
  public void configure() {
    mLeft = hardwareMap.dcMotor.get("leftMotor");
    mRight = harwareMap.dcMotor.get("rightMotor");
    sDrop = hardwareMap.servo.get("dropServo");
    sLift = hardwareMap.servo.get("liftServo");
  }
  public void adjust(double factor) {
    adjust = factor;
  }
  public void updateMotor(double left, double right) {
    mLeft.setDirection(DcMotorSimple.Direction.REVERSE);
    mLeft.setPower(left / 10);
    mRight.setPower((right * adjust) / 10);
  }
  public void updateServo(double drop, double lift) {
    sDrop.setPosition(drop);
    sLift.setPosition(lift);
  }
  public double leftPower() {
    return mLeft.getPower();
  }
  public double rightPower() {
    return mRight.getPower();
  }
  public int dropPosition() {
    return sDrop.getPosition();
  }
  public int liftPosition() {
    return sLift.getPosition();
  }
}
