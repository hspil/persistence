public class give {
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
		return Hrs + " Hours " + Mins + " Minutes " + Seconds + " Seconds" + ")"; 
	}
}

