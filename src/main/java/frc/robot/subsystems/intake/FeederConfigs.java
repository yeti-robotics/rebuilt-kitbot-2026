package frc.robot.subsystems.intake;

import com.revrobotics.spark.config.SparkMaxConfig;

public class FeederConfigs {
    static final int feederMotorID = 5;
    static final int feederMotorCurrentLimit = 60;

    static final SparkMaxConfig feederConfig =
            (SparkMaxConfig) new SparkMaxConfig().inverted(true).smartCurrentLimit(feederMotorCurrentLimit);
}
