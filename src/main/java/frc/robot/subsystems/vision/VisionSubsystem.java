package frc.robot.subsystems.vision;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.LimelightHelpers;


public class VisionSubsystem extends SubsystemBase {

    public class VisionConstants {
        public static final String LIMELIGHT_NAME = "limelight";
        public static final double BLINK_DELAY_SECONDS = 0.125;
        public static final int NUM_BLINKS = 4;
    }

    public double getX() {
        return LimelightHelpers.getTX(VisionConstants.LIMELIGHT_NAME);
    }

    public double getY() {
        return LimelightHelpers.getTY(VisionConstants.LIMELIGHT_NAME);
    }

    public LimelightHelpers.LimelightResults getTargetingResults() {
        return LimelightHelpers.getLatestResults(VisionConstants.LIMELIGHT_NAME);
    }

}