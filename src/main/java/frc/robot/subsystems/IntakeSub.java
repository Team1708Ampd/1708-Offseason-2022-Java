// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSub extends SubsystemBase {
  /** Creates a new IntakeSub. */

  public WPI_TalonFX intake = new WPI_TalonFX(20);

  public IntakeSub() {}

  public void intake() {
    intake.set(ControlMode.PercentOutput, 0.5);
  }

  public void outtake() {
    intake.set(ControlMode.PercentOutput, -0.5);
  }

  public void intakeOff() {
    intake.set(ControlMode.PercentOutput, 0);
  }
}
