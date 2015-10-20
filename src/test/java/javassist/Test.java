package javassist;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

public class Test {
  @SuppressWarnings("unchecked")
  public static void main(String[] args) throws Exception {
    ClassPool cp = ClassPool.getDefault();
    CtClass cc = cp.get("javassist.Hello");
    CtMethod m = cc.getDeclaredMethod("say");
    m.insertBefore("{ System.out.println(\"Hello.say():\"); }");
    Class<Hello> c = cc.toClass();
    Hello h = (Hello) c.newInstance();
    h.say();
  }
}
