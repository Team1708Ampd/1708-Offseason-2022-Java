// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeArmSub extends SubsystemBase {
  /** Creates a new IntakeArmSub. */
  WPI_TalonFX intakeArm = new WPI_TalonFX(19);
  public IntakeArmSub() {}

  public void armUp() {
    intakeArm.set(ControlMode.PercentOutput, 0.25);
  }

  public void armDown() {
    intakeArm.set(ControlMode.PercentOutput, -0.25);
  }

  public void armOff() {
    intakeArm.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
