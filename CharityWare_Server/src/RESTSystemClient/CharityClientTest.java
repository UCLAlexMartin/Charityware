package RESTSystemClient;

import systemHibernateEntities.Charity;

public class CharityClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String Charity_Name = "Fantastic Charity";
		String Charity_Description = "Charity for nothing";
		String Address_Line1 = "Nowhere 1";
		String Address_Line2 = "Nowhere 2";
		String Location = "London";
		String Postcode ="WC1 BS2";
		String Email ="charityfornothing.com";
		String Phone = "43124141241";
		String Registration_No ="3251324";
		Charity charity = new Charity(Charity_Name,Charity_Description,Address_Line1,Address_Line2,Location,Postcode,Email,Phone,Registration_No);
		CharityClient.addCharity(charity);
	}

}
