package ct.af.message.incoming.parser.bos;

import ec02.utils.AppLog;
import phoebe.eqx.main.EquinoxMessageParser;
import phoebe.eqx.model.dcc.cca.DiameterCreditControlAnswer;

public class DiameterCCA {
	public DiameterCreditControlAnswer createDiameterCreditControlAnswer(String input)
	  {
	    EquinoxMessageParser parser = EquinoxMessageParser.newInstance();
	    parser.setEquinoxMessage(input);
	    DiameterCreditControlAnswer cca = null;
	    try
	    {
	      cca = parser.getDiameterTranslator().getCcaTranslator().translateCCA();
	    }
	    catch (Exception e)
	    {
	      AppLog.e("Error Massage:" + e.getMessage(), e);
	    }
	    return cca;
	  }
}
