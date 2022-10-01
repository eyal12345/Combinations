import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.*;

public class Combinations {

    //Union
    public static <T> Set<T> union(Set<T> setA, Set<T> setB) {
        Set<T> tmp = new TreeSet<T>(setA);
        tmp.addAll(setB);
        return tmp;
    }

    //Intersection
    public static <T> Set<T> intersection(Set<T> setA, Set<T> setB) {
        Set<T> tmp = new TreeSet<T>();
        for (T x : setA)
            if (setB.contains(x))
                tmp.add(x);
        return tmp;
    }

    //Difference
    public static <T> Set<T> difference(Set<T> setA, Set<T> setB) {
        Set<T> tmp = new TreeSet<T>(setA);
        tmp.removeAll(setB);
        return tmp;
    }

    //Symmetric Difference
    public static <T> Set<T> symDifference(Set<T> setA, Set<T> setB) {
        Set<T> tmp = difference(union(setA, setB), intersection(setA, setB));
        return tmp;
    }

    //Check If Set Is Sub-Set For Other Set
    public static <T> boolean isSubset(Set<T> setA, Set<T> setB) {
        return setB.containsAll(setA);
    }

    //Check If Set Is Super-Set For Other Set
    public static <T> boolean isSuperset(Set<T> setA, Set<T> setB) {
        return setA.containsAll(setB);
    }

    public static Set<String> Radium(char[] x, int n) {
        Set<String> c = new TreeSet<String>();
        int size = (int) Math.pow(x.length ,n);
        for (int i = 0; i < size; i++) {
            String s = "";
            int index = i;
            for (int j = 0; j < n; j++) {
                s = x[index % x.length] + s;
                index /= x.length;
            }
            c.add(s);
        }
        return c;
    }

    public static Set<String> Ledium(char[] x, int n) {
        Set<String> c = new TreeSet<String>();
        while(n != -1) {
            c.addAll(Radium(x ,n));
            n--;
        }
        return c;
    }

    public static Vector<String> Permutations(char[] x) {
        Vector<String> perm = new Vector<String>();
        if (x.length == 0) {
            perm.add("");
            return perm;
        } else {
            char initial = x[0];
            char[] rest = Arrays.copyOfRange(x, 1, x.length);
            Vector<String> words = Permutations(rest);
            for (String strNew : words)
                for (int i = 0 ;i <= strNew.length() ;i++)
                    perm.add(charInsert(strNew, initial, i));
            return perm;
        }
    }

    public static String charInsert(String str, char c, int j) {
        String begin = str.substring(0, j);
        String end = str.substring(j);
        return begin + c + end;
    }

    public static String[] sortSet(Set<String> st) {
        String[] x = st.toArray(new String[st.size()]);
        for (int i = 1 ;i < x.length ;i++) {
            String temp = x[i];
            int j = i - 1;
            while (j >= 0 && temp.length() < x[j].length()) {
                x[j + 1] = x[j];
                j--;
            }
            x[j + 1] = temp;
        }
        return x;
    }

    public static String[] sortVector(Vector<String> st) {
        String[] x = st.toArray(new String[st.size()]);
        for (int i = 1 ;i < x.length ;i++) {
            String temp = x[i];
            int j = i - 1;
            while (j >= 0 && temp.length() < x[j].length()) {
                x[j + 1] = x[j];
                j--;
            }
            x[j + 1] = temp;
        }
        return x;
    }

    public static int factorial(int n) {
        if (n == 0 || n == 1)
            return 1;
        return n * factorial(n - 1);
    }

    public static String convertDecimalToFraction(float x){
        if (x < 0)
            return "-" + convertDecimalToFraction(-x);
        float tolerance = (float) 1.0E-12;
        float h1 = 1 ,h2 = 0;
        float k1 = 0 ,k2 = 1;
        float b = x;
        do {
            float a = (float) Math.floor(b);
            float aux = h1;
            h1 = a*h1 + h2;
            h2 = aux;
            aux = k1;
            k1 = a*k1 + k2;
            k2 = aux;
            b = 1 / (b - a);
        } while (Math.abs(x - h1/k1) > x*tolerance);
        if (k1 != 1)
            return String.valueOf((int)h1 + "/" + (int)k1);
        else
            return String.valueOf((int)h1);
    }

//    public static void getCombinatoric(char[] x ,String cond_expr) {
//        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
//        Vector<String> perms = Permutations(x);
//        Iterator<String> it = perms.iterator();
//        int quantityUnion = 0;
//        int quantityInter = 0;
//        while (it.hasNext()) {
//            String st = it.next();
//            boolean condition = engine.eval(cond_expr);
//            if (condition) {
//                System.out.println(st);
//                quantityUnion++;
//            }
//        }
//        int quantity = quantityUnion - quantityInter;
//        System.out.println("quantity options: " + quantity);
//        System.out.println("sample space options: " + perms.size());
//        System.out.println("probability: " + convertDecimalToFraction((float) quantity / perms.size()));
//    }

    public static void main(String[] args) {
//        char[] xh = {'0','1','2','3','4','5','6','7','8','9'};
//        String expr = "st.startsWith(\"12\") || st.endsWith(\"34\")";
//        getCombinatoric(xh ,expr);
//        System.exit(0);
        char[] x = {'a','b','c'};
        Set<String> set = Ledium(x,3);
        Iterator<String> it = set.iterator();
        while (it.hasNext())
            System.out.println(it.next());
        // Combinations Output:
        // R[{'a','b'} ,0] = {''}
        // R[{'a','b','c'} ,0] = {''}
        // R[{'a','b'} ,1] = {'a','b'}
        // R[{'a','b','c'} ,1] = {'a','b','c'}
        // R[{'a','b'} ,2] = {'aa','ab','ba','bb'}
        // R[{'a','b','c'} ,2] = {'aa','ab','ac','ba','bb','bc','ca','cb','cc'}
        // R[{'a','b'} ,3] = {'aaa','aab','aba','abb','baa','bab','bba','bbb'}
        /* R[{'a','b','c'} ,3] = {'aaa','aab','aac','aba','abb','abc','aca','acb','acc',
                                  'baa','bab','bac','bba','bbb','bbc','bca','bcb','bcc',
                                  'caa','cab','cac','cba','cbb','cbc','cca','ccb','ccc'} */
        // L[{'a','b'} ,0] = R[{'a','b'} ,0] = {''}
        // L[{'a','b','c'} ,0] = R[{'a','b','c'} ,0] = {''}
        // L[{'a','b'} ,1] = R[{'a','b'} ,0] U R[{'a','b'} ,1] = {'','a','b'}
        // L[{'a','b','c'} ,1] = R[{'a','b','c'} ,0] U R[{'a','b','c'} ,1] = {'','a','b','c'}
        // L[{'a','b'} ,2] = R[{'a','b'} ,0] U R[{'a','b'} ,1] U R[{'a','b'} ,2] = {'','a','b','aa','ab','ba','bb'}
        /* L[{'a','b','c'} ,2] = R[{'a','b','c'} ,0] U R[{'a','b','c'} ,1] U R[{'a','b','c'} ,2]
                               = {'','a','b','c','aa','ab','ac','ba','bb','bc','ca','cb','cc'} */
        /* L[{'a','b'} ,3] = R[{'a','b'} ,0] U R[{'a','b'} ,1] U R[{'a','b'} ,2] U R[{'a','b'} ,3]
                           = {'','a','b','aa','ab','ba','bb','aaa','aab','aba','abb','baa','bab','bba','bbb'} */
        /* L[{'a','b','c'} ,3] = R[{'a','b','c'} ,0] U R[{'a','b','c'} ,1] U R[{'a','b','c'} ,2] U R[{'a','b','c'} ,3]
                               = {'','a','b','c','aa','ab','ac','ba','bb','bc','ca','cb','cc'
                                  'aaa','aab','aac','aba','abb','abc','aca','acb','acc',
                                  'baa','bab','bac','bba','bbb','bbc','bca','bcb','bcc',
                                  'caa','cab','cac','cba','cbb','cbc','cca','ccb','ccc'} */
        // |Rn[x]| = |x|^n
        // |Ln[x]| = 1 + |x| + |x|^2 + ......... + |x|^(n - 1) + |x|^n = (|x|*|Rn[x]| - 1) / (|x| - 1)
        // Permutations Output:
        // P[{'a'}] = {'a'}
        // P[{'a','b'}] = {'ab','ba'}
        // P[{'a','b','c'}] = {'abc','acb','bac','bca','cab','cba'}
        // |P[x]| = n!
    }
}
