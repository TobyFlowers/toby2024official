// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Pivot;

public class PivotToSpeaker extends Command {
  private Pivot s_Pivot;
  WaitCommand clock = new WaitCommand(Constants.EndEffector.waitTime);


  /** Creates a new PivotToAmp. */
  public PivotToSpeaker(Pivot s_Pivot) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.s_Pivot=s_Pivot;
    addRequirements(s_Pivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //put smart dashboard running command update
    SmartDashboard.putString("Pivot CMD", "Speaker");

    s_Pivot.setRotationVoltage(s_Pivot.calculateRotationVoltage(s_Pivot.calculateGoalAngle()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {

    if(!s_Pivot.hasNote()){
      if(!clock.isScheduled()){ //init clock if not started
        clock.initialize();
      } else { // if clock is started clock
        if(clock.isFinished()){// if clock is over end cmd and reset clock 
          clock.cancel();
          return true;
        }
      }
    }

    return false;
  }
}
