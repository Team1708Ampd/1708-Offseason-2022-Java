package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.SwerveDriveLib.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import static frc.robot.Constants.*;


// Class for the Mark IV Drivetrain Subsystem. Inherits the Subsystem Base class
// for compatibility with the FRC subsystem model 
public class Mk4DriveSubsystem extends SubsystemBase {

    private static final double MAX_VOLTAGE = 12.0;
    public static final double MAX_VELOCITY_METERS_PER_SECOND = 4.14528;
    public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
            Math.hypot(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0);

    private final SwerveModule frontLeftModule;
    private final SwerveModule frontRightModule;
    private final SwerveModule backLeftModule;
    private final SwerveModule backRightModule;

    //private final PigeonIMU gyroscope = new PigeonIMU(Constants.DRIVETRAIN_PIGEON_ID);

    // Use Navx for now
    private final AHRS m_navx = new AHRS(SPI.Port.kMXP);

    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
            new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
            new Translation2d(DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0),
            new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, DRIVETRAIN_WHEELBASE_METERS / 2.0),
            new Translation2d(-DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -DRIVETRAIN_WHEELBASE_METERS / 2.0)
    );
    private final SwerveDriveOdometry odometry = new SwerveDriveOdometry(kinematics, Rotation2d.fromDegrees(m_navx.getFusedHeading()));

    private ChassisSpeeds chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

    public Mk4DriveSubsystem() {
        ShuffleboardTab shuffleboardTab = Shuffleboard.getTab("Drivetrain");

        frontLeftModule = Mk3SwerveModuleHelper.createFalcon500(
                shuffleboardTab.getLayout("Front Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(0, 0),
                Mk3SwerveModuleHelper.GearRatio.STANDARD,
                FRONT_LEFT_MODULE_DRIVE_MOTOR,
                FRONT_LEFT_MODULE_STEER_MOTOR,
                FRONT_LEFT_MODULE_STEER_ENCODER,
                FRONT_LEFT_MODULE_STEER_OFFSET
        );

        frontRightModule = Mk3SwerveModuleHelper.createFalcon500(
                shuffleboardTab.getLayout("Front Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(2, 0),
                Mk3SwerveModuleHelper.GearRatio.STANDARD,
                FRONT_RIGHT_MODULE_DRIVE_MOTOR,
                FRONT_RIGHT_MODULE_STEER_MOTOR,
                FRONT_RIGHT_MODULE_STEER_ENCODER,
                FRONT_RIGHT_MODULE_STEER_OFFSET
        );

        backLeftModule = Mk3SwerveModuleHelper.createFalcon500(
                shuffleboardTab.getLayout("Back Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(4, 0),
                Mk3SwerveModuleHelper.GearRatio.STANDARD,
                BACK_LEFT_MODULE_DRIVE_MOTOR,
                BACK_LEFT_MODULE_STEER_MOTOR,
                BACK_LEFT_MODULE_STEER_ENCODER,
                BACK_LEFT_MODULE_STEER_OFFSET
        );

        backRightModule = Mk3SwerveModuleHelper.createFalcon500(
                shuffleboardTab.getLayout("Back Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(6, 0),
                Mk3SwerveModuleHelper.GearRatio.STANDARD,
                BACK_RIGHT_MODULE_DRIVE_MOTOR,
                BACK_RIGHT_MODULE_STEER_MOTOR,
                BACK_RIGHT_MODULE_STEER_ENCODER,
                BACK_RIGHT_MODULE_STEER_OFFSET
        );

        shuffleboardTab.addNumber("Gyroscope Angle", () -> getRotation().getDegrees());
        shuffleboardTab.addNumber("Pose X", () -> odometry.getPoseMeters().getX());
        shuffleboardTab.addNumber("Pose Y", () -> odometry.getPoseMeters().getY());
    }

    public void zeroGyroscope() {
        //gyroscope.zeroYaw();
        odometry.resetPosition(
                new Pose2d(odometry.getPoseMeters().getTranslation(), Rotation2d.fromDegrees(0.0)),
                Rotation2d.fromDegrees(m_navx.getFusedHeading())
        );
    }

    public Rotation2d getRotation() {
        if (m_navx.isMagnetometerCalibrated()) {
               // We will only get valid fused headings if the magnetometer is calibrated
             return Rotation2d.fromDegrees(m_navx.getFusedHeading());
        }
        
      //  We have to invert the angle of the NavX so that rotating the robot counter-clockwise makes the angle increase.
        return Rotation2d.fromDegrees(360.0 - m_navx.getYaw());
        // return odometry.getPoseMeters().getRotation();
    }

    public void drive(ChassisSpeeds chassisSpeeds) {
        this.chassisSpeeds = chassisSpeeds;
    }

    @Override
    public void periodic() {
        odometry.update(Rotation2d.fromDegrees(m_navx.getFusedHeading()),
                new SwerveModuleState(frontLeftModule.getDriveVelocity(), new Rotation2d(frontLeftModule.getSteerAngle())),
                new SwerveModuleState(frontRightModule.getDriveVelocity(), new Rotation2d(frontRightModule.getSteerAngle())),
                new SwerveModuleState(backLeftModule.getDriveVelocity(), new Rotation2d(backLeftModule.getSteerAngle())),
                new SwerveModuleState(backRightModule.getDriveVelocity(), new Rotation2d(backRightModule.getSteerAngle()))
        );

        SwerveModuleState[] states = kinematics.toSwerveModuleStates(chassisSpeeds);

        frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[0].angle.getRadians());
        frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[1].angle.getRadians());
        backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[2].angle.getRadians());
        backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[3].angle.getRadians());
    }
}
