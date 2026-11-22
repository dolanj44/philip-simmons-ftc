package org.firstinspires.ftc.teamcode.varsity;

import android.annotation.SuppressLint;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp(name = "Test: Flywheel RPM", group = "Test")
@Disabled
public class Flywheel_RPM extends LinearOpMode {
    static final int CYCLE_MS = 50;
    static final int MOTOR_RPM_RATING = 6000;
    static final double BASE_TICKS_PER_REVOLUTION = 28;

    DcMotorEx motor;
    double  power   = 0;
    boolean rampUp  = true;
    boolean dpad_pressed = false;
    boolean dpad_toggled = false;


    @SuppressLint("DefaultLocale")
    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotorEx.class, "flywheel_motor_flywheel");

        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);
        telemetry.addData(">", "Press Start." );
        telemetry.update();
        waitForStart();

        while(opModeIsActive()) {
            dpad_toggled = !dpad_pressed && (gamepad1.dpad_up || gamepad1.dpad_down || gamepad1.dpad_right || gamepad1.dpad_left);
            dpad_pressed = gamepad1.dpad_up || gamepad1.dpad_down || gamepad1.dpad_right || gamepad1.dpad_left;

            if (dpad_toggled) {
                if (gamepad1.dpad_up) power += 0.1;
                if (gamepad1.dpad_down) power -= 0.1;
                if (gamepad1.dpad_right) power += 0.01;
                if (gamepad1.dpad_left) power -= 0.01;
            }
            if (gamepad1.b) power = 0.0;

            power = Math.min(1.0, power);
            power = Math.max(-1.0, power);

            telemetry.addLine(String.format("Motor RPM Rating: " + MOTOR_RPM_RATING));
            telemetry.addLine(String.format("Motor Power:     %8.2f", motor.getPower()));
            telemetry.addLine(String.format("Motor Velocity:  %8.2f", motor.getVelocity()));
            telemetry.addLine(String.format("Motor RPM:       %8.2f", getGoBildaRPM (motor.getVelocity(), MOTOR_RPM_RATING)));
            telemetry.update();

            motor.setPower(power);
            sleep(CYCLE_MS);
            idle();
        }

        motor.setPower(0);
        telemetry.update();
    }

    public double getGoBildaRPM(double ticks_per_sec, int rpm_rating) {
        double gear_ratio = 6000.0 / rpm_rating;
        double ticks_per_revolution = 28.0 * gear_ratio;

        return (ticks_per_sec / ticks_per_revolution) * 60.0;
    }
}
