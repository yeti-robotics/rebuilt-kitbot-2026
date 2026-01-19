package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

public class IntakeLauncherSubsystem extends SubsystemBase {
    private final TalonFX intakeLauncherMotor;

    public IntakeLauncherSubsystem() {
        intakeLauncherMotor = new TalonFX(IntakeLauncherConfigs.intakeLauncherMotorID, Constants.rioBus);
    }

    public Command setVoltage(double voltage) {
        return startEnd(() -> intakeLauncherMotor.setVoltage(voltage), () -> intakeLauncherMotor.setVoltage(0));
    }

    public Command eject() {
        return startEnd(() -> intakeLauncherMotor.setVoltage(-6), () -> intakeLauncherMotor.setVoltage(0));
    }

    public Command testing() {
        return startEnd(() -> intakeLauncherMotor.set(1), () -> intakeLauncherMotor.set(0));
    }
}
