package frc.robot.subsystems.shooter;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeLauncherSubsystem extends SubsystemBase {
    private final SparkMax intakeLauncherMotor;

    public IntakeLauncherSubsystem() {
        intakeLauncherMotor = new SparkMax(IntakeLauncherConfigs.intakeLauncherMotorID, MotorType.kBrushless);
        intakeLauncherMotor.configure(
                IntakeLauncherConfigs.intakeLauncherConfig,
                ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);
    }

    public Command setVoltage(double voltage) {
        return startEnd(() -> intakeLauncherMotor.setVoltage(voltage), () -> intakeLauncherMotor.setVoltage(0));
    }

    public Command eject() {
        return run(() -> intakeLauncherMotor.setVoltage(-6));
    }

    public Command testing() {
        return startEnd(() -> intakeLauncherMotor.set(1), () -> intakeLauncherMotor.set(0));
    }
}
