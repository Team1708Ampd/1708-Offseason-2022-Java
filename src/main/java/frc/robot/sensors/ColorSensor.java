package frc.robot.sensors;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;



public class ColorSensor {
    private I2C.Port port = I2C.Port.kOnboard;

    private ColorSensorV3 colorSensor = new ColorSensorV3(port);
    private ColorMatch colorMatch = new ColorMatch();
  
    private Color firstBlue = Color.kFirstBlue;
    private Color firstRed = Color.kFirstRed;
    private Color firstYellow = Color.kYellow;

    public ColorSensor(){
        colorMatch.addColorMatch(firstBlue);
        colorMatch.addColorMatch(firstRed);
        colorMatch.addColorMatch(firstYellow);

    }

    public Color GetColor(){
        return colorSensor.getColor();
    }

    public ColorMatchResult MatchClosestColor(Color color){
        return colorMatch.matchClosestColor(color);
    }


    public boolean isBlue(){
        return (MatchClosestColor(GetColor()).color == firstBlue);
    }

    public boolean isRed(){
        return (MatchClosestColor(GetColor()).color == firstRed);
    }
   
    public boolean isBlue(ColorMatchResult result){
        return (result.color == firstBlue);
    }
    
    public boolean isRed(ColorMatchResult result){
        return (result.color == firstRed);
    }
    
}
