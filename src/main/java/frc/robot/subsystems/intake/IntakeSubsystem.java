package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {
    //    private final SparkMax intakeRoller;
    private final Servo actuator;

    public IntakeSubsystem() {
        //        intakeRoller = new SparkMax(IntakeConfigs.intakeMotorID, SparkLowLevel.MotorType.kBrushless);
        actuator = new Servo(8);
        //        intakeRoller.configure(
        //                IntakeConfigs.feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    //    public Command setVoltage(double voltage) {
    //        return runEnd(
    //                () -> {
    //                    intakeRoller.setVoltage(voltage);
    //                },
    //                () -> {
    //                    intakeRoller.setVoltage(0);
    //                });
    //    }

    public Command set(double value) {
        return run(() -> actuator.setAngle(value));
    }

    public Command setTwo(double value) {
        return run(() -> actuator.setAngle(value));
    }
}
