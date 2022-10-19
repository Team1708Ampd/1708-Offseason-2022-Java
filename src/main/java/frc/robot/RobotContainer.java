// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
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

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
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
}
