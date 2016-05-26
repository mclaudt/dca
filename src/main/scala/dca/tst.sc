import scala.collection.mutable.{ArrayBuffer, ListBuffer}

println("alala")

val a = 5

trait t

trait t2 {type T}

trait t3 {def foo}

//trait t4 {var x}



trait b{ def a:Int}

//class aa1 extends b{override def a = 1L}
class aa2 extends b{val a = 1}
class aa3 extends b{var a = 1}
class aa4 extends b{def a = 1}

trait t11 {
  println("1")
val h1 = {println("h1")
1}
}
trait t22 {
  println("2")
  val h2 = {println("h2")
    2}
}


class vvv extends t11 with t22 {
  println("alala")

}


new vvv

trait tb {
  def a:String = "j"
}

trait tb2 {
  def a:String = "n"
}


class MM extends tb with t2 {
  override def a :String = super.a + "   "

}

(new MM()).a

def f4 = (x:Int)=>x+1


def f(i:Int):Int = (if (i>0) 0 else f(i))+6



val a:Set


Either
