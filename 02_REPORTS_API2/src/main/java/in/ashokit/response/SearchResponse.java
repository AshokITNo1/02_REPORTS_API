package in.ashokit.response;

import lombok.Data;

@Data
public class SearchResponse {
	
	private Integer srNo;
	private String name;
	private String email;
	private Long mobileNo;
	private Character gender;
	private Long ssn;

}
