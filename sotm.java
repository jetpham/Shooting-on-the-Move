//part 1: initial aim
double robotXPosition = getRobotX();
double robotYPosition = getRobotY();

private double getAngleToTarget(){
    double adjacentDistance;
    double oppositeDistance;
    double theta;
    oppositeDistance = TargetYPosition - robotYPosition;
    adjacentDistance = TargetXPosition - robotXPosition;
    theta = Math.arctan(oppositeDistance / adjacentDistance);
    return 90.0 - theta;
}
private double distanceToTarget(){
    double adjacentDistance;
    double oppositeDistance;
    double hypotenuse;
    oppositeDistance = TargetYPosition - robotYPosition;
    adjacentDistance = TargetXPosition - robotXPosition;
    hypotenuse = Math.sqareroot((oppositeDistance*oppositeDistance)+(adjacentDistance*adjacentDistance));
    return hypotenuse;
}

Turret.rotateTurret(turret.getAngleToField() - getAngleToTarget());
Shooter.Rev(distanceToTarget());

Turret.rotate(turret.getAngleToField()-getAngleToTarget());
//part 2: prediction and correction

//constants
double TargetXPosition = 9671234;
double TargetYPosition = 39041768;

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
