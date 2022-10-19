// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ShooterSub extends SubsystemBase {
  /** Creates a new ShooterSub. */
  CANSparkMax shooter = new CANSparkMax(22, MotorType.kBrushless);
  CANSparkMax shooter2 = new CANSparkMax(28, MotorType.kBrushless);
  public ShooterSub() {}

  public void shoot() {
    shooter.set(-1);
    shooter2.set(-1);
  }

  public void shooterOff() {
    shooter.set(0);
    shooter2.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
