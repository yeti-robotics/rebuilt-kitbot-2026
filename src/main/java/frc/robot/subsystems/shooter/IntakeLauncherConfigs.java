package frc.robot.subsystems.shooter;

import com.revrobotics.spark.config.SparkMaxConfig;

public class IntakeLauncherConfigs {

    static final int intakeLauncherMotorID = 10;
    static final int intakeLauncherMotorCurrentLimit = 60;

    static final SparkMaxConfig intakeLauncherConfig =
            (SparkMaxConfig) new SparkMaxConfig().inverted(true).smartCurrentLimit(intakeLauncherMotorCurrentLimit);
}
