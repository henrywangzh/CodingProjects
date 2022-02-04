package javaconceptofthedayoverloadingoverridingquestion10;

class A
{
	String methodTwo() {
		return "AAA";
	}
     void methodOne()
    {
        System.out.println(this.methodTwo());
    }
}
 
class B extends A
{
	String methodTwo() {
		return "BBB";
	}
     void methodOne()
    {
        System.out.println(super.methodTwo());
    }
}
 
public class test
{
    public static void main(String[] args)
    {
        A a = new B();
         
        a.methodOne();
    }   
}