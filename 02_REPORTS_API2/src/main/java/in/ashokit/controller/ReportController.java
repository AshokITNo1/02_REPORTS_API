package in.ashokit.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;
import in.ashokit.serviceI.ReportServiceI;

@RestController
public class ReportController {

	@Autowired
	private ReportServiceI reportServiceI;

	@GetMapping(value = "/plan")
	public ResponseEntity<List<String>> getPlanNames() {
		List<String> planNames = reportServiceI.getUniquePlanName();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}

	@GetMapping(value = "/statuses")
	public ResponseEntity<List<String>> getPlanStatuses() {
		List<String> planStatuses = reportServiceI.getUniquePlanStatuses();
		return new ResponseEntity<>(planStatuses, HttpStatus.OK);
	}

	@PostMapping(value = "/search")
	public ResponseEntity<List<SearchResponse>> search(@RequestBody SearchRequest request) {
		List<SearchResponse> response = reportServiceI.search(request);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/excel")
	public void downloadExcel(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=report.xls";

		response.setHeader(headerKey, headerValue);

		reportServiceI.generateExcel(response);

	}

	@GetMapping(value = "/pdf")
	public void downloadPdf(HttpServletResponse response) throws Exception {

		response.setContentType("application/pdf");

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=report.pdf";

		response.setHeader(headerKey, headerValue);

		reportServiceI.generatePdf(response);

	}

}
