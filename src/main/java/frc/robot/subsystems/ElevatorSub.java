// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSub extends SubsystemBase {
  /** Creates a new ElevatorSub. */
  CANSparkMax bottomElevator = new CANSparkMax(26, MotorType.kBrushless);
  CANSparkMax backElevator = new CANSparkMax(25, MotorType.kBrushless);

  public ElevatorSub() {
  }

  public void up(double speed){
    bottomElevator.set(speed);
    backElevator.set(speed);
  }

public void down(){
  bottomElevator.set(-0.4);
  backElevator.set(-0.4);
}
public void stop(){
  bottomElevator.set(0);
  backElevator.set(0);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
