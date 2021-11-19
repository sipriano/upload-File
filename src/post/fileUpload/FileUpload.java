package post.fileUpload;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.io.InputStream;

public class FileUpload {

  

    public static void main(String[] args) throws Exception {

     
    	String url = "";
    	String charset = "UTF-8";
    	String JSON = "{\"historico\":{\"observacao\":\"teste20\"},\"contato\":{\"email\":\"teste123@gmail.com\",\"telefone\":1199999999,\"celular\":11888888888},\"notaFiscal\":{\"numeroNota\":\"222222222222222\",\"dataEmissao\":\"19/11/2021\",\"valor\":20,\"tipoDocumentoFiscal\":1},\"carta\":{\"tipo\":null},\"idContaCorrenteSolicitacao\":null,\"idControleContaCorrente\":\"3751\",\"idContaCorrenteContaBancaria\":3,\"codigoEmpresa\":\"K0NbhuTmLP1QA8klrArHTQ==\",\"createdBy\":\"/59kRffNP1FGk9WmBBoFaA==\",\"idContaCorrenteCnpj\":386}";
    	String token = "";
    	File textFile = new File("C:\\Users\\rodrigo.pereira\\Downloads\\nota_devolucao.pdf");
    	String boundary = Long.toHexString(System.currentTimeMillis());
    	String CRLF = "\r\n"; 

    	HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    	connection.setDoOutput(true);
    	connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    	connection.setRequestProperty("Authorization", "Bearer " + token);
    	connection.setRequestProperty("Content-Type", "application/\"application/x-www-form-urlencoded;charset=" + charset);
    	connection.setRequestMethod("POST");

    	try (
    	    OutputStream output = connection.getOutputStream();
    		//InputStream file = new ByteArrayInputStream(myEntity.getBinaryFile());
    	    PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
    	) {

    	    writer.append("--" + boundary).append(CRLF);
    	    writer.append("Content-Disposition: form-data; name=\"notaFiscalFile\"; filename=\"nota_devolucao.pdf\"").append(CRLF);
    	    writer.append("Content-Type: application/pdf").append(CRLF);
    	    writer.append("Content-Transfer-Encoding: binary").append(CRLF);
    	    writer.append(CRLF).flush();
    	    Files.copy(textFile.toPath(), output);
    	    output.flush();
    	    writer.append(CRLF).flush();

    	    writer.append("--" + boundary).append(CRLF);
    	    writer.append("Content-Disposition: form-data; name=\"simplesNacionalFile\"; filename=\"nota_devolucao.pdf\"").append(CRLF);
    	    writer.append("Content-Type: application/pdf").append(CRLF);
    	    writer.append("Content-Transfer-Encoding: binary").append(CRLF);
    	    writer.append(CRLF).flush();
    	    Files.copy(textFile.toPath(), output);
    	    output.flush();
    	    writer.append(CRLF).flush();
    	    
    	    
    	    writer.append("--" + boundary).append(CRLF);
    	    writer.append("Content-Disposition: form-data; name=\"solicitacaoPagamento\"; filename=\"blob\"").append(CRLF);
    	    writer.append("Content-Type: application/json; charset=" + charset).append(CRLF);
    	    writer.append(CRLF).append(JSON).append(CRLF).flush();
    	    
    	    //System.out.println(writer.flush);
    	    
    	    writer.append("--" + boundary + "--").append(CRLF).flush();
    	}

    	
    	System.out.println(((HttpURLConnection) connection).getResponseCode());
    	
    	
    	
    }
    
}
