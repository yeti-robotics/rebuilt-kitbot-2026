package frc.robot.subsystems.drive;

import static frc.robot.subsystems.drive.DriveTrainConfigs.DRIVE_MOTOR_CURRENT_LIMIT;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
    private final SparkMax leftLeader;
    private final SparkMax rightLeader;
    private final SparkMax leftFollower;
    private final SparkMax rightFollower;

    private final DifferentialDrive drive;
    private DifferentialDrivePoseEstimator m_poseEstimator;
    private DifferentialDriveKinematics m_kinematics;
    private Pigeon2 m_gyro;

    public DriveTrain() {
        leftLeader = new SparkMax(DriveTrainConfigs.LEFT_LEADER_ID, MotorType.kBrushless);
        rightLeader = new SparkMax(DriveTrainConfigs.RIGHT_LEADER_ID, MotorType.kBrushless);
        leftFollower = new SparkMax(DriveTrainConfigs.LEFT_FOLLOWER_ID, MotorType.kBrushless);
        rightFollower = new SparkMax(DriveTrainConfigs.RIGHT_FOLLOWER_ID, MotorType.kBrushless);

        m_gyro = new Pigeon2(DriveTrainConfigs.PIGEON_ID);

        drive = new DifferentialDrive(leftLeader, rightLeader);

        final SparkMaxConfig config = (SparkMaxConfig)
                new SparkMaxConfig().voltageCompensation(12).smartCurrentLimit(DRIVE_MOTOR_CURRENT_LIMIT);

        // they kept calling methods that just didn't exist this is awesome
        config.follow(leftLeader);
        leftFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        config.follow(rightLeader);
        rightFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        // Remove following, then apply config to right leader
        config.disableFollowerMode();
        rightLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        // Set config to inverted and then apply to left leader. Set Left side inverted
        // so that postive values drive both sides forward
        config.inverted(true);
        leftLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        m_poseEstimator = new DifferentialDrivePoseEstimator(
                m_kinematics,
                m_gyro.getRotation2d(),
                leftLeader.getEncoder().getPosition(),
                rightLeader.getEncoder().getPosition(),
                new Pose2d(),
                VecBuilder.fill(0.05, 0.05, Units.degreesToRadians(5)),
                VecBuilder.fill(0.5, 0.5, Units.degreesToRadians(30)));
    }

    @Override
    public void periodic() {
        m_poseEstimator.update(
                m_gyro.getRotation2d(),
                leftLeader.getEncoder().getPosition(),
                rightLeader.getEncoder().getPosition());
    }

    public void driveArcade(double xSpeed, double zRotation) {
        drive.arcadeDrive(xSpeed, zRotation);
    }
}
