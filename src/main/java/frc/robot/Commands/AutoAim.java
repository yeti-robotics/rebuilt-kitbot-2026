package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drive.DriveTrain;
import frc.robot.subsystems.vision.VisionSubsystem;

public class AutoAim extends Command {
    DriveTrain drive;
    VisionSubsystem vision;
    double Kp = -0.1;

    public AutoAim(DriveTrain drive, VisionSubsystem vision) {
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drive);
        drive = drive;
        vision = vision;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        double heading_error = -vision.getX();
        if (Math.abs(heading_error) > 1.0) {
            drive.driveArcade(0, Kp * heading_error);
        }
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        drive.driveArcade(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return Math.abs(vision.getX()) < 0.05;
    }
}
