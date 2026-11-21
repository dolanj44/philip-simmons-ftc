package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

/*
 * DEVICE EXAMPLE 5 of 6: THE IMU (built into the Control Hub)
 *
 * What this shows:  how the robot knows which way it is pointing.
 *
 * Robot configuration needed on the Driver Hub:
 *     An IMU named exactly:  imu
 *     GOOD NEWS: the IMU is built INTO the REV Control Hub. There is nothing
 *     to plug in and nothing to buy. On most configurations it is already
 *     there under "I2C Bus 0" as "imu". You can run this on a bare Control
 *     Hub sitting on the table -- just pick it up and turn it.
 *
 * How to drive it:
 *     Y button  ->  reset the yaw to zero ("call this direction straight ahead")
 *
 * The big idea, and this is the important one:
 *     Every example before this was an OUTPUT -- we told the robot to DO
 *     something. This is an INPUT: the robot tells US something.
 *     Inputs are how a robot reacts to the world instead of blindly
 *     following orders, and that is the whole foundation of autonomous.
 *
 * The three angles:
 *     YAW   = spinning left/right, like shaking your head "no".
 *             This is the one you will use 95% of the time. It is the
 *             robot's compass heading.
 *     PITCH = tipping forward/backward, like nodding "yes".
 *     ROLL  = tipping side to side, like tilting your head to your shoulder.
 *
 * Note on yaw: it can pass 180 and wrap around to -180. That wrap-around
 * is the source of many confusing turning bugs later on, so it is worth
 * watching on the screen now -- spin it slowly in a full circle and see.
 */
@Disabled
@TeleOp(name = "Device Example: IMU", group = "Device Examples")
public class DeviceExample_IMU extends LinearOpMode {

    private IMU imu = null;

    @Override
    public void runOpMode() {

        // ---------- INIT ----------

        imu = hardwareMap.get(IMU.class, "imu");

        /*
         * The IMU has to be told how the Control Hub is bolted onto the robot,
         * otherwise "yaw" might actually come out as "roll" and nothing will
         * make sense.
         *
         * You describe it with two directions:
         *   1. Which way the printed REV logo faces.
         *   2. Which way the USB ports face.
         * Both are described from the robot's point of view, looking from behind.
         *
         * The settings below are for a Hub lying flat, logo UP, USB ports
         * pointing FORWARD.
         *
         * >>> EDIT THESE TWO LINES to match how YOUR hub is actually mounted. <<<
         *
         * Careful: if you pick two directions that are impossible (like logo UP
         * and USB UP), this will throw an error at INIT rather than guessing.
         */
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        RevHubOrientationOnRobot orientationOnRobot =
                new RevHubOrientationOnRobot(logoDirection, usbDirection);

        imu.initialize(new IMU.Parameters(orientationOnRobot));

        telemetry.addData("Status", "Initialized. Press PLAY.");
        telemetry.update();

        waitForStart();

        // ---------- RUN ----------
        while (opModeIsActive()) {

            if (gamepad1.y) {
                // Whatever direction the robot is facing RIGHT NOW becomes 0.
                // Teams usually do this once at the start of a match so that
                // "0 degrees" means "pointing down the field".
                imu.resetYaw();
            }

            // Ask the IMU where it is pointing. This is a "getter": it gives
            // us information back, unlike setPower() which sent a command out.
            YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();

            telemetry.addData("Yaw (heading)", "%.1f deg", orientation.getYaw(AngleUnit.DEGREES));
            telemetry.addData("Pitch (nodding)", "%.1f deg", orientation.getPitch(AngleUnit.DEGREES));
            telemetry.addData("Roll (tilting)", "%.1f deg", orientation.getRoll(AngleUnit.DEGREES));
            telemetry.addLine();
            telemetry.addLine("Turn the robot and watch Yaw change.");
            telemetry.addLine("Press Y to call this direction zero.");
            telemetry.update();
        }
    }
}
