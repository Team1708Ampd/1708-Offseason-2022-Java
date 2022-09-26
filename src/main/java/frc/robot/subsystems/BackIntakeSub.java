// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BackIntakeSub extends SubsystemBase {
  /** Creates a new BackIntakeSub. */
  WPI_TalonFX backIntake = new WPI_TalonFX(21);
  public BackIntakeSub() {}


  public void backIntake() {
    backIntake.set(ControlMode.PercentOutput, 0.5);
  }

  public void backOuttake() {
    backIntake.set(ControlMode.PercentOutput, -0.5);
  }

  public void backIntakeOff() {
    backIntake.set(ControlMode.PercentOutput, 0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
