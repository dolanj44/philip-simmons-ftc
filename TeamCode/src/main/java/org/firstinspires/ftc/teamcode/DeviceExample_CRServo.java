package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;

/*
 * DEVICE EXAMPLE 4 of 4: A CONTINUOUS ROTATION SERVO (CRServo)
 *
 * What this shows:  how a CR servo differs from a normal servo.
 *
 * Robot configuration needed on the Driver Hub:
 *     A CRServo named exactly:  crservo
 *     (In the config app this is "Continuous Rotation Servo", NOT "Servo".
 *      Picking the wrong one here is the #1 reason this example fails.)
 *
 * How to drive it:
 *     Right trigger  ->  spin one way (harder press = faster)
 *     Left trigger   ->  spin the other way
 *
 * The big idea, and this trips up almost everyone:
 *     A continuous rotation servo looks identical to a normal servo, but it
 *     behaves like a MOTOR. It takes POWER (-1.0 to 1.0), not position.
 *     It spins forever and cannot be sent to an angle.
 *
 *     Normal Servo -> setPosition(0.0 to 1.0)  -> goes to an angle and holds
 *     CRServo      -> setPower(-1.0 to 1.0)    -> spins continuously
 *
 *     Power 0.0 means STOP. If your CR servo creeps slowly at 0.0, it is not
 *     broken -- it needs its physical trim screw adjusted.
 */
@Disabled
@TeleOp(name = "Device Example: CR Servo", group = "Device Examples")
public class DeviceExample_CRServo extends LinearOpMode {

    private CRServo crservo = null;

    @Override
    public void runOpMode() {

        // ---------- INIT ----------

        // Note the class here is CRServo, not Servo. The class you ask for
        // must match the device type chosen in the robot configuration.
        crservo = hardwareMap.get(CRServo.class, "crservo");

        // Flip to REVERSE if it spins the opposite way from what you expect.
        crservo.setDirection(CRServo.Direction.FORWARD);

        // Make sure it is stopped before the match starts.
        crservo.setPower(0);

        telemetry.addData("Status", "Initialized. Press PLAY.");
        telemetry.update();

        waitForStart();

        // ---------- RUN ----------
        while (opModeIsActive()) {

            // Triggers are ANALOG: they report 0.0 (released) up to 1.0
            // (fully pressed), unlike buttons which are only true or false.
            // Subtracting them gives one number that covers both directions
            // and naturally gives 0 when both or neither are pressed.
            double power = gamepad1.right_trigger - gamepad1.left_trigger;

            power = Range.clip(power, -1.0, 1.0);

            crservo.setPower(power);

            telemetry.addData("Power", "%.2f", power);
            telemetry.addData("Right trigger", "%.2f", gamepad1.right_trigger);
            telemetry.addData("Left trigger", "%.2f", gamepad1.left_trigger);
            telemetry.addLine();
            telemetry.addLine("Triggers spin it. Press harder to go faster.");
            telemetry.update();
        }

        // Always stop on the way out.
        crservo.setPower(0);
    }
}
