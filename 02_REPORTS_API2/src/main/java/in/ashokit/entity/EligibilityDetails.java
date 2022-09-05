package in.ashokit.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import lombok.Data;

@Entity
@Table(name = "ELIGIBILITY_DETAILS")
@Data
public class EligibilityDetails {

	@Id
	@Column(name = "ELIG_ID")
	private Integer eligibiltyId;

	@Column(name = "SR_NO")
	private Integer srNo;

	@Column(name = "NAME")
	private String name;

	@Column(name = "MOBILE_NO")
	private Long mobileNo;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "GENDER")
	private Character gender;

	@Column(name = "SSN")
	private Long ssn;

	@Column(name = "PLAN_NAME")
	private String planName;

	@Column(name = "PLAN_STATUS")
	private String planStatus;

	@Column(name = "PLAN_START_DATE")
	private LocalDate planStartDate;

	@Column(name = "PLAN_END_DATE")
	private LocalDate planEndDate;

	@Column(name = "CREATED_DATE")
	@CreationTimestamp
	private LocalDate createDate;

	@Column(name = "UPDATED_DATE")
	private LocalDate updateDate;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

}
