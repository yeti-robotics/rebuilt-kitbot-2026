package frc.robot.subsystems.drive;

import static frc.robot.subsystems.drive.DrivetrainConfigs.DRIVE_MOTOR_CURRENT_LIMIT;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private final SparkMax leftLeader;
    private final SparkMax rightLeader;
    private final SparkMax leftFollower;
    private final SparkMax rightFollower;

    private final DifferentialDrive drive;

    public Drivetrain() {
        leftLeader = new SparkMax(DrivetrainConfigs.LEFT_LEADER_ID, MotorType.kBrushless);
        rightLeader = new SparkMax(DrivetrainConfigs.RIGHT_LEADER_ID, MotorType.kBrushless);
        leftFollower = new SparkMax(DrivetrainConfigs.LEFT_FOLLOWER_ID, MotorType.kBrushless);
        rightFollower = new SparkMax(DrivetrainConfigs.RIGHT_FOLLOWER_ID, MotorType.kBrushless);

        drive = new DifferentialDrive(leftLeader, rightLeader);

        final SparkMaxConfig config = (SparkMaxConfig)
                new SparkMaxConfig().voltageCompensation(12).smartCurrentLimit(DRIVE_MOTOR_CURRENT_LIMIT);

        // they kept calling methods that just didn't exist this is awesome
        // config.follow(leftLeader);
        leftFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        // config.follow(rightLeader);
        config.inverted(true);
        rightFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // Remove following, then apply config to right leader
        config.disableFollowerMode();
        config.inverted(true);
        rightLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        // Set config to inverted and then apply to left leader. Set Left side inverted
        // so that postive values drive both sides forward
        config.inverted(false);
        leftLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    }

    @Override
    public void periodic() {}

    public void driveArcade(double xSpeed, double zRotation) {
        drive.arcadeDrive(xSpeed, zRotation);
    }

    public Command driveLeft() {
        return runEnd(() -> rightFollower.set(0.2), () -> rightFollower.set(0));
    }
}
