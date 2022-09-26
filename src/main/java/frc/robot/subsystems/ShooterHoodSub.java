// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterHoodSub extends SubsystemBase {
  WPI_TalonFX shooterHood = new WPI_TalonFX(25);
  /** Creates a new ShooterHoodSub. */
  public ShooterHoodSub() {}

  public void hoodUp() {
    shooterHood.set(ControlMode.PercentOutput, 0.5);
  }

  public void hoodDown() {
    shooterHood.set(ControlMode.PercentOutput, -0.5);
  }

  public void hoodOff() {
    shooterHood.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
