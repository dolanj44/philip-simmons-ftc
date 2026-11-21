package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/*
 * TELEOP EXAMPLE: MECANUM DRIVE WITH A GAMEPAD
 *
 * What this shows:  how to drive the robot around with a controller. Forward,
 *                   backward, sideways (strafe) and spinning in place.
 *
 * Robot configuration needed on the Driver Hub:
 *     DcMotor:  FLD  (front left drive)
 *     DcMotor:  BLD  (back left drive)
 *     DcMotor:  FRD  (front right drive)
 *     DcMotor:  BRD  (back right drive)
 *     (No encoders are needed for driving. Only power is used here.)
 *
 * How to drive it:
 *     Left stick  (up/down)     ->  drive forward / backward
 *     Left stick  (left/right)  ->  strafe sideways
 *     Right stick (left/right)  ->  turn in place
 *     Right bumper (hold)       ->  "slow mode", 30% speed, for precise moves
 *
 * The big idea:
 *     With mecanum wheels, each wheel gets its own power, and the MIX of
 *     the four powers decides which way the robot moves:
 *
 *         frontLeft  = drive + strafe + turn
 *         backLeft   = drive - strafe + turn
 *         frontRight = drive - strafe - turn
 *         backRight  = drive + strafe - turn
 *
 *     Forward: all four wheels spin the same way.
 *     Strafe:  the wheels on each diagonal spin together, opposite to the
 *              other diagonal.
 *     Turn:    left side one way, right side the other.
 *
 *     Add those together and you can do all three at once. If the sum goes
 *     past 1.0 for any wheel, we shrink ALL four by the same amount so the
 *     direction stays right (see "normalize" below).
 *
 *     This only works with MECANUM wheels. With ordinary wheels (tank drive)
 *     the robot cannot strafe, and you would only use drive and turn.
 */
@TeleOp(name = "Example: TeleOp Mecanum Drive", group = "Examples")
public class Example_TeleOpDrive extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    // How much to cut the speed when the slow-mode bumper is held.
    static final double SLOW_MODE_SCALE = 0.3;
    static final double FAST_MODE_SCALE = 2;

    @Override
    public void runOpMode() {

        // ---------- INIT ----------

        frontLeft = hardwareMap.get(DcMotor.class, "FLD");
        backLeft = hardwareMap.get(DcMotor.class, "BLD");
        frontRight = hardwareMap.get(DcMotor.class, "FRD");
        backRight = hardwareMap.get(DcMotor.class, "BRD");

        // Motors on opposite sides face opposite ways, so one side normally
        // needs REVERSE for "positive power" to mean "robot goes forward".
        // These match Example_AutoDriveForward. If pushing
        // the stick forward does NOT drive the robot forward, fix the
        // directions here AND in the autonomous example so they agree.
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // We only want to set power, not use encoders for speed control.
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // BRAKE stops quickly when the sticks are released. FLOAT coasts.
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addLine("THE GAME");
        telemetry.addData("Status", "Initialized. Press PLAY.");
        telemetry.update();

        waitForStart();

        // ---------- RUN ----------
        while (opModeIsActive()) {

            // Pushing a stick UP gives a NEGATIVE number, so flip the sign
            // on the forward/back axis.
            //straif
            double drive = gamepad2.left_stick_x;
            //spin?
            double strafe = -gamepad2.right_stick_x;
            //move
            double turn = gamepad2.right_stick_y;

            // Mix the three commands into one power per wheel.
            double flPower = drive + strafe + turn;
            double blPower = drive - strafe + turn;
            double frPower = drive - strafe - turn;
            double brPower = drive + strafe - turn;

            // Normalize: motors only accept -1.0 to 1.0. If any wheel's
            // number is bigger than that, divide all four by the biggest
            // one. Clipping each wheel separately would bend the direction
            // the robot drives.
            double max = Math.max(
                    Math.max(Math.abs(flPower), Math.abs(blPower)),
                    Math.max(Math.abs(frPower), Math.abs(brPower)));
            if (max > 1.0) {
                flPower /= max;
                blPower /= max;
                frPower /= max;
                brPower /= max;
            }

            // Slow mode for careful positioning.
            if (gamepad2.right_trigger_pressed) {
                flPower *= SLOW_MODE_SCALE;
                blPower *= SLOW_MODE_SCALE;
                frPower *= SLOW_MODE_SCALE;
                brPower *= SLOW_MODE_SCALE;
            }
            //FAST
            if (gamepad2.left_trigger_pressed) {
                flPower *= FAST_MODE_SCALE;
                frPower *= FAST_MODE_SCALE;
                blPower *= FAST_MODE_SCALE;
                brPower *= FAST_MODE_SCALE;

            }

            frontLeft.setPower(flPower);
            backLeft.setPower(blPower);
            frontRight.setPower(frPower);
            backRight.setPower(brPower);

            telemetry.addLine("THE GAME");
            telemetry.addData("Drive / Strafe / Turn", "%.2f / %.2f / %.2f", drive, strafe, turn);
            telemetry.addData("Front L / R", "%.2f / %.2f", flPower, frPower);
            telemetry.addData("Back  L / R", "%.2f / %.2f", blPower, brPower);
            telemetry.addData("Slow mode", gamepad2.right_trigger_pressed ? "ON" : "off");
            telemetry.addLine();
            telemetry.addLine("Left stick = move, right stick = turn, right trigger = slow");
            telemetry.update();
        }

        // Driver pressed STOP. Always leave the motors off.
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
}
