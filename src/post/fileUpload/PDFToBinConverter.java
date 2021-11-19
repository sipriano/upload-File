package post.fileUpload;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;

public class PDFToBinConverter {

	
	public byte[] convertPDFToByte() throws IOException {
		

		Path pdfPath = Paths.get("C:\\Users\\rodrigo.pereira\\Desktop\\nota_devolucao.pdf");
		byte[] pdf = Files.readAllBytes(pdfPath);
		return pdf;
	
	}
	
}
