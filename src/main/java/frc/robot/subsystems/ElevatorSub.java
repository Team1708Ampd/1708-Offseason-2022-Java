// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSub extends SubsystemBase {
  /** Creates a new ElevatorSub. */
  CANSparkMax bottomElevator = new CANSparkMax(26, MotorType.kBrushless);
  CANSparkMax backElevator = new CANSparkMax(25, MotorType.kBrushless);

  public ElevatorSub() {}

  public void bottomElevatorUp() {
    bottomElevator.set(0.5);
  }

  public void bottomElevatorDown() {
    bottomElevator.set(-0.5);
  }

  public void bottomElevatorOff() {
    bottomElevator.set(0);
  }

  public void backElevatorUp() {
    backElevator.set(-0.5);
  }

  public void backElevatorDown() {
    backElevator.set(0.5);
  }

  public void backElevatorOff() {
    backElevator.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
