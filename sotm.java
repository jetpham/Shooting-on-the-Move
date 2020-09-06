//part 1: beginning 
double robotXPosition = getRobotX(); // the x position of the robot
double robotYPosition = getRobotY(); // the y position of the robot

private double getAngleToTarget(double x, double y){ // getting the relative angle to the target from the body of the robot
    double adjacentDistance; //this distance should be the distance from the robot and target of the x axis
    double oppositeDistance; //this distance should be the distance from the robot and target of the y axis
    double theta; // the calculated angle to the target
    oppositeDistance = TargetYPosition - y; // initializing 
    adjacentDistance = TargetXPosition - x; // initializing 
    theta = Math.arctan(oppositeDistance / adjacentDistance); // calculating using trig
    return theta; // return the angle to the target
}
private double distanceToTarget(double x, double y){ // distance to the target from the robot
    double adjacentDistance; // the distance on the x axis
    double oppositeDistance;// the distance on the y axis
    double hypotenuse; // were going to use pythagorous
    oppositeDistance = TargetYPosition - y; // initializing 
    adjacentDistance = TargetXPosition - x; // initializing 
    hypotenuse = Math.sqareroot((oppositeDistance*oppositeDistance)+(adjacentDistance*adjacentDistance)); // c = sqrt(a^2+b^2)
    return hypotenuse; // return the angle to the target
}


//part 2: prediction and correction
        
double velX = Robot.getSpeed() * (Math.sin(Robot.getAngletoField())) // the velocity of the robot in the x direction. (assumes Robot.get speed exists as well as a get angle)
double velY = Robot.getSpeed() * (Math.cos(Robot.getAngletoField())) // the velocity of the robot in the y direction. (assumes Robot.get speed exists as well as a get angle)

double predictedRobotXPosition = x + (velX * (TimeToShoot - CameraDelay)); // predict the robots x position, this also takes into account the minute amount of camera delay
double predictedRobotYPosition = x + (velY * (TimeToShoot - CameraDelay));// predict the robots y position, this also takes into account the minute amount of camera delay

Turret.rotateTurret(turret.getAngleToField() - getAngleToTarget(predictedRobotXPosition,predictedRobotYPosition)); // adjust to future
Shooter.Rev(distanceToTarget(predictedRobotXPosition,predictedRobotYPosition)); // adjust to future

//things I think could be improved,
//the predicting and use of the robot's position could be used over and over again which would partly conpensate for acceloration as well as turning as the past corrections would make it much easier to get to that next prediction.         
//predicting turning as well as acceloration in a more rigorous way would be great        
//there may be some other more efficient way to calculate some of the stuff I have been.        
//other sensor latency is not taken into account        
//there are quite allot of assumed functions that I havent checked if they existed. there is Robot.getspeed(), getRobotX(), getRobotY(),Robot.getAngletoField(), turret.getAngleToField(), Turret.rotateTurret(), Shooter.Rev(). I know most of these exist but I am not sure if all of them do        
//we asume that this is all made correctly and the the aforementioned functions are based off of a x and y where y is the length of the field, (0,0) is the center of the field, and angles where 0 is the side fieldof the enemy target
   
//constants
double TargetXPosition = 9671234; // to be filled in later
double TargetYPosition = 39041768; // to be filled in later
double TimeToShoot = 2; //seconds (this could be set with more intelligent deisgn using a lookup table and the predicted distance to target)
double CameraDelay = 0.023; //seconds (pulled from the limelight 2 website and only fluctuates between 2 ms so it is assumed as constant)

//        (Easy) Assuming you have a vision of the target, you should be able to calculate your distance to it, which
//        would allow you to have the shooter spin at the appropriate speed to land into the target.
//
//        (Hard) if you know how fast the robot is moving and in which direction it is moving in and your target is
//        stationary, you can break the movement of the robot into component vectors relative to your target. from there,
//        you need to relate the speeds of the component vectors to make your turret spin faster/slower to keep on target.
//        To make this easier, assume 0 (or very little) acceleration.
//
//        (hard) In order to get an accurate reading of the target for both points 1 and 2, you need some sort of latency
//        compensation for the camera, as it gives you data at a much slower rate than any of your other sensors. Failure
//        to take this into account will throw off all your calculations for points 1 and 2. this isn’t necessary if you
//        simulate the robot to get a work of concept, but it does become an issue in the real world. I believe the
//        solution for this is to use a Kalman/extended Kalman filter, which I understand what they do at a high level,
//        but to implement it will require us to look at other teams
