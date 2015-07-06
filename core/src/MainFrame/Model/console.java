package MainFrame.Model;

public class console {

  public static void error(Exception e) {
    // TODO Auto-generated method stub

  }

  public static void warn(String string, Exception e) {
    // TODO Auto-generated method stub

  }

  public static void info(String string) {
    System.out.println(string);
  }

  public static void badScript() {
    System.out.println("Your script is bad and you should feel bad");
  }

  public static void log(Object x) {
    System.out.println(x.toString());
  }
}
