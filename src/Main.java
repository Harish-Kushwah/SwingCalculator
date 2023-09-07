//import javax.script.*;
//public class Main {
//    public static void main(String[] args) throws Exception {
//        // create a script engine manager
//        ScriptEngineManager factory = new ScriptEngineManager();
//        // create a JavaScript engine
//        ScriptEngine engine = factory.getEngineByName("JavaScript");
//        // evaluate JavaScript code from String
////        Object obj = engine.eval("1+2");
////        System.out.println( obj );
//
//        System.out.println(engine);
//    }
//}

import org.mariuszgromada.math.mxparser.*;
public  class Main
{

    public static void main(String[] args) {


        // JAVA: import org.mariuszgromada.math.mxparser.*;
// C#: using org.mariuszgromada.math.mxparser;
// ...

        /* Non-Commercial Use Confirmation */
        boolean isCallSuccessful = License.iConfirmNonCommercialUse("John Doe");

        /* Verification if use type has been already confirmed */
        boolean isConfirmed = License.checkIfUseTypeConfirmed();

        /* Checking use type confirmation message */
        String message = License.getUseTypeConfirmationMessage();

        /* ----------- */
        mXparser.consolePrintln("isCallSuccessful = " + isCallSuccessful);
        mXparser.consolePrintln("isConfirmed = " + isConfirmed);
        mXparser.consolePrintln("message = " + message);

//        Argument x = new Argument("x = pi");
//        Expression e = new Expression("sin(x + pi)/2 + 1", x);
//        mXparser.consolePrintln("Res 1: " + e.getExpressionString() + " = " + e.calculate());
//        x.setArgumentValue(2);
//        mXparser.consolePrintln("Res 2: " + e.getExpressionString() + " = " + e.calculate());

        Expression ans = new Expression("2.3+5.7");
        System.out.println(ans.calculate());
    }
}
/*
to add image and resise the image

//                    BufferedImage bufferedImage = ImageIO.read(new File("D:\\SwingCalculator\\src\\b2.svg"));
//                    Image image = bufferedImage.getScaledInstance(28,28,Image.SCALE_DEFAULT);


                    ImageIcon back_button = new ImageIcon("D:\\SwingCalculator\\src\\b2.svg");
                    ImageIcon icon1 = new ImageIcon(back_button.getImage());
 */
