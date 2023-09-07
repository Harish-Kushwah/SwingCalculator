import org.mariuszgromada.math.mxparser.*;
public  class EvaluateExpression
{

    static
    {
        /* Non-Commercial Use Confirmation */
        boolean isCallSuccessful = License.iConfirmNonCommercialUse("John Doe");

        /* Verification if use type has been already confirmed */
        boolean isConfirmed = License.checkIfUseTypeConfirmed();

        /* Checking use type confirmation message */
        String message = License.getUseTypeConfirmationMessage();

        /* ----------- */
//        mXparser.consolePrintln("isCallSuccessful = " + isCallSuccessful);
//        mXparser.consolePrintln("isConfirmed = " + isConfirmed);
//        mXparser.consolePrintln("message = " + message);
    }
    public static double evaluate(String expression)
    {
        Expression ans = new Expression(expression);
        System.out.println(ans.calculate());
        return ans.calculate();
    }
}

/*


Argument x = new Argument("x = pi");
Expression e = new Expression("sin(x + pi)/2 + 1", x);
mXparser.consolePrintln("Res 1: " + e.getExpressionString() + " = " + e.calculate());
x.setArgumentValue(2);
mXparser.consolePrintln("Res 2: " + e.getExpressionString() + " = " + e.calculate());
 */