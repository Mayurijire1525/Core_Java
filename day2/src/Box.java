class Box
{
 //state of the Box : tight encapsulation
 private double width,depth,height;//instance variables
 //parameterized constructor to init complete state of the Box
  Box(double w,double d,double height)
 {
    width=w;
     depth=d;
     //this keyword is used to un hide instance var from local var
     this.height=height;
 }
//behaviour : methods
  //write a non static (instance method) to return Box details in String form (dimensions of Box)
  String getBoxDimensions()
 {
    return "Box Dims "+width+" "+depth+" "+height;
 }
//write a non static method To return computed volume of the box.
   double getBoxVolume()
   {
      return this.width*this.depth*this.height;
   } 
 
}