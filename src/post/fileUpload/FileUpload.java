package post.fileUpload;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileUpload {

  

    public static void main(String[] args) throws Exception {

     
    	String url = "";
    	String charset = "utf-8";
    	String JSON = "{\"historico\":{\"observacao\":\"teste20\"},\"contato\":{\"email\":\"teste123@gmail.com\",\"telefone\":1199999999,\"celular\":11888888888},\"notaFiscal\":{\"numeroNota\":\"222222222222222\",\"dataEmissao\":\"19/11/2021\",\"valor\":20,\"tipoDocumentoFiscal\":1},\"carta\":{\"tipo\":null},\"idContaCorrenteSolicitacao\":null,\"idControleContaCorrente\":\"3751\",\"idContaCorrenteContaBancaria\":3,\"codigoEmpresa\":\"K0NbhuTmLP1QA8klrArHTQ==\",\"createdBy\":\"/59kRffNP1FGk9WmBBoFaA==\",\"idContaCorrenteCnpj\":386}";
    	String token = "";
    	File textFile = new File("C:\\Users\\rodrigo.pereira\\Downloads\\nota_devolucao.pdf");
    	//String boundary = Long.toHexString(System.currentTimeMillis());
    	String boundary = "------WebKitFormBoundarysFxKywaeacWIFEsm";
    	String CRLF = "\r\n"; 
    	
    	HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    	connection.setDoOutput(true);
    	connection.setDoInput(true);
    	connection.setRequestProperty("Accept", "*/*");
    	connection.setRequestProperty("Accept-Encoding", "gzip, deflate, br");
    	connection.setRequestProperty("Authorization", "Bearer " + token);
    	connection.setRequestProperty("Connection", "keep-Alive");
    	connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
    	connection.setRequestProperty("Host", "novosiaahmg.cruzeirodosul.edu.br");
    	connection.setRequestProperty("Origin", "https://novosiaahmg.cruzeirodosul.edu.br");
    	connection.setRequestProperty("Referer", "https://novosiaahmg.cruzeirodosul.edu.br/");
    	connection.setRequestProperty("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"");
    	connection.setRequestProperty("sec-ch-ua-mobile", "?0");
    	connection.setRequestProperty("sec-ch-ua-platform", "Windows");
    	connection.setRequestProperty("Sec-Fetch-Dest", "empty");
    	connection.setRequestProperty("Sec-Fetch-Mode", "cors");
    	connection.setRequestProperty("Sec-Fetch-Site", "same-site");
    	connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36");
    	connection.setRequestMethod("POST");
    	
    	try (
    	    OutputStream output = connection.getOutputStream();
    		InputStream file = new FileInputStream(textFile);
    		InputStream file2 = new FileInputStream(textFile);
    	    PrintWriter writer = new PrintWriter(new OutputStreamWriter(output, charset), true);
    	) {

    	    writer.append(boundary).append(CRLF);
    	    writer.append("Content-Disposition: form-data; name=\"notaFiscalFile\"; filename=\"nota_devolucao.pdf\"").append(CRLF);
    	    writer.append("Content-Type: application/pdf").append(CRLF);
    	    //writer.append("Content-Transfer-Encoding: binary").append(CRLF);
    	    writer.append(CRLF).flush();
    	    //Files.copy(textFile.toPath(), output);
    	    
    	    int byteRead;
    	    
    	    while ((byteRead = file.read()) != -1) {
    	    	//System.out.println(byteRead); 
    	    	output.write(byteRead);
    	    	    	    	
    	    }
    	    
    	    output.flush();
    	    
    	    writer.append(CRLF).flush();
    	    
    	    file.close();

    	    writer.append(boundary).append(CRLF);
    	    writer.append("Content-Disposition: form-data; name=\"simplesNacionalFile\"; filename=\"nota_devolucao.pdf\"").append(CRLF);
    	    writer.append("Content-Type: application/pdf").append(CRLF);
    	    //writer.append("Content-Transfer-Encoding: binary").append(CRLF);
    	    writer.append(CRLF).flush();
    	    //Files.copy(textFile.toPath(), output);
    	    
    	    //connection.
    	    while ((byteRead = file2.read()) != -1) {
    	    	//System.out.println(byteRead); 
    	    	output.write(byteRead);
    	    	    	    
    	    }
    	    
    	    output.flush();
    	    
    	    writer.append(CRLF).flush();
    	    
    	    file2.close();
    	    
    	    writer.append(boundary).append(CRLF);
    	    writer.append("Content-Disposition: form-data; name=\"solicitacaoPagamento\"; filename=\"blob\"").append(CRLF);
    	    writer.append("Content-Type: application/json; charset=" + charset).append(CRLF);
    	    writer.append(CRLF).append(JSON).append(CRLF).flush();
    	    
    	    //System.out.println(writer.flush);
    	    
    	    writer.append(boundary + "--").append(CRLF).flush();
    	}
    	
    	BufferedWriter writer = new BufferedWriter(new FileWriter("request.txt"));
    	writer.write(((HttpURLConnection) connection).getOutputStream().toString());
    	writer.close();
    	
    	
    	System.out.println(((HttpURLConnection) connection).getRequestMethod());
    	System.out.println(((HttpURLConnection) connection).getResponseCode());
    	System.out.println(((HttpURLConnection) connection).getResponseMessage());
    	System.out.println(((HttpURLConnection) connection).getURL());
    	
    	
    	
        
        
        
    	
    	  /*BufferedReader in=new BufferedReader(new InputStreamReader(connection.getInputStream()));
          String inputLine;
          StringBuffer response=new StringBuffer();

          while((inputLine=in.readLine()) !=null)
          {
              response.append(inputLine);
          }
          in.close();
          System.out.println(response.toString());
    	System.out.println(((HttpURLConnection) connection).getResponseCode());*/

    	
    	
    }
    
}
