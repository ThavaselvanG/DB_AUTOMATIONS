package csv;

import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        Optional<String> op2
                = Optional.ofNullable( null);

        // print value
        System.out.println("Optional 2: "
                + op2);
        if (op2.isPresent()){
            System.out.println("Optional 2: "
                    + op2);
        }
    }
}
