package in.ashokit.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import in.ashokit.entity.EligibilityDetails;
import in.ashokit.repository.EligibilityDetailsRepo;

@Component
public class AppRunner implements ApplicationRunner {

	@Autowired
	private EligibilityDetailsRepo eligibilityDetailsRepo;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		EligibilityDetails entity1 = new EligibilityDetails();
		entity1.setEligibiltyId(1);
		entity1.setName("DENZEL");
		entity1.setMobileNo(846325564l);
		entity1.setGender('M');
		entity1.setEmail("di@gmail.com");
		entity1.setSrNo(101);
		entity1.setSsn(123456l);
		entity1.setPlanName("CCAP");
		entity1.setPlanStatus("Approved");
		eligibilityDetailsRepo.save(entity1);

		EligibilityDetails entity2 = new EligibilityDetails();
		entity2.setEligibiltyId(2);
		entity2.setName("NICK");
		entity2.setMobileNo(85756174l);
		entity2.setGender('M');
		entity2.setEmail("ni@gmail.com");
		entity2.setSrNo(102);
		entity2.setSsn(7890123l);
		entity2.setPlanName("FOOD");
		entity2.setPlanStatus("Denied");
		eligibilityDetailsRepo.save(entity2);

		EligibilityDetails entity3 = new EligibilityDetails();
		entity3.setEligibiltyId(3);
		entity3.setName("BATI");
		entity3.setMobileNo(87656446l);
		entity3.setGender('M');
		entity3.setEmail("bi@gmail.com");
		entity3.setSrNo(103);
		entity3.setSsn(456789l);
		entity3.setPlanName("DEISEL");
		entity3.setPlanStatus("Closed");
		eligibilityDetailsRepo.save(entity3);

		EligibilityDetails entity4 = new EligibilityDetails();
		entity4.setEligibiltyId(4);
		entity4.setName("VIN");
		entity4.setMobileNo(98908098l);
		entity4.setGender('M');
		entity4.setEmail("vi@gmail.com");
		entity4.setSrNo(104);
		entity4.setSsn(2023278l);
		entity4.setPlanName("PETROL");
		entity4.setPlanStatus("Open");
		eligibilityDetailsRepo.save(entity4);
	}

}
