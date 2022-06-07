package frc.robot.SwerveDriveLib;

public interface DriveController {
    Object getDriveMotor();

    void setReferenceVoltage(double voltage);

    double getStateVelocity();
}
