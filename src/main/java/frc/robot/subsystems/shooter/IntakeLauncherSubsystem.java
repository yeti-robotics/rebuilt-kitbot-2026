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
        intakeLauncherMotor = new SparkMax(IntakeLauncherConfigs.intakeLauncherMotorID, MotorType.kBrushed);
        intakeLauncherMotor.configure(
                IntakeLauncherConfigs.intakeLauncherConfig,
                ResetMode.kResetSafeParameters,
                PersistMode.kPersistParameters);
    }

    public Command setVoltage(double voltage) {
        return run(() -> intakeLauncherMotor.setVoltage(voltage));
    }

    public Command eject(double volts) {
        return run(() -> intakeLauncherMotor.setVoltage(volts));
    }
}
