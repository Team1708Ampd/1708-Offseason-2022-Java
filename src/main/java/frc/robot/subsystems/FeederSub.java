// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FeederSub extends SubsystemBase {
  /** Creates a new FeederSub. */
  CANSparkMax feeder = new CANSparkMax(24, MotorType.kBrushless);

  public FeederSub() {}

  public void feed() {
    feeder.set(1);
  }

  public void feederOff() {
    feeder.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
