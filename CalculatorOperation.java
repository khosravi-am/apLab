package khosro;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * background calculation of program.
 */
public class CalculatorOperation {

    private  static ScriptEngine scriptEngine;
    public static String calculate(String string ) {
        try {
            ScriptEngineManager sem = new ScriptEngineManager();
            scriptEngine =  sem.getEngineByName("JavaScript");
            System.out.println(string);
            string = convertToValidForm(string);
            System.out.println(string);
            return "" + scriptEngine.eval(string);
        }catch (Exception e){ return "not able to calculate";}
    }

    /**
     * calculate "sin,cos,tan,cot,log,exp"
     * replace "PI" and "E" with their values in Math class.
     * @param string string of a mathematical operation may contains sin,cos ...
     * @return string without sin,cos,...
     * @throws ScriptException if not be able to calculate.
     */
    private static String convertToValidForm(String string) throws ScriptException {
        string = string.replaceAll("PI","" + Math.PI);
        string = string.replaceAll("E","" + Math.E);
        Pattern sinPat = Pattern.compile("sin[(]([0-9 *-+/().]*)[)]");
        Matcher sinMatch = sinPat.matcher(string);
        while (sinMatch.find()) {
            String resOfPar = "" + scriptEngine.eval(sinMatch.group(1));
            String res = "" + Math.sin(Double.parseDouble(resOfPar));
            string = sinMatch.replaceAll(res);
        }
        Pattern cosPat = Pattern.compile("cos[(]([0-9 *-+/().]*)[)]");
        Matcher cosMatch = cosPat.matcher(string);
        while (cosMatch.find()) {
            String resOfPar = "" + scriptEngine.eval(cosMatch.group(1));
            String res = "" + Math.cos(Double.parseDouble(resOfPar));
            string = cosMatch.replaceAll(res);
        }

        Pattern tanPat = Pattern.compile("tan[(]([0-9 *-+/().]*)[)]");
        Matcher tanMatch = tanPat.matcher(string);
        while (tanMatch.find()) {
            String resOfPar = "" + scriptEngine.eval(tanMatch.group(1));
            String res = "" + Math.tan(Double.parseDouble(resOfPar));
            string = tanMatch.replaceAll(res);
        }
        Pattern cotPat = Pattern.compile("cot[(]([0-9 *-+/().]*)[)]");
        Matcher cotMatch = cotPat.matcher(string);
        while (cotMatch.find()) {
            String resOfPar = "" + scriptEngine.eval(cotMatch.group(1));
            String res = "" + 1/Math.tan(Double.parseDouble(resOfPar));
            string = cotMatch.replaceAll(res);
        }
        Pattern expPat = Pattern.compile("exp[(]([0-9 *-+/().]*)[)]");
        Matcher expMatch = expPat.matcher(string);
        while (expMatch.find()) {
            String resOfPar = "" + scriptEngine.eval(expMatch.group(1));
            String res = "" + Math.exp(Double.parseDouble(resOfPar));
            string = expMatch.replaceAll(res);
        }
        Pattern logPat = Pattern.compile("log[(]([0-9 *-+/().]*)[)]");
        Matcher logMatch = logPat.matcher(string);
        while (logMatch.find()) {
            String resOfPar = "" + scriptEngine.eval(logMatch.group(1));
            String res = "" + Math.log(Double.parseDouble(resOfPar));
            string = logMatch.replaceAll(res);
        }
        return string;
    }
}