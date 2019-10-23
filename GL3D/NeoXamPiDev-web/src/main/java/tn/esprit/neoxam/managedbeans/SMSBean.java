package tn.esprit.neoxam.managedbeans;

import javax.faces.bean.ManagedBean;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSBean {

	// Find your Account Sid and Token at twilio.com/user/account
	public static final String ACCOUNT_SID = "AC37084ec8758d7d41aacb40383e917904";
	public static final String AUTH_TOKEN = "7c7f0944abb510bd11a76d21ee5996a0";

	public void SendSMS() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(new PhoneNumber("+19712051320"), new PhoneNumber("+21658652521"), "Welcome")
				.create();
		System.out.println("test");
		System.out.println(message.getSid());
	}

}
