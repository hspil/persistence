/**********************************************************************\
 * This is a very hacky class whose purpose is to generate Strings for  *
 *                          various purposes.                           *
 *                      Written by: Kaleb Burris                        *
 *                   Public Domain, use at own risk.                    *
 \**********************************************************************/

public class give {
	public String giveMe (int stringLength, String firstChar) {
		String result;
		for(result = firstChar; stringLength > 0; stringLength--){
			result += firstChar;
		}
		return result;
	}
	public String giveMe (int stringLength) {
		String result;
		for(result = "1"; stringLength > 0; stringLength--){
			result += "0";
		}
		return result;
	}
	public String timeFormat(long nanoSeconds){
		double Seconds = (double)nanoSeconds/1000000000.0;
		int Hrs = (int)(Seconds / 3600);
		Seconds %= 3600; 
		int Mins = (int)(Seconds / 60);
		Seconds %= 60;
		return Hrs + " Hours " + Mins + " Minutes " + String.format("%.3f", Seconds) + " Seconds" + ")"; 
	}
	public String outOfOrder(String unorderedString, int largestValueToSlice){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < unorderedString.length(); i++){
			if ((unorderedString.charAt(i) + "").equals(Integer.toString(largestValueToSlice))) {
				sb.append(unorderedString, 0, i);
				sb.append(Integer.toString(largestValueToSlice).repeat(unorderedString.length() - i));
				break;
			}
		}
		//System.out.println("outOfOrder is returning " + retString);
		return sb.toString();
	}
}

