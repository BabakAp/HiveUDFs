import org.apache.hadoop.hive.ql.exec.UDF;

/**
 *
 * @author Babak Alipour (babak.alipour@gmail.com)
 */
public final class INT2IP extends UDF {

    /**
     * Convert a IP address from int to String\n
     * e.g. 168496141 returns "10.11.12.13"\n
     * Returns 128.X for negative int\n
     * @param ip
     * @return 
     */
    public String evaluate(final int ip) {
        int tmp = ip;
        int fourth = tmp & 0xFF;
        tmp >>= 8;
        int third = tmp & 0xFF;
        tmp >>= 8;
        int second = tmp & 0xFF;
        tmp >>= 8;
        int first = tmp & 0xFF;
        return first + "." + second + "." + third + "." + fourth;
    }
}
