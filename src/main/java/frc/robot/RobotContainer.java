// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Commands.AutoAim;
import frc.robot.Commands.Drive;
import frc.robot.constants.Constants;
import frc.robot.subsystems.drive.Drivetrain;
import frc.robot.subsystems.intake.FeederSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.shooter.IntakeLauncherSubsystem;
import frc.robot.subsystems.vision.VisionSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // Controller
    CommandXboxController primary;

    // Subsystems
    private final IntakeLauncherSubsystem intakeLauncher;
    private final IntakeSubsystem intake;
    private final FeederSubsystem feeder;
    private final Drivetrain drive;
    private final VisionSubsystem vision;

    // Commands
    private final AutoAim autoAim;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        primary = new CommandXboxController(Constants.PRIMARY_CONTROLLER_PORT);

        intakeLauncher = new IntakeLauncherSubsystem();
        intake = new IntakeSubsystem();
        feeder = new FeederSubsystem();
        drive = new Drivetrain();
        vision = new VisionSubsystem();

        autoAim = new AutoAim(drive, vision);

        configureBindings();
    }

    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        drive.setDefaultCommand(new Drive(drive, primary));

        //        primary.leftTrigger().whileTrue(intakeLauncher.setVoltage(10));
        //        primary.leftBumper().whileTrue(intake.setVoltage(-12));
        //        primary.rightTrigger().whileTrue(shooter.launchShooter(10.6));
        primary.a().whileTrue(intake.set(180));
        primary.b().whileTrue(intake.setTwo(1));
        primary.x().whileTrue(intake.set(360));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
