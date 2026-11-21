package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/*
 * DEVICE EXAMPLE 2 of 4: A DC MOTOR WITH AN ENCODER
 *
 * What this shows:  how to send a motor to an exact spot, instead of just
 *                   telling it how hard to push.
 *
 * Robot configuration needed on the Driver Hub:
 *     A DcMotor named exactly:  motor
 *     (Same motor as DeviceExample_Motor -- run one OpMode at a time.)
 *     Its encoder cable must be plugged in, or this will not work.
 *
 * How to drive it:
 *     A button  ->  go to the "home" spot (0 turns)
 *     B button  ->  go one full turn away from home
 *     Y button  ->  go two full turns away from home
 *     X button  ->  re-zero the encoder wherever the motor is right now
 *
 * The big idea:
 *     An encoder counts how far the motor shaft has turned, in "ticks".
 *     That lets the motor controller do the work for you: you say WHERE
 *     to go, and it picks the power needed to get there and stop.
 *
 *     Plain motor    -> setPower()          -> "push this hard"
 *     Encoder motor  -> setTargetPosition() -> "go here and stop"
 *
 *     Ticks are not degrees. Every motor model has a different number of
 *     ticks per revolution, so this OpMode asks the SDK for that number at
 *     startup rather than hardcoding it -- which means it works with whatever
 *     motor you end up using, as long as the robot configuration names the
 *     right model.
 */
@Disabled
@TeleOp(name = "Device Example: Motor w/ Encoder", group = "Device Examples")
public class DeviceExample_MotorEncoder extends LinearOpMode {

    private DcMotor motor = null;

    // How many encoder ticks make one full turn of the output shaft.
    // We do NOT hardcode this. Instead we ask the SDK at startup -- see
    // runOpMode() below. Every motor model has a different number, and
    // hardcoding the wrong one silently makes every distance wrong.
    private double countsPerMotorRev = 0;

    // How fast to travel toward the target. Start slow while testing!
    static final double SPEED = 0.3;

    @Override
    public void runOpMode() {

        // ---------- INIT ----------

        motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setDirection(DcMotor.Direction.FORWARD);

        /*
         * Ask the motor how many encoder ticks it takes to turn once.
         *
         * This works because when you add the motor in the Driver Hub robot
         * configuration, you pick its model from a dropdown (goBILDA 5202,
         * REV HD Hex, NeveRest 40, and so on). The SDK already knows the tick
         * count for each model, so we can just look it up instead of pasting
         * a magic number into the code.
         *
         * IMPORTANT: this is only right if the motor is configured as the
         * correct model. If it is set to a generic type, the number below
         * will be wrong and every distance will be off. Check the value on
         * the telemetry screen against the manufacturer's spec once.
         */
        countsPerMotorRev = motor.getMotorType().getTicksPerRev();

        // Set the current spot as "zero". Everything is measured from here,
        // so make sure the mechanism is where you want home to be.
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Order matters: you must have a target BEFORE switching to
        // RUN_TO_POSITION, or the motor may lurch toward a stale target.
        motor.setTargetPosition(0);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // In RUN_TO_POSITION, power is a SPEED LIMIT, not a direction.
        // The controller decides which way to turn to reach the target.
        // If power is 0 the motor will never move, no matter the target.
        motor.setPower(SPEED);

        telemetry.addData("Status", "Initialized. Press PLAY.");
        telemetry.update();

        waitForStart();

        // ---------- RUN ----------
        while (opModeIsActive()) {

            if (gamepad1.a) {
                // Home. Multiplying by 0 is silly math, but it keeps all
                // four lines below looking the same, which makes the
                // pattern easy to see.
                motor.setTargetPosition((int) (0 * countsPerMotorRev));

            } else if (gamepad1.b) {
                // One full revolution from home.
                motor.setTargetPosition((int) (1 * countsPerMotorRev));

            } else if (gamepad1.y) {
                motor.setTargetPosition((int) (2 * countsPerMotorRev));

            } else if (gamepad1.x) {
                // Re-zero: forget where we were and call this spot home.
                // We have to leave RUN_TO_POSITION to reset, then set a
                // fresh target and go back in.
                motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                motor.setTargetPosition(0);
                motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                motor.setPower(SPEED);
            }

            // Notice we never told it HOW to get there, and we never wait
            // around for it to arrive. The loop keeps running and the motor
            // controller drives toward the target in the background.

            telemetry.addData("Ticks per revolution", "%.1f (from robot config)", countsPerMotorRev);
            telemetry.addData("Target (ticks)", motor.getTargetPosition());
            telemetry.addData("Actual (ticks)", motor.getCurrentPosition());
            telemetry.addData("Still moving?", motor.isBusy() ? "YES" : "no -- arrived");
            telemetry.addData("Turns from home", "%.2f", motor.getCurrentPosition() / countsPerMotorRev);
            telemetry.addLine();
            telemetry.addLine("A = home, B = 1 turn, Y = 2 turns, X = re-zero");
            telemetry.update();
        }

        motor.setPower(0);
    }
}
