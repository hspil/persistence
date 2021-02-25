/**********************************************************************\
 * This is a very hacky class whose purpose is to generate Strings for  *
 *                          various purposes.                           *
 *                      Written by: Kaleb Burris                        *
 *                   Public Domain, use at own risk.                    *
 \**********************************************************************/

public class give {
    public String giveMe(int stringLength, String firstChar) {
        StringBuilder sb = new StringBuilder();
        for (sb.append(firstChar); stringLength > 0; stringLength--) {
            sb.append(firstChar);
        }
        return sb.toString();
    }

    public String giveMe(int stringLength) {
        StringBuilder sb = new StringBuilder();
        for (sb.append(1); stringLength > 0; stringLength--) {
            sb.append(0);
        }
        return sb.toString();
    }

    public String timeFormat(long nanoSeconds) {
        double Seconds = (double) nanoSeconds / 1000000000.0;
        int Hrs = (int) (Seconds / 3600);
        Seconds %= 3600;
        int Mins = (int) (Seconds / 60);
        Seconds %= 60;
        return Hrs + " Hours " + Mins + " Minutes " + String.format("%.3f", Seconds) + " Seconds" + ")";
    }

    public String outOfOrder(String unorderedString, int largestValueToSlice) {
        StringBuilder sb = new StringBuilder();
        int length = unorderedString.length();
        int slicePoint = unorderedString.indexOf(largestValueToSlice + '0') + 1;
        sb.append(unorderedString, 0, slicePoint);
        for (int i = slicePoint; i < length; i++) {
            sb.append(largestValueToSlice);
        }
        return sb.toString();
    }
}

