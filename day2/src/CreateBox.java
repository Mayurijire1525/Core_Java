import java.util.Scanner;
class CreateBox
{
  public static void main(String[] args)
  {
 //create scanner's instance to wrap std i/p
   Scanner sc=new Scanner(System.in);
    System.out.println("Enter Box dimensions : w d h");
//int data=100;
    Box box1;//box1 : ref type of var(class type of ref), no obj created so far, method local var : 
//mem allocated on the stack : how many bytes : as per JVM spec.
  //  System.out.println(box1); javac err
     box1=new Box(sc.nextDouble(),sc.nextDouble(),sc.nextDouble());//Box class gets loaded in meth area //it's instance is created in the heap.
     //print box dims
     System.out.println("Box 1 dims "+box1.getBoxDimensions());
    //print box volume
     System.out.println("Volume : "+box1.getBoxVolume());
     Box box2=new Box(4,5,6);
      System.out.println(box2.getBoxDimensions());
     Box box3=box1;//copy of refs
      System.out.println("Box 3 dims "+box3.getBoxDimensions());
     box1=box3=null;//How many objs are marked for GC ? 1
   //  System.out.println("Box 1 dims "+box1.getBoxDimensions());//java.lang.NullPointerException

    //close scanner 
    sc.close();
     System.out.println("main over...");
  }
}