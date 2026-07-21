// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.Indexer;

import org.littletonrobotics.junction.Logger;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Indexer extends SubsystemBase {
  private SparkMax indexerMotor;

  private IndexerState currentState = IndexerState.OFF;

  /** Creates a new Indexer. */
  public Indexer() {
    indexerMotor = new SparkMax(IndexerConstants.kMotorId, MotorType.kBrushless);
  }

  public void setGoal(IndexerState desiredState) {
    currentState = desiredState;
    switch (desiredState) {
      case OFF:
      indexerMotor.stopMotor();
      break;
      case TO_SHOOTER:
        indexerMotor.set(IndexerConstants.kShootSpeed);
        break;
      case INTO_HOPPER:
        indexerMotor.set(-IndexerConstants.kIntakeSpeed);
        break;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    logMotorData();
  }

  public void logMotorData() {
    Logger.recordOutput("Subsystems/Indexer/IndexerState", currentState.name());
  }
}
