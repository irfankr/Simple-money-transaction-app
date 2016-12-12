package ssmtk_t3;

import java.util.*;
import parlayxws.px_spec_v2_1.components.common.PolicyException;
import parlayxws.px_spec_v2_1.components.common.ServiceException;
import parlayxws.px_spec_v2_1.components.se.sms_send_service_2_2.SendSmsBean;

public class SendSms {
	public void sendSms(String toAdd, String fromNum, String message) {
		// Minimum required system parameters
		String serviceUrl = "http://localhost:8080/parlayx_gateway/ParlayXSmsAccess/services/SendSms";
		// To-addresses added as a list
		List<String> toAddresses = new LinkedList<String>();
		toAddresses.add(toAdd);
		// Create bean with system parameters
		SendSmsBean send = new SendSmsBean(serviceUrl, null, false, null, null,
				null);
		// Set user parameters
		send.setToAddresses(toAddresses);
		send.setFromAddress(fromNum);
		send.setMessageText(message);
		// Call business method
		try {
			send.sendSms();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PolicyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
