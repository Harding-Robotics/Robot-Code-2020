/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
//import edu.wpi.first.wpilibj.util.*;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final MecanumDrive m_robotDrive = new MecanumDrive(new PWMVictorSPX(0), new PWMVictorSPX(1),
      new PWMVictorSPX(2), new PWMVictorSPX(3));
  private final PWMVictorSPX motor_5 = new PWMVictorSPX(4);
  private final XboxController m_stick = new XboxController(0);
  private final ColorSensorV3 Color_sensor = new ColorSensorV3(I2C.Port.kOnboard);

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    m_robotDrive.setExpiration(0.1);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
    case kCustomAuto:
      // Put custom auto code here
      break;
    case kDefaultAuto:
    default:
      // Put default auto code here
      break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // todo - figure out how to get rotation from 2nd joystick
    // double whatDoWeDoWithThisY =
    // m_stick.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);

    final double rotation_x = m_stick.getX(edu.wpi.first.wpilibj.GenericHID.Hand.kRight);
    // System.out.printf("Right Joystick X is: %f and Y is: %f%n", rotation_x,
    // whatDoWeDoWithThisY);
    // System.out.print("printing");
    m_robotDrive.driveCartesian(-m_stick.getY(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft),
        m_stick.getX(edu.wpi.first.wpilibj.GenericHID.Hand.kLeft), rotation_x);

    // Drive shooter motot, X button on game controller is used. Motor runs while
    // X Button is pressed.
    if (m_stick.getXButton()) {
      motor_5.setSpeed(1);
    } else {
      motor_5.setSpeed(0);
    }

    System.out.println("Color_sensor: Red: " + Color_sensor.getRed() + " green: " + Color_sensor.getGreen() + " blue: "
        + Color_sensor.getBlue());
  
  String gameData;
  gameData = DriverStation.getInstance().getGameSpecificMessage();
  if(gameData.length() > 0)
  
  {
    switch (gameData(0))
  {
    case 'B' :
      //Blue case code
      break;
    case 'G' :
      //Green case code
      break;
    case 'R' :
      //Red case code
      break;
    case 'Y' :
      //Yellow case code
      break;
    default :
      //This is corrupt data
      break;
  }
} else {
  //Code for no data received yet
}
  }
  
  private int gameData(int i) {
    return 0;
  }


  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
