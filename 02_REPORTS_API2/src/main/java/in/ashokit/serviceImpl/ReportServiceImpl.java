package in.ashokit.serviceImpl;

import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.entity.EligibilityDetails;
import in.ashokit.repository.EligibilityDetailsRepo;
import in.ashokit.request.SearchRequest;
import in.ashokit.response.SearchResponse;
import in.ashokit.serviceI.ReportServiceI;

@Service
public class ReportServiceImpl implements ReportServiceI {

	@Autowired
	private EligibilityDetailsRepo eligibilityDetailsRepo;

	@Override
	public List<String> getUniquePlanName() {
		return eligibilityDetailsRepo.getPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatuses() {
		return eligibilityDetailsRepo.getPlanStatuses();

	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {

		List<SearchResponse> response = new ArrayList<>();

		// THIS IS queryBuider TO CREATE THE QUERY DYNAMICALLY WE ARE USING (WE CAN USE
		// criteriaBuilder AS WELL)
		EligibilityDetails queryBuider = new EligibilityDetails();

		String planName = request.getPlanName();
		if (planName != null && !planName.equals("")) {
			queryBuider.setPlanName(planName);
		}
		String planStatus = request.getPlanStatus();
		if (planStatus != null && !planStatus.equals("")) {
			queryBuider.setPlanStatus(planStatus);
		}
		LocalDate planStartDate = request.getPlanStartDate();
		if (planStartDate != null) {
			queryBuider.setPlanStartDate(planStartDate);
		}

		LocalDate planEndDate = request.getPlanEndDate();
		if (planEndDate != null) {
			queryBuider.setPlanEndDate(planEndDate);
		}

		Example<EligibilityDetails> example = Example.of(queryBuider);

		List<EligibilityDetails> entities = eligibilityDetailsRepo.findAll(example);

		for (EligibilityDetails entity : entities) {

			SearchResponse searchResponse = new SearchResponse();

			BeanUtils.copyProperties(entity, searchResponse);

			response.add(searchResponse);
		}
		return response;
	}

	@Override
	public void generateExcel(HttpServletResponse response) throws Exception {

		List<EligibilityDetails> entities = eligibilityDetailsRepo.findAll();

		// creating an instance of HSSFWorkbook class

		HSSFWorkbook workbook = new HSSFWorkbook();

		// invoking creatSheet() method and passing the name of the sheet to be created
		HSSFSheet sheet = workbook.createSheet();

		// creating the 0th row using the createRow() method
		HSSFRow rowhead = sheet.createRow(0);

		// creating cell by using the createCell() method and setting the values to the
		// cell by using the setCellValue() method
		rowhead.createCell(0).setCellValue("Sr.No.");
		rowhead.createCell(1).setCellValue("Name");
		rowhead.createCell(2).setCellValue("Email");
		rowhead.createCell(3).setCellValue("Mobile");
		rowhead.createCell(4).setCellValue("Gender");
		rowhead.createCell(5).setCellValue("SSN");

		int i = 1;

		for (EligibilityDetails entity : entities) {
			// creating the 1st row
			HSSFRow row = sheet.createRow(i);
			// inserting data in the first row
			row.createCell(0).setCellValue(String.valueOf(entity.getSrNo()));
			row.createCell(1).setCellValue(entity.getName());
			row.createCell(2).setCellValue(entity.getEmail());
			row.createCell(3).setCellValue(String.valueOf(entity.getMobileNo()));
			row.createCell(4).setCellValue(String.valueOf(entity.getGender()));
			row.createCell(5).setCellValue(String.valueOf(entity.getSsn()));

			i++;

		}
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	@Override
	public void generatePdf(HttpServletResponse response) throws Exception {

		List<EligibilityDetails> entities = eligibilityDetailsRepo.findAll();

		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		// TO WRITE NAME "Search Report" IN THE REPORT WE HAVE WROTE THESE 6 LINES
		// CODE(ONLY PARAGRAPH WILL BE CREATED )
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(12);
		font.setColor(Color.BLACK);

		Paragraph p = new Paragraph("Search Report", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		// DATA WILL BE ADDDED IN THE FORM OF TABLE
		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 1.5f, 1.0f, 1.0f, 1.5f, 1.5f });
		table.setSpacingBefore(4);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.ORANGE);
		cell.setPadding(2);

		Font font2 = FontFactory.getFont(FontFactory.HELVETICA);
		font2.setColor(Color.YELLOW);

		cell.setPhrase(new Phrase("Sr.No", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("NAME", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("EMAIL", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("MOBILE", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("GENDER", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("SSN", font));
		table.addCell(cell);

		for (EligibilityDetails entity : entities) {
			table.addCell(String.valueOf(entity.getSrNo()));
			table.addCell(entity.getName());
			table.addCell(entity.getEmail());
			table.addCell(String.valueOf(entity.getMobileNo()));
			table.addCell(String.valueOf(entity.getGender()));
			table.addCell(String.valueOf(entity.getSsn()));
		}

		document.add(table);
		document.close();

	}

}
