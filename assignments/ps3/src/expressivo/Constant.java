package expressivo;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * 代表expression中的常量, 是expression中的最小单元
 */
public class Constant implements Expression {

    private final double value;

    // Abstract Function
    //  AF(value) = a constant in an expression
    // Representation invariant
    //  true

    // Safety from rep exposure:
    //   1. all fields are private, immutable and unreassignable

    /**
     * @param num requires to be nonnegative
     */
    public Constant(double num) {
        this.value = num;
    }

    /**
     * @return 常量的字符串表示, 省略末尾的0, 并且最多保留小数点后8位
     */
    @Override
    public String toString() {
        DecimalFormat format = new DecimalFormat("#.########");
        return format.format(value);
    }

    @Override
    public boolean equals(Object thatObject) {
        if (!(thatObject instanceof Constant)) {
            return false;
        }
        return this.value == ((Constant) thatObject).value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
