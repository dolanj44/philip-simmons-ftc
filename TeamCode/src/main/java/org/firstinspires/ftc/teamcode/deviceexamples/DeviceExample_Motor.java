package org.firstinspires.ftc.teamcode.deviceexamples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

/*
 * DEVICE EXAMPLE 1 of 4: A PLAIN DC MOTOR
 *
 * What this shows:  how to make a motor spin, and how fast.
 *
 * Robot configuration needed on the Driver Hub:
 *     A DcMotor named exactly:  motor
 *
 * How to drive it:
 *     Left stick (up/down)  ->  motor speed, forwards and backwards
 *     Right bumper (hold)   ->  "slow mode", 30% power
 *
 * The big idea:  a motor takes POWER, a number from -1.0 to +1.0.
 *     +1.0 = full speed one way
 *      0.0 = stopped
 *     -1.0 = full speed the other way
 * The motor does NOT know where it is or how far it has turned. It only
 * knows how hard to push. (DeviceExample_MotorEncoder.java fixes that.)
 */
@Disabled
@TeleOp(name = "Device Example: Motor", group = "Device Examples")
public class DeviceExample_Motor extends LinearOpMode {

    // Holds the motor once we look it up. "null" just means "nothing yet".
    private DcMotor motor = null;

    // How much to cut the power when the slow-mode bumper is held.
    static final double SLOW_MODE_SCALE = 0.3;

    @Override
    public void runOpMode() {

        // ---------- INIT: runs once, when you press INIT on the Driver Hub ----------

        // Look up the motor BY NAME. This string must match the robot
        // configuration exactly -- capital letters and spelling included.
        // If it does not match, the OpMode crashes here with an error telling
        // you the name it could not find. That error is your friend.
        motor = hardwareMap.get(DcMotor.class, "motor");

        // Which way is "positive"? If +power spins the wrong direction,
        // change FORWARD to REVERSE here instead of adding minus signs
        // all over your code later.
        motor.setDirection(DcMotor.Direction.FORWARD);

        // What should the motor do when power is 0?
        //   BRAKE = resist turning, stops quickly
        //   FLOAT = coast to a stop
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Status", "Initialized. Press PLAY.");
        telemetry.update();

        // ---------- Pause here until the driver presses PLAY ----------
        waitForStart();

        // ---------- RUN: this loop repeats many times per second until STOP ----------
        while (opModeIsActive()) {

            // Read the stick. Pushing a stick UP gives a NEGATIVE number,
            // which surprises everyone, so we flip it with a minus sign.
            double power = -gamepad1.left_stick_y;

            // Hold the right bumper for finer control.
            if (gamepad1.right_bumper) {
                power = power * SLOW_MODE_SCALE;
            }

            // Safety net: force the number into the legal -1.0 .. 1.0 range.
            power = Range.clip(power, -1.0, 1.0);

            // Actually move the motor. Nothing happens until this line runs.
            motor.setPower(power);

            // Print to the Driver Hub screen so you can see what the code thinks.
            telemetry.addData("Power", "%.2f", power);
            telemetry.addData("Slow mode", gamepad1.right_bumper ? "ON" : "off");
            telemetry.addLine();
            telemetry.addLine("Left stick = speed, right bumper = slow");
            telemetry.update();
        }

        // Loop is over (driver pressed STOP). Always leave the motor off.
        motor.setPower(0);
    }
}
