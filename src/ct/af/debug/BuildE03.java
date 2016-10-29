package ct.af.debug;

import ct.af.enums.EStats;

public class BuildE03 {

	public static void main(String[] args) {

		String templateID = "sacf_alm_";
		String templateE03 = " <Alarm id=\"@AlertID\" name=\"@AlertName\">"
								+ "<RetryPattern value=\"10|10|10|300|600\"/>"
							+ "</Alarm>";
		
		String output = "";
		int numRun = 1;
		for (EStats n : EStats.values()) {
			
			String newtem = templateE03.replaceAll("@AlertID", templateID+String.format("%02d", numRun));
			newtem = newtem.replaceAll("@AlertName", n.getStatName());
			
			output = output + newtem + "\n";
			numRun++;
		}
		
		System.out.print(output);

	}

}
