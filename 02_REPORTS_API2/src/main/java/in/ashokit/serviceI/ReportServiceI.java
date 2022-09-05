package in.ashokit.serviceI;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;

public interface ReportServiceI {

	public List<String> getUniquePlanName();

	public List<String> getUniquePlanStatuses();

	public List<SearchResponse> search(SearchRequest request);

	public void generateExcel(HttpServletResponse response) throws Exception;

	public void generatePdf(HttpServletResponse response) throws Exception;

}
