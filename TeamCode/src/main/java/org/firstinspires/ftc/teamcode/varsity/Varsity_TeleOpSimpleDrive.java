package org.firstinspires.ftc.teamcode.varsity;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/*
 * TELEOP EXAMPLE: SIMPLE DRIVE (FORWARD, BACKWARD, TURN)
 *
 * What this shows:  the simplest way to drive a robot with a controller.
 *                   Forward, backward, and turning in place. No sideways.
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
 *     Right stick (left/right)  ->  turn in place
 *
 * The big idea:
 *     The robot has two sides, and each side gets its own power.
 *
 *         left side  = drive + turn
 *         right side = drive - turn
 *
 *     Drive only:  both sides get the same power, so the robot goes straight.
 *     Turn only:   the sides get opposite power, so the robot spins in place.
 *     Both:        one side goes faster than the other, so it curves.
 *
 *     Example: drive = 0.5 and turn = 0.3
 *         left side  = 0.8
 *         right side = 0.2
 *     The robot moves forward while curving to the right.
 */
@TeleOp(name = "Varsity Example: TeleOp Simple Drive", group = "Varsity")
public class Varsity_TeleOpSimpleDrive extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    @Override
    public void runOpMode() {

        // ---------- INIT ----------

        frontLeft = hardwareMap.get(DcMotor.class, "FLD");
        backLeft = hardwareMap.get(DcMotor.class, "BLD");
        frontRight = hardwareMap.get(DcMotor.class, "FRD");
        backRight = hardwareMap.get(DcMotor.class, "BRD");

        // On our robot all four motors need REVERSE for positive power to
        // drive the robot forward. If it drives backwards, change these
        // (and the other OpModes so they all agree).
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // BRAKE stops quickly when the stick is released. FLOAT coasts.
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized. Press PLAY.");
        telemetry.update();

        waitForStart();

        // ---------- RUN ----------
        while (opModeIsActive()) {

            // Pushing a stick UP gives a NEGATIVE number, so flip the sign.
            double drive = -gamepad2.left_stick_y;
            double turn = gamepad2.right_stick_x;

            double leftPower = drive + turn;
            double rightPower = drive - turn;

            // Motors only accept -1.0 to 1.0. Full drive plus full turn
            // would add up to 2.0, so force the numbers back into range.
            leftPower = Range.clip(leftPower, -1.0, 1.0);
            rightPower = Range.clip(rightPower, -1.0, 1.0);

            // Both motors on a side get the same power.
            frontLeft.setPower(leftPower);
            backLeft.setPower(leftPower);
            frontRight.setPower(rightPower);
            backRight.setPower(rightPower);

            telemetry.addData("Drive", "%.2f", drive);
            telemetry.addData("Turn", "%.2f", turn);
            telemetry.addData("Left side power", "%.2f", leftPower);
            telemetry.addData("Right side power", "%.2f", rightPower);
            telemetry.addLine();
            telemetry.addLine("Left stick = forward/back, right stick = turn");
            telemetry.update();
        }

        // Driver pressed STOP. Always leave the motors off.
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
}
