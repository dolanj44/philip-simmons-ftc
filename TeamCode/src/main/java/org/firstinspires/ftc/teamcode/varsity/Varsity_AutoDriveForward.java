package org.firstinspires.ftc.teamcode.varsity;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
 * AUTONOMOUS EXAMPLE: DRIVE FORWARD 2 FEET (LEAVE THE PARKING SPOT)
 *
 * What this shows:  the simplest useful autonomous. The robot drives straight
 *                   forward a set distance and stops. No driver input.
 *
 * Robot configuration needed on the Driver Hub:
 *     DcMotor:  FLD  (front left drive)
 *     DcMotor:  BLD  (back left drive)
 *     DcMotor:  FRD  (front right drive)
 *     DcMotor:  BRD  (back right drive)
 *     All four need their encoder cables plugged in.
 *
 * How to run it:
 *     Pick "Autonomous" on the Driver Station, choose this OpMode, press INIT,
 *     then PLAY. The robot drives 2 feet forward and stops.
 *
 * The big idea:
 *     Autonomous has no gamepad. Instead of "how hard to push" we say
 *     "how far to go", using the encoders (see DeviceExample_MotorEncoder).
 *     The only new step is turning a DISTANCE into encoder TICKS:
 *
 *         ticks = (distance / wheel circumference) * ticks per wheel turn
 *
 *     Wheel diameter is set below to 104 mm. Change it if the wheels change.
 *     TEST WITH THE ROBOT ON A STAND OR BLOCKS FIRST.
 */
@Autonomous(name = "Varsity Example: Auto Drive Forward 2 ft", group = "Varsity")
public class Varsity_AutoDriveForward extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    // ---------- THINGS YOU MUST CHECK FOR YOUR ROBOT ----------

    // How far to drive. 2 feet = 24 inches.
    static final double DISTANCE_INCHES = 24.0;

    // Our wheels are 104 mm across (104 / 25.4 = about 4.094 in).
    // A wrong number here means the wrong distance.
    static final double WHEEL_DIAMETER_INCHES = 104.0 / 25.4;

    // Gear ratio between the motor and the wheel from any chain or belt you
    // added. 1.0 means the wheel is attached directly to the motor gearbox.
    static final double EXTERNAL_GEAR_RATIO = 1.0;

    // Keep it slow while testing. Raise it once the distance is right.
    static final double DRIVE_SPEED = 0.4;

    @Override
    public void runOpMode() {

        // ---------- INIT ----------

        frontLeft = hardwareMap.get(DcMotor.class, "FLD");
        backLeft = hardwareMap.get(DcMotor.class, "BLD");
        frontRight = hardwareMap.get(DcMotor.class, "FRD");
        backRight = hardwareMap.get(DcMotor.class, "BRD");

        // Which way is "positive"? On our robot all four motors needed
        // REVERSE for positive power to drive the robot forward. If the
        // robot drives backwards or in circles, change these (and the same
        // lines in Varsity_TeleOpDrive so the two agree).
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // BRAKE makes the robot stop instead of coasting past the target.
        setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Ask the SDK how many ticks one motor turn is (see MotorEncoder
        // example), then work out ticks per inch of floor travelled.
        double ticksPerMotorRev = frontLeft.getMotorType().getTicksPerRev();
        double wheelCircumference = Math.PI * WHEEL_DIAMETER_INCHES;
        double ticksPerInch = (ticksPerMotorRev * EXTERNAL_GEAR_RATIO) / wheelCircumference;
        int targetTicks = (int) Math.round(DISTANCE_INCHES * ticksPerInch);

        // Zero the encoders, set the target, THEN switch to RUN_TO_POSITION.
        setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setTargetPosition(targetTicks);
        setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Status", "Initialized. Press PLAY.");
        telemetry.addData("Distance", "%.1f in", DISTANCE_INCHES);
        telemetry.addData("Target (ticks)", targetTicks);
        telemetry.update();

        waitForStart();

        // ---------- RUN ----------

        // In RUN_TO_POSITION, power is just a speed limit. The motors turn
        // whichever way is needed to reach the target and then hold there.
        setPower(DRIVE_SPEED);

        // Wait until the robot arrives. Stop waiting if STOP is pressed.
        // (Only checking one motor is fine for a simple straight drive, but
        // if one wheel stalls the others will keep going.)
        while (opModeIsActive() && frontLeft.isBusy()) {
            telemetry.addData("Target (ticks)", targetTicks);
            telemetry.addData("Front left", frontLeft.getCurrentPosition());
            telemetry.addData("Front right", frontRight.getCurrentPosition());
            telemetry.addData("Back left", backLeft.getCurrentPosition());
            telemetry.addData("Back right", backRight.getCurrentPosition());
            telemetry.update();
        }

        // Arrived (or STOP was pressed). Always leave the motors off.
        setPower(0);

        telemetry.addData("Status", "Done. Parked 2 ft forward.");
        telemetry.update();

        // Keep the OpMode alive briefly so the final message can be read.
        sleep(1000);
    }

    // Small helpers so each setting is written once instead of four times.

    private void setMode(DcMotor.RunMode mode) {
        frontLeft.setMode(mode);
        backLeft.setMode(mode);
        frontRight.setMode(mode);
        backRight.setMode(mode);
    }

    private void setTargetPosition(int ticks) {
        frontLeft.setTargetPosition(ticks);
        backLeft.setTargetPosition(ticks);
        frontRight.setTargetPosition(ticks);
        backRight.setTargetPosition(ticks);
    }

    private void setPower(double power) {
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
    }

    private void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        frontLeft.setZeroPowerBehavior(behavior);
        backLeft.setZeroPowerBehavior(behavior);
        frontRight.setZeroPowerBehavior(behavior);
        backRight.setZeroPowerBehavior(behavior);
    }
}
