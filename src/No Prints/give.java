public class give {
	public String giveMe (int stringLength) {
		String result;
		for(result = "1"; stringLength > 0; stringLength--){
			result += "0";
		}
		return result;
	}
}

