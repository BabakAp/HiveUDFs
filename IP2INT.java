import org.apache.hadoop.hive.ql.exec.UDF;

/**
 *
 * @author Babak Alipour (babak.alipour@gmail.com)
 */
public final class IP2INT extends UDF {

    /**
     * Convert a IP address from String to int\n
     * e.g. "10.11.12.13" returns 168496141\n
     * Returns a negative int for 128.X\n
     * @param ip
     * @return 
     */
    public int evaluate(final String ip) {
        if (ip == null) {
            return 0;
        } else {
            int[] nums = new int[4];
            /**
             * Slower version - ~4.5s for 10M (your mileage will vary)
             */
//            String[] bytes = ip.split("\\.");
//            for (int i = 0; i < 4; i++) {
//                nums[i] = Integer.parseInt(bytes[i]);
//            }
            /**
             * Faster version - ~2.5s for 10M (your mileage will vary)
             */
            byte b = 0;
            for (int i = 0; i < ip.length(); i++) {
                if (ip.charAt(i) == '.') {
                    b++;
                } else {
                    nums[b] = nums[b] * 10 + (ip.charAt(i) - 48);
                }
            }
//            return nums[0] * 16777216L + nums[1] * 65536 + nums[2] * 256 + nums[3];
            return (nums[0] << 24) + (nums[1] << 16) + (nums[2] << 8) + nums[3];
        }
    }
}
