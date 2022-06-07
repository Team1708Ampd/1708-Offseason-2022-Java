package frc.robot.SwerveDriveLib;

@FunctionalInterface
public interface AbsoluteEncoderFactory<Configuration> {
    AbsoluteEncoder create(Configuration configuration);
}
