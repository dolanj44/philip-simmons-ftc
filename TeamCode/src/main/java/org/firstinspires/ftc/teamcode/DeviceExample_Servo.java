package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/*
 * DEVICE EXAMPLE 3 of 4: A NORMAL (POSITIONAL) SERVO
 *
 * What this shows:  how to send a servo to an exact angle and keep it there.
 *
 * Robot configuration needed on the Driver Hub:
 *     A Servo named exactly:  servo
 *
 * How to drive it:
 *     A button  ->  jump to the CLOSED position
 *     B button  ->  jump to the OPEN position
 *     Dpad up   ->  nudge a little bit toward open
 *     Dpad down ->  nudge a little bit toward closed
 *
 * The big idea:  a servo takes a POSITION, from 0.0 to 1.0.
 *     This is completely different from a motor!
 *     A motor takes power ("how hard to push").
 *     A servo takes position ("where to go and stay").
 *     There is no such thing as a negative servo position.
 *
 * Also worth knowing -- LINEAR servos:
 *     A linear servo pushes a rod in and out instead of rotating. In code it
 *     is IDENTICAL to this example: same Servo class, same setPosition().
 *     Only the mechanics differ. Note for inspection that linear servos have
 *     a much stricter current limit than rotary ones (1A vs 4A stall at 6V).
 *
 * WARNING, read this before you run it:
 *     A servo will try to reach the position you ask for even if something
 *     is in the way, and it will keep straining until it burns out. Start
 *     with the servo arm OFF, or with the OPEN/CLOSED values close together,
 *     and widen them once you know the safe range of your mechanism.
 */
@Disabled
@TeleOp(name = "Device Example: Servo", group = "Device Examples")
public class DeviceExample_Servo extends LinearOpMode {

    private Servo servo = null;

    // The two positions we jump between. TUNE THESE for your mechanism.
    // Keep them gentle at first (like 0.4 and 0.6) until you know it is safe.
    static final double CLOSED_POSITION = 0.2;
    static final double OPEN_POSITION   = 0.8;

    // How far one dpad press moves the servo.
    static final double NUDGE_AMOUNT = 0.01;

    @Override
    public void runOpMode() {

        // ---------- INIT ----------

        // Look up the servo by its configured name.
        servo = hardwareMap.get(Servo.class, "servo");

        // If the servo moves the opposite way from what you expect,
        // flip this to REVERSE rather than rewriting your numbers.
        servo.setDirection(Servo.Direction.FORWARD);

        // We track the position ourselves in a variable, because we want to
        // do math on it (nudging up and down).
        double position = CLOSED_POSITION;

        // Move to a known starting spot so the servo is somewhere predictable.
        servo.setPosition(position);

        telemetry.addData("Status", "Initialized. Press PLAY.");
        telemetry.update();

        waitForStart();

        // ---------- RUN ----------
        while (opModeIsActive()) {

            if (gamepad1.a) {
                // Jump straight to a preset.
                position = CLOSED_POSITION;

            } else if (gamepad1.b) {
                position = OPEN_POSITION;

            } else if (gamepad1.dpad_up) {
                // Nudge. Note this runs every time through the loop while the
                // button is held, so holding dpad slides the servo smoothly.
                position = position + NUDGE_AMOUNT;

            } else if (gamepad1.dpad_down) {
                position = position - NUDGE_AMOUNT;
            }

            // Never let the number leave 0.0 .. 1.0. Without this, nudging
            // repeatedly would push it to 3.5 and the servo would misbehave.
            position = Range.clip(position, 0.0, 1.0);

            // Send it. The servo holds this spot until we tell it otherwise.
            servo.setPosition(position);

            telemetry.addData("Commanded position", "%.3f", position);
            telemetry.addLine();
            telemetry.addLine("A = closed, B = open, dpad up/down = nudge");
            telemetry.addLine("Note: a servo cannot tell you where it ACTUALLY is,");
            telemetry.addLine("only where you asked it to go.");
            telemetry.update();
        }
    }
}
