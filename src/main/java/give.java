public class give {
	public String giveMe (int stringLength, String firstChar) {
		String result;
		for(result = firstChar; stringLength > 0; stringLength--){
			result += "0";
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
	public String outOfOrder(String unorderedString, int sliceLocation){
		String newString = unorderedString.substring(0, sliceLocation);
		String lastChar = unorderedString.charAt(sliceLocation - 1) + "";
		for(int i = sliceLocation; i < unorderedString.length(); i++){
			newString += lastChar;
		}
		return newString;
	}
}

