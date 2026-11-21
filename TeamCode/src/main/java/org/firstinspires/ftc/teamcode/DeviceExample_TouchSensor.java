package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

/*
 * DEVICE EXAMPLE 6 of 6: A TOUCH SENSOR (limit switch)
 *
 * What this shows:  the simplest possible input -- a yes/no question.
 *
 * Robot configuration needed on the Driver Hub:
 *     A REV Touch Sensor named exactly:  touch
 *     Configure it as "REV Touch Sensor" on a DIGITAL port. Note that it
 *     plugs into a digital port and actually occupies the SECOND of the two
 *     channels on that port -- if it never reads as pressed, that is the
 *     first thing to check.
 *
 * How to drive it:
 *     Just press the button on the sensor with your finger.
 *
 * The big idea:
 *     The IMU gave us a number. This gives us a boolean -- true or false,
 *     nothing in between. It answers one question: "is something touching me
 *     right now?"
 *
 *     Simple as it is, this is the single most useful safety device on a
 *     robot. An arm that drives into its own frame will strip gears or burn
 *     out a motor in seconds. A touch sensor at the end of travel lets the
 *     code notice and stop. That is called a LIMIT SWITCH, and the pattern
 *     is shown at the bottom of this file.
 */
@Disabled
@TeleOp(name = "Device Example: Touch Sensor", group = "Device Examples")
public class DeviceExample_TouchSensor extends LinearOpMode {

    private TouchSensor touch = null;

    @Override
    public void runOpMode() {

        // ---------- INIT ----------

        // TouchSensor is the friendly wrapper: isPressed() reads the way you
        // expect. (The SDK samples use DigitalChannel instead, which is the
        // lower-level version -- there, false means pressed, which catches
        // everyone out. Prefer TouchSensor.)
        touch = hardwareMap.get(TouchSensor.class, "touch");

        // We will count presses. To do that we need to remember what the
        // sensor said LAST time through the loop, so we can spot the moment
        // it changes.
        boolean wasPressed = false;
        int pressCount = 0;

        telemetry.addData("Status", "Initialized. Press PLAY.");
        telemetry.update();

        waitForStart();

        // ---------- RUN ----------
        while (opModeIsActive()) {

            boolean isPressed = touch.isPressed();

            /*
             * EDGE DETECTION -- worth understanding properly.
             *
             * This loop runs maybe 100 times a second. If you hold the button
             * for one second and just count "is it pressed?", you would count
             * 100 presses, not 1.
             *
             * So instead we look for the MOMENT it changes from not-pressed
             * to pressed: it is pressed now, AND it was not pressed last time.
             * That happens exactly once per physical press.
             *
             * You will need this same trick for gamepad buttons constantly --
             * for example, toggling something on and off with one button.
             */
            if (isPressed && !wasPressed) {
                pressCount = pressCount + 1;
            }

            // Remember for next time around. This line must come AFTER the
            // check above, or the comparison is meaningless.
            wasPressed = isPressed;

            telemetry.addData("Button", isPressed ? "PRESSED" : "not pressed");
            telemetry.addData("Times pressed", pressCount);
            telemetry.addLine();
            telemetry.addLine("Press the sensor with your finger.");
            telemetry.addLine("Hold it down -- notice the count only goes up once.");
            telemetry.update();
        }
    }
}

/*
 * THE LIMIT SWITCH PATTERN (for later)
 *
 * Once you combine this with DeviceExample_Motor, you get the pattern that
 * protects your mechanisms. Roughly:
 *
 *     double power = -gamepad1.left_stick_y;
 *
 *     // If the arm is at the bottom, refuse to keep going down.
 *     if (touch.isPressed() && power < 0) {
 *         power = 0;
 *     }
 *
 *     motor.setPower(power);
 *
 * Note that it only blocks motion in ONE direction. If it blocked both, the
 * arm would hit the limit and then be stuck there forever with no way to
 * drive back off it. That detail is easy to get wrong and confusing to debug.
 */
