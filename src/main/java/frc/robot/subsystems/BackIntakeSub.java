// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BackIntakeSub extends SubsystemBase {
  /** Creates a new BackIntakeSub. */
  WPI_TalonFX backIntake = new WPI_TalonFX(21);
  ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
  ColorMatch colorMatcher = new ColorMatch();

  final Color red = Color.kFirstRed;
  final Color blue = Color.kFirstBlue;
  final Color white = Color.kWhite;

  public BackIntakeSub() {
    colorMatcher.addColorMatch(red);
    colorMatcher.addColorMatch(blue);
    colorMatcher.addColorMatch(white);
  }


  public void backIntake() {
    backIntake.set(ControlMode.PercentOutput, 0.5);
  }

  public void backOuttake() {
    backIntake.set(ControlMode.PercentOutput, -0.5);
  }

  public void backIntakeOff() {
    backIntake.set(ControlMode.PercentOutput, 0);
  }

  public String matchColor() {
    Color detectedColor = colorSensor.getColor();

    ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
    if(match.color == red) {
      return "red";
    } else if(match.color == blue){
      return "blue";
    } else {
      return "white";
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println(matchColor());
  } 
}
