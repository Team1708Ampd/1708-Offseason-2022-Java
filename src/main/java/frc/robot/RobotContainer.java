// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.XboxController.Axis;
import frc.robot.commands.BothIntakes;
import frc.robot.commands.BothOuttakes;
import frc.robot.commands.BottomElevatorDown;
import frc.robot.commands.BottomElevatorUp;
import frc.robot.commands.ElevatorDown;
import frc.robot.commands.ElevatorUp;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.Feed;
import frc.robot.commands.IntakeArmDown;
import frc.robot.commands.IntakeArmUp;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.OuttakeCommand;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
=======
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;
import frc.robot.subsystems.Mk4DriveSubsystem;
import frc.robot.commands.DriveCommand;
>>>>>>> origin/swerve_drv_specials

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
<<<<<<< HEAD
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    XboxController joystick = new XboxController(0);
    XboxController mech = new XboxController(1);

    JoystickButton intake = new JoystickButton(joystick, XboxController.Button.kA.value);
    intake.whileHeld(new BothIntakes());

    JoystickButton outtake = new JoystickButton(joystick, XboxController.Button.kB.value);
    outtake.whileHeld(new BothOuttakes());

    JoystickButton shoot = new JoystickButton(joystick, XboxController.Button.kLeftBumper.value);
    shoot.whileHeld(new Shoot());

    JoystickButton armUp = new JoystickButton(joystick, XboxController.Button.kX.value);
    armUp.whileHeld(new IntakeArmUp());

    JoystickButton armDown = new JoystickButton(joystick, XboxController.Button.kY.value);
    armDown.whileHeld(new IntakeArmDown());

    JoystickButton feed = new JoystickButton(joystick, XboxController.Button.kRightBumper.value);
    feed.whileHeld(new Feed());

    // JoystickButton hoodUp = new JoystickButton(joystick, XboxController.Button.kBack.value);
    // hoodUp.whileHeld(new ShooterHoodUp());

    // JoystickButton hoodDown = new JoystickButton(joystick, XboxController.Button.kStart.value);
    // hoodDown.whileHeld(new ShooterHoodDown());

    JoystickButton elevatorUp = new JoystickButton(joystick, XboxController.Button.kStart.value);
    elevatorUp.whileHeld(new ElevatorUp());

    JoystickButton elevatorDown = new JoystickButton(joystick, XboxController.Button.kBack.value);
    elevatorDown.whileHeld(new ElevatorDown());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }
=======
  private final Mk4DriveSubsystem drivetrain = new Mk4DriveSubsystem();

    private final XboxController controller = new XboxController(0);

    public RobotContainer() {
        drivetrain.register();

        drivetrain.setDefaultCommand(new DriveCommand(
                drivetrain,
                () -> -modifyAxis(controller.getLeftY()), // Axes are flipped here on purpose
                () -> -modifyAxis(controller.getLeftX()),
                () -> -modifyAxis(controller.getRightX())
        ));

        new Button(controller::getBackButtonPressed)
                .whenPressed(drivetrain::zeroGyroscope);
    }

    public Mk4DriveSubsystem getDrivetrain() {
        return drivetrain;
    }

    private static double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    private static double modifyAxis(double value) {
        // Deadband
        value = deadband(value, 0.05);

        // Square the axis
        value = Math.copySign(value * value, value);

        return value;
    }

>>>>>>> origin/swerve_drv_specials
}
