package frc.robot.subsystems.intake;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSubsystem extends SubsystemBase {
    private final SparkMax feederRoller;

    public FeederSubsystem() {
        feederRoller = new SparkMax(FeederConfigs.feederMotorID, SparkLowLevel.MotorType.kBrushed);
        feederRoller.configure(
                FeederConfigs.feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    public Command setVoltage(double voltage) {
        return run(() -> feederRoller.setVoltage(voltage));
    }
}
