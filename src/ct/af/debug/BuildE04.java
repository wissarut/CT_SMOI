package ct.af.debug;

import ct.af.enums.EStats;

public class BuildE04 {

	public static void main(String[] args) {

		String templateE03 = "<Stat name=\"@StatName\" group=\"N/A\">"
								+ "<InvertThreshold value=\"0\"/>"
								+ "<NormalThreshold value=\"0\"/>"
								+ "<RepeatEnable value=\"YES\"/>"
								+ "<ClearEnable value=\"YES\"/>"
								+ "<Severity value=\"warning\"/>"
							+ "</Stat>";
		
		String output = "";
		
		for (EStats n : EStats.values()) {
			
			String newtem = templateE03.replaceAll("@StatName", n.getStatName());
			
			output = output + newtem + "\n";
			
		}
		
		System.out.print(output);

	}

}
